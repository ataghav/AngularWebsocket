import { Component, Input, OnDestroy } from '@angular/core';
import { InterCommService } from '../inter-comm.service';
import { Subscription } from 'rxjs';

export interface Player {
  name: string;
  isReady: boolean;
}

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnDestroy {
  @Input() newplayer: string;
  message = '';
  subscription: Subscription;


  constructor(private interCommService: InterCommService) {
    this.subscription = interCommService.playerJoined$.subscribe(
      message => {
        this.message = message;
        // console.log('I am player and I have Got your message!!!!');
        // console.log(message);
        var parsedMessage = JSON.parse(message);
        // console.log(parsedMessage);
        let joinedPlayer: Player = {
          name: parsedMessage.user,
          isReady: false
        };
        this.players.push(joinedPlayer);
      });

    this.subscription = interCommService.playerLeft$.subscribe(
      message => {
        this.message = message;
        var parsedMessage = JSON.parse(message);
        let that = this;
        this.players.forEach(function (player, index) {
          if (player.name == parsedMessage.user) {
            that.players.splice(index, 1);
          }
        });
      });

    this.subscription = interCommService.playerReady$.subscribe(
      message => {
        this.message = message;
        var parsedMessage = JSON.parse(message);
        let that = this;
        this.players.forEach(function (player, index) {
          if (player.name == parsedMessage.user) {
            player.isReady = true;
          }
        });
      });
  }

  players: Player[] = [];

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }
}

import { Component, OnInit } from '@angular/core';

export interface Player {
  name: string;
  isReady: boolean;
}

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent {

  players: Player[] = [
    {name: 'Ali', isReady: false},
    {name: 'Akbar', isReady: true},
    {name: 'Asghar', isReady: false},
    {name: 'Kambiz', isReady: true}
  ];

}

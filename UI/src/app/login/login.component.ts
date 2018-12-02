import { Component, Input, OnDestroy } from '@angular/core';
import { InterCommService } from '../inter-comm.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy {
  subscription: Subscription;

  playerName: string;

  constructor(private interCommService: InterCommService) {
    this.subscription = interCommService.questionSubmitted$.subscribe();
  }

  doLogin() { }

  doSignup() { }

  playerLogin() {
    this.interCommService.handleInternalPlayerLogin(this.playerName);
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

}

import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Player {
  name: string;
  status: boolean;
}

export interface Option {
  text: string;
  isCorrect: boolean;
}

export interface Question {
  text: string;
  options: Option[];
}


@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver) {}

  players: Player[] = [
    {name: 'Ali', status: false},
    {name: 'Akbar', status: true},
    {name: 'Asghar', status: false},
    {name: 'Kambiz', status: true}
  ];

  myQuestion: Question = {
    text: '123',
    options: [
      {text: 'he', isCorrect: false},
      {text: 'she', isCorrect: false}
    ]
  };

  addNewOption() {
    
  }
}




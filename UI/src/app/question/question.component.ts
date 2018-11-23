import { Component, OnInit } from '@angular/core';

export interface CreatedOption {
  text: string;
  index: number;
  isCorrect: boolean;
}

export interface CreatedQuestion {
  text: string;
  options: CreatedOption[];
}

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {
  myQuestion: CreatedQuestion = {
    text: '',
    options: [{
      text: '',
      index: 1,
      isCorrect: false
    }]
  };

  selectionChanged($event) {}

  submitQuestion() {
    // console.log('the selected value is:' + this.selectedOption);
  }
}

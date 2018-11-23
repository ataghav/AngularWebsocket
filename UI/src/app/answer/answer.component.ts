import { Component, OnInit } from '@angular/core';

export interface ReceivedOption {
  text: string;
  index: number;
}

export interface ReceivedQuestion {
  text: string;
  options: ReceivedOption[];
}

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})

export class AnswerComponent {
  selectedOption: number;

  // TODO: load question dynamically
  justReceivedQuestion: ReceivedQuestion = {
    text: 'Is this a sample questoin text?',
    options: [
      { text: 'option1', index: 1},
      { text: 'option2', index: 2},
      { text: 'option3', index: 3},
    ]
  };

  selectionChanged(event: any) {
    this.selectedOption = event.target.value;
  }

  submitAnswer() {
    console.log('the selected value is:' + this.selectedOption);
  }
}

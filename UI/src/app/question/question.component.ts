import { Component, OnInit } from '@angular/core';

export interface DraftOption {
  text: string;
  index: number;
  isCorrect: boolean;
}

export interface DraftQuestion {
  text: string;
  options: DraftOption[];
}

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {
  optionsCounter: number = 1;

  myQuestion: DraftQuestion = {
    text: '',
    options: [{
      text: '',
      index: 1,
      isCorrect: false
    }]
  };

  addNewOption() {
    this.optionsCounter++;
    let newOption: DraftOption = {
      text: '',
      index: this.optionsCounter,
      isCorrect: false
    };
    this.myQuestion.options.push(newOption);
  }

  removeOption(event: any) {
    this.optionsCounter--;
    // TODO: remove selected option
    // TODO: reindex remaining options
  }



  selectionChanged($event) {}

  submitQuestion() {
    // console.log('the selected value is:' + this.selectedOption);
  }
}

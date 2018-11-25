import { Component, Input, OnDestroy } from '@angular/core';
import { InterCommService } from '../inter-comm.service';
import { Subscription } from 'rxjs';

// export interface DraftOption {
//   text: string;
//   index: number;
//   isCorrect: boolean;
// }

export interface DraftQuestion {
  text: string;
  options: string[];
  answerIndex: string;
}

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})

export class QuestionComponent implements OnDestroy {
  subscription: Subscription;

  constructor(private interCommService: InterCommService) { }

  myQuestion: DraftQuestion = {
    text: 'my text',
    options: ['opt1', 'opt2'],
    answerIndex: '1'
  };

  addNewOption() {
    // this.optionsCounter++;
    // let newOption: DraftOption = {
    //   text: '',
    //   index: this.optionsCounter,
    //   isCorrect: false
    // };
    this.myQuestion.options.push('');
  }

  removeOption(event: any) {
    // this.optionsCounter--;
    // TODO: remove selected option
    // TODO: reindex remaining options
  }

  selectionChanged($event) {
    console.log('SELECTION HAS CHANGED ');
    this.myQuestion.answerIndex = (<HTMLInputElement>event.target).value;

  }

  submitQuestion() {
    console.log('[INFO] submitQuestion is hited. myQuestion is: ' + JSON.stringify(this.myQuestion));
    this.interCommService.handleQuestionSubmited(JSON.stringify(this.myQuestion));
  }

  trackByFn(index: any, item: any) {
    return index;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }
}

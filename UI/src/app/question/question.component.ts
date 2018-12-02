import { Component, Input, OnDestroy } from '@angular/core';
import { InterCommService } from '../inter-comm.service';
import { Subscription } from 'rxjs';

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

  constructor(private interCommService: InterCommService) {
    this.subscription = interCommService.playerSelected$.subscribe(
      message => {
        const parsedMessage = JSON.parse(message);
        // console.log(parsedMessage.options);
        // this.myQuestion.text = 'TADA!!!';
      });
  }

  myQuestion: DraftQuestion = {
    text: '',
    options: [''],
    answerIndex: null
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

  removeOption($event) {
    // this.optionsCounter--;
    console.log((<HTMLSpanElement>event.target).id);
    this.myQuestion.options.splice(Number((<HTMLSpanElement>event.target).id), 1);
    // TODO: remove selected option
    // TODO: reindex remaining options
  }

  selectionChanged($event) {
    console.log('SELECTION HAS CHANGED ');
    this.myQuestion.answerIndex = (<HTMLInputElement>event.target).value;

  }

  clearForm() {
    this.myQuestion.text = '';
    this.myQuestion.options = [''];
    this.myQuestion.answerIndex = null;
  }

  submitQuestion() {
    console.log('[INFO] submitQuestion is hited. myQuestion is: ' + JSON.stringify(this.myQuestion));
    this.interCommService.handleInternalSubmitQuestion(JSON.stringify(this.myQuestion));
  }

  trackByFn(index: any, item: any) {
    return index;
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }
}

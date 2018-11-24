import { Component, Input, OnDestroy } from '@angular/core';
import { InterCommService } from '../inter-comm.service';
import { Subscription } from 'rxjs';

// export interface ReceivedOption {
//   text: string;
//   index: number;
// }

export interface ReceivedQuestion {
  text: string;
  options: string[];
}

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})

export class AnswerComponent implements OnDestroy {
  @Input() newplayer: string;
  selectedOption: number;
  subscription: Subscription;

  justReceivedQuestion: ReceivedQuestion;

  constructor(private interCommService: InterCommService) {
    this.subscription = interCommService.questionSubmited$.subscribe(
      message => {
        // this.message = message;
        // console.log('I am player and I have Got your message!!!!');
        // console.log(message);
        var parsedMessage = JSON.parse(message);
        console.log(parsedMessage.options);
        this.justReceivedQuestion.text = parsedMessage.question;
        this.justReceivedQuestion.options = parsedMessage.options;
      });
  }

  // TODO: load question dynamically
  justReceivedQuestion = {
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

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }
}

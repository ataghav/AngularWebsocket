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
  @Input() newQuestion: string;
  selectedOption: number;
  subscription: Subscription;

  justReceivedQuestion: ReceivedQuestion = {
    text: 'test question',
    options: ['test answer1', 'test answer2']
  };

  constructor(private interCommService: InterCommService) {
    this.subscription = interCommService.questionSubmitted$.subscribe(
      message => {
        const parsedMessage = JSON.parse(message);
        console.log(parsedMessage.options);
        this.justReceivedQuestion.text = parsedMessage.text;
        this.justReceivedQuestion.options = parsedMessage.options;
      });
  }

  selectionChanged(event: any) {
    this.selectedOption = event.target.value;
  }

  submitAnswer() {
    console.log('the selected value is:' + this.selectedOption);

    this.interCommService.handleAnswerSubmitted(String(this.selectedOption));
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }
}

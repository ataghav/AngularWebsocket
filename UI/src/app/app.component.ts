import { Component } from '@angular/core';
import { InterCommService } from './inter-comm.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

export interface Question {
  type: string;
  user: string;
  text: string;
  options: string[];
  answerIndex: number;
  createdAt: Date;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'game';
  localPlayer: string;
  isJoined: boolean = false;

  history: string[] = [];

  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string;
  disabled: boolean;

  constructor(private interCommService: InterCommService) {
    interCommService.answerSubmitted$.subscribe(
      answer => this.sayAnswerSubmitted(answer)
    );

    interCommService.internalSubmitQuestion$.subscribe(
      question => {
        // console.log('[INFO] submitQuestion is hited in: ' + this);
        this.sayQuestionSubmitted(JSON.parse(question));
      }
    );
  }

  connect() {

    // connect to stomp where stomp endpoint is exposed
    // let ws = new SockJS(http://localhost:8080/greeting);
    const socket = new WebSocket('ws://localhost:8080/greeting');
    this.ws = Stomp.over(socket);
    const that = this;
    this.ws.connect(
      {},
      function (frame) {
        that.ws.subscribe('/errors', function (message) {
          alert('Error ' + message.body);
        });
        that.ws.subscribe('/topic/reply', function (message) {
          const parsedMessage = JSON.parse(message.body);
          console.log('[RECEIVED] at ' + new Date());
          console.log(message);
          console.log('-=- '.repeat(15));
          const messageType = parsedMessage.type;
          let user = parsedMessage.user;
          // console.log(messageType);
          switch (messageType) {
            case 'PLAYER_JOINED':
              that.interCommService.handlePlayerJoined(message.body);
              break;
            case 'PLAYER_LEFT':
              that.interCommService.handlePlayerLeft(message.body);
              break;
            case 'PLAYER_READY':
              that.interCommService.handlePlayerReady(message.body);
              break;
            case 'PLAYER_SELECTED':
              // console.log('PLAYER_SELECTED has been received');
              if (user == that.localPlayer) {
                // TODO: go to question view. else wait.
                that.interCommService.handlePlayerSelected(message.body);
              }

              break;
            case 'QUESTION_SUBMITTED':
              // if (user = that.localPlayer) {
              //   console.log('QUESTION IS MINE');
              //   break;
              // }
              // console.log('QUESTION IS NOT MINE');
              that.interCommService.handleQuestionSubmitted(message.body);
              break;
            // case 'ANSWER_SUBMITTED':
            //   that.interCommService.handleAnswerSubmitted(message.body);
            //   break;
            case 'SCORE_ADDED':
              that.interCommService.handleScoreAdded(message.body);
              break;
          }
          // that.showGreeting(message.body);
        });
        that.disabled = true;
      },
      function (error) {
        alert('STOMP error ' + error);
      }
    );
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.ws.close();
    }
    this.setConnected(false);
    console.log('Disconnected');
  }

  sendName() {
    const data = JSON.stringify({
      name: this.name
    });

    // var message = JSON.stringify({
    //   type: 'PLAYER_SELECTED',
    //   user: 'user',
    //   createdAt: new Date()
    // });

    // var message = JSON.stringify({
    //   type: 'SCORE_ADDED',
    //   addedScores: [{ user: 'user', score: '10' }],
    //   createdAt: new Date()
    // });

    this.ws.send('/app/message', {}, data);
    console.log('[SENT] at ' + new Date());
    console.log(data);
    console.log('-=- '.repeat(15));
  }


  sayPlayerJoined(user: string) {
    if (this.isJoined) {
      return;
    }
    const message = JSON.stringify({
      type: 'PLAYER_JOINED',
      // user: 'user' + Math.floor(Math.random()*10) + 1,
      user: this.localPlayer,
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
    console.log('[SENT] at ' + new Date());
    console.log(message);
    console.log('-=- '.repeat(15));
  }

  sayPlayerLeft(user: string) {
    this.isJoined = false;
    const message = JSON.stringify({
      type: 'PLAYER_LEFT',
      user: this.localPlayer,
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
    console.log('[SENT] at ' + new Date());
    console.log(message);
    console.log('-=- '.repeat(15));
  }

  sayPlayerReady() {
    const message = JSON.stringify({
      type: 'PLAYER_READY',
      user: this.localPlayer,
      createdAt: new Date()
    });
    // console.log('I will say ' + message);
    this.ws.send('/app/message', {}, message);
    console.log('[SENT] at ' + new Date());
    console.log(message);
    console.log('-=- '.repeat(15));
  }

  sayQuestionSubmitted(question: Question) {
    // console.log('[INFO] sayQuestionSubmitted is hited' + this);
    const message = JSON.stringify({
      type: 'QUESTION_SUBMITTED',
      user: this.localPlayer,
      text: question.text,
      options: question.options,
      answerIndex: question.answerIndex,
      createdAt: new Date()
    });
    // console.log('THIS IS THE FINAL FORM OF QUESTION TO SEND' + message);
    this.ws.send('/app/message', {}, message);
    console.log('[SENT] at ' + new Date());
    console.log(message);
    console.log('-=- '.repeat(15));
  }

  sayAnswerSubmitted(answer: string) {
    const message = JSON.stringify({
      type: 'ANSWER_SUBMITTED',
      user: this.localPlayer,
      answerIndex: answer,
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
    console.log('[SENT] at ' + new Date());
    console.log(message);
    console.log('-=- '.repeat(15));
  }

  showGreeting(message) {
    this.showConversation = true;
    this.greetings.push(message);
  }

  setConnected(connected) {
    this.disabled = connected;
    this.showConversation = connected;
    this.greetings = [];
  }
}

import { Component } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string;
  disabled: boolean;

  constructor() {}

  connect() {
    // connect to stomp where stomp endpoint is exposed
    // let ws = new SockJS(http://localhost:8080/greeting);
    const socket = new WebSocket('ws://localhost:8080/greeting');
    this.ws = Stomp.over(socket);
    const that = this;
    this.ws.connect(
      {},
      function(frame) {
        that.ws.subscribe('/errors', function(message) {
          alert('Error ' + message.body);
        });
        that.ws.subscribe('/topic/reply', function(message) {
          console.log(message);
          that.showGreeting(message.body);
        });
        that.disabled = true;
      },
      function(error) {
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
    //
    // var message = JSON.stringify({
    //   type: 'SCORE_ADDED',
    //   addedScores: [{ user: 'user', score: '10' }],
    //   createdAt: new Date()
    // });

    this.ws.send('/app/message', {}, data);
  }

  sayPlayerJoined() {
    const message = JSON.stringify({
      type: 'PLAYER_JOINED',
      user: 'user',
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
  }

  sayPlayerLeft() {
    const message = JSON.stringify({
      type: 'PLAYER_LEFT',
      user: 'user',
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
  }

  sayPlayerReady() {
    const message = JSON.stringify({
      type: 'PLAYER_READY',
      user: 'user',
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
  }

  sayQuestionSubmited() {
    const message = JSON.stringify({
      type: 'QUESTION_SUBMITED',
      user: 'user',
      question: 'question',
      options: ['opt1', 'opt2', 'opt3'],
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
  }

  sayAnswerSubmited() {
    const message = JSON.stringify({
      type: 'ANSWER_SUBMITED',
      user: 'user',
      answerIndex: 1,
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
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

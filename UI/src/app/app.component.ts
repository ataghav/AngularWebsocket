import { Component } from '@angular/core';
import { InterCommService } from './inter-comm.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'game';
  localPlayer: string = 'TODO';

  history: string[] = [];

  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string;
  disabled: boolean;

  constructor(private interCommService: InterCommService) {
    interCommService.answerSubmited$.subscribe(
      answer => this.sayAnswerSubmited(answer)
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
      function(frame) {
        that.ws.subscribe('/errors', function(message) {
          alert('Error ' + message.body);
        });
        that.ws.subscribe('/topic/reply', function(message) {
          var parsedMessage = JSON.parse(message.body);
          // console.log('NEW_MESSAGE_ARRIVED!!!');
          // console.log(message);
          let messageType = parsedMessage.type;
          console.log(messageType);
          switch(messageType){
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
              that.interCommService.handlePlayerSelected(message.body);
              break;
            case 'QUESTION_SUBMITED':
              that.interCommService.handleQuestionSubmited(message.body);
              break;
            // case 'ANSWER_SUBMITED':
            //   that.interCommService.handleAnswerSubmited(message.body);
            //   break;
            case 'SCORE_ADDED':
              that.interCommService.handleScoreAdded(message.body);
              break;
          }
          // that.showGreeting(message.body);
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
    
    // var message = JSON.stringify({
    //   type: 'SCORE_ADDED',
    //   addedScores: [{ user: 'user', score: '10' }],
    //   createdAt: new Date()
    // });

    this.ws.send('/app/message', {}, data);
  }


  sayPlayerJoined(user: string) {
    const message = JSON.stringify({
      type: 'PLAYER_JOINED',
      user: 'user',
      createdAt: new Date()
    });
    this.ws.send('/app/message', {}, message);
  }

  sayPlayerLeft(user: string) {
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
    console.log('I will say '+message);
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

  sayAnswerSubmited(answer: string) {
    const message = JSON.stringify({
      type: 'ANSWER_SUBMITED',
      user: this.localPlayer,
      answerIndex: answer,
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

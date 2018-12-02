import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterCommService {

  // Observable string sources
  private playerJoinedSource = new Subject<string>();
  private playerLeftSource = new Subject<string>();
  private playerReadySource = new Subject<string>();
  private playerSelectedSource = new Subject<string>();
  private questionSubmittedSource = new Subject<string>();
  private answerSubmittedSource = new Subject<string>();
  private scoreAddedSource = new Subject<string>();

  private internalPlayerLoginSource = new Subject<string>();
  private internalSubmitQuestionSource = new Subject<string>();

  // Observable string streams
  playerJoined$ = this.playerJoinedSource.asObservable();
  playerLeft$ = this.playerLeftSource.asObservable();
  playerReady$ = this.playerReadySource.asObservable();
  playerSelected$ = this.playerSelectedSource.asObservable();
  questionSubmitted$ = this.questionSubmittedSource.asObservable();
  answerSubmitted$ = this.answerSubmittedSource.asObservable();
  scoreAdded$ = this.scoreAddedSource.asObservable();

  internalPlayerLogin$ = this.internalPlayerLoginSource.asObservable();
  internalSubmitQuestion$ = this.internalSubmitQuestionSource.asObservable();

  // Service message commands
  // announceMission(mission: string) {
  //   this.missionAnnouncedSource.next(mission);
  // }

  // confirmMission(astronaut: string) {
  //   this.missionConfirmedSource.next(astronaut);
  // }

  handlePlayerJoined(message: string) {
    this.playerJoinedSource.next(message);
  }
  handlePlayerLeft(message: string) {
    this.playerLeftSource.next(message);
  }
  handlePlayerReady(message: string) {
    this.playerReadySource.next(message);
  }
  handlePlayerSelected(message: string) {
    this.playerSelectedSource.next(message);
  }
  handleQuestionSubmitted(message: string) {
    this.questionSubmittedSource.next(message);
  }
  handleAnswerSubmitted(message: string) {
    this.answerSubmittedSource.next(message);
  }
  handleScoreAdded(message: string) {
    this.scoreAddedSource.next(message);
  }

  handleInternalPlayerLogin(message: string) {
    this.internalPlayerLoginSource.next(message);
  }
  handleInternalSubmitQuestion(message: string) {
    this.internalSubmitQuestionSource.next(message);
  }
}

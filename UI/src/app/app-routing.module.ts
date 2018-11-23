import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AnswerComponent } from './answer/answer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { QuestionComponent } from './question/question.component';


const routes: Routes = [
  {path: 'answer', component: AnswerComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'question', component: QuestionComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

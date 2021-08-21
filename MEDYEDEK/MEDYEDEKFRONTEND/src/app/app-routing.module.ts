import { ErrordialogComponent } from './errordialog/errordialog.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { profile } from 'console';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { PostsComponent } from './posts/posts.component';
import { ConfirmaccountComponent } from './confirmaccount/confirmaccount.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { ConfirmationResetComponent } from './confirmation-reset/confirmation-reset.component';


const routes: Routes = [
  {
    path: 'home',
    canActivate: [AuthGuardService],
    component: HomeComponent,
    children: [
      {
        path: 'userprofile', component: MyprofileComponent
      },
      {
        path: 'posts/all', component: PostsComponent,
      }
    ]
  },
 
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'somethingswrong', component: ErrordialogComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: '', component: HomeComponent },
  { path: 'confirmed', component: ConfirmaccountComponent },
  { path:'resetPassword' , component:PasswordResetComponent},
  { path:'reset-password-confirm' , component:ConfirmationResetComponent} 


  // { path: 'userprofile', outlet: 'in-actions', component: MyprofileComponent }



]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
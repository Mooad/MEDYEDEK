import { ErrordialogComponent } from './errordialog/errordialog.component';
import { InscriptionComponent } from './registration/registration.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PostsComponent } from './posts/posts.component';
import { ConfirmaccountComponent } from './confirmaccount/confirmaccount.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { ConfirmationResetComponent } from './confirmation-reset/confirmation-reset.component';
import {PostCommentsComponent} from "./post-comments/post-comments.component";


const routes: Routes = [
  {
    path: 'home',
    canActivate: [AuthGuardService],
    component: HomeComponent,
    children: [
      {
        path: 'userprofile', loadChildren : () => (import(`./myprofile/myprofile.module`).then(p => p.MyprofileModule))
      },
      {
        path: 'posts/all', component: PostsComponent,
      },
    ]
  },
  /* {
    path: 'home',
    component: MyprofileComponent,
    children: [
      {
        path: 'userprofile/infos',
        component: ProfileInformationsComponent,
      },
      {
        path: 'userprofile/security',
        component: ProfileSecuritySettingsComponent,
      },
      {
        path: 'userprofile/preferences',
        component: ProfileGeneralSettingsComponent,
      }
    ],
    outlet: "profile"
  }, */
  { path: 'login', component: LoginComponent },
  { path: 'home', component: LoginComponent },
  { path: 'somethingswrong', component: ErrordialogComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: '', component: HomeComponent },
  { path: 'confirmed', component: ConfirmaccountComponent },
  { path:'resetPassword' , component:PasswordResetComponent},
  { path:'reset-password-confirm' , component:ConfirmationResetComponent},
  { path:'**' , component:HomeComponent},
  { path:'*' , component:HomeComponent}


  // { path: 'userprofile', outlet: 'in-actions', component: MyprofileComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

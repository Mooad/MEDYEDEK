import { ErrordialogComponent } from './errordialog/errordialog.component';
import { ErrorDialogService } from './services/errordialog.service';
import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER , NgModule } from '@angular/core';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JsonAppConfigService } from './config/json-app-config.service';
import { AppConfig } from './config/appConfig';
import { SearchLeftComponent } from './search-left/search-left.component';
import { PostsComponent } from './posts/posts.component';
import { PostComponent } from './post/post.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ScrollingModule} from '@angular/cdk/scrolling';
import { NavbarComponent } from './navbar/navbar.component';
import { AuthGuardService } from './auth-guard.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SomethingwrongComponent } from './somethingwrong/somethingwrong.component';
import { InscriptionComponent } from './registration/registration.component';
import { AuthInterceptor } from './interceptor/AuthInterceptor';
import { LoginnavComponent } from './loginnav/loginnav.component';
import { InscriptionstepsComponent } from './inscriptionsteps/inscriptionsteps.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { SpinnerOverlayComponent } from './spinner-overlay/spinner-overlay.component';
import { NewpostComponent } from './newpost/newpost.component';
import { ConfirmaccountComponent } from './confirmaccount/confirmaccount.component';
import { PasswordResetComponent } from './password-reset/password-reset.component';
import { ConfirmationResetComponent } from './confirmation-reset/confirmation-reset.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { GetIntouchComponent } from './get-intouch/get-intouch.component';
import { UsefulLinksComponent } from './useful-links/useful-links.component';
import { FooterComponent } from './footer/footer.component';
import { ConfirmResetPassDialogComponent } from './confirm-reset-pass-dialog/confirm-reset-pass-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogService } from './services/DialogService';
import { RouterModule } from '@angular/router';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { SharedProfile } from './services/SharedProfile';
import { PostInteractionComponent } from './post-interaction/post-interaction.component';
import { PostCommentsComponent } from './post-comments/post-comments.component';
import {StoreModule} from "@ngrx/store";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../environments/environment";
import {postreducers} from "./store/state/posts/Postreducers";
import {EffectsModule} from "@ngrx/effects";
import {PostEffects} from "./store/state/posts/Posteffects";
import {CommonModule} from "@angular/common";
import {contentreducers} from "./store/state/post/content/ContentReducers";
import {ContentEffects} from "./store/state/post/content/ContentEffects";
import {CommentEffects} from "./store/state/post/comments/CommentEffects";
import { CommentsSectionComponent } from './comments-section/comments-section.component';
import {commentreducers} from "./store/state/post/comments/CommentReducers";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {Utils} from "./services/utils/UtilMethods";
import { CommentActionConfirmationComponent } from './comment-action-confirmation/comment-action-confirmation.component';



export function initializerFn(jsonAppConfigService: JsonAppConfigService) {
  return () => {
    return jsonAppConfigService.load();
  };
}

@NgModule({
  declarations: [
    AppComponent,
    SearchLeftComponent,
    ErrordialogComponent,
    PostsComponent,
    PostComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    SomethingwrongComponent,
    InscriptionComponent,
    LoginnavComponent,
    InscriptionstepsComponent,
    SpinnerComponent,
    SpinnerOverlayComponent,
    NewpostComponent,
    ConfirmaccountComponent,
    PasswordResetComponent,
    ConfirmationResetComponent,
    AboutUsComponent,
    GetIntouchComponent,
    UsefulLinksComponent,
    FooterComponent,
    ConfirmResetPassDialogComponent ,
    MyprofileComponent,
    PostInteractionComponent,
    PostCommentsComponent,
    CommentsSectionComponent,
    CommentActionConfirmationComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ScrollingModule,
    MatDialogModule,
    RouterModule,
    EffectsModule.forRoot(),
    CommonModule,
    StoreModule.forRoot({reducers: postreducers},),
    StoreModule.forRoot({reducers: contentreducers},),
    StoreModule.forRoot({reducers: commentreducers},),

    //{
    //runtimeChecks: {
    //strictStateImmutability: false,
    // strictActionImmutability: false,
    // },}
    StoreModule.forFeature('posts', postreducers),
    StoreModule.forFeature('content', contentreducers),
    StoreModule.forFeature('comment', commentreducers),

    EffectsModule.forFeature([PostEffects, ContentEffects, CommentEffects]),

    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production,
      autoPause: true,
      features: {
        pause: false,
        lock: true,
        persist: true
      }
    }),
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatExpansionModule,
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA],
  providers: [{
    provide: AppConfig,
    deps: [HttpClient],
    useExisting: JsonAppConfigService,
  },
   AuthGuardService,
   ErrorDialogService,
   DialogService,
   SharedProfile,
    Utils,
   { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  {
    provide: APP_INITIALIZER,
    multi: true,
    deps: [JsonAppConfigService],
    useFactory: initializerFn
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

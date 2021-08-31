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
import { InscriptionComponent } from './inscription/inscription.component';
import { AuthInterceptor } from './interceptor/AuthInterceptor';
import { LoginnavComponent } from './loginnav/loginnav.component';
import { InscriptionstepsComponent } from './inscriptionsteps/inscriptionsteps.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { SpinnerOverlayComponent } from './spinner-overlay/spinner-overlay.component';
import { NewpostComponent } from './newpost/newpost.component';
import { MyprofileComponent } from './myprofile/myprofile.component';
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
    MyprofileComponent,
    ConfirmaccountComponent,
    PasswordResetComponent,
    ConfirmationResetComponent,
    AboutUsComponent,
    GetIntouchComponent,
    UsefulLinksComponent,
    FooterComponent,
    ConfirmResetPassDialogComponent  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ScrollingModule,
    MatDialogModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA],
    entryComponents: [ErrordialogComponent,ConfirmResetPassDialogComponent],
  providers: [{
    provide: AppConfig,
    deps: [HttpClient],
    useExisting: JsonAppConfigService,
  },
   AuthGuardService,
   ErrorDialogService,
   DialogService,
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

import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AppConfig } from '../config/appConfig';
import { TokenStorageService } from '../bearer/TokenStorageService';
import { map } from "rxjs/operators";
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginproxyService {

  authenticated = false;
  token: string ;
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;


  public get currentUserValue(): any {
      return this.currentUserSubject.value;
  }
  constructor(private http: HttpClient, private router: Router, private appConfig: AppConfig,private tokenStorageService :TokenStorageService) {

    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(sessionStorage.getItem('thCurUsr')));
    this.currentUser = this.currentUserSubject.asObservable();
   }


  login(username: string, password: string,kms: string)  : any{

    // Provide username and password for authentication, and once authentication is successful, 
//store JWT token in session   

return this.http
.post<any>(this.appConfig.authUrl, { username, password ,kms})
.pipe(map(user => {
  // store user details and jwt token in local storage to keep user logged in between page refreshes
  sessionStorage.setItem('thCurUsr', user['token']);
  this.currentUserSubject.next(user);
  console.log(user);
  return user;
}));
  }


  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password);
  }

  registerSuccessfulLogin(username, password) {
    localStorage.setItem('thCurUsr', JSON.stringify(username + ":" + password));
  }

  
  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("thCurUsr")
  }

}
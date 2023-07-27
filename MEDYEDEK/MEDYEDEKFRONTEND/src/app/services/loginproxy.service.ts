import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {AppConfig} from '../config/appConfig';
import {TokenStorageService} from '../bearer/TokenStorageService';
import {map} from "rxjs/operators";
import {BehaviorSubject, Observable} from 'rxjs';

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

    this.currentUserSubject = new BehaviorSubject<any>(JSON.stringify(sessionStorage.getItem('thCurUsr')));
    this.currentUser = this.currentUserSubject.asObservable();
   }


  login(username: string, password: string,kms: string)  : any{

    // Provide username and password for auth
    // entication, and once authentication is successful,
//store JWT token in session
    const headers = this.createBasicAuthToken(username, password);
    const options = { headers: headers };

return this.http
.post<any>(this.appConfig.authUrl, { username, password ,kms},options)
.pipe(map(user => {
  // store user details and jwt token in local storage to keep user logged in between page refreshes
  sessionStorage.setItem('thCurUsr', user['token']);
  this.currentUserSubject.next(user);
  console.log(user);
  return user;
}));
  }


  createBasicAuthToken(username: string, password: string) {
    const token = btoa(`${username}:${password}`);
    return new HttpHeaders().set('Authorization', `Basic ${token}`);  }

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

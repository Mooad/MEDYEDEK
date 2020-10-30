import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../config/appConfig';

@Injectable({
  providedIn: 'root'
})
export class LoginproxyService {

  authenticated = false;

  constructor(private http: HttpClient, private router: Router, private appConfig: AppConfig) { }


  public login(username: string, password: string): any {
    return this.http.get(this.appConfig.authUrl,
      // tslint:disable-next-line: max-line-length
      {
        headers: {
          authorization: this.createBasicAuthToken(username, password),
          'Access-Control-Expose-Headers': '*'
        },
        responseType: 'text' as 'json'
      })
      .subscribe((next) => {
        console.log(next);
        this.registerSuccessfulLogin(username, password);
        this.router.navigate(['/home']);
      },
        (error) => {
          if (error.error === 401) {
            return 'NOACCESS';
          }

        }

      );
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password);
  }

  registerSuccessfulLogin(username, password) {
    localStorage.setItem('user', JSON.stringify(username + ":" + password));
  }
}

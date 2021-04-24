import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppConfig } from 'src/app/config/appConfig';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient, private router: Router, private appConfig: AppConfig) { }

  addUser(user: JSON,):any{
    return this.http.post(this.appConfig.baseUrl + 'addUser', user)
      .subscribe(() =>
        (error: any) => console.error(error)); {
          
        }
  }
}

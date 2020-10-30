import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppConfig } from 'src/app/config/appConfig';
import { error } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient, private router: Router, private appConfig: AppConfig) { }

  addUser(user: JSON,):any{
    return this.http.post(this.appConfig.baseUrl + 'nouveauUtilisateur', user)
      .subscribe(() =>
        (error: any) => console.error(error)); {
          
        }
  }
}

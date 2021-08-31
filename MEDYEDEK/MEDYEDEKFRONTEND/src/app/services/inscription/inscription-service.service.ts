import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppConfig } from 'src/app/config/appConfig';
import { DialogService } from '../DialogService';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient, private router: Router, private appConfig: AppConfig,private dialogService:DialogService) { }

  addUser(user: JSON,):any{
    return this.http.post(this.appConfig.baseUrl + 'addUser', user)
      .subscribe(() =>
      (data) =>{
            this.dialogService.confirmationDialog("Your Account was created please confirm your subscription by checking your inbox");   
      },
        (error: HttpErrorResponse) => {
          if(error.error.message ==="MEDERR010")
          {        this.dialogService.confirmationDialog("The Account that was entered already exist");        
          }
          console.error(error);;
        })
        

  }
}

import { CanActivate, ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ConnexionState } from './entities/ConnexionState';
import { AppConfig } from './config/appConfig';


@Injectable() 
export class AuthGuardService implements CanActivate {

connexionState:ConnexionState;

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    : Observable<boolean> | Promise<boolean> | boolean {
    
    if (sessionStorage.getItem('thCurUsr') && sessionStorage.getItem('thCurUsr').length>0) {
      let headers = new HttpHeaders();
     this.createAuthorizationHeader(headers); 
      this.http.get<ConnexionState>(this.appConfig.syncUrl,{headers: {'Authorization': "Bearer "+ sessionStorage.getItem('thCurUsr')}}).subscribe(
        (res) => {this.connexionState=res;
          if(res.status==='401')
          {
            this.router.navigate(['/login']);
            return false;
          }
        }
        );

        return true;
    }

   // return false;
  }
   createAuthorizationHeader(headers:HttpHeaders) {

    headers.append('Authorization', 'Bearer '+sessionStorage.getItem('thCurUsr')); 
    return headers
  }

  constructor(private router: Router,private http:HttpClient,private appConfig:AppConfig) {


  }
}
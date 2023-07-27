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

      const headers = this.createBasicAuthToken(sessionStorage.getItem('thCurUsr'));
      const options = { headers: headers };

      this.http.get<ConnexionState>(this.appConfig.syncUrl,options).subscribe(
        (res) => {this.connexionState = res;
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
  createBasicAuthToken(token: string) {
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);  }


  constructor(private router: Router,private http:HttpClient,private appConfig:AppConfig) {


  }
}

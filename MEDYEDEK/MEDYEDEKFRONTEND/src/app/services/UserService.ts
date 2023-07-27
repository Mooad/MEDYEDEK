import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../config/appConfig';
import { Utilisateur } from '../entities/Post';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class UserService {
    public href: string = "";


    ngOnInit() {
        this.href = this.router.url;
        console.log(this.router.url);
    }
    constructor(private http: HttpClient, private appConfig: AppConfig,private router: Router) { }


    public getUserByToken(): Observable<any> {
        return this.http.get<Utilisateur[]>(this.appConfig.baseUrl + "user/token");
    }
}

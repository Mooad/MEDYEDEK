import { Post, PostDto } from './../entities/Post';
import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../config/appConfig';
import { AjaxResponse } from '../entities/AjaxResponse';
import { UserResetDto } from '../entities/UserResetDto';

@Injectable({
    providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class ChangePassword {

    constructor(private http: HttpClient, private appConfig: AppConfig) { }


    public changeUserPassword(email:String): Observable<UserResetDto[]> {
               return this.http.post<UserResetDto[]>(this.appConfig.changePasswordUrl,{"userEmail":email});
    }

    /*     uploadPhotoProduct(file: File, idProduct): Observable<HttpEvent<{}>> {
            const formdata: FormData = new FormData();
            formdata.append('file', file);
           // const req = new HttpRequest('POST', this.host + '/uploadPhoto/' + idProduct, formdata, {
                reportProgress: true,
                responseType: 'text'
            });
    
            return this.http.request(req);
        }*/

}


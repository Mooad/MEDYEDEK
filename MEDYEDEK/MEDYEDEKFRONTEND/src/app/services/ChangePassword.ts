import { Post, PostDto } from './../entities/Post';
import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../config/appConfig';
import { SimpleUserDto, PasswordDto } from '../entities/UserResetDto';

@Injectable({
    providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class ChangePassword {

    constructor(private http: HttpClient, private appConfig: AppConfig) { }


    public changeUserPassword(email:String): Observable<SimpleUserDto[]> {
               return this.http.post<SimpleUserDto[]>(this.appConfig.changePasswordUrl,{"userEmail":email});
    }

    public confirmNewPassword(passwordDto : PasswordDto): Observable<PasswordDto[]> {
        return this.http.post<PasswordDto[]>(this.appConfig.confirmPassword,passwordDto);
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


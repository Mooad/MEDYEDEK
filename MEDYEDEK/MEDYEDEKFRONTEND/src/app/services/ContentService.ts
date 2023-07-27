import {Content} from './../entities/Post';
import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../config/appConfig';

@Injectable({
  providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class ContentService {

  constructor(private http: HttpClient, private appConfig: AppConfig) { }


  public getPostContent(post_id:any): Observable<Content[]> {
    return this.http.post<Content[]>(this.appConfig.baseUrl + "content/postContent",{post_id});
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


//

/*
    const  post = new Post('Hey', 'Hello Man this is the first Post',1 , new Date);
    const  post1  = new Post('Hey', 'Hello Man this is the Second Post', 1 , new Date);
    const  post2 = new Post('Hey', 'Hello Man this is the Third Post', 1 , new Date);
    const  post3 = new Post('Hey', 'Hello Man this is the Last Post', 1 , new Date);

    this.Posts.push(post);
    this.Posts.push(post1);
    this.Posts.push(post2);
    this.Posts.push(post3); */

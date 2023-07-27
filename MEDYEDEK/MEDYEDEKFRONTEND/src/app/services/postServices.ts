import { PostDto } from './../entities/Post';
import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../config/appConfig';
import {PostInteractionDto} from "../entities/UserResetDto";

@Injectable({
    providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class PostService {

    constructor(private http: HttpClient, private appConfig: AppConfig) { }


    public getAllPosts(lastPost:String): Observable<PostDto[]> {
        return this.http.post<PostDto[]>(this.appConfig.baseUrl + "posts/all",{"lastPost":''}).pipe();
    }

  public interactWithPost(postInteractionDto:PostInteractionDto): Observable<PostInteractionDto> {
    return this.http.post<PostInteractionDto>(this.appConfig.baseUrl + "postInteraction/interact",postInteractionDto);
  }

  public currentUserLikePost(postInteractionDto:PostInteractionDto): Observable<PostInteractionDto> {
    return this.http.post<PostInteractionDto>(this.appConfig.baseUrl + "postInteraction/currentUserLikePost",postInteractionDto);
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

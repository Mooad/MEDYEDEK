import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config/appConfig';
import { entitiesAsConsts } from '../constants/entitiesAsConsts';


@Injectable({
  providedIn: 'root'
})

export class TypePostService {
  private url: String;
  constructor(private http: HttpClient, private appConfig: AppConfig) { this.url = "typeposts";}

  public getTypePosts() {
    return this.http.get("http://localhost:8093/typeposts");
  }
}

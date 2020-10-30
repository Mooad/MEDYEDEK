import { Component, OnInit, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config/appConfig';

@Injectable({
  providedIn: 'root'
})
export class InitialSearchServiceService implements OnInit {

  constructor(  private http: HttpClient,private appConfig: AppConfig) { }

  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }



}

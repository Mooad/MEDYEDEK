import { Injectable } from '@angular/core';

@Injectable()
export abstract class AppConfig {

    baseUrl: string;
    title: string;
     authUrl: string;
}
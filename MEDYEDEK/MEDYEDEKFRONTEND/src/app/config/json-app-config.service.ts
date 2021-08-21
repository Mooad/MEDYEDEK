import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { AppConfig } from './appConfig';


@Injectable({
  providedIn: 'root'
})
export class JsonAppConfigService extends AppConfig {

  constructor(private http: HttpClient ) {
    super();
  }

  // This function needs to return a promise
  load() {
    return this.http.get<AppConfig>('app.config.json')
      .toPromise()
      .then(data => {
        this.title = data.title;
        this.baseUrl = data.baseUrl;
        this.authUrl = data.authUrl;
        this.syncUrl = data.syncUrl;
        this.changePasswordUrl = data.changePasswordUrl;
      })
      .catch(() => {
        console.error('Could not load configuration');
      });
  }
}
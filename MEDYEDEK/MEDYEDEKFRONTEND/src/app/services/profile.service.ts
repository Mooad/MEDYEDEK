import { Injectable } from '@angular/core';
import { ProfileDto } from '../entities/UserResetDto';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config/appConfig';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient,private appConfig: AppConfig) { }

  public getUserProfile(email:String): Observable<ProfileDto> {
    //we set the user email that we got in the beggining
    if(email)
        {
        return this.http.post<ProfileDto>(this.appConfig.baseUrl + "user/profile",{"userEmail":email});
        }
    }

    public syncUserProfile(profileDto:ProfileDto): Observable<ProfileDto> {
      //we set the user email that we got in the beggining
      if(profileDto)
          {
          return this.http.post<ProfileDto>(this.appConfig.baseUrl + "user/synchProfile",profileDto);
          }
      }
}

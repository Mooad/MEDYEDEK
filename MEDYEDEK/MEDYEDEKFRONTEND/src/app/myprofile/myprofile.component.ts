import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileDto } from '../entities/UserResetDto';
import { AppConfig } from '../config/appConfig';
import { ProfileService } from '../services/profile.service';
import { SharedProfile } from '../services/SharedProfile';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.scss']
})
export class MyprofileComponent implements OnInit {

  @Input() profileDto:ProfileDto;
  respProfile;
  constructor(private route:Router,private appconfig:AppConfig,private  profileService:ProfileService,private sharedProfile:SharedProfile) { 
    this.route.navigateByUrl('/home/userprofile/infos'); // navigate to the informations page in the begining
  }

  ngOnInit(): void {

 this.respProfile= this.profileService.getUserProfile(localStorage.getItem("userEmail"))
    .subscribe(
        (res) => { 
          
          this.profileDto = res; 
          if(this.profileDto)
          {
            localStorage['currentProfile']= JSON.stringify(this.profileDto);
          }

        }
    );

    
    console.log(this.profileDto);


    this.route.navigateByUrl('/home/userprofile/infos'); // navigate to the informations page in the begining
  }

}
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.scss']
})
export class MyprofileComponent implements OnInit {

  constructor(private route:Router) { 
    this.route.navigateByUrl('/home/userprofile/infos'); // navigate to the informations page in the begining
  }

  ngOnInit(): void {
    this.route.navigateByUrl('/home/userprofile/infos'); // navigate to the informations page in the begining
  }

}
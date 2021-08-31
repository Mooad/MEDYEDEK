import { Component, Input, OnInit, ElementRef } from '@angular/core';
import { postService } from '../services/postServices';
import { AppConfig } from '../config/appConfig';
import { TypePostService } from '../services/type-post-service';
import { Router } from '@angular/router';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @Input() title: string;

  @Input() Url: string;


  constructor(private appConfig: AppConfig, postservice: postService,
    private typePostService: TypePostService,
    private elementRef: ElementRef,
    private router: Router,
    private http:HttpClient) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('thCurUsr'))
    {
    this.router.navigateByUrl("/home/posts/all")
    }
    else
    {
      this.router.navigate(['/login']);
    }
  }
  //var headers = new HttpHeaders()

  //if(sessionStorage.getItem('token'))
  //{
  //headers.append('Content-Type','application/json');
  //headers.append('Authorization',`Bearer ${sessionStorage.getItem('token')}`);

   //this.http.get("http://localhost:8080/syncAuth", { headers: headers }).subscribe(
    //result=>  {},
    //error => {
     // sessionStorage.clear();
      //this.router.navigate(['/login'])
    //});
//}
}

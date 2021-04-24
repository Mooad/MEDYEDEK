import { Component, Input, OnInit, ElementRef } from '@angular/core';
import { postService } from '../services/postServices';
import { AppConfig } from '../config/appConfig';
import { TypePostService } from '../services/type-post-service';
import { Router } from '@angular/router';

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
    private router: Router) { }

  ngOnInit(): void {
    this.router.navigateByUrl("home/posts/all");
  }

}

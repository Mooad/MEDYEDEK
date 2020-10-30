import { postService } from './services/postServices';
import { Component, Input, OnInit, ElementRef } from '@angular/core';
import { AppConfig } from './config/appConfig';
import { TypePostService } from './services/type-post-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})


export class AppComponent implements OnInit {
  @Input() title: string;

  @Input() Url: string;



  constructor() { }
  ngOnInit(): void {
  }
  // tslint:disable-next-line: use-lifecycle-interface
  ngAfterViewInit() {
    //this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = "rgb(141, 141, 141)";
  }
}

import { Component, OnInit, Input } from '@angular/core';
import { postService } from '../services/postServices';
import { AppConfig } from '../config/appConfig';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input() textContent: string;

  constructor(private postService: postService, private appConfig: AppConfig) { }

  ngOnInit(): void {


  }

}

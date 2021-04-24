import { Component, OnInit, Input } from '@angular/core';
import { postService } from '../services/postServices';
import { AppConfig } from '../config/appConfig';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Content, PostDto } from '../entities/Post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input() post : PostDto;
  @Input() textContent: string;
  @Input() imageUrl : SafeUrl;
  @Input() selectedImage : String;
  slider_img = document.querySelector('.slider-img');
  i = 1;
  constructor(private postService: postService, private appConfig: AppConfig , private sanitizer:DomSanitizer) { }

  ngOnInit(): void {
  this.getImageFromService();

  if( this.post.postContent.length>0)
  {  this.selectedImage = 'data:image/png;base64,'  + this.post.postContent[0].content;
  
  }
}
  getImageFromService() {
      this.post.beneficiaire.image =   'data:image/png;base64,' + this.post.beneficiaire.image ;
      
}

 prev(){
	if(this.i <= 0) this.i  = this.post.postContent.length;	
	this.i --;
	return this.setImg();			 
}

 next(){
	if(this.i >= this.post.postContent.length-1) this.i = -1;
	this.i++;
	return this.setImg();			 
}

   setImg(){
	return  this.selectedImage = 'data:image/png;base64,'  + this.post.postContent[this.i].content;
	
}
}

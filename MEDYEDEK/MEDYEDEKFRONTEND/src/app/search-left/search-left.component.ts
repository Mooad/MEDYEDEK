import { Router } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { AppConfig } from '../config/appConfig';
import { TypePostService } from '../services/type-post-service';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-search-left',
  templateUrl: './search-left.component.html',
  styleUrls: ['./search-left.component.scss']
})
export class SearchLeftComponent implements OnInit {

  @Input() Typeposts: any;


  constructor(private appConfig: AppConfig, private typePostService: TypePostService,private router :Router) { }
  ngOnInit(): void {

    
  }

  loadScript() {
    let node = document.createElement('script'); // creates the script tag
    node.src = ''; // sets the source (insert url in between quotes)
    node.type = 'text/javascript'; // set the script type
    node.async = true; // makes script run asynchronously
    node.charset = 'utf-8'    // append to head of document
    document.getElementsByTagName('head')[0].appendChild(node); 
  }

  private getTypePosts() {

    this.typePostService.getTypePosts()
      .subscribe(data => { this.Typeposts = data; });
}
SignOut(): void {
  sessionStorage.clear();
  this.router.navigate(['/login']);
}
GotoProfile()
{
return 'home/userprofile';
}
}

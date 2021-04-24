import {  PostDto } from './../entities/Post';
import { postService } from './../services/postServices';
import { Component, OnInit, ɵConsole, Input } from '@angular/core';
import { AppConfig } from '../config/appConfig';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  public Url;
  @Input() Posts: PostDto[];
  //public dataSource: FactsDataSource;


  constructor(private postService: postService, private appConfig: AppConfig) {

    //this.dataSource = new FactsDataSource(postService);

  }
 
  ngOnInit(): void {
    this.Url = this.appConfig.baseUrl;
   this.getPosts();
   console.log(this.Posts);
  }

  private getPosts() {

    this.postService.getAllPosts()
    .subscribe(
      (res) => {this.Posts=res;}
    );

  }
  /* 
  export class FactsDataSource extends DataSource<Post | undefined> {
    private cachedFacts = Array.from<Post>({ length: 0 });
    private dataStream = new BehaviorSubject<(Post | undefined)[]>(this.cachedFacts);
    private subscription = new Subscription();
  
    private pageSize = 10;
    private lastPage = 0;
  
    constructor(private postservice: postService) {
      super();
  
      // Start with some data.
      this._fetchFactPage();
    }
  
    connect(collectionViewer: CollectionViewer): Observable<(Post | undefined)[] | ReadonlyArray<Post | undefined>> {
  
      this.subscription.add(collectionViewer.viewChange.subscribe(range => {
  
        const currentPage = this._getPageForIndex(10);
  
        if (currentPage && range) {
          console.log(currentPage, this.lastPage);
  
  
        }
  
        if (currentPage > this.lastPage) {
          this.lastPage = currentPage;
          this._fetchFactPage();
        }
      }));
  
      return this.dataStream;
    }
  
  
  
    disconnect(collectionViewer: CollectionViewer): void {
      this.subscription.unsubscribe();
    }
  
    private _fetchFactPage(): void {
      for (let i = 0; i < this.pageSize; ++i) {
        this.postservice.getAllPosts().subscribe(res => {
          this.cachedFacts = this.cachedFacts.concat(res);
          this.dataStream.next(this.cachedFacts);
        });
      }
    }
  
    private _getPageForIndex(i: number): number {
      return Math.floor(i / this.pageSize);
    }
  
  }
  
   */
}
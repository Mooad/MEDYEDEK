import { PostDto } from './../entities/Post';
import { PostService } from './../services/postServices';
import { Component, OnInit } from '@angular/core';
import { AppConfig } from '../config/appConfig';
import {select, Store} from "@ngrx/store";
import * as PostActions from "../store/state/posts/PostActions";
import {Observable} from "rxjs";
import {isLoading, posts} from "../store/state/posts/Postselectors";
import {ProfileService} from "../services/profile.service";
import {ProfileDto} from "../entities/UserResetDto";

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  public Url;
     $posts: Observable<PostDto[]>;
  //public dataSource: FactsDataSource;
    isLoading$ : Observable<boolean>;
  profile: ProfileDto
  constructor(private postService: PostService,  private appConfig: AppConfig,private store :Store, private profileService:ProfileService) {
    this.isLoading$ = this.store.pipe(select(isLoading));
    this.$posts = this.store.pipe(select(posts));

    //this.dataSource = n ew FactsDataSource(postService);
    this.$posts.subscribe(value =>
      console.log(value));


  }



  ngOnInit(): void {
    this.store.dispatch(PostActions.getPosts());
    this.Url = this.appConfig.baseUrl;
    this.profileService.getUserProfile(localStorage.getItem("userEmail")).subscribe(value => this.profile = value);

  }

  protected readonly isLoading = isLoading;
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

import {PostDto} from './../entities/Post';
import {PostService} from './../services/postServices';
import {Component, OnInit} from '@angular/core';
import {AppConfig} from '../config/appConfig';
import {select, Store} from "@ngrx/store";
import * as PostActions from "../store/state/posts/PostActions";
import {Observable} from "rxjs";
import {isLoading, posts} from "../store/state/posts/Postselectors";
import {ProfileService} from "../services/profile.service";
import {ProfileDto} from "../entities/UserResetDto";
import {AddingPostModalComponent} from "../adding-post-modal/adding-post-modal.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  public Url;
  $posts: Observable<PostDto[]>;
  isLoading$: Observable<boolean>;
  profile: ProfileDto

  constructor(private postService: PostService, private appConfig: AppConfig, private store: Store, private profileService: ProfileService, private dialog: MatDialog) {
    this.isLoading$ = this.store.pipe(select(isLoading));
    this.$posts = this.store.pipe(select(posts));

    this.$posts.subscribe(value =>
      console.log(value));
  }


  ngOnInit(): void {
    this.store.dispatch(PostActions.getPosts());
    this.Url = this.appConfig.baseUrl;
    this.profileService.getUserProfile(localStorage.getItem("userEmail")).subscribe(value => this.profile = value);
  }

  openModal(): void {
    const dialogRef = this.dialog.open(AddingPostModalComponent, {
      width: '40%', // Adjust the width as needed
      // You can pass data to the modal using the 'data' property
      data: {
        // Any data you want to pass to the modal
      },
    });
  }

  protected readonly isLoading = isLoading;
}


import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as PostActions from './PostActions'
import {catchError, map, mergeMap, of} from "rxjs";
import {PostService} from "../../../services/postServices";
import {Injectable} from "@angular/core";
import {ContentService} from "../../../services/ContentService";
import {getPostContent} from "./PostActions";


@Injectable()
export class PostEffects {


  constructor(private actions$: Actions, private postService: PostService,private contentService : ContentService) {
  }

  getPosts$ = createEffect(() =>
    this.actions$.pipe(
      ofType(PostActions.getPosts),
      mergeMap(() => {
        return this.postService
          .getAllPosts("")
          .pipe(map(
            (posts) =>
              PostActions.getPostsSuccess({posts : posts})
          ), catchError(err => {
            return of(PostActions.getPostsFailure({error: err.message}));
          }));

      })
    )
  );



}

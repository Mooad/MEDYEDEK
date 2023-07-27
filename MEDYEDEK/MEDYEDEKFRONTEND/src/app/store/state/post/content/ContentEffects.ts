import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as ContentActions from './ContentActions'
import {catchError, map, mergeMap, of} from "rxjs";
import {Injectable} from "@angular/core";
import {PostService} from "../../../../services/postServices";
import {ContentService} from "../../../../services/ContentService";


@Injectable()
export class ContentEffects {


  constructor(private actions$: Actions, private postService: PostService,private contentService : ContentService) {
  }

  getPostContent$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ContentActions.selectContents),
      mergeMap((action) =>
        this.contentService.getPostContent(action.postId).pipe(
          map((contents) => ContentActions.getContentsSuccess({ contents })),
          catchError((error) =>
            of(ContentActions.getContentsFailure({ error: error.message }))
          )
      )
      )
  ))

}

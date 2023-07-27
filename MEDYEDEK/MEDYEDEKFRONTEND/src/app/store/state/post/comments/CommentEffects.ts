import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as CommentActions from './CommentActions'
import {catchError, map, mergeMap, of} from "rxjs";
import {Injectable} from "@angular/core";
import {PostService} from "../../../../services/postServices";
import {CommentService} from "../../../../services/commentService";
import {select, Store} from "@ngrx/store";


@Injectable()
export class CommentEffects {


  constructor(private actions$: Actions, private postService: PostService,private commentService : CommentService,private store: Store) {
  }

  getPostComment$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CommentActions.selectComments),
      mergeMap((action) =>
        this.commentService.getCommentsGrappe(action.identifier).pipe(
          map((comments) => CommentActions.getCommentSuccess({ comments })),
          catchError((error) =>
            of(CommentActions.getCommentFailure({ error: error.message }))
          )
        )
      )
    ));



addCommentLevel0$ = createEffect(() =>
  this.actions$.pipe(
    ofType(CommentActions.addCommentLevel0),
    mergeMap(({ comment }) =>
      this.commentService.insertCommentlevel0(comment).pipe(
        map((comment) => CommentActions.addCommentLevel0Success({ comment })),
        catchError((error) => of(CommentActions.addCommentLevel0Failure({ error: error.message })))
      )
    )
  )
);

}



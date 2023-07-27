import {createAction, props} from "@ngrx/store";
import {Comment, CommentDto} from "../../../../entities/Post";

export const getComments = createAction('[Comment] Get Comment')

export const selectComments = createAction(
  '[Comment] Select Commments',
  props<{ identifier: string }>()
);



export const getCommentSuccess = createAction('[Comment] Get Comments success', props<{comments : CommentDto[] }>());
export const getCommentFailure = createAction('[Comment] Get Comments failure', props<{error: string }>());

export const addCommentLevel0 = createAction(
  '[Comment] Add Comment',
  props<{ comment: Comment }>()
);
export const addCommentLevel0Success = createAction('[Comment] Get Comments success', props<{comment : Comment }>());
export const addCommentLevel0Failure = createAction('[Comment] Get Comments failure', props<{error: string }>());

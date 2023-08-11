import {createAction, props} from "@ngrx/store";
import {Comment, CommentDto} from "../../../../entities/Post";

export const getComments = createAction('[Comment] Get Comment')

export const selectComments = createAction(
  '[Comment] Select Commments',
  props<{ identifier: string }>()
)



export const getCommentSuccess = createAction('[Comment] Get Comments success', props<{comments : CommentDto[] }>());
export const getCommentFailure = createAction('[Comment] Get Comments failure', props<{error: string }>());

export const addCommentLevel0 = createAction(
  '[Comment] Add Comment Level 0',
  props<{ comment: Comment }>()
);
export const addCommentLevel0Success = createAction('[Comment] Add Comment Level 0 success', props<{commentsDTO : any }>());
export const addCommentLevel0Failure = createAction('[Comment] Add Comment Level 0 failure', props<{error: string }>());


export const addCommentLevelx = createAction(
  '[Comment] Add Comment level X',
  props<{parent_id:string,  reply: Comment , comments : Comment[] }>()
);
export const addCommentLevelxSuccess = createAction('[Comment] Add Comment Level X success', props<{reply : any, comments:Comment[] }>());
export const addCommentLevelxFailure = createAction('[Comment] Add Comment Level X failure', props<{error: string }>());


export const deleteCommentLevel0 = createAction(
  '[Comment] Delete Comment Level 0',
  props<{ comment: Comment }>()
);
export const deleteCommentLevel0Success = createAction('[Comment] Delete Comment Level 0 success', props<{comment : any }>());
export const deleteCommentLevel0Failure = createAction('[Comment] Delete Comment Level 0 failure', props<{error: string }>());

import {createAction, props} from "@ngrx/store";
import {Content} from "../../../../entities/Post";

export const getContents = createAction('[Content] Get Contents')

export const selectContents = createAction(
  '[Content] Select Contents',
  props<{ postId: number }>()
);

export const getContentsSuccess = createAction('[Content] Get Contents success', props<{contents : Content[] }>());
export const getContentsFailure = createAction('[Content] Get Contents failure', props<{error: string }>());

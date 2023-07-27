import {createAction, props} from "@ngrx/store";
import {Content, PostDto} from "../../../entities/Post";

export const getPosts = createAction('[Posts] Get Posts')
export const getPostsSuccess = createAction('[Posts] Get Posts success', props<{posts : PostDto[] }>());
export const getPostsFailure = createAction('[Posts] Get Posts failure', props<{error: string }>());
export const getPostContent = createAction('[Posts] Get Posts Content', props<{id_post : number }>());

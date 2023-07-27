import {createReducer, on} from "@ngrx/store";
import * as Actions from './PostActions'
import {AppStateInterface} from "../AppState.interface";

export const initialState: AppStateInterface = {
  isLoading: false,
  postsState: {posts : [], isLoading : true,error:null},
  contentsState : [],
  commentsState : [],
  error: null,
}

export const postreducers = createReducer(initialState,

  on(Actions.getPosts, (state) => ({...state, isLoading: true})),
  on(Actions.getPostsSuccess, (state,action) => ({...state, isLoading: false,postsState: {posts : action.posts ,isLoading : false , error :null} })),
  on(Actions.getPostsFailure, (state,action) => ({...state, isLoading: false,postsState: {posts : [] ,isLoading : false , error :action.error} , error: action.error })));

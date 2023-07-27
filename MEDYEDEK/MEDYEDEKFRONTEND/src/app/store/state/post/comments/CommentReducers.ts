import {createReducer, on} from "@ngrx/store";
import * as Actions from './CommentActions'
import {AppStateInterface} from "../../AppState.interface";
import {CommentReducerFunctions} from "./CommentReducerFunctions";

export const initialState: AppStateInterface = {
  isLoading: false,
  postsState: {posts : [], isLoading : true,error:null},
  contentsState : [],
  commentsState : [],
  error: null,
}

const commentReducerFunctions = new CommentReducerFunctions();
export const commentreducers = createReducer(initialState,

  on(Actions.getComments, (state) => ({...state, isLoading: true})),
  on(Actions.getCommentFailure, (state,action) => ({...state, isLoading: false, error: action.error })),
  on(Actions.getCommentSuccess, (state,action) => ({...state, isLoading: false , commentsState :state.commentsState.concat(action.comments)})),

  on(Actions.addCommentLevel0, (state) => ({...state, isLoading: true})),
  on(Actions.addCommentLevel0Success, (state,action) =>
    ({...state, isLoading: false , commentsState : commentReducerFunctions.updateCommentInsertionFirstState(state.commentsState,action.comment)})),
  on(Actions.addCommentLevel0Failure, (state,action) => ({...state, isLoading: false, error: action.error })));


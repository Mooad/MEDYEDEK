import {createReducer, on} from "@ngrx/store";
import * as Actions from './ContentActions'
import {AppStateInterface} from "../../AppState.interface";

export const initialState: AppStateInterface = {
  isLoading: false,
  postsState: {posts : [], isLoading : true,error:null},
  contentsState : [],
  commentsState : [],
  error: null,
}

export const contentreducers = createReducer(initialState,

  on(Actions.getContents, (state) => ({...state, isLoading: true})),
  on(Actions.getContentsSuccess, (state,action) => ({...state, isLoading: false , contentsState :state.contentsState.concat(action.contents)})),
  on(Actions.getContentsFailure, (state,action) => ({...state, isLoading: false, error: action.error })));

import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AppStateInterface} from "../../AppState.interface";


export const state = createFeatureSelector<AppStateInterface>('comment');

export const commmentSelector =  createSelector(state, (state) => {return state.commentsState})

export const commentSelectorByID = (identifier: string) =>
  createSelector(commmentSelector, (state) =>
    state.filter(element =>((element) && (element._id == identifier))).length > 0
      ? state.filter(element => ((element) && (element._id == identifier))).pop() : undefined );

export const commentSelectorByPostId = (post_id: number) =>
  createSelector(commmentSelector, (state) =>
    state.filter(element =>((element) && (element.post_id == post_id))).length > 0
      ? state.filter(element => ((element) && (element.post_id == post_id))).pop() : undefined );


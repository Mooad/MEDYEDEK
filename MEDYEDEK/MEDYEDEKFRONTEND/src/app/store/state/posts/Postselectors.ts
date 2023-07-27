import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AppStateInterface} from "../AppState.interface";


export const state = createFeatureSelector<AppStateInterface>('posts');

export const isLoading = createSelector(state, (state) => state.isLoading);
export const postsSelector =  createSelector(state, (state) => {return state.postsState.posts})
export const posts =  createSelector(postsSelector, (state) => state);

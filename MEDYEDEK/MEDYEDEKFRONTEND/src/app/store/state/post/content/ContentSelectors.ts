import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AppStateInterface} from "../../AppState.interface";


export const state = createFeatureSelector<AppStateInterface>('content');
export const postsSelector =  createSelector(state, (state) => {return state.contentsState})
export const contentSelectorByIDPost = (post_id: number) =>  createSelector(postsSelector, (state) => state.filter(state => state.post_id == post_id));


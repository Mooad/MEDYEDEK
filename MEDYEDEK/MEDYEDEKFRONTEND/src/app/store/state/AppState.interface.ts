import {PostsStateInterface} from "../../posts/types/postState.Interface";
import {CommentDto, Content} from "../../entities/Post";

export interface AppStateInterface
{
  postsState?:PostsStateInterface,
  contentsState : Content[],
  commentsState : CommentDto[],
  error : string,
  isLoading : boolean

}

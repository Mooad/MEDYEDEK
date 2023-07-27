import {PostDto} from "../../entities/Post";

export interface PostsStateInterface {
  isLoading: boolean,
  posts: PostDto[],
  error: string | null;
}

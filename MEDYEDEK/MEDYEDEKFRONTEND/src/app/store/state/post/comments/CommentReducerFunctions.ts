import { CommentDto, Comment } from "../../../../entities/Post";

export class CommentReducerFunctions {
  updateCommentInsertionFirstState(comments: CommentDto[], addedComment: Comment): CommentDto[] {
    // Deep copy the comments array, including nested objects within commentsTree.
    const commentsSnapshot: CommentDto[] = JSON.parse(JSON.stringify(comments));

    if (addedComment) {
      // Find the index of the CommentDto with the matching post_id.
      const commentDtoIndex = commentsSnapshot.findIndex((commentDto) => commentDto.post_id === addedComment.post_id);

      // If a CommentDto with the post_id is found, add the new comment to its commentsTree.
      if (commentDtoIndex !== -1) {
        commentsSnapshot[commentDtoIndex].commentsTree.comments.push(addedComment);
        console.log(addedComment);
        commentsSnapshot.forEach(value => console.log(value));
      }
    }

    console.log(JSON.stringify('commentsSnapshot   '+commentsSnapshot[0]));
    return commentsSnapshot;
  }
}


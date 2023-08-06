import {CommentDto, Comment} from "../../../../entities/Post";

export class CommentReducerFunctions {

  updateCommentInsertionFirstState(comments: CommentDto[], addedGrappe: any): CommentDto[] {

    // Deep copy the comments array, including nested objects within commentsTree.
    const commentsSnapshot: CommentDto[] = JSON.parse(JSON.stringify(comments));
    let addedComment: Comment;

    addedComment = addedGrappe;

    if (addedGrappe && !addedGrappe.parent && addedGrappe.commentsTree.comments) {
      addedComment = addedGrappe.commentsTree.comments[addedGrappe.commentsTree.comments.length - 1];
    }


    if (addedComment) {
      // Find the index of the CommentDto with the matching post_id.
      const commentDtoIndex = commentsSnapshot.findIndex((commentDto) => commentDto && commentDto.post_id === addedComment.post_id);

      // If a CommentDto with the post_id is found, add the new comment to its commentsTree.
      if (commentsSnapshot && commentDtoIndex !== -1) {
        commentsSnapshot[commentDtoIndex].commentsTree.comments.push(addedComment);
        commentsSnapshot.forEach(value => console.log(value));
      } else {
        commentsSnapshot.push({
          commentsTree: {comments: [addedComment]},
          post_id: addedComment.post_id,
          _id: addedGrappe._id,
          user_id: addedComment.user_id
        })
      }

      console.log('Added Comment ' + addedComment);
    }
    return commentsSnapshot.filter(value => value != null);
  }


  replaceCommentTreeAfterReplyAdded(subComment: Comment, comment: Comment[], commentsDtos: CommentDto[]): CommentDto[] {
    if (commentsDtos) {
      const commentsSnapshot: CommentDto[] = JSON.parse(JSON.stringify(commentsDtos));

      const commentDtoIndex = commentsSnapshot.findIndex((commentDto) => commentDto && commentDto.post_id === comment[0].post_id);

      this.addSubCommentToParent(subComment, commentsSnapshot[commentDtoIndex].commentsTree.comments);

      return commentsSnapshot;
    }

  }


  addSubCommentToParent(subComment: Comment, comments: Comment[]): Comment[] {
    for (const comment of comments) {
      if (comment._id === subComment.parent) {
        if (!comment.commentsTree || !comment.commentsTree.comments) {
          comment.commentsTree = { comments: [] };
        }

        comment.commentsTree.comments.push(subComment);
        return;
      } else if (comment.commentsTree?.comments) {
         this.addSubCommentToParent(subComment, comment.commentsTree.comments);
        }
      }

  }



}


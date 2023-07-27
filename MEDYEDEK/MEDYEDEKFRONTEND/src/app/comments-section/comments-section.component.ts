import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import {Comment, CommentDto } from '../entities/Post';
import { select, Store } from '@ngrx/store';
import { commentSelectorByID } from '../store/state/post/comments/CommentSelectors';
import { CommentService } from '../services/commentService';
import * as CommentActions from '../store/state/post/comments/CommentActions';

@Component({
  selector: 'app-comments-section',
  templateUrl: './comments-section.component.html',
  styleUrls: ['./comments-section.component.scss'],
})
export class CommentsSectionComponent implements OnInit {
  comments: CommentDto;
  showReply: { [commentId: string]: boolean } = {};
  replyText: { [commentId: string]: string } = {};
  newCommentText: string = '';
  numCommentsToShow: number = 1; // Initially show only one comment

  @Input() identifier;
  @Input() post_id;

  @ViewChild('commentTextArea') commentTextArea!: ElementRef<HTMLTextAreaElement>;

  constructor(private commentService: CommentService, private store: Store) {}

  ngOnInit(): void {
    if (this.identifier) {
      this.store.dispatch(CommentActions.selectComments({ identifier: this.identifier }));
      this.store.pipe(select(commentSelectorByID(this.identifier))).subscribe((value) => {
        this.comments = value;
      console.log(JSON.stringify(value));
      });
    }
  }

  // Function to automatically resize the textarea based on content
  autoResizeTextArea(textArea: HTMLTextAreaElement) {
    textArea.style.height = 'auto'; // Reset the height to auto to get the actual height needed
    textArea.style.height = `${textArea.scrollHeight}px`; // Set the height to the calculated height
  }

  toggleReplies(commentId: string): void {
    this.showReply[commentId] = !this.showReply[commentId];
  }

  submitReply(commentId: string, event: Event): void {
    const replyText = this.replyText[commentId].trim();
    if (replyText === '') {
      // Do not submit empty replies
      return;
    }

    console.log(`Submitting reply for comment ${commentId}: ${replyText}`);

    // Add the reply to the parent comment in the this.comments object
    const parentComment = this.findCommentById(this.comments, commentId);
    if (parentComment) {
      const reply: Comment = {
        _id: "",
        content: "",
        level:  ++parentComment.commentsTree[0].level,
        user: "",
        user_id: 124, // Replace with the actual user or get from a service
        post_id: this.post_id,
        text: replyText,
        parent:this.identifier,
        commentsTree: {
          comments: [], // Replies cannot have further replies initially
        }
      };
      parentComment.commentsTree.comments.push(reply);
    }

    // Clear the reply input and hide it
    this.showReply[commentId] = false;
    this.replyText[commentId] = '';

    // Dispatch an action to update the store with the new comment tree
 //   this.store.dispatch(CommentActions.updateComments({ identifier: this.identifier, comments: this.comments }));
  }

  submitComment(): void {
    const newCommentText = this.newCommentText.trim();
    if (newCommentText === '') {
      // Do not submit empty comments
      return;
    }

    console.log(`Submitting new comment: ${newCommentText}`);

    // Add the new comment to the this.comments object
    const newComment: Comment = {
      _id: this.getRandomString(10), content: "", level: 0, user: "",
      commentsTree: undefined,
      user_id : 123,
      text :newCommentText,
      post_id : this.post_id,
      parent : this.identifier
    };


    // Dispatch an action to update the store with the new comment tree
    console.log(this.comments);
    this.store.dispatch(CommentActions.addCommentLevel0({comment:newComment}));
  }

  findCommentById(rootComment: CommentDto, commentId: string): CommentDto | null {
    // Helper function to find a comment by its ID in the comments tree
    if (rootComment && (rootComment._id === commentId)) {
      return rootComment;
    }
    for (const subComment of rootComment.commentsTree.comments) {
      const foundComment = this.findCommentById({commentsTree: subComment.commentsTree ,user_id:123, _id :subComment._id,post_id:this.post_id}, commentId);
      if (foundComment) {
        return foundComment;
      }
    }
    return null;
  }

  showMoreComments() {
    this.numCommentsToShow += 10; // Increase the number of comments to show
  }
  getRandomString(length: number): string {
    const characters = 'ABCDEFGHIJyz0123456789';
    let result = '';
    const charactersLength = characters.length;

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charactersLength);
      result += characters.charAt(randomIndex);
    }

    return result;
  }



}

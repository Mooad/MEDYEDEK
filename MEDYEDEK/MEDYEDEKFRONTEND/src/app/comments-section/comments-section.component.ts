import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import {Comment, CommentDto } from '../entities/Post';
import { select, Store } from '@ngrx/store';
import {commentSelectorByID, commentSelectorByPostId} from '../store/state/post/comments/CommentSelectors';
import { CommentService } from '../services/commentService';
import * as CommentActions from '../store/state/post/comments/CommentActions';
import {ProfileDto} from "../entities/UserResetDto";
import {Utils} from "../services/utils/UtilMethods";
import {CommentActionConfirmationComponent} from "../comment-action-confirmation/comment-action-confirmation.component";
import {MatDialog} from "@angular/material/dialog";
import {Observable} from "rxjs";

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
  numCommentsToShow: number = 2; // Initially show only one comment
  userEmail : string;
  @Input() profile : ProfileDto;
  @Input() identifier;
  @Input() post_id;

  @ViewChild('commentTextArea') commentTextArea!: ElementRef<HTMLTextAreaElement>;

  constructor(private commentService: CommentService, private store: Store, private utils: Utils,private dialog: MatDialog) {}

  ngOnInit(): void {
    if (this.identifier) {
      this.store.dispatch(CommentActions.selectComments({ identifier: this.identifier }));
      this.initialiseCommentsValues();
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

// Inside your component class
  deleteComment(deletedCommment: Comment): void {

    this.openConfirmationDialog({type: "delete comment :" , question:"Are you sure you want to delete the comment ?"}).subscribe(
      result => {
        if (result) {
          // User confirmed the action
          this.store.dispatch(CommentActions.deleteCommentLevel0({comment:deletedCommment}));
          this.commentService.updateAllTree(this.comments);
        } else {
          // User did not confirm the action
          console.log('User did not confirm the action.');
        }
      }
    )

  console.log(parent);

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
      _id: "", content: "", level: 0,
      commentsTree: {
        comments: [],
      },
      user_id:this.profile.user_id, // Replace with the actual user or get from a service
      text : newCommentText,
      post_id : this.post_id,
      parent : this.identifier.trim() == '' ? null : this.identifier
    };



    // Dispatch an action to update the store with the new comment tree
    this.store.dispatch(CommentActions.addCommentLevel0({comment:newComment}));
    this.setCurrentCommentsValues();

  }


  submitReply(parent: Comment, event: Event): void {

    const replyText = this.replyText[parent._id].trim();
    if (replyText === '') {
      // Do not submit empty replies
      return;
    }
    const uniqueId: string = this.utils.generateCustomUniqueId();


      const reply : Comment = {
        _id: uniqueId,
        content: "",
        level: (parent.level+1),
        user_id:this.profile.user_id, // Replace with the actual user or get from a service
        post_id: this.post_id,
        text: replyText,
        parent: parent._id.trim(),
        commentsTree: {
          comments: [], // Replies cannot have further replies initially
        }
      };


      this.store.dispatch(CommentActions.addCommentLevelx({parent_id:reply.parent , reply: reply , comments :this.comments.commentsTree.comments}));
      this.commentService.synchroniseCommentlevelx(reply,this.identifier).subscribe();


  }


  showMoreComments() {
    this.numCommentsToShow += 10; // Increase the number of comments to show
  }

  setCurrentCommentsValues()
  {
    this.store.pipe(select(commentSelectorByPostId(this.post_id))).subscribe((value) => {
      //attaching comment to post
      if(value)
      {
        this.comments = value;
        this.identifier = value._id;
        this.commentService.attachCommentToPost({comment_id:value._id ,post_id:this.post_id});
      }
    });
  }
  initialiseCommentsValues()
  {
    this.store.pipe(select(commentSelectorByID(this.identifier.trim()))).subscribe((value) => {
      if(value)
      this.comments = value;
      console.log(this.identifier +'initialiseCommentsValues identifier ' )

      console.log('value ' +value)
    });
  }
  openConfirmationDialog(action:any): Observable<any> {
    const dialogRef = this.dialog.open(CommentActionConfirmationComponent, {
      width: '400px', // You can adjust the width as needed
      data: {
        title: action.type,
        message: action.question
      }
    });

    return dialogRef.afterClosed();


  }
}

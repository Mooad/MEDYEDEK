import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-post-comments',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.scss']
})
export class PostCommentsComponent implements OnInit {

  @Input() comments : any;
  newCommentText : String;
  constructor() { }

  ngOnInit(): void {
  }

  likeComment(comment): void {
  }

  replyComment(comment): void {
  }

  deleteComment(comment): void {
  }

  addComment(comment): void {
  }
}

interface Comment {
  text: string;
  likes: number;
}

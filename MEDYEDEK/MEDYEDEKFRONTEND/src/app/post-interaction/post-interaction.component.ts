import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../services/postServices";
import {InteractionType, PostDto, PostInteractionDto} from "../entities/UserResetDto";
import {CommentService} from "../services/commentService";

@Component({
  selector: 'app-post-interaction',
  templateUrl: './post-interaction.component.html',
  styleUrls: ['./post-interaction.component.scss']
})
export class PostInteractionComponent implements OnInit {

  constructor(public postService : PostService,  private  commentService : CommentService ) { }
  @Input() post : PostDto
  @Output() postInteraction = new EventEmitter<Boolean>();
  interaction: PostInteractionDto;
  liked : boolean = false;
  disabled : boolean;
  likesNB : number= 0;

  ngOnInit(): void {
    if (this.post) {
      this.likesNB = this.post.likesNB;
      this.liked = false;
      this.reloadInteraction();
      this.postService.currentUserLikePost(this.interaction).subscribe(userExistingLikeInteraction => {
        if (userExistingLikeInteraction && userExistingLikeInteraction.interactionType) {
          if (InteractionType.LIKE.startsWith(userExistingLikeInteraction.interactionType)) {
            this.liked = true;
          }
          this.interaction.interactionType = userExistingLikeInteraction.interactionType;
          if(this.post)
          this.likesNB = this.interaction.likesNB;
        }});

    }

  }

  interact(interactionType: string) : void {

    this.disabled = true;

    this.reloadInteraction();

    if (this.post) {
      this.postService.interactWithPost(this.interaction).subscribe(value => {
        this.interaction = value;
        this.disabled =  false;
      }, error => {
        console.log(error)
        this.disabled =  false;

      })
    }

    this.liked =! this.liked;



    this.updatepostLikes();
  }

  getInteractionType () : InteractionType
  {
   return  this.liked  ? InteractionType.DISLIKE : InteractionType.LIKE;
  }

  reloadInteraction()
  {
    this.interaction  = {
      post_id: this.post.post_id,
      user_id: this.post.user_id,
      comment: '',
      interactionType:  this.getInteractionType(),
      nblikesIncremented: false,
      likesNB : this.post.likesNB
    }
  }

  updatepostLikes() : void
{
  if(this.liked)
  {
    ++this.likesNB;
  }
  else {
    --this.likesNB;
  }
}
}

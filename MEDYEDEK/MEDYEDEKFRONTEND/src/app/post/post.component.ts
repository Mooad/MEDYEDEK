import {Component, OnInit, Input} from '@angular/core';
import {SafeUrl} from '@angular/platform-browser';
import {PostDto, ProfileDto} from "../entities/UserResetDto";
import {ContentService} from "../services/ContentService";
import {select, Store} from "@ngrx/store";
import * as ContentActions from './../store/state/post/content/ContentActions'
import {Content} from "../entities/Post";
import {contentSelectorByIDPost} from "../store/state/post/content/ContentSelectors";
import {ProfileService} from "../services/profile.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input() post: PostDto;
  content: Content[];

  @Input() textContent: string;
  @Input() imageUrl: SafeUrl;
  @Input() selectedImage: String;
  @Input() profile: ProfileDto;

  isLoading: boolean = true;

  i = 1;

  constructor(private contentService: ContentService, private store: Store,private  profileService: ProfileService) {

  }

  ngOnInit(): void {
    this.getImageFromService();


    this.store.pipe(select(contentSelectorByIDPost(this.post.post_id))).subscribe(
      value => {
        this.content = value;

        if (this.content && this.content.length > 0) {
          this.selectedImage = 'data:image/jpeg;base64,' + this.content[0].content;
        }
        this.isLoading = false;

      }
    );
    this.store.dispatch(ContentActions.selectContents({postId: this.post.post_id}));

  }

  getImageFromService() {
    return this.post.userImage;

  }

  prev() {
    if (this.i <= 0) this.i = this.content.length;
    this.i--;
    console.log(this.i);
    return this.setImg();
  }

  next() {
    if (this.i >= this.content.length - 1) this.i = -1;
    this.i++;
    console.log(this.i);
    return this.setImg();
  }

  setImg() {
    return this.selectedImage = 'data:image/jpeg;base64,' + this.content[this.i].content;
  }

  synchronizeInteractionWithPost(value) {
    //
  }
}

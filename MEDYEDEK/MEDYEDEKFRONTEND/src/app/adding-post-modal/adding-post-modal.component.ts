import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { animate, style, transition, trigger } from "@angular/animations";
import { ProfileService } from "../services/profile.service";
import { ProfileDto } from "../entities/UserResetDto";

// @ts-ignore
@Component({
  selector: 'app-adding-post-modal',
  templateUrl: './adding-post-modal.component.html',
  styleUrls: ['./adding-post-modal.component.scss'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 }))
      ])
    ])
  ]
})
export class AddingPostModalComponent {
  postForm: FormGroup;
  showEmojiPicker = false;
  showPopin = false;  // To toggle the popin visibility
  profile: ProfileDto;
  cursorPosition: number;

  constructor(
    public dialogRef: MatDialogRef<AddingPostModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private profileService: ProfileService
  ) {
    this.createForm();
    this.fetchUserProfile();
  }

  createForm() {
    this.postForm = this.fb.group({
      postText: ['', Validators.required],
      postType: ['', Validators.required]
    });
  }

  fetchUserProfile(): void {
    this.profileService.getUserProfile(localStorage.getItem("userEmail")).subscribe(
      value => this.profile = value,
      error => console.error('Error fetching user profile:', error)  // Simple error logging
    );
  }

  closeModal(): void {
    this.dialogRef.close();
  }

  post(): void {
    if (this.postForm.valid) {
      this.closeModal();
    }
  }

  togglePopin(): void {
    this.showPopin = !this.showPopin;
  }

  addEmojiToText(event): void {
    const emoji = event.emoji.native;
    const currentText = this.postForm.get('postText').value;
    const beforeCursor = currentText.substring(0, this.cursorPosition);
    const afterCursor = currentText.substring(this.cursorPosition);

    this.postForm.get('postText').setValue(beforeCursor + emoji + afterCursor);
    this.showEmojiPicker = false;
    this.showPopin = false; // hide the popin
  }

  onInput(event: any): void {
    this.cursorPosition = event.target.selectionStart;
  }

  toggleEmojiPicker(): void {
    this.showEmojiPicker = !this.showEmojiPicker;
  }

  onImageIconClick(): void {
    // Logic to handle image
  }

  onLocationIconClick(): void {
    // Logic to handle location
  }

  onGifIconClick(): void {
    // Logic to handle GIF
  }

  getImage() {
    return this.profile ? this.profile.image : undefined;
  }

  getUserName() {
    return this.profile ? this.profile.pseudo : undefined;
  }
}

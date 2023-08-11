import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-comment-action-confirmation',
  templateUrl: './comment-action-confirmation.component.html',
  styleUrls: ['./comment-action-confirmation.component.scss']
})

export class CommentActionConfirmationComponent {
  constructor(
    public dialogRef: MatDialogRef<CommentActionConfirmationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string; message: string }
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onYesClick(): void {
    this.dialogRef.close(true);
  }
}

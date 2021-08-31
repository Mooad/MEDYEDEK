

import { Component, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-reset-pass-dialog',
  templateUrl: './confirm-reset-pass-dialog.component.html',
  styleUrls: ['./confirm-reset-pass-dialog.component.scss']
})
export class ConfirmResetPassDialogComponent {
  constructor(public dialogRef: MatDialogRef<ConfirmResetPassDialogComponent>) {}

  public confirmMessage:string;
}
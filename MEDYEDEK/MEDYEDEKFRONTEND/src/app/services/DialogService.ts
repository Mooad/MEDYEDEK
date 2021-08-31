import { Router, ActivatedRoute } from '@angular/router';
import { Injectable } from '@angular/core';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { ConfirmResetPassDialogComponent } from '../confirm-reset-pass-dialog/confirm-reset-pass-dialog.component';

@Injectable()
export class DialogService {
  public isDialogOpen: Boolean = false;
  dialogRef: MatDialogRef<any>;


  constructor(private router: Router,private activatedRoute: ActivatedRoute,public dialog: MatDialog) { }

  confirmationDialog(msg:string) {
    this.dialogRef = this.dialog.open(ConfirmResetPassDialogComponent, {
        disableClose: false,
      });


      this.dialogRef.componentInstance.confirmMessage = msg ;
    
      this.dialogRef.afterClosed().subscribe(result => {
        if(result) {
          // do confirmation actions
        }
  })
      this.dialogRef=null;

}

}
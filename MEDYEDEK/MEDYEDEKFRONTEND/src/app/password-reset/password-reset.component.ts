import { Component, OnInit, Input } from '@angular/core';
import { ChangePassword } from '../services/ChangePassword';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { ConfirmResetPassDialogComponent } from '../confirm-reset-pass-dialog/confirm-reset-pass-dialog.component';
import { DialogService } from '../services/DialogService';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit {


  @Input() ErrorLogin : any;
   email:string;
   dialogRef: MatDialogRef<ConfirmResetPassDialogComponent>;

  constructor(private changePassword:ChangePassword,private router: Router,private activatedRoute: ActivatedRoute,public dialog: MatDialog,private dialogService: DialogService) {

    this.activatedRoute.queryParams.subscribe(params => {
      let emailParam = params['m'];
      this.email = emailParam.replace('"',"");
  });
   }

  ngOnInit(): void {
  }

  initForm() {
  }

  async onSubmitForm() {
    const access = this.changePassword.changeUserPassword(this.email)
    .subscribe(
        data => {
          this.dialogService.confirmationDialog("Please check the password that was sent to your email !");
            this.router.navigateByUrl('/reset-password-confirm?m='+this.email);
        },
        error => {
            this.ErrorLogin = error;
            
        });
}}
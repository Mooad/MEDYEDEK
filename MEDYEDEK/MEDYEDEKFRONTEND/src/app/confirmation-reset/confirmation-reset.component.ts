import { Component, OnInit, Input } from '@angular/core';
import * as $ from 'jquery';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ChangePassword } from '../services/ChangePassword';
import { DialogService } from '../services/DialogService';
import { PasswordDto } from '../entities/UserResetDto';

@Component({
  selector: 'app-confirmation-reset',
  templateUrl: './confirmation-reset.component.html',
  styleUrls: ['./confirmation-reset.component.scss']
})
export class ConfirmationResetComponent implements OnInit {

  resetPasswordForm: FormGroup;
   passwordDto : PasswordDto;
   emailParam : string;
  constructor(private formBuilder: FormBuilder, private router: Router , private changePassword :ChangePassword,private activatedRoute:ActivatedRoute,private dialogService : DialogService) { 
    this.activatedRoute.queryParams.subscribe(params => {
      this.emailParam= params['m'].replace('"',"");
  });
  }

  ngOnInit(): void {
    this.resetPasswordForm = this.formBuilder.group({
      newPass: ['', Validators.required],
      tempPass: ['', Validators.required],
      newPassConfirm: ['', Validators.required]
    }, {
    });
  }

  async onSubmit() {
    
    this.passwordDto = {email: this.emailParam, newPass: this.resetPasswordForm.controls['newPass'].value, tempPass: this.resetPasswordForm.controls['tempPass'].value};
    if(!(this.passwordDto.newPass===this.resetPasswordForm.controls['newPassConfirm'].value))
    {
        this.dialogService.confirmationDialog("The passwords entered does not match please confirm your password !");
        return;
    }

    const access = this.changePassword.confirmNewPassword(this.passwordDto)
    .subscribe(
        data => {
          this.dialogService.confirmationDialog("Your Password has been modified succefully");

            this.router.navigate(['/login']);
        },
        error => {
        });

   /*   const access = this.loginproxy.login(formValue['email'], formValue['mdp'],formValue['kms']) .pipe(first())
  .subscribe(
        data => {
            this.router.navigate(['/home']);
        },
        error => {
            this.ErrorLogin = error;
            this.Loading = false;
        });
      

    if(access instanceof HttpErrorResponse)
    {
      this.Logged=false;
    }
    console.log(access);
    this.Loading = false; */
  }

}

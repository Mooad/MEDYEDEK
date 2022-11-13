import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginproxyService } from '../services/loginproxy.service';
import { HttpErrorResponse } from '@angular/common/http';
import { first } from 'rxjs/operators';
import { ConfirmResetPassDialogComponent } from '../confirm-reset-pass-dialog/confirm-reset-pass-dialog.component';
import { MatDialogRef } from '@angular/material/dialog';
import { DialogService } from '../services/DialogService';
import { AppConfig } from '../config/appConfig';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() errorLogin: string;
  @Input() loading: boolean;
  @Input() logged: boolean;
  @Input() connected: boolean;

  loginForm: FormGroup;
  public LOGO = 'medyedek.jpeg';
  dialogRef: MatDialogRef<ConfirmResetPassDialogComponent>;

  // tslint:disable-next-line:max-line-length
  constructor(private loginproxy: LoginproxyService, private formBuilder: FormBuilder, private router: Router, private dialogService: DialogService,private appConfig:AppConfig) {

  }

  ngOnInit(): void {
    this.logged = true;

    if (sessionStorage.getItem('thCurUsr')) {
      this.router.navigate(['/home']);
    }

    this.initForm();

  }

  initForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      mdp: ['', Validators.required],
      kms: ['']

    });
  }

  async onSubmitForm() {
    this.loading = true;
    this.connected = true;

    const formValue = this.loginForm.value;
    const isKms =  formValue['kms'];
    const access = this.loginproxy.login(formValue['email'], formValue['mdp'],formValue['kms']).pipe(first())
    .subscribe(
        data => {
          this.router.navigateByUrl("home/posts/all");
        },
        error => {
            this.errorLogin = error;
            this.loading = false;
        });


    //Storing the connected user
   localStorage.setItem("userEmail", this.loginForm.value['email']);

    if(access instanceof HttpErrorResponse)
    {
      this.logged=false;
    }

    console.log(access);
    this.loading = false;
    debugger;
  }


  changePasswordRequest()

  {
if(!this.loginForm.controls['email'].invalid)
{
  this.router.navigateByUrl("/resetPassword"+"?m="+this.loginForm.controls['email'].value);
}

else
{
  this.dialogService.confirmationDialog("Please enter your email :)")
}

}

  }

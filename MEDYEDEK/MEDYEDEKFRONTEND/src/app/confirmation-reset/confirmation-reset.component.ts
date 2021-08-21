import { Component, OnInit, Input } from '@angular/core';
import * as $ from 'jquery';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-confirmation-reset',
  templateUrl: './confirmation-reset.component.html',
  styleUrls: ['./confirmation-reset.component.scss']
})
export class ConfirmationResetComponent implements OnInit {

  @Input() ErrorLogin: string;
  @Input() Loading: boolean;
  resetPasswordForm: FormGroup;


  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
  
  }

  async onSubmitForm() {
    this.Loading = true;
    const formValue = this.resetPasswordForm.value;

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

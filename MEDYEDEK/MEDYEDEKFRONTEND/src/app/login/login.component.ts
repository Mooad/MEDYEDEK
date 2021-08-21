import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginproxyService } from '../services/loginproxy.service';
import { HttpErrorResponse } from '@angular/common/http';
import { first } from 'rxjs/operators';

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

  constructor(private loginproxy: LoginproxyService, private formBuilder: FormBuilder, private router: Router) {

  }

  ngOnInit(): void {
    this.logged = true;

    if (sessionStorage.getItem('token')) {
      this.router.navigate(['/home']);
    }

    this.initForm();
    
  }
 
  initForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.email,Validators.required]],
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
          this.router.navigate(['/home/posts/all']);
        },
        error => {
            this.errorLogin = error;
            this.loading = false;
        });

    if(access instanceof HttpErrorResponse)
    {
      this.logged=false;
    }
    else
    {
      this.router.navigate(['/home/posts/all']);
    }
    console.log("fffff");

    console.log(access);
    this.loading = false;
    debugger;
  }
}
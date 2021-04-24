
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginproxyService } from '../services/loginproxy.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  constructor(private loginproxy: LoginproxyService, private formBuilder: FormBuilder, private router: Router) 
  {
    this.loginproxy.login(undefined, undefined);
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {

    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      mdp: ['', Validators.required]
    });
  }

  onSubmitForm() {
    const formValue = this.loginForm.value;
    this.loginproxy.login(formValue['email'], formValue['mdp']);

  }
}

import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginproxyService } from '../services/loginproxy.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() ErrorLogin: string;
  loginForm: FormGroup;
  public LOGO = 'medyedek.jpeg';

  constructor(private loginproxy: LoginproxyService, private formBuilder: FormBuilder, private router: Router) {
    this.loginproxy.login(undefined, undefined);
  }

  ngOnInit(): void {
    if (localStorage.getItem('user')) {
      this.router.navigate(['/home']);
    }

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

    const error = this.loginproxy.login(formValue['email'], formValue['mdp']);
    if (error === 'NOACCESS') {
      this.ErrorLogin = 'Le Mot de passe ou le non d utilisateur est erronee';
    }
  }
  manageAccess() {

  }
}

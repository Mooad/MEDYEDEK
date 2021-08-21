
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
  const o =   this.loginproxy.login(formValue['email'], formValue['mdp']).subscribe((value) => {
           console.log("Oh an error occured"+value);
  }, 

  (error) => {
    console.log('Uh-oh, an error occurred! : ' + error);
  },
  () => {
    console.log('Observable complete!');
  }
);
console.log("heeeeey");
  }
}

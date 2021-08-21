import { Component, OnInit, Input } from '@angular/core';
import { ChangePassword } from '../services/ChangePassword';
import { Router } from '@angular/router';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit {


  @Input() ErrorLogin : any;
  constructor(private changePassword:ChangePassword,private router: Router) { }

  ngOnInit(): void {
  }


  initForm() {

  }

  async onSubmitForm() {
    const access = this.changePassword.changeUserPassword("moad52@hotmail.fr")
    .subscribe(
        data => {
            this.router.navigate(['/reset-password-confirm']);
        },
        error => {
            this.ErrorLogin = error;
        });
  }

}
 
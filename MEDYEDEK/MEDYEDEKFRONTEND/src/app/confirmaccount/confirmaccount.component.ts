import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { LoginproxyService } from '../services/loginproxy.service';
import { UserInfo } from 'os';
import { Utilisateur } from '../entities/Post';
import { UserService } from '../services/userService';

@Component({
  selector: 'app-confirmaccount',
  templateUrl: './confirmaccount.component.html',
  styleUrls: ['./confirmaccount.component.scss']
})
export class ConfirmaccountComponent implements OnInit {
  ErrorLogin: string;

  constructor(private router:Router ,private userService :UserService) { }

  @Input() user: string;

  ngOnInit(): void {
  /*   if (localStorage.getItem('user')) {
    return;
    }
    else
    {
      this.router.navigate(['/Error']);
    } */
  }

  onSubmit() {
  
    }
    
    getUserWithToken(): void {
    const error = this.userService.getUserByToken();
    }
}

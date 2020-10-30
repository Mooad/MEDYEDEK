import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class ErrorDialogService {
  public isDialogOpen: Boolean = false;
  constructor(private router: Router) { }

  openDialogInterneError(data): any {
    if (data.type === 'server' && data.status !== 401) {
      this.gotoErrorPage();
    } else {
      return data;
    }
  }
  public gotoErrorPage() {

    this.router.navigateByUrl('/somethingswrong');
  }
}
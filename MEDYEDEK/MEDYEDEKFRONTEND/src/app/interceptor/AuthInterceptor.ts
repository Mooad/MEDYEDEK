import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse} from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
  router: any;
  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    if (!sessionStorage.getItem('thCurUsr')) {
      sessionStorage.clear();
    }

    return next.handle(req).pipe(catchError(x=> this.handleAuthError(x)));
    //here use an arrow function, otherwise you may get "Cannot read property 'navigate' of undefined" on angular 4.4.2/net core 2/webpack 2.70
  }
  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401) {
        sessionStorage.clear();
        localStorage.clear();

        this.router.navigate(['/login'])
        //navigate /delete cookies or whatever
        // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
    }
    return throwError(err);
  }

}

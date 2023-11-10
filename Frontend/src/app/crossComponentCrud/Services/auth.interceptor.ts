import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private _tostr:ToastrService) {}
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const localtoken= localStorage.getItem('token');
    request=request.clone({
      headers:request.headers.set('Authorization','Bearer ' + `${localtoken}`)
    }),
    (error:any)=>{
      this._tostr.error(error.message)
    }
    return next.handle(request);
  }
}

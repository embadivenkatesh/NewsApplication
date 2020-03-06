import { Injectable } from '@angular/core';

import { 
  HttpRequest ,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { AuthenticationService } from  './../authentication/authentication.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor{

  constructor(private authenticationService:AuthenticationService) { }
  
  intercept(request:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>>{

    console.log('TokenInterceptor>>intercept>>start');
    request = request.clone({
      setHeaders:{
        authorization : `Bearer ${this.authenticationService.getToken()}`
      }
    });

    return next.handle(request);
  }
}

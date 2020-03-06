import { Injectable } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router,CanActivate } from '@angular/router';


@Injectable()
export class AuthguardService implements CanActivate {


  constructor(private authenticationService:AuthenticationService,private router:Router) {

   }

   canActivate(){
     if(this.authenticationService.isTokenExpired()){
      this.router.navigate(['/login']);
      console.log('authguard returning false');
      return false;
     }
     console.log('authguard returning true');
     return true;
   }

}

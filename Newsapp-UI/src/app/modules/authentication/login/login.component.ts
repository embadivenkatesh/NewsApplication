import { Component, OnInit } from '@angular/core';
import {user} from '../user';
import {Router} from '@angular/router';
import{AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  

  newUser:user;

  constructor(private authService:AuthenticationService, private router:Router) { 
    this.newUser = new user();

  }

  ngOnInit() {
  }

  loginUser(){
    this.authService.loginUser(this.newUser).subscribe((data)=>{
      console.log("New user "+ data['token']);
      this.authService.setToken(data['token']);
      this.router.navigate(['/news/headlines']);
    });

  }

}

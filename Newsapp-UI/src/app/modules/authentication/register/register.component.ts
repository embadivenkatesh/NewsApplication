import { Component, OnInit } from '@angular/core';
import {user} from '../user';
import{AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'news-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser:user;

  constructor(private authService:AuthenticationService, private router:Router) { 
    this.newUser = new user();

  }

  ngOnInit() {
  }

  registerUser(){
    this.authService.registerUser(this.newUser).subscribe((data)=>{
      console.log("New user "+ data);
    });
    this.router.navigate(['/login']);

  }

}

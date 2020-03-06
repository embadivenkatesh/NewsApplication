import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './../authentication.service';


@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styles:['./logout.component.css']
})
export class LogoutComponent implements OnInit {  
  constructor(private authenticationService:AuthenticationService) {  
  }

  ngOnInit() {
    console.log('LogoutComponent>>ngOnInit>>start');   
    this.authenticationService.deleteToken();
    console.log('LogoutComponent>>ngOnInit>>end'); 
  }
}

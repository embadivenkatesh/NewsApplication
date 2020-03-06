import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NewsModule } from './modules/news/news.module';
import { HttpClientModule } from '@angular/common/http'; 
import { RouterModule,Routes } from '@angular/router';
import { BrowserAnimationsModule } from  '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthguardService } from './authguard.service';

const appRoutes: Routes= [
  {
    //path:'',
    //redirectTo:'/news/headlines',
    //pathMatch:'full'

    path:'',
    redirectTo:'/login',
    pathMatch:'full'
  }
];

@NgModule({
  declarations: [
    AppComponent    
  ],
  imports: [
    BrowserModule,MatDialogModule,FormsModule,BrowserAnimationsModule,MatToolbarModule,MatButtonModule,NewsModule,HttpClientModule,RouterModule.forRoot(appRoutes),AuthenticationModule,
  ],
  providers: [AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor(){   
    console.log("AppModule>>constructor");   
  }

}

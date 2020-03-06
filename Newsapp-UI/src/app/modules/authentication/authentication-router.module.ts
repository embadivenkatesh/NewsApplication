import { RouterModule, Routes} from '@angular/router';
import { NgModule } from '@angular/core';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';


const authRoutes : Routes =  [
    {
        path:'',
        
        children:[{
            path:'',
      redirectTo:'/login',
      pathMatch:'full',

        },
        {
            path :'register',
            component : RegisterComponent

        },
        {
            path :'login',
            component : LoginComponent

        },
        {
            path:   'logout',
            pathMatch: 'full',
            component: LogoutComponent                  
        } 

        ] 
    }

];

@NgModule({
    imports: [
        RouterModule.forChild(authRoutes),
    ],
    exports:[
        RouterModule,
    ],
    
  })
export class AuthenticationRouterModule {}



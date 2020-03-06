import { TestBed, inject, ComponentFixture, tick, fakeAsync, getTestBed, async } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule, HttpClient, HttpEvent, HttpEventType, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Router } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule, MatIcon, MatInputModule } from '@angular/material';
import { LoginComponent } from './login.component';
import { AuthenticationService } from '../authentication.service';



const testConfig = {
    userData: {
        firstName: 'user',
        lastName: 'user',
        userId: 'testUser',
        password: 'testPass'
    }
}
describe('Logincomponent', () => {
    let fixture: ComponentFixture<LoginComponent>;
    let spyUser: any;
    let loginComponent; LoginComponent;
    let authService; AuthenticationService
    let routes: Router;
    let location: Location;

    class AuthserviceStub {
        currentUser: any;
        constructor() {

        }
        loginUser(credentials) {
            if (credentials.userId == testConfig.userData.userId) {
                return of(credentials.userId);
            }
            return of(false);

        }
    }

    class dummy {

    }

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [LoginComponent],
            imports: [FormsModule, HttpClientModule, HttpClientTestingModule, BrowserAnimationsModule,
                MatFormFieldModule, MatInputModule,
                RouterTestingModule.withRoutes([{ path: '', component: dummy }])],
            providers: [{ provide: AuthenticationService, useClass: AuthserviceStub }]
        }).compileComponents();

    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LoginComponent);
        loginComponent = fixture.componentInstance;
        routes = TestBed.get(Router);
        location = TestBed.get(Location);
        fixture.detectChanges();
        fixture.debugElement.injector.get(AuthenticationService);
    });

    it('should create app component',
        async(() => {
            const app = fixture.debugElement.componentInstance;
            expect(app).toBeTruthy();

        }));

    it('should contain two input boxes for user id and password',
        async(() => {
            let userId = fixture.debugElement.query(By.css('#userid'));
            let userPassword = fixture.debugElement.query(By.css('#password'));
            let registerButton = fixture.debugElement.query(By.css('.register-button'));
            let loginButton = fixture.debugElement.query(By.css('.login-user'));

            let userIdInput = userId.nativeElement;
            let userPasswordInput = userPassword.nativeElement;
            let registerButtonInput = registerButton.nativeElement;
            let loginButtonInput = loginButton.nativeElement;

            expect(userIdInput).toBeTruthy();
            expect(userPasswordInput).toBeTruthy();
            expect(registerButtonInput).toBeTruthy();
            expect(loginButtonInput).toBeTruthy();


        }));

    it('should redirect to login if registered successfully',
        async(() => {
            let userId = fixture.debugElement.query(By.css('#userid'));
            let userPassword = fixture.debugElement.query(By.css('#password'));
            let loginButton = fixture.debugElement.query(By.css('.login-user'));

            let userIdInput = userId.nativeElement;
            let userPasswordInput = userPassword.nativeElement;
            let loginButtonInput = loginButton.nativeElement;

            fixture.detectChanges();
            fixture.whenStable().then(() => {
                userIdInput.value = 'testUser';
                userPasswordInput.value = 'testPass';
                userIdInput.dispatchEvent(new Event('input'));
                userPasswordInput.dispatchEvent(new Event('input'));
                loginButtonInput.click();

            }).then(() => {
                expect(location.path()).toBe('');
            })

        }));
})












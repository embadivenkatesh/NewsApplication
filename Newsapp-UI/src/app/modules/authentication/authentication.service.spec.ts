import { TestBed, inject,fakeAsync,getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule, HttpClient, HttpEvent,HttpEventType,  HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from './authentication.service';

const testConfig = {
    addUser:{
        positive:{
            firstName: 'user',
            lastName: 'user',
            userId: 'testUser',
            password:'testPass'

        }
    },
    loginUser:{
        positive:{
            userId: 'testUser',
            password:'testPass'
        }
    }
}
describe('AuthenticationService', ()=>{
let authService:AuthenticationService

beforeEach(() => {    
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [AuthenticationService]
    });
    authService = TestBed.get(AuthenticationService);
});
    it('should create authentication service', 
    inject([AuthenticationService], (service:AuthenticationService) => {
        expect(service).toBeTruthy();

    }));

    it('should register user data', fakeAsync(()=>{
    let data = testConfig.addUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend:HttpTestingController) => {
        const mockReq = backend.expectOne(authService.authEndpoint);
        expect(mockReq.request.url).toEqual(authService.authEndpoint, "request url matches");
        expect(mockReq.request.method).toBe('POST', "post request");
        mockReq.flush(data);
        backend.verify();

    });

    authService.registerUser(data).subscribe((res:any)=>{
        expect(res).toBeDefined();
        expect(res._body).toBe(data, "data should be same");

    })
}));

it('should login user data', fakeAsync(()=>{
    let data = testConfig.loginUser.positive;
    inject([AuthenticationService, HttpTestingController], (backend:HttpTestingController) => {
        const mockReq = backend.expectOne(authService.authEndpoint);
        expect(mockReq.request.url).toEqual(authService.authEndpoint, "request url matches");
        expect(mockReq.request.method).toBe('POST', "post request");
        mockReq.flush(data);
        backend.verify();

    });

    authService.loginUser(data).subscribe((res:any)=>{
        expect(res).toBeDefined();
        expect(res._body).toBe(data, "data should be same");

    })
}));
})





    
    
  

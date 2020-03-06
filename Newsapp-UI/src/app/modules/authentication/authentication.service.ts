import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME:string='jwt_token';


@Injectable()
export class AuthenticationService {
     authEndpoint: string;
  constructor(private httpClient: HttpClient) {
    this.authEndpoint = "http://localhost:8089/user";
   }

  registerUser(user){
    const endPoint=this.authEndpoint+"/register";
    return this.httpClient.post(endPoint, user, {responseType:'text'});
  }

  loginUser(user){
    const endPoint=this.authEndpoint+"/login";
    return this.httpClient.post(endPoint, user);
  }

  setToken(token:string){
    return localStorage.setItem(TOKEN_NAME,token);
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }
  deleteToken(){
    return localStorage.removeItem(TOKEN_NAME);
  }

  public isTokenExpired(token?: string): boolean {
    console.log('tokenExpired>>token:'+token);
    if(!token) token = this.getToken();
    console.log('tokenExpired>>getToken:'+token);
    if(!token) return true;
    const tokenExpiredDate= this.getTokenExpirationDate(token);
    console.log('tokenExpired>>tokenExpiredDate:'+tokenExpiredDate);
    if(tokenExpiredDate ===undefined || tokenExpiredDate ===null) return false;
    console.log('tokenExpired>>tokenExpiredDate.valueOf():'+tokenExpiredDate.valueOf());
    console.log('tokenExpired>>new Date().valueOf():'+new Date().valueOf());
    return !(tokenExpiredDate.valueOf()> new Date().valueOf());
  }

  public getTokenExpirationDate(token: string ): Date | null {
    let decoded: any;
    console.log('getJwtTokenExpirationDate>>start>>token:'+token);
    decoded = jwt_decode(token);
    console.log('decoded:'+decoded+' decoded.exp:'+decoded.exp);
    if(decoded.exp === undefined) return null;
    const date = new Date(0);
    console.log('date:'+date);
    date.setUTCSeconds(decoded.exp);
    console.log('getJwtTokenExpirationDate>>end');
    return date;
  }
}

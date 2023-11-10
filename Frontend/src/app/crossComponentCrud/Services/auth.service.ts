import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Login } from '../Models/login';

@Injectable({
  providedIn:'root'
})
export class AuthService {
  constructor(private _http:HttpClient) { }

  SignIn(body:Login){
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this._http.post(`${environment.base_url}/auth/login`,body)
  }
  // issLogedIn(){
  //   return localStorage.getItem('token')!=null
  // }
}

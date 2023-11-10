import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class DeptService {

  constructor(private _http:HttpClient) { }
  getAllDepartments():Observable<any>{
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this._http.get<any>(`${environment.base_url}/departments/`,{responseType:'json'})
  }
}

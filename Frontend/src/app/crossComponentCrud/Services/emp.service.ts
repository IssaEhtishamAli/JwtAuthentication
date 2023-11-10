import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Emp } from '../Models/emp';
import { UpdateEmp } from '../Models/updateEmp';


@Injectable()
export class EmpService {
  constructor(private http: HttpClient) { }

  getEmployees():Observable<any>{
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this.http.get<any>(`${environment.base_url}/employees/`,{responseType:'json'});
        }
  postEmployee(body:Emp)
  {
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this.http.post(`${environment.base_url}/employees/`,body);
  }
  updateEmployee(emp:UpdateEmp) {
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this.http.put(`${environment.base_url}/employees/`,emp);
  }
  deleteEmployee(id:any){
    const httpOptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    }
    return this.http.delete(`${environment.base_url}/employees/`+ "?Id=" +`${id}`)
  }
}

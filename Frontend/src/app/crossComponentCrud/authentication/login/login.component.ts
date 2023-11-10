import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../Services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(
    private router:Router,
    private _authService:AuthService,
    private _toastr:ToastrService
    ){}

  ngOnInit() {
      this.loginForm = new FormGroup({
          user_email:new FormControl('',Validators.required),
          user_password:new FormControl('', Validators.required)
      });
  }

  get LoginFormControl() { return this.loginForm.controls; }

  onSubmit() {
    this._authService.SignIn(this.loginForm.value).subscribe((res:any)=>{
      if(res){
        localStorage.setItem("token",res.token)
        this._toastr.success("Login Successfully")
        this.submitted = true;
        this.router.navigate(['/home/employeeadd/'])
      }
      else{
        this._toastr.error('Internal server','',{
          timeOut:2000
        })
      }

    },
    (error:any)=>{
      this._toastr.error(error.message);
    }
    )
    }
}

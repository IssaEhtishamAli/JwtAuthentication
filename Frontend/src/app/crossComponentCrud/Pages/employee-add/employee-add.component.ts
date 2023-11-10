import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { DeptService } from '../../Services/dept.service';
import { EmpService } from '../../Services/emp.service';

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent implements OnInit {
  submitted = false;
  updateList:boolean=false;
  departments:any[];


  empForm = new FormGroup({
    _id:new FormControl(''),
    emp_name: new FormControl('',Validators.required),
    dept_id: new FormControl('',Validators.required),
    emp_age: new FormControl('',Validators.required),
    emp_email: new FormControl('',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
    emp_contactno: new FormControl('',Validators.required)
  });
  constructor(private empService:EmpService,private _deptService:DeptService,private _toastr:ToastrService) { }

  ngOnInit() {
    this.getDepartments()
  }
  get registerFormControl() {
    return this.empForm.controls;
  }
  onSubmit() {
    this.updateList=false;
    if(this.empForm.invalid){
      alert("Please fill the required fields")
      return
    }
    if(this.empForm.controls._id){
      this.empService.updateEmployee(this.empForm.value).subscribe(()=>{
        this.updateList=true;
        alert("user updated successfully");
      })
    }
    else{
      this.submitted = false;
      this.empService.postEmployee(this.empForm.value).subscribe(()=>{
        this.empForm.reset();
        this.updateList=true;
      });
     
      }
  }
  getDepartments(){
    this._deptService.getAllDepartments().subscribe((res:any)=>{
      this.departments=res;
    },
    (error)=>{
      this._toastr.error(error.message);
    }
    )
  }
  addUser(evnt:any){
    this.empForm.setValue({
      _id:evnt._id,
      emp_name:evnt.emp_name,
      dept_id:evnt.dept_id,
      emp_age:evnt.emp_age,
      emp_email:evnt.emp_email,
      emp_contactno:evnt.emp_contactno
    })
  }
}

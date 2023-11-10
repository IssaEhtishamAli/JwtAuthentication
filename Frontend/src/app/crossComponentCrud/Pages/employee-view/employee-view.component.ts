import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DeptService } from '../../Services/dept.service';
import { EmpService } from '../../Services/emp.service';


@Component({
  selector: 'app-employee-view',
  templateUrl: './employee-view.component.html',
  styleUrls: ['./employee-view.component.css']
})
export class EmployeeViewComponent implements OnInit,OnChanges {
  @Input() update: boolean;
  @Output() onAdd = new EventEmitter();
  empList:any[]=[];
  departments:any=[];
  constructor(private _empService: EmpService,private _deptService:DeptService,private _toastr:ToastrService) { }

  ngOnInit() {
    this.getData();
  }
  getAllDept(){
    this._deptService.getAllDepartments().subscribe((res:any)=>{
      console.log(res);
      this.departments=res;
    },
    (error)=>{
      this._toastr.error(error.message)
    }
    )
  }
  getData(){
    this
      ._empService
      .getEmployees()
      .subscribe((data: any) => {
        this.empList = data;
        console.log(this.empList);
    });
  }
  removeEmp(n:string){
    this._empService.deleteEmployee(n).subscribe(()=>{
      alert("Employee deleted successfully");
      this.getData();
    })
   }

  ngOnChanges(changes: SimpleChanges): void {
      this.getData();
  }
  editEmp(emp:any){
    this.onAdd.emit(emp);
  }
}

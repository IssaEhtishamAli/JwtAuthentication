import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AuthInterceptor } from '../Services/auth.interceptor';
import { DeptService } from '../Services/dept.service';
import { EmpService } from '../Services/emp.service';
import { EmployeeAddComponent } from './employee-add/employee-add.component';
import { EmployeeViewComponent } from './employee-view/employee-view.component';


const routes:Routes=[
    {
        path:'employeeadd',
        component:EmployeeAddComponent
    },
    {
        path:'employeeview',
        component:EmployeeViewComponent
    }
]

@NgModule({
    declarations:[EmployeeAddComponent,EmployeeViewComponent],
    imports:[
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forChild(routes)
    ],
    exports:[],
    providers:[
        EmpService,
        DeptService,
        {
            provide:HTTP_INTERCEPTORS,
            useClass:AuthInterceptor,
            multi:true
        }
    ]
})
export class PagesModule {}
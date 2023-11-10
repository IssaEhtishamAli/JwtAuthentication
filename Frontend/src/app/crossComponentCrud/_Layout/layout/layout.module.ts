import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SidenavComponent } from '../sidenav/sidenav.component';
import { HeaderComponent } from '../header/header.component';
import { LayoutRoutingModule } from './layout-routing.module';



@NgModule({
  declarations: [
    SidenavComponent,
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    LayoutRoutingModule
  ],
})
export class LayoutModule { }

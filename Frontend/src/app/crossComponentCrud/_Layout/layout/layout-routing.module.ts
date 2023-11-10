import {NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { SidenavComponent } from '../sidenav/sidenav.component';
const routes:Routes=[
    {
      path:'sidenav',
      component:SidenavComponent
    },
    {
      path:'header',
      component:HeaderComponent
    }
  ]
  

@NgModule({
    imports: [RouterModule.forChild(routes)],                                                                                                                                                                                                                                                                                                          
    exports: [RouterModule]
})
export class LayoutRoutingModule {}

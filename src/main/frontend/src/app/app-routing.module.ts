import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuardService} from "./service/auth-guard.service";
import {ProductComponent} from "./product/product.component";

const routes: Routes = [
  {path:  "", component: ProductComponent, canActivate: [AuthGuardService]},
  {path:  "**", redirectTo: "" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

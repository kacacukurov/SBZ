import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {AnonymousGuard} from "./core/guards/anonymous.guard";
import {AdminGuard} from "./core/guards/admin.guard";
import {DoktorGuard} from "./core/guards/doktor.guard";

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [AnonymousGuard]},
  { path: 'admin', loadChildren: 'app/admin/admin.module#AdminModule', canActivate: [AdminGuard]},
  { path: 'doktor', loadChildren: 'app/doktor/doktor.module#DoktorModule', canActivate: [DoktorGuard]},
  { path: '**', redirectTo: 'login'}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [RouterModule]
})
export class AppRouterModule { }

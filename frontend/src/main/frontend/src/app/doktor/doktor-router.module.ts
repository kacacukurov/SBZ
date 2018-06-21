import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PacijentiListaComponent} from "./pacijenti-lista/pacijenti-lista.component";

const routes: Routes = [
  { path: '', component:  PacijentiListaComponent},
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class DoktorRouterModule { }

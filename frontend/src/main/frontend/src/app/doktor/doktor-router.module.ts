import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PacijentiListaComponent} from "./pacijenti-lista/pacijenti-lista.component";
import {DijagnozaComponent} from "./dijagnoza/dijagnoza.component";
import {IzvestajiComponent} from "./izvestaji/izvestaji.component";

const routes: Routes = [
  { path: '', component:  PacijentiListaComponent},
  { path: 'izvestaji', component:  IzvestajiComponent},
  { path: 'dijagnoza/:jmbg', component:  DijagnozaComponent
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class DoktorRouterModule { }

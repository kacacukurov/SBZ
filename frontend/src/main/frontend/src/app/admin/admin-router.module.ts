import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
// component
import {KorisnikListaComponent} from "./korisnik-lista/korisnik-lista.component";
import {BolestiListaComponent} from "./bolesti-lista/bolesti-lista.component";
import {LekoviListaComponent} from "./lekovi-lista/lekovi-lista.component";
import {SimptomiListaComponent} from "./simptomi-lista/simptomi-lista.component";

const routes: Routes = [
  { path: '', component: KorisnikListaComponent },
  { path: 'bolesti', component: BolestiListaComponent},
  { path: 'lekovi', component: LekoviListaComponent},
  { path: 'simptomi', component: SimptomiListaComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AdminRouterModule { }

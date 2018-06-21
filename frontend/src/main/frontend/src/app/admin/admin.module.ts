import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KorisnikListaComponent } from './korisnik-lista/korisnik-lista.component';
import { BolestiListaComponent } from './bolesti-lista/bolesti-lista.component';
import { LekoviListaComponent } from './lekovi-lista/lekovi-lista.component';
import {SharedModule} from "../shared/shared.module";
import {AdminRouterModule} from "./admin-router.module";
import {ToasterModule} from "angular5-toaster/dist";
import { KorisnikModalComponent } from './korisnik-modal/korisnik-modal.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AdminRouterModule,
    ToasterModule
  ],
  declarations: [
    KorisnikListaComponent,
    BolestiListaComponent,
    LekoviListaComponent,
    KorisnikModalComponent],
  entryComponents: [
    KorisnikModalComponent
  ]
})
export class AdminModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KorisnikListaComponent } from './korisnik-lista/korisnik-lista.component';
import { BolestiListaComponent } from './bolesti-lista/bolesti-lista.component';
import { LekoviListaComponent } from './lekovi-lista/lekovi-lista.component';
import {SharedModule} from "../shared/shared.module";
import {AdminRouterModule} from "./admin-router.module";
import {ToasterModule} from "angular5-toaster/dist";
import { KorisnikModalComponent } from './korisnik-modal/korisnik-modal.component';
import { LekoviModalComponent } from './lekovi-modal/lekovi-modal.component';
import { BolestiModalComponent } from './bolesti-modal/bolesti-modal.component';
import { SimptomiListaComponent } from './simptomi-lista/simptomi-lista.component';
import { SimptomiModalComponent } from './simptomi-modal/simptomi-modal.component';

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
    KorisnikModalComponent,
    LekoviModalComponent,
    BolestiModalComponent,
    SimptomiListaComponent,
    SimptomiModalComponent],
  entryComponents: [
    KorisnikModalComponent,
    LekoviModalComponent,
    BolestiModalComponent,
    SimptomiModalComponent
  ]
})
export class AdminModule { }

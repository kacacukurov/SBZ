import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DoktorRouterModule} from "./doktor-router.module";
import {SharedModule} from "../shared/shared.module";
import {ToasterModule} from "angular5-toaster/dist";
import { PacijentiListaComponent } from './pacijenti-lista/pacijenti-lista.component';
import { PacijentModalComponent } from './pacijent-modal/pacijent-modal.component';
import { DijagnozaComponent } from './dijagnoza/dijagnoza.component';
import { IzvestajiComponent } from './izvestaji/izvestaji.component';
import { DijagnozaModulComponent } from './dijagnoza-modul/dijagnoza-modul.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    DoktorRouterModule,
    ToasterModule
  ],
  declarations: [
    PacijentiListaComponent,
    PacijentModalComponent,
    DijagnozaComponent,
    IzvestajiComponent,
    DijagnozaModulComponent],
  entryComponents:[
    PacijentModalComponent,
    DijagnozaModulComponent
  ]
})
export class DoktorModule { }

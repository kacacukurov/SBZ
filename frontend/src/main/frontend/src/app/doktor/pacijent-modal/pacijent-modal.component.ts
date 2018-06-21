import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {novaBolestDTO} from "../../shared/models/novaBolestDTO";
import {SimptomService} from "../../core/services/simptom.service";
import {AppError} from '../../shared/errors/app-error';
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {noviPacijentDTO} from "../../shared/models/noviPacijentDTO";
import {LekService} from "../../core/services/lek.service";


@Component({
  selector: 'app-pacijent-modal',
  templateUrl: './pacijent-modal.component.html',
  styleUrls: ['./pacijent-modal.component.css']
})
export class PacijentModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() noviPacijent: noviPacijentDTO;
  @Input() izmena: boolean;

  svi_lekovi: Array<string>;
  svi_sastojci: Array<string>;

  alergija_lekovi: Array<string>;
  alergija_sastojci: Array<string>;

  lek: string;
  sastojak: string;

  pacijentDefinisan: EventEmitter<noviPacijentDTO> = new EventEmitter();

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private lekService: LekService, private simptomService: SimptomService) {
    this.noviPacijent = new noviPacijentDTO;
    this.izmena = false;
    this.dobaviLekove();
    this.dobaviSastojke();
    this.alergija_lekovi = new Array<string>();
    this.alergija_sastojci = new Array<string>();
    this.svi_lekovi = new Array<string>();
  }

  ngOnInit() {
  }

  dobaviLekove(){
    this.lekService.dobaviSveLekove().subscribe(data =>{
      for(let i in data)
        this.svi_lekovi.push(data[i].naziv);
      this.lek = this.svi_lekovi[0];
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Lekovi nisu pronadjeni!');
      else if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  dobaviSastojke(){
    this.lekService.dobaviSveSastojke().subscribe(data =>{
      this.svi_sastojci = data;
      this.sastojak = this.svi_sastojci[0];
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Simptomi nisu pronadjeni!');
      else if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  dodajLek(){
    this.alergija_lekovi.push(this.lek);
  }

  dodajSastojak(){
    this.alergija_sastojci.push(this.sastojak);
  }

  ukloniLek(lek){
    let pos = this.alergija_lekovi.indexOf(lek);
    this.alergija_lekovi.splice(pos, 1);
  }

  ukloniSastojak(sastojak){
    let pos = this.alergija_sastojci.indexOf(sastojak);
    this.alergija_sastojci.splice(pos, 1);
  }

  posaljiPodatke(){
    this.noviPacijent.lekovi_alergija = this.alergija_lekovi;
    this.noviPacijent.sastojci_alergija = this.alergija_sastojci;
    this.pacijentDefinisan.emit(this.noviPacijent);
    this.modalRef.hide();

  }
}

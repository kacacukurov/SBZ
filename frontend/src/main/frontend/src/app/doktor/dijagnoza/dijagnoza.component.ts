import { Component, OnInit } from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {JwtService} from "../../core/services/jwt.service";
import {PacijentService} from "../../core/services/pacijent.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {AppError} from "../../shared/errors/app-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {pacijent} from "../../shared/models/pacijent";
import {dijagnozaDTO} from "../../shared/models/dijagnozaDTO";
import {simptomDTO} from "../../shared/models/simptomDTO";
import {DroolsService} from "../../core/services/drools.service";
import {novaBolestDTO} from "../../shared/models/novaBolestDTO";
import {listaBolestiDTO} from "../../shared/models/listaBolestiDTO";
import {LekService} from "../../core/services/lek.service";
import {noviLekDTO} from "../../shared/models/noviLekDTO";
import {prepisaniLekoviDTO} from "../../shared/models/prepisaniLekoviDTO";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {dijagnozaPoIdDTO} from "../../shared/models/dijagnozaPoIdDTO";
import {DijagnozaModulComponent} from "../dijagnoza-modul/dijagnoza-modul.component";

@Component({
  selector: 'app-dijagnoza',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})
export class DijagnozaComponent implements OnInit {

  private jmbg: string;
  private pac: pacijent;
  private simptomi: string;
  private naziv_bolesti: string;
  private dijagnoza: dijagnozaDTO;
  private uspostavljena_dijagnoza: dijagnozaDTO;
  private bolest_simptomi: novaBolestDTO;
  private lista_bolesti: listaBolestiDTO;
  private lista_simptoma: Array<simptomDTO>;
  private postojeci_lekovi: Array<noviLekDTO>;
  private prepisani_lekovi: prepisaniLekoviDTO;
  private lek: noviLekDTO;
  private prikazi_dijagnozu: dijagnozaPoIdDTO;

  modalRef: BsModalRef;


  constructor(private router: Router, private pacijentService: PacijentService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService, private route: ActivatedRoute,
              private droolsService: DroolsService, private lekService: LekService) {
    this.route.params.subscribe((param: Params) => {
      this.jmbg = param['jmbg'];
      this.dobaviPacijenta();
    });
    this.simptomi = '';
    this.naziv_bolesti = '';
    this.dijagnoza = new dijagnozaDTO;
    this.ucitajLekove();
  }

  ngOnInit() {
  }

  dobaviPacijenta(){
    this.pacijentService.dobaviPacijentaPoJmbg(this.jmbg).subscribe(data =>{
      this.pac = data;
      console.log(this.pac);
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  najverovanija(){
    this.kreirajDijagnozu();
    console.log(this.dijagnoza);
    this.droolsService.najverovatnijaBolest(this.dijagnoza).subscribe(data =>{
      this.dijagnoza = data;
      if(this.dijagnoza.naziv_bolesti != null){
        this.uspostavljena_dijagnoza = this.dijagnoza;
        this.prepisani_lekovi = new prepisaniLekoviDTO;
        this.prepisani_lekovi.lekovi = new Array();
      }
      else {
        this.toasterService.pop('info', 'Info', 'Ne postoje bolesti za ove simptome!');
        this.uspostavljena_dijagnoza = null;
      }
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  licno(){
    this.kreirajDijagnozu();
    this.dijagnoza.naziv_bolesti = this.naziv_bolesti;
    console.log(this.dijagnoza);
    this.droolsService.licnoDijagnoza(this.dijagnoza).subscribe(data =>{
      this.dijagnoza = data;
      if(this.dijagnoza.naziv_bolesti != null) {
        this.uspostavljena_dijagnoza = this.dijagnoza;
        this.prepisani_lekovi = new prepisaniLekoviDTO;
        this.prepisani_lekovi.lekovi = new Array();
      }
      console.log(this.dijagnoza);
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError){
        this.toasterService.pop('info', 'Info', 'Ne postoji unesena bolest!');
        this.uspostavljena_dijagnoza = null;
      }
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  dobaviSimptome(){
    console.log(this.naziv_bolesti);
    this.droolsService.dobaviSimptome(this.naziv_bolesti).subscribe(data =>{
      this.bolest_simptomi = data;
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError) {
        this.bolest_simptomi = null;
        this.toasterService.pop('info', 'Info', 'Ne postoji unesena bolest!');
      }
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  izlistajBolesti(){
    this.kreirajDijagnozu();
    console.log(this.dijagnoza);
    this.droolsService.izlistajBolesti(this.dijagnoza).subscribe(data =>{
      this.lista_bolesti = data;
      if(this.lista_bolesti.bolesti.length == 0){
        this.toasterService.pop('info', 'Info', 'Nema bolesti sa tim simptomima!');
        this.lista_bolesti = null;
      }
      console.log(this.lista_bolesti);
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });

  }

  kreirajDijagnozu(){
    this.dijagnoza = new dijagnozaDTO;
    this.dijagnoza.naziv_bolesti = null;
    this.dijagnoza.jmbg = this.jmbg;
    this.dijagnoza.simptomi = new Array();
    let simptomi_list: Array<string>;
    simptomi_list = this.simptomi.split(',');
    let jedan_sim;
    let novi_simptom : simptomDTO;
    for(let sim of simptomi_list){
      jedan_sim = sim.trim().split(':');
      novi_simptom = new simptomDTO;
      novi_simptom.naziv = jedan_sim[0];
      if(jedan_sim.length == 2)
        novi_simptom.vrednost = jedan_sim[1];
      else
        novi_simptom.vrednost = null;
      this.dijagnoza.simptomi.push(novi_simptom);
    }
  }

  ucitajLekove(){
    this.lekService.dobaviSveLekove().subscribe(data =>{
      this.postojeci_lekovi = data;
      this.lek = this.postojeci_lekovi[0]
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

  dodajLek(){
    this.prepisani_lekovi.lekovi.push(this.lek);
  }


  posaljiLekove(){
    this.prepisani_lekovi.id_dijagnoze = this.uspostavljena_dijagnoza.id;
    this.droolsService.prepisiLekove(this.prepisani_lekovi).subscribe(data =>{
      this.toasterService.pop('success', 'Sacuvani', 'Lekovi uspesno sacuvani!');
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  proveriAlergije(){
    this.droolsService.proveriAlergije(this.uspostavljena_dijagnoza.id.toString()).subscribe(data =>{
      console.log(data);
      if(data.nazivi.length == 0)
        this.toasterService.pop('success', 'OK', 'Pacijent nije alergican!');
      else
        this.toasterService.pop('error', 'Paznja', 'Pacijent je alergican  na lek!');
    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }

  pregledaj(id_dijagnoze){
    this.pacijentService.dobaviDijagnozuPoId(id_dijagnoze).subscribe(data =>{
      this.prikazi_dijagnozu = data;


      this.modalRef = this.modalService.show(
        DijagnozaModulComponent,
        Object.assign({},{ class: 'modal-lg' })
      );

      this.modalRef.content.modalRef = this.modalRef;
      this.modalRef.content.dijagnoza = this.prikazi_dijagnozu;





    },(error: AppError) => {
      if(error instanceof ForbiddenError)
        this.toasterService.pop('error', 'Error', 'Nemate permisiju za ovu akciju!');
      else if(error instanceof BadRequestError)
        this.toasterService.pop('error', 'Error', 'Los zahtev!');
      else {
        this.toasterService.pop('error', 'Error', 'Greska! Pogledajte konzolu!');
        throw error;
      }
    });
  }
}

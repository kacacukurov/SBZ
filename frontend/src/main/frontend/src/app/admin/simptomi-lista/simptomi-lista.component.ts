import {Component, OnInit} from '@angular/core';
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {simptomDTO} from "../../shared/models/simptomDTO";
import {SimptomService} from "../../core/services/simptom.service";
import {SimptomiModalComponent} from "../simptomi-modal/simptomi-modal.component";

@Component({
  selector: 'app-simptomi-lista',
  templateUrl: './simptomi-lista.component.html',
  styleUrls: ['./simptomi-lista.component.css']
})
export class SimptomiListaComponent implements OnInit {

  listaSimptoma: Array<simptomDTO>;
  noviSimptom: simptomDTO;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  constructor(private router: Router, private simptomService: SimptomService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000})
    this.ucitajSimptome();
  }

  ngOnInit() {
  }

  ucitajSimptome(){
    this.simptomService.dobaviSveSimptome().subscribe(data =>{
      this.listaSimptoma = data;
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

  obrisi(simptom: simptomDTO){
    this.simptomService.obrisiSimptom(simptom.naziv).subscribe(data =>{
      this.simptomService.dobaviSveSimptome().subscribe(data =>{
        this.listaSimptoma = data;
      });
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

  dodajLek(){
    this.modalRef = this.modalService.show(
      SimptomiModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;

    this.modalRef.content.simptomDefinisan.subscribe( data => {
      this.noviSimptom = data;
      this.simptomService.kreirajSimptom(this.noviSimptom).subscribe(data =>{
        this.simptomService.dobaviSveSimptome().subscribe(data =>{
          this.listaSimptoma = data;
        });
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
    })
  }

  pregledaj(simptom: simptomDTO){
    this.modalRef = this.modalService.show(
      SimptomiModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.modalRef.content.izmena = true;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.noviSimptom = simptom;
  }

}

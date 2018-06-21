import {Component, OnInit} from '@angular/core';
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {novaBolestDTO} from "../../shared/models/novaBolestDTO";
import {BolestService} from "../../core/services/bolest.service";
import {BolestiModalComponent} from "../bolesti-modal/bolesti-modal.component";

@Component({
  selector: 'app-bolesti-lista',
  templateUrl: './bolesti-lista.component.html',
  styleUrls: ['./bolesti-lista.component.css']
})
export class BolestiListaComponent implements OnInit {

  listaBolesti: Array<novaBolestDTO>;
  novaBolest: novaBolestDTO;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  constructor(private router: Router, private bolestServis: BolestService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000})
    this.ucitajBolesti();
  }

  ngOnInit() {
  }

  ucitajBolesti(){
    this.bolestServis.dobaviSveBolesti().subscribe(data =>{
      console.log(data);
      this.listaBolesti = data;
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

  izmeni(bolest: novaBolestDTO){
    this.modalRef = this.modalService.show(
      BolestiModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.modalRef.content.izmena = true;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.opsti_simptomi = bolest.opsti;
    this.modalRef.content.spec_simptomi = bolest.specificni;
    this.modalRef.content.novaBolest = bolest;


    this.modalRef.content.bolestDefinisana.subscribe( data => {
      this.novaBolest = data;
      this.bolestServis.izmeniBolest(this.novaBolest).subscribe(data =>{
        this.bolestServis.dobaviSveBolesti().subscribe(data =>{
          this.listaBolesti = data;
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

  obrisi(bolest: novaBolestDTO){
    this.bolestServis.obrisiBolest(bolest.naziv).subscribe(data =>{
      this.bolestServis.dobaviSveBolesti().subscribe(data =>{
        this.listaBolesti = data;
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

  dodajBolest(){
    this.modalRef = this.modalService.show(
      BolestiModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.bolestDefinisana.subscribe( data => {
      console.log(data);
      this.novaBolest = data;
      this.bolestServis.kreirajBolest(this.novaBolest).subscribe(data =>{
        this.bolestServis.dobaviSveBolesti().subscribe(data =>{
          this.listaBolesti = data;
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
}

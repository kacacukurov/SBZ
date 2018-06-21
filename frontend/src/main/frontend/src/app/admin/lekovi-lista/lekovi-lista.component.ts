import {Component, OnInit} from '@angular/core';
import {noviLekDTO} from "../../shared/models/noviLekDTO";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {LekService} from "../../core/services/lek.service";
import {JwtService} from "../../core/services/jwt.service";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {LekoviModalComponent} from "../lekovi-modal/lekovi-modal.component";

@Component({
  selector: 'app-lekovi-lista',
  templateUrl: './lekovi-lista.component.html',
  styleUrls: ['./lekovi-lista.component.css']
})
export class LekoviListaComponent implements OnInit {

  listaLekova: Array<noviLekDTO>;
  noviLek: noviLekDTO;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  constructor(private router: Router, private lekService: LekService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000})
    this.ucitajLekove();
  }


  ngOnInit() {
  }

  ucitajLekove(){
    this.lekService.dobaviSveLekove().subscribe(data =>{
      this.listaLekova = data;
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

  izmeni(lek: noviLekDTO){
    this.modalRef = this.modalService.show(
      LekoviModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.modalRef.content.izmena = true;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.noviLek = lek;
    this.modalRef.content.tipLeka = lek.tipLeka;
    var sastojci = "";
    for(let s in lek.sastojci){
      sastojci += lek.sastojci[s] + ", ";
    }
    sastojci = sastojci.substring(0, sastojci.length - 2);
    this.modalRef.content.sastojci_string = sastojci;

    this.modalRef.content.lekDefinisan.subscribe( data => {
      this.noviLek = data;
      this.lekService.izmeniLek(this.noviLek).subscribe(data =>{
        this.lekService.dobaviSveLekove().subscribe(data =>{
          this.listaLekova = data;
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

  obrisi(lek: noviLekDTO){
    this.lekService.obrisiLek(lek.naziv).subscribe(data =>{
      this.lekService.dobaviSveLekove().subscribe(data =>{
        this.listaLekova = data;
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
      LekoviModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.lekDefinisan.subscribe( data => {
      console.log(data);
      this.noviLek = data;
      this.lekService.kreirajLek(this.noviLek).subscribe(data =>{
        this.lekService.dobaviSveLekove().subscribe(data =>{
          this.listaLekova = data;
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

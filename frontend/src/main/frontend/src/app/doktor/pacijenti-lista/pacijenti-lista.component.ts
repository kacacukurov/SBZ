import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {noviPacijentDTO} from "../../shared/models/noviPacijentDTO";
import {PacijentService} from "../../core/services/pacijent.service";
import {PacijentModalComponent} from "../pacijent-modal/pacijent-modal.component";


@Component({
  selector: 'app-pacijenti-lista',
  templateUrl: './pacijenti-lista.component.html',
  styleUrls: ['./pacijenti-lista.component.css']
})
export class PacijentiListaComponent implements OnInit {

  listaPacijenata: Array<noviPacijentDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  noviPacijent: noviPacijentDTO;

  constructor(private router: Router, private pacijentService: PacijentService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.ucitajPacijente();
  }

  ngOnInit() {
  }

  ucitajPacijente(){
    this.pacijentService.dobaviSvePacijente().subscribe(data =>{
      this.listaPacijenata = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Pacijenti nisu pronadjeni!');
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

  izmeni(pacijent: noviPacijentDTO){
    this.modalRef = this.modalService.show(
      PacijentModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.modalRef.content.izmena = true;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.alergija_lekovi = pacijent.lekovi_alergija;
    this.modalRef.content.alergija_sastojci = pacijent.sastojci_alergija;
    this.modalRef.content.noviPacijent = pacijent;


    this.modalRef.content.pacijentDefinisan.subscribe( data => {
      this.noviPacijent = data;
      this.pacijentService.izmeniPacijenta(this.noviPacijent).subscribe(data =>{
        this.pacijentService.dobaviSvePacijente().subscribe(data =>{
          this.listaPacijenata = data;
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

  obrisi(pacijent: noviPacijentDTO){
    this.pacijentService.obrisiPacijenta(pacijent.jmbg).subscribe(data =>{
      this.pacijentService.dobaviSvePacijente().subscribe(data =>{
        this.listaPacijenata = data;
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

  dodajPacijenta(){
    this.modalRef = this.modalService.show(
      PacijentModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.pacijentDefinisan.subscribe( data => {
      console.log(data);
      this.noviPacijent = data;
      this.pacijentService.kreirajPacijenta(this.noviPacijent).subscribe(data =>{
        this.pacijentService.dobaviSvePacijente().subscribe(data =>{
          this.listaPacijenata = data;
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

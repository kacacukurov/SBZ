import {Component, OnInit} from '@angular/core';
import {accountDTO} from "../../shared/models/accountDTO";
import {KorisnikService} from "../../core/services/korisnik.service";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {AppError} from "../../shared/errors/app-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {KorisnikModalComponent} from "../korisnik-modal/korisnik-modal.component";
import {registracijaDTO} from "../../shared/models/registracijaDTO";
import {Login} from '../../shared/models/login';

@Component({
  selector: 'app-korisnik-lista',
  templateUrl: './korisnik-lista.component.html',
  styleUrls: ['./korisnik-lista.component.css']
})
export class KorisnikListaComponent implements OnInit {

  listaAdmina: Array<accountDTO>;
  listaDoktora: Array<accountDTO>;

  toasterConfig : ToasterConfig;
  modalRef: BsModalRef;

  registracijaNovog: registracijaDTO;

  constructor(private router: Router, private korisnikService: KorisnikService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    this.ucitajAdmine();
    this.ucitajDoktore();
  }

  ngOnInit() {
  }

  ucitajAdmine(){
    this.korisnikService.dobaviSveAdmine().subscribe(data =>{
      this.listaAdmina = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Admini nisu pronadjeni!');
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

  ucitajDoktore(){
    this.korisnikService.dobaviSveLekare().subscribe(data =>{
      this.listaDoktora = data;
    },(error: AppError) => {
      if(error instanceof NotFoundError)
        this.toasterService.pop('error', 'Error', 'Doktori nisu pronadjeni!');
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

  izmeni(account: accountDTO, admin: boolean){
    this.modalRef = this.modalService.show(
      KorisnikModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );

    this.registracijaNovog = new registracijaDTO;
    this.registracijaNovog.loginAccount = new Login(account.username, '');
    this.registracijaNovog.ime = account.ime;
    this.registracijaNovog.prezime = account.prezime;

    this.modalRef.content.izmena = true;
    this.modalRef.content.registracija = this.registracijaNovog;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.korisnikPostavljen.subscribe( data => {
      this.registracijaNovog = data;
      this.korisnikService.izmeniNalog(this.registracijaNovog).subscribe(data =>{
        if(admin){
          this.korisnikService.dobaviSveAdmine().subscribe(data =>{
            this.listaAdmina = data;
          });
        }else{
          this.korisnikService.dobaviSveLekare().subscribe(data =>{
            this.listaDoktora = data;
          });
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
    })
  }

  obrisi(account: accountDTO, admin: boolean){
    this.korisnikService.obrisiKorisnika(account.username).subscribe(data =>{
      if(admin){
        this.korisnikService.dobaviSveAdmine().subscribe(data =>{
          this.listaAdmina = data;
        });
      }else{
        this.korisnikService.dobaviSveLekare().subscribe(data =>{
          this.listaDoktora = data;
        });
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

  dodajAdmina(){
    this.modalRef = this.modalService.show(
      KorisnikModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.korisnikPostavljen.subscribe( data => {
      this.registracijaNovog = data;
      this.korisnikService.kreirajAdmina(this.registracijaNovog).subscribe(data =>{
        this.korisnikService.dobaviSveAdmine().subscribe(data =>{
          this.listaAdmina = data;
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

  dodajDoktora(){
    this.modalRef = this.modalService.show(
      KorisnikModalComponent,
      Object.assign({},{ class: 'modal-lg' })
    );
    this.modalRef.content.izmena = false;
    this.modalRef.content.modalRef = this.modalRef;
    this.modalRef.content.korisnikPostavljen.subscribe( data => {
      this.registracijaNovog = data;
      this.korisnikService.kreirajDoktora(this.registracijaNovog).subscribe(data =>{
        this.korisnikService.dobaviSveLekare().subscribe(data =>{
          this.listaDoktora = data;
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

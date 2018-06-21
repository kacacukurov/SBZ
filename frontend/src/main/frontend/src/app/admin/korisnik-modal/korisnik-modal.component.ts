import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {registracijaDTO} from "../../shared/models/registracijaDTO";
import {ToasterService} from "angular5-toaster/dist";
import {KorisnikService} from "../../core/services/korisnik.service";
import {Login} from "../../shared/models/login";

@Component({
  selector: 'app-korisnik-modal',
  templateUrl: './korisnik-modal.component.html',
  styleUrls: ['./korisnik-modal.component.css']
})
export class KorisnikModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() registracija: registracijaDTO;
  @Input() admin: boolean;
  @Input() izmena: boolean;


  loginDto: Login;

  korisnikPostavljen: EventEmitter<registracijaDTO> = new EventEmitter();

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private korisnikService: KorisnikService) {
    this.registracija = new registracijaDTO;
    this.loginDto = new Login('', '');
    this.registracija.loginAccount = this.loginDto;
    this.registracija.ime = '';
    this.registracija.prezime = '';
    this.izmena = false;
  }

  ngOnInit() {
  }

  posaljiPodatke(){
    this.korisnikPostavljen.emit(this.registracija);
    this.modalRef.hide();

  }
}

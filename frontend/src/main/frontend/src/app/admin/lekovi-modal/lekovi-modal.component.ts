import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {KorisnikService} from "../../core/services/korisnik.service";
import {noviLekDTO} from "../../shared/models/noviLekDTO";


@Component({
  selector: 'app-lekovi-modal',
  templateUrl: './lekovi-modal.component.html',
  styleUrls: ['./lekovi-modal.component.css']
})
export class LekoviModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() noviLek: noviLekDTO;
  @Input() izmena: boolean;

  sastojci: Array<string>;
  sastojci_string: string;
  tipLeka: string;
  tipovi: Array<string>;

  lekDefinisan: EventEmitter<noviLekDTO> = new EventEmitter();

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private korisnikService: KorisnikService) {
    this.noviLek = new noviLekDTO;
    this.noviLek.naziv = '';
    this.izmena = false;
    this.tipLeka = "ANTIBIOTIK";
    this.tipovi = ["ANTIBIOTIK", "ANELGETIK", "OSTALO"];
  }

  ngOnInit() {
  }

  posaljiPodatke(){
    this.sastojci = this.sastojci_string.split(",");
    this.sastojci.map(Function.prototype.call, String.prototype.trim);
    this.noviLek.tipLeka = this.tipLeka;
    this.noviLek.sastojci = this.sastojci;
    this.lekDefinisan.emit(this.noviLek);
    this.modalRef.hide();

  }

}

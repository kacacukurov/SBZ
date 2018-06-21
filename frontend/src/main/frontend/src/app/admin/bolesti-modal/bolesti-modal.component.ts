import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {novaBolestDTO} from "../../shared/models/novaBolestDTO";
import {BolestService} from "../../core/services/bolest.service";
import {simptomDTO} from "../../shared/models/simptomDTO";
import {SimptomService} from "../../core/services/simptom.service";
import {AppError} from '../../shared/errors/app-error';
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {BadRequestError} from "../../shared/errors/bad-request-error";

@Component({
  selector: 'app-bolesti-modal',
  templateUrl: './bolesti-modal.component.html',
  styleUrls: ['./bolesti-modal.component.css']
})
export class BolestiModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() novaBolest: novaBolestDTO;
  @Input() izmena: boolean;

  svi_simptomi: Array<simptomDTO>;
  opsti_simptomi: Array<simptomDTO>;
  spec_simptomi: Array<simptomDTO>;

  opsti: simptomDTO;
  spec: simptomDTO;

  bolestDefinisana: EventEmitter<novaBolestDTO> = new EventEmitter();

  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private bolestService: BolestService, private simptomService: SimptomService) {
    this.novaBolest = new novaBolestDTO;
    this.novaBolest.naziv = '';
    this.izmena = false;
    this.dobaviSimptome();
    this.opsti_simptomi = new Array<simptomDTO>();
    this.spec_simptomi = new Array<simptomDTO>();
  }

  ngOnInit() {
  }

  dobaviSimptome(){
    this.simptomService.dobaviSveSimptome().subscribe(data =>{
      this.svi_simptomi = data;
      this.opsti = this.svi_simptomi[0];
      this.spec = this.svi_simptomi[0];
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

  posaljiPodatke(){
    this.novaBolest.opsti = this.opsti_simptomi;
    this.novaBolest.specificni = this.spec_simptomi;
    this.bolestDefinisana.emit(this.novaBolest);
    this.modalRef.hide();

  }

  dodajOpsti(){
    this.opsti_simptomi.push(this.opsti);
  }

  dodajSpec(){
    this.spec_simptomi.push(this.spec);
  }

  ukloniOpsti(simptom){
    let pos = this.opsti_simptomi.indexOf(simptom);
    this.opsti_simptomi.splice(pos, 1);
  }

  ukloniSpec(simptom){
    let pos = this.spec_simptomi.indexOf(simptom);
    this.spec_simptomi.splice(pos, 1);
  }

}

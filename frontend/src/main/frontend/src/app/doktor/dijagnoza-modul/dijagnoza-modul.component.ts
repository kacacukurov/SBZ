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
import {dijagnozaPoIdDTO} from "../../shared/models/dijagnozaPoIdDTO";


@Component({
  selector: 'app-dijagnoza-modul',
  templateUrl: './dijagnoza-modul.component.html',
  styleUrls: ['./dijagnoza-modul.component.css']
})
export class DijagnozaModulComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() dijagnoza: dijagnozaPoIdDTO;


  constructor(private modalService : BsModalService,  private toasterService: ToasterService,
              private lekService: LekService, private simptomService: SimptomService) {
    this.dijagnoza = new dijagnozaPoIdDTO;
  }

  ngOnInit() {
  }

}

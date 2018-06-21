import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ToasterService} from "angular5-toaster/dist";
import {simptomDTO} from "../../shared/models/simptomDTO";

@Component({
  selector: 'app-simptomi-modal',
  templateUrl: './simptomi-modal.component.html',
  styleUrls: ['./simptomi-modal.component.css']
})
export class SimptomiModalComponent implements OnInit {

  modalRef: BsModalRef;
  @Input() noviSimptom: simptomDTO;
  @Input() izmena: boolean;

  simptomDefinisan: EventEmitter<simptomDTO> = new EventEmitter();

  constructor(private modalService : BsModalService,  private toasterService: ToasterService) {
    this.izmena = false;
    this.noviSimptom = new simptomDTO;
  }

  ngOnInit() {
  }

  posaljiPodatke(){
    this.simptomDefinisan.emit(this.noviSimptom);
    this.modalRef.hide();

  }

}

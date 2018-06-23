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
import {DroolsService} from "../../core/services/drools.service";
import {spisakPacijenataDTO} from "../../shared/models/spisakPacijenataDTO";



@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.css']
})
export class IzvestajiComponent implements OnInit {


  toasterConfig : ToasterConfig;
  private spisak: spisakPacijenataDTO;


  constructor(private router: Router, private droolsService: DroolsService, private jwtService: JwtService,
              private toasterService: ToasterService,private modalService : BsModalService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});

  }

  ngOnInit() {
  }

  hronicna(){
    this.droolsService.pacijentiHronicno().subscribe(data =>{
      console.log(data.pacijenti);
      this.spisak = new spisakPacijenataDTO;
      this.spisak.pacijenti = new Array();
      this.spisak.pacijenti = data.pacijenti; //dsa
      this.spisak = data;
      console.log(this.spisak.pacijenti)
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

  zavisnici(){
    this.droolsService.pacijetniZavisnici().subscribe(data =>{
      this.spisak = data;
      if(this.spisak.pacijenti.length == 0)
        this.toasterService.pop('info', 'Info', 'Ne postoje pacijenti koji zadovoljavaju uslov!');
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

  imunitet(){
    this.droolsService.pacijentiSlabImunitet().subscribe(data =>{
      this.spisak = data;
      if(this.spisak.pacijenti.length == 0)
        this.toasterService.pop('info', 'Info', 'Ne postoje pacijenti koji zadovoljavaju uslov!');
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

}

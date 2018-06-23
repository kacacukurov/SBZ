import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {novaBolestDTO} from "../../shared/models/novaBolestDTO";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {dijagnozaDTO} from "../../shared/models/dijagnozaDTO";
import {listaBolestiDTO} from "../../shared/models/listaBolestiDTO";
import {prepisaniLekoviDTO} from "../../shared/models/prepisaniLekoviDTO";
import {spisakAlergijaDTO} from "../../shared/models/spisakAlergijaDTO";
import {spisakPacijenataDTO} from "../../shared/models/spisakPacijenataDTO";
@Injectable()
export class DroolsService {
  private readonly urlBase = '/api/drools';

  constructor(private http: HttpClient) { }

  najverovatnijaBolest(dijagnoza: dijagnozaDTO): Observable<dijagnozaDTO> {
    return this.http.post<dijagnozaDTO>(`${this.urlBase}/najverovatnijaBolest`, dijagnoza).catch(this.handleErrors);
  }

  licnoDijagnoza(dijagnoza: dijagnozaDTO): Observable<dijagnozaDTO> {
    return this.http.post<dijagnozaDTO>(`${this.urlBase}/licnoDijagnoza`, dijagnoza).catch(this.handleErrors);
  }

  dobaviSimptome(naziv_bolesti: string): Observable<novaBolestDTO> {
    return this.http.get<novaBolestDTO>(`${this.urlBase}/dobaviSimptome/?naziv_bolesti=${naziv_bolesti}`)
      .catch(this.handleErrors); //alkdj
  }

  izlistajBolesti(dijagnoza: dijagnozaDTO): Observable<listaBolestiDTO> {
    return this.http.post<listaBolestiDTO>(`${this.urlBase}/izlistajBolesti`, dijagnoza).catch(this.handleErrors);
  }

  prepisiLekove(prepisani_lekovi: prepisaniLekoviDTO): Observable<void> {
    return this.http.post<void>(`${this.urlBase}/prepisiLekove`, prepisani_lekovi).catch(this.handleErrors);
  }

  proveriAlergije(id_dijagnoze: string): Observable<spisakAlergijaDTO> {
    return this.http.get<spisakAlergijaDTO>(`${this.urlBase}/proveriAlergije/?id_dijagnoze=${id_dijagnoze}`)
      .catch(this.handleErrors);
  }

  pacijentiHronicno(): Observable<spisakPacijenataDTO> {
    return this.http.get<spisakPacijenataDTO>(`${this.urlBase}/pacijentiHronicno`)
      .catch(this.handleErrors);
  }

  pacijetniZavisnici(): Observable<spisakPacijenataDTO> {
    return this.http.get<spisakPacijenataDTO>(`${this.urlBase}/pacijetniZavisnici`)
      .catch(this.handleErrors);
  }

  pacijentiSlabImunitet(): Observable<spisakPacijenataDTO> {
    return this.http.get<spisakPacijenataDTO>(`${this.urlBase}/pacijentiSlabImunitet`)
      .catch(this.handleErrors);
  }


  protected handleErrors(response: Response) {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 403)
      return Observable.throw(new ForbiddenError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    else if(response.status === 409)
      return Observable.throw(new ConflictError());
    return Observable.throw(new AppError(response));
  }

}

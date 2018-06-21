import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {registracijaDTO} from "../../shared/models/registracijaDTO";
import {accountDTO} from "../../shared/models/accountDTO";

@Injectable()
export class KorisnikService {
  private readonly urlBase = '/api/korisnik';

  constructor(private http: HttpClient) { }

  kreirajAdmina(registracija: registracijaDTO): Observable<void> {
    return this.http.post<registracijaDTO>(`${this.urlBase}/admin`, registracija).catch(this.handleErrors);
  }

  kreirajDoktora(registracija: registracijaDTO): Observable<void> {
    return this.http.post<registracijaDTO>(`${this.urlBase}/doktor`, registracija).catch(this.handleErrors);
  }

  izmeniNalog(registracija: registracijaDTO): Observable<void> {
    return this.http.put<registracijaDTO>(`${this.urlBase}/izmeniNalog`, registracija).catch(this.handleErrors);
  }

  dobaviSveAdmine(): Observable<Array<accountDTO>> {
    return this.http.get<Array<accountDTO>>(`${this.urlBase}/svi/?uloga=ADMIN`).catch(this.handleErrors);
  }

  dobaviSveLekare(): Observable<Array<accountDTO>> {
    return this.http.get<Array<accountDTO>>(`${this.urlBase}/svi/?uloga=DOKTOR`).catch(this.handleErrors);
  }

  obrisiKorisnika(username: string): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/?username=${username}`).catch(this.handleErrors);
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

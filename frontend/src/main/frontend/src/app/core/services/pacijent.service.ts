import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {noviPacijentDTO} from "../../shared/models/noviPacijentDTO";
import {pacijent} from "../../shared/models/pacijent";

@Injectable()
export class PacijentService<T> {
  private readonly urlBase = '/api/pacijent';

  constructor(private http: HttpClient) { }

  kreirajPacijenta(pacijent: noviPacijentDTO): Observable<T> {
    return this.http.post<T>(`${this.urlBase}/kreiraj`, pacijent).catch(this.handleErrors);
  }

  dobaviSvePacijente(): Observable<Array<T>> {
    return this.http.get<Array<T>>(`${this.urlBase}/svi`).catch(this.handleErrors);
  }

  obrisiPacijenta(jmbg: string): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/${jmbg}`).catch(this.handleErrors);
  }

  izmeniPacijenta(pacijent: noviPacijentDTO): Observable<T> {
    return this.http.put<T>(`${this.urlBase}/izmeni`, pacijent).catch(this.handleErrors);
  }

  dobaviPacijentaPoJmbg(jmbg: string): Observable<T> {
    return this.http.get<Array<T>>(`${this.urlBase}/jmbg/${jmbg}`).catch(this.handleErrors);
  }

  dodajAlergijePacijentu(p: pacijent): Observable<T> {
    return this.http.put<T>(`${this.urlBase}/alergije`, p).catch(this.handleErrors);
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

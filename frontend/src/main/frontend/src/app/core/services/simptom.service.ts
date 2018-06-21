import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {simptomDTO} from "../../shared/models/simptomDTO";

@Injectable()
export class SimptomService<T> {
  private readonly urlBase = '/api/simptomi';

  constructor(private http: HttpClient) { }

  kreirajSimptom(simptom: simptomDTO): Observable<T> {
    return this.http.post<T>(`${this.urlBase}/kreiraj`, simptom).catch(this.handleErrors);
  }

  dobaviSveSimptome(): Observable<Array<T>> {
    return this.http.get<Array<T>>(`${this.urlBase}/svi`).catch(this.handleErrors);
  }

  obrisiSimptom(naziv: string): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/${naziv}`).catch(this.handleErrors);
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

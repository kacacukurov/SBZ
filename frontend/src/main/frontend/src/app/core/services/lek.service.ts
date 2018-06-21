import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {noviLekDTO} from "../../shared/models/noviLekDTO";

@Injectable()
export class LekService {
  private readonly urlBase = '/api/lek';

  constructor(private http: HttpClient) { }

  kreirajLek(lek: noviLekDTO): Observable<noviLekDTO> {
    return this.http.post<noviLekDTO>(`${this.urlBase}/kreiraj`, lek).catch(this.handleErrors);
  }

  dobaviSveLekove(): Observable<Array<noviLekDTO>> {
    return this.http.get<Array<noviLekDTO>>(`${this.urlBase}/svi`).catch(this.handleErrors);
  }

  obrisiLek(naziv: string): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/?naziv=${naziv}`).catch(this.handleErrors);
  }

  izmeniLek(lek: noviLekDTO): Observable<void> {
    return this.http.put<noviLekDTO>(`${this.urlBase}/izmeni`, lek).catch(this.handleErrors);
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

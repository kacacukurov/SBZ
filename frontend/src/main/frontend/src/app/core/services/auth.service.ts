import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
// models
import { Login } from "../../shared/models/login";
import { JwtToken } from "../../shared/models/jwt-token";
// errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
// service
import 'rxjs/add/operator/map'
import { JwtService } from "./jwt.service";


@Injectable()
export class AuthService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  login(credentials: Login): Observable<boolean> {
    return this.http.post(`${this.urlBase}/korisnik/login`, credentials)
      .map((token: JwtToken) => {
        if(token.value) {
          this.jwtService.setToken(token.value);
          return true;
        }
        else return false;
      }).catch(this.handleErrors);
  }

  logout() {
    this.jwtService.removeToken();
  }

  checkUsername(username: string): Observable<boolean> {
    let params: HttpParams = new HttpParams()
      .append('username', username);
    return this.http.get<boolean>(`${this.urlBase}/check_username`, {params})
      .catch(this.handleErrors);
  }

  currentUser(): Observable<any> {
    return this.http.get<any>(`${this.urlBase}/current_user`)
      .catch(this.handleErrors);
  }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    return Observable.throw(new AppError(response));
  }
}

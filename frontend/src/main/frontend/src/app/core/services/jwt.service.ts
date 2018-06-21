import { Injectable } from '@angular/core';

// model

import {JwtHelper} from "angular2-jwt";
import {Token} from "../../shared/models/toekn";


@Injectable()
export class JwtService {
  private jwtHelper: JwtHelper;

  constructor() {
    this.jwtHelper = new JwtHelper();
  }

  tokenExist(): boolean {
    return !!(localStorage.getItem('Authentication-Token'));
  }

  isTokenExpired(): boolean {
    return this.jwtHelper.isTokenExpired(this.getToken());
  }

  hasRole(role: string) {
    return (this.getRolesFromToken().indexOf(role) != -1);
  }

  getRolesFromToken(): Array<string> {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.roles;
  }

  getUsernameFromToken(): string {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.sub;
  }

  getApartmentIdFromToken(): number {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.apartmentID;
  }

  getBuildingIdFromToken(): number {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.buildingID;
  }

  getIdFromToken(): number {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.id;
  }


  getToken(): string {
    return localStorage.getItem('Authentication-Token');
  }

  setToken(token: string): void {
    localStorage.setItem('Authentication-Token', token);
  }

  removeToken(): void {
    localStorage.removeItem('Authentication-Token');
  }

  private decodeToken(): Token {
    return this.jwtHelper.decodeToken(this.getToken());
  }
}

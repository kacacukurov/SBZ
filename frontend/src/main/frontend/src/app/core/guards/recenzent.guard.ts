import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {JwtService} from "../services/jwt.service";

@Injectable()
export class RecenzentGuard implements CanActivate {
  constructor(private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.jwtService.tokenExist() && this.jwtService.hasRole('Recenzent');
  }
}

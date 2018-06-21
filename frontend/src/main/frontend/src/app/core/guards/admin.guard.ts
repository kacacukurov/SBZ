import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {JwtService} from "../services/jwt.service";

@Injectable()
export class AdminGuard implements CanActivate {
  constructor(private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.jwtService.tokenExist() && this.jwtService.hasRole('ADMIN');
  }
}

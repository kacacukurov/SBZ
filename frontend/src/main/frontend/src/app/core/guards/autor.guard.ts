import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from "../services/jwt.service";

@Injectable()
export class AutorGuard implements CanActivate {

  constructor(private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.jwtService.tokenExist() && this.jwtService.hasRole('Autor');
  }
}

import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from "../services/jwt.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(this.jwtService.tokenExist() && !this.jwtService.isTokenExpired())
      return true;

    // not logged in so redirect to login page with the return url
    this.router.navigate(['login'], { queryParams: { returnUrl: state.url }});
    return false;
  }
}

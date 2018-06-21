import { Component, OnInit } from '@angular/core';
import {JwtService} from "../core/services/jwt.service";
import {AuthService} from "../core/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public jwtService :JwtService, public authService: AuthService, public router : Router) { }

  ngOnInit() {
  }


  logout()
  {
    this.authService.logout();
    this.router.navigate(['login']);
  }
}

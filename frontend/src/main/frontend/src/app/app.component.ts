import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {JwtService} from "./core/services/jwt.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private router: Router, private titleService: Title, private jwtService: JwtService){
    this.titleService.setTitle('SBZ projekat');
  }
}

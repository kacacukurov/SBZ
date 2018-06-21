import {Component, OnInit} from '@angular/core';
import {Validators, FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import 'rxjs/add/operator/catch';
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
// error
import {AppError} from "../shared/errors/app-error";
import {BadRequestError} from "../shared/errors/bad-request-error";
import {NotFoundError} from "../shared/errors/not-found-error";
// model
import {Login} from "../shared/models/login";
// service
import {AuthService} from "../core/services/auth.service";
import {JwtService} from "../core/services/jwt.service";
import {ForbiddenError} from "../shared/errors/forbidden-error";

// validator


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  returnURL: string = '';
  toasterConfig: ToasterConfig;

  constructor(private fb: FormBuilder, private authService: AuthService,
              private router: Router, private route: ActivatedRoute, private toasterService: ToasterService,
              private jwt: JwtService) {
    this.form = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required]
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});

  }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get username() {
    return this.form.get('username');
  }

  get password() {
    return this.form.get('password');
  }

  login() {
    let login = new Login(this.username.value, this.password.value);
    this.authService.login(login)
      .subscribe((successfullyLoggedIn) => {
        console.log(this.jwt.getRolesFromToken());
        if(successfullyLoggedIn){
          if(this.jwt.hasRole("ADMIN"))
            this.router.navigate(['admin']);
          else if(this.jwt.hasRole("DOKTOR"))
            this.router.navigate(['doktor']);
        }
        else
          this.toasterService.pop('error', 'Error', 'Invalid login');
      }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid format of given data!');
        else if (error instanceof NotFoundError)
          this.toasterService.pop('error', 'Error', 'Bad credentials!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
      });
  }
}

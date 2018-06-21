import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NavbarComponent} from './navbar/navbar.component';
import {SharedModule} from "./shared/shared.module";
import {CoreModule} from "./core/core.module";
import {AppRouterModule} from "./app-router.module";
import {AppErrorHandler} from "./core/error-handlers/app-error-handler";
import {LoginComponent} from "./login/login.component";
import {AuthService} from "./core/services/auth.service";
import {JwtService} from "./core/services/jwt.service";
import {ToasterModule, ToasterService} from "angular5-toaster/dist";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {JwtInterceptor} from "./core/interceptors/jwt-interceptor";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    SharedModule,
    AppRouterModule,
    ToasterModule,
    BrowserAnimationsModule
  ],
  providers: [{
    provide: ErrorHandler,
    useClass: AppErrorHandler
  },
    AuthService,
    JwtService,
    ToasterService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {SafeHtmlPipe} from './pipe/safe-html.pipe';
import {ModalModule} from "ngx-bootstrap";



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ModalModule.forRoot()
    ],
  declarations: [SafeHtmlPipe],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SafeHtmlPipe
  ],
})
export class SharedModule { }

import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AnonymousGuard} from "./guards/anonymous.guard";
import {AuthGuard} from "./guards/auth.guard";
import {RecenzentGuard} from "./guards/recenzent.guard";
import {UrednikGuard} from "./guards/urednik.guard";
import {AutorGuard} from "./guards/autor.guard";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    AnonymousGuard,
    AuthGuard,
    RecenzentGuard,
    UrednikGuard,
    AutorGuard,
  ]
})
export class CoreModule {
  constructor (@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}

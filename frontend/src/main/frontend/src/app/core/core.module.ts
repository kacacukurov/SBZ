import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AnonymousGuard} from "./guards/anonymous.guard";
import {AuthGuard} from "./guards/auth.guard";
import {AdminGuard} from "./guards/admin.guard";
import {DoktorGuard} from "./guards/doktor.guard";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    AnonymousGuard,
    AuthGuard,
    AdminGuard,
    DoktorGuard
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

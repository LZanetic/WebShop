import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { ProizvodiComponent } from './proizvodi/proizvodi.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { ProizvodDetailComponent } from './proizvod-detail/proizvod-detail.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { LoginComponent } from './login/login.component';
import { AuthExpiredInterceptor } from './interceptors/auth-expired.inteceptor';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { KorisniciComponent } from './korisnici/korisnici.component';
import { MainComponent } from './main/main.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    ProizvodiComponent,
    NavbarComponent,
    ProizvodDetailComponent,
    PageNotFoundComponent,
    ForbiddenPageComponent,
    LoginComponent,
    KorisniciComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    TranslateModule.forRoot(
      {
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        },
        defaultLanguage: 'hr'
      }
    )
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

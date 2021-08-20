import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProizvodiComponent } from './proizvodi/proizvodi.component';
import { RouterModule, Routes } from '@angular/router';
import { ProizvodDetailComponent } from './proizvod-detail/proizvod-detail.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { AdminAuthGuardService } from './guards/admin-auth-guard.service';
import { KorisniciComponent } from './korisnici/korisnici.component';
import { MainComponent } from './main/main.component';


const routes: Routes= [
  {
    path: 'main',
    component: MainComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'users',
    component: KorisniciComponent,
    canActivate: [AdminAuthGuardService]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'proizvodi',
    component: ProizvodiComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'detail/:naslov',
    component: ProizvodDetailComponent
  },
  {
    path: 'forbidden',
    component: ForbiddenPageComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  },
  {
    path: '',
    redirectTo: 'main',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

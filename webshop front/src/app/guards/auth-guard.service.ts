import { Injectable } from '@angular/core';
import { KorisnikService } from '../korisnik/korisnik.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private router: Router,
    private userService: KorisnikService,
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.checkLogin();
  }

  checkLogin(): Observable<boolean> {
    return this.userService.getCurrentUser().pipe(
      map(user => {
        if (user) {
          return true;
        } else {
          this.router.navigate(['/login']);
          return false;
        }
      })
    );
  }
}


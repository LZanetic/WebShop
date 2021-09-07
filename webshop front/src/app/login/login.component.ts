import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { UserCredentials } from './user-credentials.model';
import { Router } from '@angular/router';
import { JwtToken } from './jwt-token.model';
import { Korisnik } from '../korisnik/korisnik';
import { KorisnikService } from '../korisnik/korisnik.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  authenticating = false;
  loginFailed = false; 

  userCredentials: UserCredentials;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private userService: KorisnikService
  ) {
  }

  ngOnInit(): void {
    this.userCredentials = new UserCredentials();
  }


  login() {
    this.authenticating = true;
    this.loginFailed = false;

    this.loginService.authenticate(this.userCredentials).subscribe(
      (jwtToken: JwtToken) => this.successfulLogin(jwtToken),
      () => this.loginFailed = true
    ).add(() => this.authenticating = false);
  }

  successfulLogin(jwtToken: JwtToken) {
    localStorage.setItem('token', jwtToken.token); // store token value to localstorage
    this.userService.getCurrentUser().subscribe((currentUser: Korisnik) => this.userService.currentUser = currentUser);
    this.router.navigate(['/main']);
  }

}

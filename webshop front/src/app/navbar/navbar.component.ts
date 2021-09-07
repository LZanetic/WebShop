import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import{ TranslateService } from "@ngx-translate/core";
import { LanguageEnum } from "../constants/language.enum";
import { LoginService } from '../login/login.service';
import { KorisnikService } from '../korisnik/korisnik.service';
import { Korisnik } from '../korisnik/korisnik';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {

  isNavbarCollapsed: boolean = false;
  currentLanguage: string | undefined;

  constructor(
    private router: Router,
    private translateService: TranslateService,
    private loginService: LoginService,
    public userService: KorisnikService
  ) { }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe((currentUser: Korisnik)=> {
      this.userService.currentUser = currentUser;
    })


    this.setCurrentLanguageDropdownValue();
  }

  setCurrentLanguageDropdownValue() {
    if (this.translateService.currentLang === LanguageEnum.HR) {
      this.translateService.get('language.croatian').subscribe(language => this.currentLanguage = language);
    } else if (this.translateService.currentLang === LanguageEnum.EN) {
      this.translateService.get('language.english').subscribe(language => this.currentLanguage = language);
    } else {
      throw Error('Unknown current language!');
    }
  }

  onLanguageChange(newLanguage: string) {
    this.translateService.use(newLanguage).subscribe(
      languageSwitched => this.setCurrentLanguageDropdownValue()
    );
  }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  isUserLoggedIn(): boolean {
    return !!this.userService.currentUser;
  }

  logout() {
    this.loginService.logout();
    this.userService.currentUser = null;
    this.router.navigate(['/login']);
  }

}

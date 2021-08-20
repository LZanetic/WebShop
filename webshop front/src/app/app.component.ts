import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Web Shop';

  public constructor(private titleService: Title, translate: TranslateService) {
    this.titleService.setTitle('Web Shop');

    translate.setDefaultLang('hr');
    translate.use('hr');
  }
}

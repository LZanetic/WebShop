import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent implements OnInit {
  title = 'Web Shop';

  constructor() { }

  ngOnInit(): void {
  }

}

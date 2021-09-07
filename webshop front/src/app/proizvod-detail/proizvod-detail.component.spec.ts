import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProizvodDetailComponent } from './proizvod-detail.component';

describe('ProizvodDetailComponent', () => {
  let component: ProizvodDetailComponent;
  let fixture: ComponentFixture<ProizvodDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProizvodDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProizvodDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

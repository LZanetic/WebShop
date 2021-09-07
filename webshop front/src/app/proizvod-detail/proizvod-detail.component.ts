import { Component, Input, OnInit } from '@angular/core';
import { Proizvod } from '../proizvod';
import { ProizvodService } from '../proizvod.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { KorisnikService } from '../korisnik/korisnik.service';

@Component({
  selector: 'app-proizvod-detail',
  templateUrl: './proizvod-detail.component.html',
  styleUrls: ['./proizvod-detail.component.css']
})
export class ProizvodDetailComponent implements OnInit {

  @Input() proizvod?: Proizvod;
  proizvodi? : Proizvod[];

  constructor(
    private route: ActivatedRoute,
    private proizvodService: ProizvodService,
    private location: Location,
    public userService: KorisnikService
  ) { }

  ngOnInit(): void {
    this.getProizvod();
  }

  getProizvod(): void {
    const naslov = this.route.snapshot.paramMap.get('naslov');

    if (naslov !== null) {
      this.proizvodService.getProizvodByNaslov(naslov)
        .subscribe(proizvod => {
          this.proizvod = proizvod;
        });
    } else {
      console.error('naslov can not be null!');
    }
  }

  updateProizvod(id: number, korisnikId: number ,naslov: string, opis: string, cijena: number, kategorija: string, stanje: string): void{
    naslov = naslov.trim();
    opis = opis.trim();
    stanje = stanje.trim();
    kategorija = kategorija.trim();

    if(!naslov || !opis || !cijena){
      return;
    }

    this.proizvodService.updateProizvod({id, korisnikId, naslov, opis, cijena, kategorija, stanje} as Proizvod)
      .subscribe(
        proizvod => {
          this.proizvodi?.push(proizvod);
        }
      );
  }

  save(): void {
    if (this.proizvod !== undefined) {
      this.proizvodService.updateProizvod(this.proizvod)
      .subscribe(() => this.goBack());
    } else {
      console.error('proizvod can not be undefined!')
    }
  }

  goBack(): void{
    this.location.back();
  }

}

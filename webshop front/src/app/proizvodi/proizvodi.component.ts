import { Component, OnInit } from '@angular/core';
import { Proizvod } from '../proizvod';
import { ProizvodService } from '../proizvod.service';
import { KorisnikService } from '../korisnik/korisnik.service';


@Component({
  selector: 'app-proizvodi',
  templateUrl: './proizvodi.component.html',
  styleUrls: ['./proizvodi.component.css']
})
export class ProizvodiComponent implements OnInit {

  proizvodi? : Proizvod[];
  proizvod? : Proizvod;
  

  constructor(
    private proizvodService: ProizvodService,
    public userService: KorisnikService,
  ) { }

  ngOnInit(): void {
    this.getProizvodi();
  }

  getProizvodi(): void{
    this.proizvodService.getProizvodi()
      .subscribe(proizvodi => this.proizvodi = proizvodi);
  }


  add( korisnikId: number, naslov: string, opis: string, cijena: number, kategorija: string, stanje: string): void{
    naslov = naslov.trim();
    opis = opis.trim();
    stanje = stanje.trim();
    kategorija = kategorija.trim();

    if(!naslov || !opis || !cijena){
      return;
    }

    this.proizvodService.addProizvod({ korisnikId, naslov, opis, cijena, kategorija, stanje} as Proizvod)
      .subscribe(
        proizvod => {
          this.proizvodi?.push(proizvod);
        }
      );
  }

  delete(proizvod: Proizvod): void{
    this.proizvodi = this.proizvodi?.filter(p => p !==proizvod);
    this.proizvodService.deleteProizvod(proizvod).subscribe();
  }

}

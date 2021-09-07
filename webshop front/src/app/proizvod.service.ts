import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Proizvod } from './proizvod';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProizvodService {
  private proizvodUrl = 'http://localhost:8800/proizvod';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(
    private http: HttpClient
  ) { }

  getProizvodi(): Observable<Proizvod[]> {
    return this.http.get<Proizvod[]>(this.proizvodUrl)
      .pipe(
        tap(_=> console.log('fetched proizvodi')),
        catchError(this.handleError<Proizvod[]>('getProizvodi',[]))
      );
  }

  getProizvodById(id: number): Observable<Proizvod> {
    const url = `${this.proizvodUrl}/${id}`;
    return this.http.get<Proizvod>(url)
      .pipe(
        tap(_=> console.log(`fetched proizvod id =${id}`)),
        catchError(this.handleError<Proizvod>(`getProizvod id=${id}`))
     );
  }

  getProizvodByNaslov(naslov: string):Observable<Proizvod> {
    const url = `${this.proizvodUrl}/${naslov}`;
    return this.http.get<Proizvod>(url)
      .pipe(
        tap(_=> console.log(`fetched proizvod naslov =${naslov}`)),
        catchError(this.handleError<Proizvod>(`getProizvod naslov=${naslov}`))
      );
  }

  getProizvodByKorisnikId(KorisnikId: number): Observable<Proizvod> {
    const url = `${this.proizvodUrl}/${KorisnikId}`;
    return this.http.get<Proizvod>(url)
      .pipe(
        tap(_=> console.log(`fetched proizvod KorisnikId =${KorisnikId}`)),
        catchError(this.handleError<Proizvod>(`getProizvod id=${KorisnikId}`))
      );
  }

  updateProizvod(proizvod: Proizvod): Observable<any> {
    const url = `${this.proizvodUrl}/${proizvod.id}`;
    return this.http.put(url, proizvod, this.httpOptions)
      .pipe(
        tap(_=> console.log(`updated proizvod id= ${proizvod.id}`)),
        catchError(this.handleError<any>('updateProizvod'))
      );
  }

  addProizvod(proizvod: Proizvod): Observable<Proizvod> {
    return this.http.post<Proizvod>(this.proizvodUrl, proizvod, this.httpOptions)
      .pipe(
        tap((newProizvod: Proizvod) => console.log(`added proizvod w naslov=${newProizvod.naslov}`)),
        catchError(this.handleError<Proizvod>('addProizvod'))
      );
  }

  deleteProizvod(proizvod: Proizvod | string): Observable<Proizvod> {
    const naslov = typeof proizvod === 'string' ? proizvod : proizvod.naslov;
    const url = `${this.proizvodUrl}/${naslov}`;

    return this.http.delete<Proizvod>(url, this.httpOptions)
      .pipe(
        tap(_=> console.log(`deleted proizvod naslov = ${naslov}`)),
        catchError(this.handleError<Proizvod>('deleteProizvod'))
      )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
}
}

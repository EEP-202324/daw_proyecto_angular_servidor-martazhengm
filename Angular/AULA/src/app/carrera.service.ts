import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Carrera } from './carrera';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class CarreraService {
  private carrerasUrl = 'api/carreras';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET carreras from the server */
  getCarreras(): Observable<Carrera[]> {
    return this.http.get<Carrera[]>(this.carrerasUrl)
      .pipe(
        tap(_ => this.log('fetched carreras')),
        catchError(this.handleError<Carrera[]>('getCarreras', []))
      );
  }

  /** GET carrera by id. Return `undefined` when id not found */
  getHeroNo404<Data>(id: number): Observable<Carrera> {
    const url = `${this.carrerasUrl}/?id=${id}`;
    return this.http.get<Carrera[]>(url)
      .pipe(
        map(carreras => carreras[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} carrera id=${id}`);
        }),
        catchError(this.handleError<Carrera>(`getCarrera id=${id}`))
      );
  }

  /** GET carrera by id. Will 404 if id not found */
  getCarrera(id: number): Observable<Carrera> {
    const url = `${this.carrerasUrl}/${id}`;
    return this.http.get<Carrera>(url).pipe(
      tap(_ => this.log(`fetched carrera id=${id}`)),
      catchError(this.handleError<Carrera>(`getCarrera id=${id}`))
    );
  }

  /* GET carreras whose name contains search term */
  searchCarreras(term: string): Observable<Carrera[]> {
    if (!term.trim()) {
      // if not search term, return empty carrera array.
      return of([]);
    }
    return this.http.get<Carrera[]>(`${this.carrerasUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found carreras matching "${term}"`) :
        this.log(`no carreras matching "${term}"`)),
      catchError(this.handleError<Carrera[]>('searchCarreras', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new carrera to the server */
  addCarrera(carrera: Carrera): Observable<Carrera> {
    return this.http.post<Carrera>(this.carrerasUrl, carrera, this.httpOptions).pipe(
      tap((newCarrera: Carrera) => this.log(`added carrera w/ id=${newCarrera.id}`)),
      catchError(this.handleError<Carrera>('addCarrera'))
    );
  }

  /** DELETE: delete the carrera from the server */
  deleteCarrera(id: number): Observable<Carrera> {
    const url = `${this.carrerasUrl}/${id}`;

    return this.http.delete<Carrera>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted carrera id=${id}`)),
      catchError(this.handleError<Carrera>('deleteCarrera'))
    );
  }

  /** PUT: update the carrera on the server */
  updateCarrera(carrera: Carrera): Observable<any> {
    return this.http.put(this.carrerasUrl, carrera, this.httpOptions).pipe(
      tap(_ => this.log(`updated carrera id=${carrera.id}`)),
      catchError(this.handleError<any>('updateCarrera'))
    );
  }

    /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: string) {
    this.messageService.add(`CarreraService: ${message}`);
  }
}

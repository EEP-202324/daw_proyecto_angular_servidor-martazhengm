import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Carrera } from '../carrera';
import { CarreraService } from '../carrera.service';

@Component({
  selector: 'app-carrera-search',
  templateUrl: './carrera-search.component.html',
  styleUrls: [ './carrera-search.component.css' ]
})
export class CarreraSearchComponent implements OnInit {
  carreras$!: Observable<Carrera[]>;
  private searchTerms = new Subject<string>();

  constructor(private carreraService: CarreraService) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.carreras$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.carreraService.searchCarreras(term)),
);
  }
}

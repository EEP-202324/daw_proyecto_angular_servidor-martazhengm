import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Carrera } from './carrera';
import { CARRERAS } from './mock-carreras';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class CarreraService {

  constructor(private messageService: MessageService) { }
  getCarreras(): Observable<Carrera[]> {
    const carreras = of(CARRERAS);
    this.messageService.add('CarreraService: fetched carreras');
    return carreras;
  }

  getCarrera(id: number): Observable<Carrera> {
    // For now, assume that a hero with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.
    const carrera = CARRERAS.find(h => h.id === id)!;
    this.messageService.add(`CarreraService: fetched carrera id=${id}`);
    return of(carrera);
  }
}



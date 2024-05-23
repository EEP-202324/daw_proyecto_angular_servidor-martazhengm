import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Carrera } from './carrera';
import { CARRERAS } from './mock-carreras';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class CarreraService {

  constructor(private messageService: MessageService) { }
  getCarreras(): Observable<Carrera[]> {
    const carreras = of(CARRERAS);
    this.messageService.add('CarreraService: fetched carreras');
  return carreras;
  }
}

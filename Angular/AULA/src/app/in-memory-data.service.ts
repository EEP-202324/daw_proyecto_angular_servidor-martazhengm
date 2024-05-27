import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Carrera } from './carrera';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const carreras = [
      { id: 1, nombre: 'Psicología', rama: 'Ciencias de la Salud', duracion: '4 años', precio: '6120€ (anual)' },
      { id: 2, nombre: 'ADE', rama: 'Ciencias Sociales y Jurídicas', duracion: '4 años', precio: '6120€ (anual)' },
      { id: 3, nombre: 'Derecho', rama: 'Ciencias Sociales y Jurídicas', duracion: '4 años', precio: '6120€ (anual)' },
      { id: 4, nombre: 'Derecho + ADE', rama: 'Ciencias Sociales y Jurídicas', duracion: '6 años', precio: '7038€ (anual)' },
      { id: 5, nombre: 'Máster acceso al Ejercicio de la Abogacia y la Procura', rama: 'Ciencias Sociales y Jurídicas', duracion: '18 meses', precio: '7000€ (pago único)' },
      { id: 6, nombre: 'Máster Oficial en Psicología General Sanitaria', rama: 'Ciencias de la Salud', duracion: '2 años', precio: '8969€ (anual)' }
    ];
    return {carreras};
  }

  genId(carreras: Carrera[]): number {
    return carreras.length > 0 ? Math.max(...carreras.map(carrera => carrera.id)) + 1 : 11;
  }
}



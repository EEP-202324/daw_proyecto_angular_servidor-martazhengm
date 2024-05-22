import { Component } from '@angular/core';
import { Carrera } from '../carrera';

@Component({
  selector: 'app-carreras',
  templateUrl: './carreras.component.html',
  styleUrl: './carreras.component.css'
})
export class CarrerasComponent {
  carrera: Carrera = {
    id: 1,
  nombre: 'Psicología',
  rama: 'Ciencias de la salud',
  duracion: '4 años',
  precio: '6120€',
  };
}

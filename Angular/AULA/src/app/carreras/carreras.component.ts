import { Component } from '@angular/core';
import { Carrera } from '../carrera';
import { CARRERAS } from '../mock-carreras';
import { NgFor} from '@angular/common';

@Component({
  selector: 'app-carreras',
  templateUrl: './carreras.component.html',
  styleUrl: './carreras.component.css'
})
export class CarrerasComponent {
  carreras = CARRERAS;
  selectedCarrera?: Carrera;
onSelect(carrera: Carrera): void {
  this.selectedCarrera = carrera;
}
}

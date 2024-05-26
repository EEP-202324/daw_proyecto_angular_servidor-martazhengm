import { Component, OnInit } from '@angular/core';

import { Carrera } from '../carrera';
import { CarreraService } from '../carrera.service';

@Component({
  selector: 'app-carreras',
  templateUrl: './carreras.component.html',
  styleUrl: './carreras.component.css'
})
export class CarrerasComponent implements OnInit {
  carreras: Carrera[] = [];
  constructor(private carreraService: CarreraService) { }

  ngOnInit(): void {
  this.getCarreras();
  }

  getCarreras(): void {
    this.carreraService.getCarreras()
      .subscribe(carreras => this.carreras = carreras);
  }

  add(nombre: string): void {
    nombre = nombre.trim();
    if (!nombre) { return; }
    this.carreraService.addCarrera({ nombre } as Carrera)
      .subscribe(carrera => {
        this.carreras.push(carrera);
      });
  }

  delete(carrera: Carrera): void {
  this.carreras = this.carreras.filter(h => h !== carrera);
  this.carreraService.deleteCarrera(carrera.id).subscribe();
}
}

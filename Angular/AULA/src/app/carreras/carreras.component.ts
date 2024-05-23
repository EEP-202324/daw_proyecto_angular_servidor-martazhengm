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
}

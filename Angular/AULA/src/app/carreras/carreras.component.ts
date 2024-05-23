import { Component, OnInit } from '@angular/core';

import { Carrera } from '../carrera';
import { CarreraService } from '../carrera.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-carreras',
  templateUrl: './carreras.component.html',
  styleUrl: './carreras.component.css'
})
export class CarrerasComponent implements OnInit {
  selectedCarrera?: Carrera;
  carreras: Carrera[] = [];
  constructor(private carreraService: CarreraService, private messageService: MessageService) { }

  ngOnInit(): void {
  this.getCarreras();
  }
  onSelect(carrera: Carrera): void {
    this.selectedCarrera = carrera;
    this.messageService.add(`CarrerasComponent: Selected carrera id=${carrera.id}`);
  }

  getCarreras(): void {
    this.carreraService.getCarreras()
      .subscribe(carreras => this.carreras = carreras);
  }
}

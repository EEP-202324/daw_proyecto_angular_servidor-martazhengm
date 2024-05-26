import { Component, OnInit } from '@angular/core';
import { Carrera } from '../carrera';
import { CarreraService } from '../carrera.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  carreras: Carrera[] = [];

  constructor(private carreraService: CarreraService) { }

  ngOnInit(): void {
    this.getCarreras();
  }

  getCarreras(): void {
    this.carreraService.getCarreras()
      .subscribe(carreras => this.carreras = carreras.slice(1, 5));
  }
}

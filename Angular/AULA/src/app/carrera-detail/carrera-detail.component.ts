import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Carrera } from '../carrera';
import { CarreraService } from '../carrera.service';

@Component({
  selector: 'app-carrera-detail',
  templateUrl: './carrera-detail.component.html',
  styleUrls: [ './carrera-detail.component.css' ]
})
export class CarreraDetailComponent implements OnInit {
  carrera: Carrera | undefined;

  constructor(
    private route: ActivatedRoute,
    private carreraService: CarreraService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getCarrera();
  }

  getCarrera(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.carreraService.getCarrera(id)
      .subscribe(carrera => this.carrera = carrera);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
  if (this.carrera) {
    this.carreraService.updateCarrera(this.carrera)
      .subscribe(() => this.goBack());
  }
}
}

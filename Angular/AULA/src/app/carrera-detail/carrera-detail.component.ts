import {Component, Input} from '@angular/core';
import { Carrera } from '../carrera';

@Component({
  selector: 'app-carrera-detail',
  templateUrl: './carrera-detail.component.html',
  styleUrl: './carrera-detail.component.css'
})
export class CarreraDetailComponent {
  @Input() carrera?: Carrera;
}

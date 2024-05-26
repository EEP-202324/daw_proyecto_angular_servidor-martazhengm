import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarreraSearchComponent } from './carrera-search.component';

describe('CarreraSearchComponent', () => {
  let component: CarreraSearchComponent;
  let fixture: ComponentFixture<CarreraSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarreraSearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarreraSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

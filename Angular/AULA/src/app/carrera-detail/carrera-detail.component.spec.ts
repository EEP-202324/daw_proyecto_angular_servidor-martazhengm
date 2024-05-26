import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarreraDetailComponent } from './carrera-detail.component';

describe('CarreraDetailComponent', () => {
  let component: CarreraDetailComponent;
  let fixture: ComponentFixture<CarreraDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarreraDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarreraDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendreVehiculeComponent } from './vendre-vehicule.component';

describe('VendreVehiculeComponent', () => {
  let component: VendreVehiculeComponent;
  let fixture: ComponentFixture<VendreVehiculeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VendreVehiculeComponent]
    });
    fixture = TestBed.createComponent(VendreVehiculeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

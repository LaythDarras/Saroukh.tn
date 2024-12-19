import { Component, OnInit } from '@angular/core';
import { trigger, transition, style, animate } from '@angular/animations';
import { VehiclesService, Vehicle } from '../services/vehicles.service';

@Component({
  selector: 'app-vehicules',
  templateUrl: './vehicules.component.html',
  styleUrls: ['./vehicules.component.scss'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [ // Animation à l'entrée
        style({ opacity: 0, transform: 'translateY(-20px)' }),
        animate('500ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
      ]),
      transition(':leave', [ // Animation à la sortie
        animate('500ms ease-in', style({ opacity: 0, transform: 'translateY(-20px)' }))
      ])
    ])
  ]
})
export class VehiculesComponent implements OnInit {
  public selectedType: string = 'All'; // Filtrage par type
  vehicles: Vehicle[] = []; // Liste des véhicules
  public newVehicle: Vehicle = {
    price: 0,
    image_url: '',
    type: '',
    brand: '',
    model: '',
    year: new Date().getFullYear(),
    category: '',
    created_at: Number(new Date().toISOString()),
  }; // Modèle pour un nouveau véhicule

  constructor(private vehiclesService: VehiclesService) {}

  ngOnInit(): void {
    this.loadVehicles(); // Charger les véhicules lors de l'initialisation
  }

  /**
   * Charger les véhicules depuis l'API
   */
  loadVehicles(): void {
    this.vehiclesService.getVehicles().subscribe({
      next: (data) => {
        this.vehicles = data;
      },
      error: (err) => console.error('Erreur lors du chargement des véhicules:', err),
    });
  }

  /**
   * Retourne les véhicules filtrés par type
   */
  get filteredVehicles(): Vehicle[] {
    if (this.selectedType === 'All') {
      return this.vehicles;
    }
    return this.vehicles.filter(vehicle => vehicle.type === this.selectedType);
  }
}

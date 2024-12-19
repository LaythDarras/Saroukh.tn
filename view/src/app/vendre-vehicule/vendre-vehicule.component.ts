import { Component } from '@angular/core';
import { VehiclesService, Vehicle } from '../services/vehicles.service';

@Component({
  selector: 'app-vendre-vehicule',
  templateUrl: './vendre-vehicule.component.html',
  styleUrls: ['./vendre-vehicule.component.scss']
})
export class VendreVehiculeComponent {
  message: string = 'correct';

  vehicle: Vehicle = {
    price: 0,
    image_url: '',
    type: '',
    brand: '',
    model: '',
    year: new Date().getFullYear(),
    category: '',
    created_at: new Date().getFullYear(),
  };

  errorMessage: string = '';
  successMessage: string = '';

  constructor(private vehiclesService: VehiclesService) {}

  async addVehicle(): Promise<void> {
    this.successMessage = '';
    this.errorMessage = '';
    this.message = 'En cours de traitement...';

    // Vérification de l'URL de l'image
    if (!this.vehicle.image_url.startsWith('http://') && !this.vehicle.image_url.startsWith('https://')) {
      this.errorMessage = 'L\'URL de l\'image est invalide.';
      this.message = '';
      return;
    }

    try {
      const isValidImage = await this.validateImage(this.vehicle.image_url);
      if (!isValidImage) {
        this.errorMessage = 'L\'image ne semble pas être valide.';
        this.message = '';
        return;
      }

      
      this.vehiclesService.imgIsValid(this.vehicle.image_url, this.vehicle.type).subscribe({
        next: (response) => {
          if (response?.isvalid === "true") {
            this.successMessage = 'Image validée : ' + response.message;
            this.submitVehicle();
          } else {
            this.errorMessage = 'Validation échouée : ' + response.message;
            this.message = '';
          }
        },
        error: (error) => {
          this.errorMessage = 'Erreur de validation : ' + (error.error?.message || 'Inconnue');
          this.message = '';
        },
      });
    } catch (error) {
      this.errorMessage = 'Erreur inattendue lors de la validation.';
      this.message = '';
    }
  }

  private submitVehicle(): void {
    this.vehiclesService.addVehicle(this.vehicle).subscribe({
      next: () => {
        this.successMessage = 'Véhicule ajouté avec succès!';
        this.message = this.successMessage;
      },
      error: (error) => {
        this.errorMessage = 'Erreur lors de l\'ajout du véhicule : ' + (error.error?.message || 'Inconnue');
        this.message = '';
      },
    });
  }

  private validateImage(imageUrl: string): Promise<boolean> {
    return new Promise((resolve) => {
      const img = new Image();
      img.onload = () => resolve(true);
      img.onerror = () => resolve(false);
      img.src = imageUrl;
    });
  }
}

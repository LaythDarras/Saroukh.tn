import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Vehicle {
  price: number;
  image_url: string;
  type: string;
  brand: string;
  model: string;
  year: number;
  category: string;
  created_at: number;
}

@Injectable({
  providedIn: 'root',
})
export class VehiclesService {
  private apiUrl = 'http://localhost:5000'; // URL de votre API Flask

  constructor(private http: HttpClient) {}

  // Ajouter un véhicule
  addVehicle(vehicle: Vehicle): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter_vehicle`, vehicle);
  }
  getVehicles():Observable<any>{
    return this.http.get(`${this.apiUrl}/vehicles`);

  }
  imgIsValid(imageUrl: string, category: string): Observable<any> {

    const params = {
      image_path: imageUrl,
      category: category
    };
  
    return this.http.get(`${this.apiUrl}/detect`, { params });
  }
 
}

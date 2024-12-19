import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VendreVehiculeComponent } from './vendre-vehicule/vendre-vehicule.component';
import { VehiculesComponent } from './vehicules/vehicules.component';

const routes: Routes = [
  { path: '', component: VehiculesComponent },
   { path: 'vendre-vehicule', component: VendreVehiculeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

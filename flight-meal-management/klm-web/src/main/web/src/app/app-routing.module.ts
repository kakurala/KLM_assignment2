import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddFlightComponent } from './add-flight/add-flight.component';
import { AddMealsComponent } from './add-meals/add-meals.component';
import { DeleteFlightComponent } from './delete-flight/delete-flight.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: LandingPageComponent },
  { path: 'flight', component: AddFlightComponent },
  { path: 'meals', component: AddMealsComponent },
  { path: 'removeflight', component: DeleteFlightComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Injectable } from '@angular/core';
import { LoadingService } from './loading.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FlightsService {

  constructor(
    private http: HttpClient,
    private loadingService: LoadingService
  ) { }

  public addFlight(flight: object) {
    return this.http.post(`${environment.apiURL}/addflight`, flight);
  }

  public addMeals(mealsObject: object){

    const body = {
      meals: [
        mealsObject['businessMeal'],
        mealsObject['economyMeal']
      ]
    };

    const path = mealsObject['flightNumber'] +'/'+ mealsObject['flightDepartureDate'];

    return this.http.post(`${environment.apiURL}/addmeals/${path}`, body);
  }

  public deleteFlight(flight: object){

    const path = flight['flightNumber'] +'/'+ flight['flightDepartureDate'];

    return this.http.delete(`${environment.apiURL}/removeflight/${path}`);
  }
}

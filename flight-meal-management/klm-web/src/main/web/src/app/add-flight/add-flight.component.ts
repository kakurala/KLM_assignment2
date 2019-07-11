import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { FlightsService } from '../service/flights.service';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.scss']
})
export class AddFlightComponent implements OnInit {

  flightsFrom: FormGroup;
  submitted = false;
  response = {};

  constructor(
    private formBuilder: FormBuilder,
    private flightsService: FlightsService
    ) { }

  ngOnInit() {
      this.flightsFrom = this.formBuilder.group({
        flightNumber: ['', Validators.required],
        flightDepartureDate: ['', Validators.required]
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.flightsFrom.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.flightsFrom.invalid) {
          return;
      }

      this.flightsService.addFlight(this.flightsFrom.value).subscribe(response => {
        this.response = response;
      });
  }
}

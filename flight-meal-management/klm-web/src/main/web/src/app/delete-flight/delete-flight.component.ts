import { Component, OnInit } from '@angular/core';
import { FlightsService } from '../service/flights.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-delete-flight',
  templateUrl: './delete-flight.component.html',
  styleUrls: ['./delete-flight.component.scss']
})
export class DeleteFlightComponent implements OnInit {

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

      this.flightsService.deleteFlight(this.flightsFrom.value).subscribe(response => {
        this.response = response;
      });
  }

}

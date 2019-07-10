import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { DISABLED } from '@angular/forms/src/model';
import { FlightsService } from '../service/flights.service';

@Component({
  selector: 'app-add-meals',
  templateUrl: './add-meals.component.html',
  styleUrls: ['./add-meals.component.scss']
})
export class AddMealsComponent implements OnInit {

  mealsForm: FormGroup;
  submitted = false;
  response = {};

  constructor(
    private formBuilder: FormBuilder,
    private flightsService: FlightsService
    ) { }

  ngOnInit() {

    this.mealsForm = this.formBuilder.group({
      flightNumber: ['', Validators.required],
      flightDepartureDate: ['', Validators.required],
      businessMeal: this.formBuilder.group({
        mealClass: ['BUSINESS'],
        breakfast: [0, Validators.required],
        lightSnack: [0, [Validators.required]],
        lunch: [0, [Validators.required]],
        dinner: [0, Validators.required]
      }),
      economyMeal: this.formBuilder.group({
        mealClass: ['ECONOMY'],
        breakfast: [0, Validators.required],
        lightSnack: [0, [Validators.required]],
        lunch: [0, [Validators.required]],
        dinner: [0, Validators.required]
      })
    });

// console.log(this.mealsForm.controls.businessMeal.controls);
  }

  // convenience getter for easy access to form fields
  get f() { return this.mealsForm.controls; }

  get b() { return this.mealsForm.controls.businessMeal['controls']; }

  get e() { return this.mealsForm.controls.economyMeal['controls']; }


  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.mealsForm.invalid) {
        console.log('ERROR!! :-)\n\n' + JSON.stringify(this.mealsForm.value))
          return;
      }

      this.flightsService.addMeals(this.mealsForm.value).subscribe(resp => {
        this.response = resp;
        console.log(resp);
      });
  }
}
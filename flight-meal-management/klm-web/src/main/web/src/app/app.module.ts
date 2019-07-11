import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { NgxLoadingModule } from 'ngx-loading';
import { AddFlightComponent } from './add-flight/add-flight.component';
import { AddMealsComponent } from './add-meals/add-meals.component';
import { DeleteFlightComponent } from './delete-flight/delete-flight.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    AddFlightComponent,
    AddMealsComponent,
    DeleteFlightComponent,
    LandingPageComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TypeaheadModule.forRoot(),
    NgxLoadingModule.forRoot({}),
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

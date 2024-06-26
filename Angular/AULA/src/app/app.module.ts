import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CarreraDetailComponent } from './carrera-detail/carrera-detail.component';
import { CarrerasComponent } from './carreras/carreras.component';
import { CarreraSearchComponent } from './carrera-search/carrera-search.component';
import { MessagesComponent } from './messages/messages.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    CarrerasComponent,
    CarreraDetailComponent,
    MessagesComponent,
    CarreraSearchComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }

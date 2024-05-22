import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarrerasComponent } from './carreras/carreras.component';
import { CarreraDetailComponent } from './carrera-detail/carrera-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    CarrerasComponent,
    CarreraDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

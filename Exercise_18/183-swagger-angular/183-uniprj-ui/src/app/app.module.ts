import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CourseControllerService } from './services/api/courseController.service';
import { StudentControllerService } from './services/api/studentController.service';
import { OthersControllerService } from './services/api/othersController.service';
import { StudentComponent } from './student/student.component';
import { BASE_PATH, Configuration } from './services';
import { API_BASE_PATH } from 'src/environments/environment';
import { KeycloakService } from './services/KeycloakService';


@NgModule({
  declarations: [
    AppComponent,
      StudentComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    //{provide: BASE_PATH, useValue: API_BASE_PATH},
    {
      provide: Configuration,
      useFactory: (authService: Configuration) => new Configuration({ basePath: API_BASE_PATH }),
      multi: false
    },


    CourseControllerService, StudentControllerService, OthersControllerService, KeycloakService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

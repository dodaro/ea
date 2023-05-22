import { Component, OnInit } from '@angular/core';
import { Configuration, StudentControllerService } from '../services';
import { KeycloakService } from '../services/KeycloakService';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: any[] = [];

  token: any;

  constructor(private studentControllerService: StudentControllerService,
    private keycloakService: KeycloakService) { }


  ngOnInit() {

  //  this.studentControllerService.all().subscribe(data => { console.log(data);  this.students = data});

  }

  getToken(): void {
    this.keycloakService.generateToken('globaladmin', 'password').subscribe(
      (response) => {
        this.token = response;
        console.log('Token:', response);
      },
      (error) => {
        console.error('Error generating token:', error);
      }
    );
  }

}

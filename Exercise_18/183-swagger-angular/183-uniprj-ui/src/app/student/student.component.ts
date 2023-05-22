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

  constructor(private studentControllerService: StudentControllerService,
    private keycloakService: KeycloakService) { }


  ngOnInit() {
    this.keycloakService.generateToken('globaladmin', 'password').subscribe(
      (response) => {
        console.log('Token:', response);
      },
      (error) => {
        console.error('Error generating token:', error);
      }
    );
  //  this.studentControllerService.all().subscribe(data => { console.log(data);  this.students = data});

  }

}

import { Component, OnInit } from '@angular/core';
import { Configuration, StudentControllerService } from '../services';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: any[] = [];

  constructor(private studentControllerService: StudentControllerService) { }


  ngOnInit() {
    this.studentControllerService.all().subscribe(data => { console.log(data);  this.students = data});
  }

}

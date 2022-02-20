import { Component, OnInit } from '@angular/core';
import {Student} from "../service/student";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../service/student-service.service";

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss']
})
export class StudentFormComponent implements OnInit {

  public student = new Student(null, "", 0);

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param["id"] === "new") {
        this.student = new Student(null, "", 0);
      }
      this.studentService.findById(param["id"])
        .subscribe(student => {
          this.student = student;
        }, error => {
          console.log(error);
        })
    })
  }

}

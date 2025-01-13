import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../sevices/register.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registerprovider',
  templateUrl: './registerprovider.component.html',
  styleUrls: ['./registerprovider.component.css']
})
export class RegisterproviderComponent implements OnInit {
  form:FormGroup
  submitted = false;


  constructor(private Service:RegisterService,
    private formbuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.form=this.formbuilder.group({
      username:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required],
      matricule:['',Validators.required],
      service:['',Validators.required],
      company:['',Validators.required],
  })
  }

  registerprovider(){
    this.Service.signupprovider(this.form.value).subscribe((res:any)=>{

      console.log("register  :",res);
      Swal.fire("register successes")

    })
   }

   get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }
    this.Service.signupprovider(this.form.value).subscribe((res:any)=>{

      console.log("register  :",res);
      Swal.fire("register successes")

    })
    console.log(JSON.stringify(this.form.value, null, 2));
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}




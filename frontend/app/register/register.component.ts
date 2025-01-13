import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../sevices/register.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form:FormGroup

  constructor(private Service:RegisterService,
    private formbuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.form=this.formbuilder.group({
      username:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required],
      adress:['',Validators.required],
      city:['',Validators.required],
  })
  }

  registercustomer(){
    this.Service.signupcustomer(this.form.value).subscribe((res:any)=>{

      console.log("register  :",res);
      Swal.fire("register successes")
      

    })
   }



}

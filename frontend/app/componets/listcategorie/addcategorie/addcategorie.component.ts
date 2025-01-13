import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategorieService } from 'src/app/sevices/categorie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-addcategorie',
  templateUrl: './addcategorie.component.html',
  styleUrls: ['./addcategorie.component.css']
})
export class AddcategorieComponent implements OnInit {
  form:FormGroup



  constructor(private Service:CategorieService,
    private formbuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.form=this.formbuilder.group({
      description:['',Validators.required],
      title:['',Validators.required],

  })
  }
  ajoutercategorie(){
    this.Service.addcategorie(this.form.value).subscribe((res:any)=>{

      console.log("Categorie ajouter :",res);
      Swal.fire("Categorie added successfully")
      this.router.navigateByUrl("/listCategorie")

    })
   }

}

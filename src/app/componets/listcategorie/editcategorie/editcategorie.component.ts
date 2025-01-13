import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from 'src/app/sevices/categorie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editcategorie',
  templateUrl: './editcategorie.component.html',
  styleUrls: ['./editcategorie.component.css']
})
export class EditcategorieComponent implements OnInit {

  onecategorie:any
  form:FormGroup
  id=this.activerouter.snapshot.params["id"]
  constructor(private Service:CategorieService,
    private activerouter:ActivatedRoute,
    private formbuilder:FormBuilder, private router:Router ) { }

  ngOnInit(): void {
   this.categorieone(),
   this.form=this.formbuilder.group({
    description:['',Validators.required],
    title:['',Validators.required],
   })

  }
  categorieone(){
    this.Service.getcategorie(this.id).subscribe((res:any)=>{
      this.onecategorie= res
      this.form.patchValue({
        description:this.onecategorie.description,
        title:this.onecategorie.title,
      })
      console.log("categorie details :",this.onecategorie);
    })
  }


 modfiercategorie(){
  this.Service.editcategorie(this.id,this.form.value).subscribe((res:any)=>{

    console.log("Categorie modifier :",res);
    Swal.fire("Categorie updated successfully")
    this.router.navigateByUrl("/listCategorie")

  })
 }


}

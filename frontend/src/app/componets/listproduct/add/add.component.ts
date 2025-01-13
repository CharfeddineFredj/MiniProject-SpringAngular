import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/sevices/product.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  form:FormGroup
  selectedFile: File;

  constructor(private Service:ProductService,
    private formbuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.form=this.formbuilder.group({
      name:['',Validators.required],
      description:['',Validators.required],
      price:['',Validators.required],
      qte:['',Validators.required],
  })
  }

  ajouterproduct(){
    const formData=new FormData()
    formData.append("name",this.form.value.name)
    formData.append("description",this.form.value.description)
    formData.append("qte",this.form.value.qte)
    formData.append("price",this.form.value.price)
    formData.append("file",this.selectedFile)
    this.Service.addproduct(formData).subscribe((res:any)=>{

      console.log("Product ajouter :",res);
      Swal.fire("product ajoute avec succes")
      this.router.navigateByUrl("/listProduct")

    })
   }
   onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files) {
      this.selectedFile = inputElement.files[0];
    }
  }

}

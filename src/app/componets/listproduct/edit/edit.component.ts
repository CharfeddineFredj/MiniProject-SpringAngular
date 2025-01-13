import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/sevices/product.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  oneproduct: any;
  form: FormGroup;
  selectedFile: File | null = null;
  id: number;
  image: string | ArrayBuffer = '';

  constructor(
    private service: ProductService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.params['id']; // Assure that the ID is a number
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      qte: ['', Validators.required],
    });
    this.productone();
  }

  // Fetches the product data using the ID
  productone(): void {
    this.service.getproduct(this.id).subscribe((res: any) => {
      this.oneproduct = res;
      this.form.patchValue({
        name: this.oneproduct.name,
        description: this.oneproduct.description,
        price: this.oneproduct.price,
        qte: this.oneproduct.qte,
      });

      // Optional: You can display an image preview if it exists
      if (this.oneproduct.image) {
        this.image = `http://localhost:8085/products/files/${this.oneproduct.image}`; // Image URL
      }
    });
  }

  // Handles file selection for product image
  onImageSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  // Resets the image
  resetImage(): void {
    this.image = '';
    this.selectedFile = null;
    // Optionally reset the form control for the image
    this.form.patchValue({ image: null });
  }

  // Updates the product information
  modifierProduct(): void {
    if (this.form.invalid) {
      return;
    }

    const productData = {
      ...this.oneproduct,
      ...this.form.value,
    };


    console.log('Données envoyées au backend :',productData);

    this.service.updateProduct(this.id,this.form.value , this.selectedFile).subscribe(
      (res: any) => {
        console.log('Réponse du backend :', res);
        Swal.fire('Produit mis à jour avec succès');
        this.router.navigate(['/listProduct']);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du produit :', error);
        Swal.fire('Erreur', 'Erreur lors de la mise à jour du produit', 'error');
      }
    );
  }




}

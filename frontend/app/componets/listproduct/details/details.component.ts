import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/sevices/product.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  oneproduct: any = null; // Contient les détails du produit
  image: string | ArrayBuffer = '';
  id: string; // ID du produit

  constructor(
    private service: ProductService,
    private activaterouter: ActivatedRoute
  ) {
    // Récupérer l'ID depuis les paramètres de la route
    this.id = this.activaterouter.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getproductone(); // Charger les détails du produit
  }

  // Méthode pour récupérer les détails du produit
  getproductone(): void {
    this.service.getproduct(this.id).subscribe(
      (res: any) => {
        this.oneproduct = res;
        console.log('Product details:', this.oneproduct);

        // Construire l'URL de l'image si elle existe
        if (this.oneproduct.image) {
          this.image = `http://localhost:8085/products/files/${this.oneproduct.image}`; // Image URL
        } else {
          this.image = 'assets/images/default-product.png'; // Image par défaut
        }
      },
      (error) => {
        console.error('Erreur lors de la récupération des détails du produit:', error);
      }
    );
  }
}

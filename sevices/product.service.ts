import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor( private http:HttpClient) { }

 getallproduct(){
  return this.http.get(`${environment.baseurl}/products/All`)
 }
 getproduct(id:any){
  return this.http.get(`${environment.baseurl}/products/getone/${id}`)
 }

 updateProduct(id:any ,product: any, file: File | null): Observable<any> {
  const formData = new FormData();

  // Ajouter les données du produit
  formData.append('name', product.name);
  formData.append('description', product.description);
  formData.append('price', product.price);
  formData.append('qte', product.qte);  

  if (file) {
    formData.append('file', file, file.name);
  }

  // Log pour vérifier que les données sont bien envoyées
  console.log('FormData:', formData);

  return this.http.put(`${environment.baseurl}/products/updatep/${id}`, formData);
}


 removeproduct(id:any){
    return this.http.delete(`${environment.baseurl}/products/delet/${id}`)
 }
 addproduct(products:any){
  return this.http.post(`${environment.baseurl}/products/addprodact`,products)
 }






}

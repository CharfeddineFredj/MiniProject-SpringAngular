import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http:HttpClient) { }

  getallcategorie(){
    return this.http.get(`${environment.baseurl}/categorys/all`)
   }
   getcategorie(id:any){
    return this.http.get(`${environment.baseurl}/categorys/getone/${id}`)
   }
   editcategorie(id:any,categorys:any){
    return this.http.put(`${environment.baseurl}/categorys/updatec/${id}`,categorys)
   }
   removecategorie(id:any){
      return this.http.delete(`${environment.baseurl}/categorys/delet/${id}`)
   }
   addcategorie(categorys:any){
    return this.http.post(`${environment.baseurl}/categorys/save`,categorys)
   }



}

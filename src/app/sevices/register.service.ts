import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http:HttpClient) { }

  signupcustomer(customers){
    return this.http.post(`${environment.baseurl}/customers/signup`,customers)
   }

   signupprovider(providers){
    return this.http.post(`${environment.baseurl}/providers/signup`,providers)
   }

}

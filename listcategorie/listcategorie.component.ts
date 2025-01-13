import { Component, OnInit } from '@angular/core';
import { CategorieService } from 'src/app/sevices/categorie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listcategorie',
  templateUrl: './listcategorie.component.html',
  styleUrls: ['./listcategorie.component.css']
})
export class ListcategorieComponent implements OnInit {
  listcategorie:any
  p:number=1
  search:any
  constructor(private service:CategorieService) { }

  ngOnInit(): void {
    this.allcategorie()
  }

  allcategorie(){
    this.service.getallcategorie().subscribe((res:any)=>{
      this.listcategorie= res
      console.log("list of product :",this.listcategorie);
    })
  }
  deletecategorie(id:any){
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.removecategorie(id).subscribe((res:any)=>{
            console.log("categorie deleted",res);
            this.allcategorie();

        })
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
      }
    })

  }

}

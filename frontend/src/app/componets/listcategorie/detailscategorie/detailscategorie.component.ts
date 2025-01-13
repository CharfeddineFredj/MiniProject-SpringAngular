import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategorieService } from 'src/app/sevices/categorie.service';

@Component({
  selector: 'app-detailscategorie',
  templateUrl: './detailscategorie.component.html',
  styleUrls: ['./detailscategorie.component.css']
})
export class DetailscategorieComponent implements OnInit {

  onecategory:any
  id=this.activaterouter.snapshot.params["id"]
  constructor( private Service:CategorieService,
    private activaterouter:ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.getcategorieone()
  }

  getcategorieone(){
    this.Service.getcategorie(this.id).subscribe((res:any)=>{
      this.onecategory= res
      console.log("Category details :",this.onecategory);
    })
  }


}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './componets/about/about.component';
import { ContactComponent } from './componets/contact/contact.component';
import { ListproductComponent } from './componets/listproduct/listproduct.component';
import { DetailsComponent } from './componets/listproduct/details/details.component';
import { EditComponent } from './componets/listproduct/edit/edit.component';
import { AddComponent } from './componets/listproduct/add/add.component';
import { ListcategorieComponent } from './componets/listcategorie/listcategorie.component';
import { DetailscategorieComponent } from './componets/listcategorie/detailscategorie/detailscategorie.component';
import { AddcategorieComponent } from './componets/listcategorie/addcategorie/addcategorie.component';
import { EditcategorieComponent } from './componets/listcategorie/editcategorie/editcategorie.component';
import { RegisterComponent } from './register/register.component';
import { RegisterproviderComponent } from './registerprovider/registerprovider.component';

const routes: Routes = [
{
  path:"about" ,component:AboutComponent
},
{
  path:"contact" , component:ContactComponent
},
{
  path:"listProduct" ,component:ListproductComponent
},
{
  path:"details/:id" ,component:DetailsComponent
},
{
  path:"edit/:id" ,component:EditComponent
},
{
  path:"addproduct" ,component:AddComponent
},
{
  path:"listCategorie" ,component:ListcategorieComponent
},
{
  path:"detailscat/:id" ,component:DetailscategorieComponent
},
{
  path:"addcategorie" ,component:AddcategorieComponent
},
{
  path:"editcategorie/:id" ,component:EditcategorieComponent
},
{
  path:"register" ,component:RegisterComponent
},
{
  path:"registerpro" ,component:RegisterproviderComponent
},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

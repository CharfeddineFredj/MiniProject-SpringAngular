import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './componets/about/about.component';
import { ContactComponent } from './componets/contact/contact.component';
import { NavbarComponent } from './componets/navbar/navbar.component';
import { FooterComponent } from './componets/footer/footer.component';
import { ListproductComponent } from './componets/listproduct/listproduct.component';
import { HttpClientModule } from '@angular/common/http';
import { DetailsComponent } from './componets/listproduct/details/details.component';
import { EditComponent } from './componets/listproduct/edit/edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddComponent } from './componets/listproduct/add/add.component';
import { ListcategorieComponent } from './componets/listcategorie/listcategorie.component';
import { DetailscategorieComponent } from './componets/listcategorie/detailscategorie/detailscategorie.component';
import { EditcategorieComponent } from './componets/listcategorie/editcategorie/editcategorie.component';
import { AddcategorieComponent } from './componets/listcategorie/addcategorie/addcategorie.component';
import { RegisterComponent } from './register/register.component';
import { RegisterproviderComponent } from './registerprovider/registerprovider.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { RecherchePipe } from './pipes/recherche.pipe';
import { RecherchecatPipe } from './pipes/recherchecat.pipe';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ContactComponent,
    NavbarComponent,
    FooterComponent,
    ListproductComponent,
    EditComponent,
    DetailsComponent,
    AddComponent,
    ListcategorieComponent,
    DetailscategorieComponent,
    EditcategorieComponent,
    AddcategorieComponent,
    RegisterComponent,
    RegisterproviderComponent,
    RecherchePipe,
    RecherchecatPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    FormsModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

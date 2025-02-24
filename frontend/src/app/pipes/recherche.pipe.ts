import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'recherche'
})
export class RecherchePipe implements PipeTransform {

  transform(value: any, term: any): any{
    if(term==null){
      return value;
    }else
    {
      return value.filter((item:any)=>(item.name.includes(term))||(item.price.includes(term)))
    }

  }

}

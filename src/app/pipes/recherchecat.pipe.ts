import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'recherchecat'
})
export class RecherchecatPipe implements PipeTransform {

  transform(value: any, term: any): any{
    if(term==null){
      return value;
    }else
    {
      return value.filter((item:any)=>(item.title.toLowerCase().includes(term.toLowerCase())))
    }

  }

}

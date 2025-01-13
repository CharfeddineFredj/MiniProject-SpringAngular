package com.securityecommerce.controllers;


import com.securityecommerce.models.Category;
import com.securityecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<Category> ListCategory(){
        return categoryService.getall();
    }
    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category c){
        return categoryService.create(c);
    }

    @GetMapping("getone/{id}")
    public Category getone(@PathVariable Long id){
         return categoryService.getbyId(id);
        }

    @PutMapping("updatec/{idc}")
    public Category updateCtegory(@PathVariable Long idc , @RequestBody Category c){
        c.setId(idc);
        return  categoryService.update(c);
    }
    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletCategory(@PathVariable Long id){

        HashMap message = new HashMap();
        try {
            categoryService.delete(id);
            message.put("etat", "category delet");
            return message;
        }catch (Exception e){
            message.put("etat","Error");
            return message;
        }
    }

}

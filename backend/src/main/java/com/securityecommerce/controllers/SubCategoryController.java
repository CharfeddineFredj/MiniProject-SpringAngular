package com.securityecommerce.controllers;




import com.securityecommerce.models.Category;
import com.securityecommerce.models.SubCategory;
import com.securityecommerce.service.CategoryService;
import com.securityecommerce.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/subcategorys")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/All")
    public List<SubCategory> ListSubCategory(){
        return  subCategoryService.getall();
    }
    @PostMapping("/save")
    public  SubCategory savasubCategory(@RequestBody SubCategory sc){
        return  subCategoryService.create(sc);
    }
    @GetMapping("getone/{id}")
    public SubCategory getone(@PathVariable Long id){
        return  subCategoryService.getbyId(id);
    }
    @PutMapping("updatesc/{idp}")
    public SubCategory updateSubCategory(@PathVariable Long idp,@RequestBody SubCategory sc){
        sc.setId(idp);
        return subCategoryService.update(sc);
    }
    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletprod(@PathVariable Long id) {
        HashMap message = new HashMap();
        try {
            subCategoryService.delete(id);
            message.put("etat", "subcategory delet");
            return message;
        } catch (Exception e) {
            message.put("etat", "Error");
            return message;
        }
    }

    @PostMapping("/savecat/{idcat}")
    public  SubCategory savasubCat(@RequestBody SubCategory sc,@PathVariable Long idcat){
        Category c = categoryService.getbyId(idcat);
        sc.setCategory(c);
        return  subCategoryService.create(sc);
    }
}











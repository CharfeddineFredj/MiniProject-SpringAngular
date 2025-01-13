package com.securityecommerce.controllers;


import com.securityecommerce.models.Product;
import com.securityecommerce.models.Provider;
import com.securityecommerce.models.SubCategory;
import com.securityecommerce.repository.ProductRepository;
import com.securityecommerce.repository.RoleRepository;
import com.securityecommerce.repository.UserRepository;
import com.securityecommerce.security.jwt.JwtUtils;
import com.securityecommerce.security.services.RefreshTokenService;
import com.securityecommerce.service.ProductService;
import com.securityecommerce.service.ProviderService;
import com.securityecommerce.service.SubCategoryService;
import com.securityecommerce.utils.StorgeService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private StorgeService storgeService;
    @Autowired
    private ProductRepository productRepository;





    @GetMapping("/All")
    public List<Product> ListProduct(){
        return  productService.getall();
    }
    @PostMapping("/save")
    public  Product savaProduct(@RequestBody Product p){
        return  productService.create(p);
    }
    //save image
    @PostMapping("/addprodact")
    public  Product savaimgpro(Product p, @RequestParam("file") MultipartFile file)
    {
        String image=storgeService.store(file);
        p.setImage(image);
        return  productService.create(p);
    }
    @GetMapping("getone/{id}")
    public Product getone(@PathVariable Long id){
        return  productService.getbyId(id);
    }
    @PutMapping("/updatep/{id}")
    public Product updateProd(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("qte") Integer qte,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        Product product = productService.getbyId(id);
        if (product == null) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }

        if (name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Name and description must not be empty");
        }
        if (price.isEmpty()) {
            throw new IllegalArgumentException("Price must not be empty");
        }

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQte(qte);

        // Si un fichier est fourni, on met à jour l'image
        if (file != null && !file.isEmpty()) {
            String imagePath = storgeService.store(file);  // Méthode qui gère le stockage du fichier
            product.setImage(imagePath);
        }

        // Mettre à jour le produit dans la base de données
        return productService.update(product);
    }



    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletprod(@PathVariable Long id){
        HashMap message = new HashMap();
        try {
            productService.delete(id);
            message.put("etat", "product delet");
            return message;
        }catch (Exception e){
            message.put("etat","Error");
            return message;
        }
    }

    @PostMapping("/savesub/{idsub}")
    public Product saveproduct(@RequestBody Product p, @PathVariable Long idsub){
        SubCategory s = subCategoryService.getbyId(idsub);
        p.setSubCategory(s);
        return  productService.create(p);
    }

    @PostMapping("/savesp/{ids}/{idp}")
    public Product saveproductsp(@RequestBody Product p, @PathVariable Long ids,@PathVariable Long idp) {
        SubCategory s = subCategoryService.getbyId(ids);
        Provider provider = providerService.getbyId(idp);
        p.setSubCategory(s);
        p.setProvider(provider);
        return  productService.create(p);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> loadfile(@PathVariable String filename ) {
        Resource res = storgeService.loadFile(filename);
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> extensionToContentType = new HashMap<>();
        extensionToContentType.put("pdf", "application/pdf");
        extensionToContentType.put("jpg", "image/jpeg");
        extensionToContentType.put("jpeg", "image/jpeg");
        extensionToContentType.put("png", "image/png");
// Obtenez l'extension du fichier à partir du nom de fichier
        String fileExtension = FilenameUtils.getExtension(filename);
// Obtenez le type de contenu à partir de la correspondance
        String contentType = extensionToContentType.getOrDefault(fileExtension.toLowerCase(),
                MediaType.APPLICATION_OCTET_STREAM_VALUE);
// Définissez le type de contenu dans les en-têtes de réponse
        httpHeaders.setContentType(MediaType.parseMediaType(contentType));
        return ResponseEntity.ok().headers(httpHeaders).body(res);
    }
    @PostMapping("/chercher")
    public List<Product> findProductbyName(String name) {

        return productRepository.chercher("%"+name+"%");
    }




}

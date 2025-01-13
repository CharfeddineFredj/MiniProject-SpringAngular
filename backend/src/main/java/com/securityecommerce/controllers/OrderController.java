package com.securityecommerce.controllers;


import com.securityecommerce.models.Customer;
import com.securityecommerce.models.Order;
import com.securityecommerce.models.Product;
import com.securityecommerce.service.CustomerService;
import com.securityecommerce.service.OrderService;
import com.securityecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Order> ListOrder(){
        return orderService.getall();
    }
    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order o){
        return orderService.create(o);
    }

    @GetMapping("getone/{id}")
    public Order getone(@PathVariable Long id){
        return orderService.getbyId(id);
    }

    @PutMapping("updateo/{idc}")
    public Order updateOrder(@PathVariable Long idc , @RequestBody Order o){
        o.setId(idc);
        return  orderService.update(o);
    }
    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletCategory(@PathVariable Long id){

        HashMap message = new HashMap();
        try {
            orderService.delete(id);
            message.put("etat", "order delet");
            return message;
        }catch (Exception e){
            message.put("etat","Error");
            return message;
        }
    }
    @PostMapping("/saveor/{idcu}")
    public Order saveorders(@RequestBody Order o, @PathVariable Long idcu){
        Customer c = customerService.getbyId(idcu);
        o.setCustomer(c);
        return  orderService.create(o);
    }

    @PostMapping("/saveords/{idcu}")
    public Order saveord(@RequestBody Order o, @PathVariable Long idcu,@RequestParam List<Long> idpro){
        for (Long productid:idpro){
            Product p= productService.getbyId(productid);
            o.addproduct(p);
        }
        Customer c = customerService.getbyId(idcu);
        o.setCustomer(c);
        return  orderService.create(o);
    }
}

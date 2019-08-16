package com.application.app.Controller;


import com.application.app.Entity.Product;
import com.application.app.Pojo.ApiResponse;
import com.application.app.Pojo.Operation;
import com.application.app.Services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @ApiOperation("Get Products")
    @GetMapping
    public ResponseEntity getProducts(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "sortBy") String sortBy){
                System.out.println(page + size);
                return ResponseEntity.status(200).body(productService.getProducts(page, size, sortBy));
    }

    @ApiOperation("Search By Field")
    @GetMapping("/search")
    public ResponseEntity searchProductByField(
            @RequestParam(name = "field") String field,
            @RequestParam(name = "value") String value,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size){

        return ResponseEntity.status(200).body(productService.searchByField(field, value, page, size));
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable(name = "productId") Long productId){

        return ResponseEntity.status(200).body(productService.getProductById(productId)
                .orElseThrow(() -> new NullPointerException("Product Not found")));
    }


    @ApiOperation("Insert Product")
    @PostMapping
    public ResponseEntity insertProduct(@RequestBody @Valid Product product){
        System.out.println(product);
        return ResponseEntity.status(201).body(productService.insertProduct(product));
    }



    @ApiOperation("Update a product by id")
    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid Product product){
        Operation operation = productService.updateProduct(product);
        if(!operation.getIsSuccess()){
            return ResponseEntity.status(404).body(operation);
        }
        return ResponseEntity.status(200).body(operation);

    }

    @ApiOperation("Delete product by id")
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable(name = "productId") Long productId){
        Operation operation = productService.deleteProduct(productId);
        if(!operation.getIsSuccess()){
            return ResponseEntity.status(404).body(operation);
        }
        return ResponseEntity.status(200).body(operation);
    }
}

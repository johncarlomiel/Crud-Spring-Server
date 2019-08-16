package com.application.app.Services;


import com.application.app.Entity.Product;
import com.application.app.Pojo.Operation;
import com.application.app.Repository.ProductRepositoryCustom;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private ProductRepositoryCustom productRepositoryCustom;
    @Autowired
    public ProductService(@Qualifier("jpa") ProductRepositoryCustom productRepositoryCustom) {
        this.productRepositoryCustom = productRepositoryCustom;
    }

    public Page<Product> getProducts(Integer page, Integer size, String sortBy){
        Pageable pageable;
        if(sortBy.equals("DESC")){
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }else{
            pageable = PageRequest.of(page,size, Sort.by("id").ascending());
        }

        System.out.println(pageable);
       return productRepositoryCustom.getProducts(pageable);
    }

    public Optional<Product> getProductById(Long productId){
        return productRepositoryCustom.getProductById(productId);
    }

    public List<Product> findProductsByFieldWithSortingAndPageable(String fields, String orderBy, String sortBy, int offset, int limit){
        PageRequest pageRequest = PageRequest.of(offset,limit, Sort.by(orderBy).ascending());
        if(sortBy == "ASC"){
            pageRequest = PageRequest.of(offset,limit, Sort.by(orderBy).ascending());
        }else if(sortBy == "DESC"){
            pageRequest = PageRequest.of(offset,limit, Sort.by(orderBy).descending());
        }
        System.out.println(fields);
        return productRepositoryCustom.findProductsByFieldWithSortingAndPageable(fields);
    }
    public Page<Product> searchByField(String field, String value, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepositoryCustom.searchByField(field, value, pageable);
    }

    public Product insertProduct(Product product){
        return productRepositoryCustom.saveProduct(product);
    }

    public Operation updateProduct(Product product){
        return productRepositoryCustom.updateProduct(product);
    }

    public Operation deleteProduct(Long productId){
        return productRepositoryCustom.deleteProduct(productId);
    }



}

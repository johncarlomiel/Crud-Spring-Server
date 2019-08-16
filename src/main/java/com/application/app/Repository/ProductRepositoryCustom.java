package com.application.app.Repository;

import com.application.app.Entity.Product;
import com.application.app.Pojo.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProductRepositoryCustom {
    Page<Product> getProducts(Pageable pageable);
    Product saveProduct(Product product);
    Operation updateProduct(Product product);

    Operation deleteProduct(Long productId);

    Optional<Product> getProductById(Long productId);

    List<Product> findProductsByFieldWithSortingAndPageable(String fields);

    Page<Product> searchByField(String field, String value, Pageable pageable);

}

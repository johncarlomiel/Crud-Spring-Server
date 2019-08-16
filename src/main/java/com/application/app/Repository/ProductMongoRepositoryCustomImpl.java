package com.application.app.Repository;

import com.application.app.Entity.Product;
import com.application.app.Pojo.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository("mongodb")
public class ProductMongoRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    private ProductMongoRepository productMongoRepository;

    public ProductMongoRepositoryCustomImpl(ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Operation updateProduct(Product product) {
        return null;
    }

    @Override
    public Operation deleteProduct(Long productId) {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> findProductsByFieldWithSortingAndPageable(String fields) {
        return null;
    }

    @Override
    public Page<Product> searchByField(String field, String value, Pageable pageable) {
        return null;
    }
}

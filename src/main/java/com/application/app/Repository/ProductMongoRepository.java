package com.application.app.Repository;

import com.application.app.Entity.Product;
import com.application.app.Entity.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<Product, String> {
}

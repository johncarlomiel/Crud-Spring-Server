package com.application.app.Repository;

import com.application.app.Entity.Product;
import com.application.app.Pojo.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Primary
@Repository("jpa")
public class ProductJpaRepositoryCustomImpl implements ProductRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Autowired
    private ProductJpaRepository productJpaRepository;

    public ProductJpaRepositoryCustomImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);

        query.select(productRoot).where(builder.equal(productRoot.get("isDeleted"), false));

        Sort sort = pageable.getSort();
        query.orderBy(QueryUtils.toOrders(sort, productRoot, builder));

        TypedQuery<Product> products = entityManager.createQuery(query);
        int max = products.getResultList().size();
        products.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        products.setMaxResults(pageable.getPageSize());
        Page<Product> results = new PageImpl<Product>(products.getResultList(),pageable,max);
        System.out.println(results.getTotalPages());
        return results;
    }

    @Override
    public List<Product> findProductsByFieldWithSortingAndPageable(String fields) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = builder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productCriteriaQuery.select(productRoot);
        List<Product> results = entityManager.createQuery(productCriteriaQuery).getResultList();
        System.out.println(results);
        return results;
    }

    @Override
    public Page<Product> searchByField(String field, String value, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = builder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        //Condition 1
        Predicate condition1 = builder.like(productRoot.get(field).as(String.class), "%"+value+"%");
        //Condition 2
        Predicate condition2 = builder.equal(productRoot.get("isDeleted"), false);
        List<Predicate> predList = new LinkedList<Predicate>();
        predList.add(condition1);
        predList.add(condition2);
        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);

        productCriteriaQuery.select(productRoot).where(predArray);


        TypedQuery<Product> results = entityManager.createQuery(productCriteriaQuery);
        int max = results.getResultList().size();
        results.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        results.setMaxResults(pageable.getPageSize());
        Page<Product> products = new PageImpl<Product>(results.getResultList(),pageable,max);
        System.out.println(products);
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productJpaRepository.findById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        System.out.println(product);return productJpaRepository.save(product);
    }

    @Override
    public Operation updateProduct(Product product) {
        return productJpaRepository.findById(product.getId()).map(product1 -> {
            productJpaRepository.save(product);
            return Operation.builder().message("Product Updated").isSuccess(true).build();
        }).orElse(Operation.builder().isSuccess(false).message("Id not found").build());

    }

    @Override
    public Operation deleteProduct(Long productId) {
        return productJpaRepository.findById(productId).map(product -> {
            product.setDeleted(true);
            productJpaRepository.save(product);
            return Operation.builder()
                    .isSuccess(true)
                    .message("Product Deleted")
                    .build();
        }).orElse(
                Operation.builder()
                        .isSuccess(false)
                        .message("Product Id doesn't exist")
                        .build()
        );
    }
}

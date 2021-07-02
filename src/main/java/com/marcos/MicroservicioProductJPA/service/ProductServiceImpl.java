package com.marcos.MicroservicioProductJPA.service;

import com.marcos.MicroservicioProductJPA.entity.Category;
import com.marcos.MicroservicioProductJPA.entity.Product;
import com.marcos.MicroservicioProductJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatedAt(new Date());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());

        if(null == productDB){
            return null;
        }

        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());

        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);

        if(null == productDB){
            return null;
        }

        productDB.setStatus("DELETED");

        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);

        if(null == productDB){
            return null;
        }

        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);

        return productRepository.save(productDB);
    }
}

package app.repository;

import app.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryList implements ProductRepository {

    // Этот лист является имитацией базы данных.
    // Здесь хранятся все продукты, которые имеются в нашем магазине.
    private List<Product> database = new ArrayList<>();

    // Это поле отслеживает, какой идентификатор был назначен
    // последнему сохраняемому продукту
    private long currentId = 0;


    @Override
    public Product save(Product product) {
        product.setId(++currentId);
        database.add(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return database
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return database;
    }

    @Override
    public void update(Product product) {
        Long id = product.getId();
        double newPrice = product.getPrice();

        Product existedProduct = findById(id);

        if(existedProduct != null) {
            existedProduct.setPrice(newPrice);
        }
    }

    @Override
    public void deleteById(Long id) {
        database.removeIf(x -> x.getId().equals(id));
    }
}

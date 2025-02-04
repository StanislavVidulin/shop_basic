package app.controller;

import app.domain.Product;
import app.service.ProductService;

import java.util.List;

// Это контроллер продуктов - представляет собой часть четвёртого слоя приложения.
// Задача контроллера - принять запрос, поступивший на наше приложение из внешней
// среды (от какого-то клиента, например, фронт-енда или другого приложения),
// обратиться к сервису, чтобы получить нужные данные, и отдать ответ клиенту.
// В реальной практике контроллеры принимают настоящие http-запросы,
// которые пришли через интернет или по локальной сети.
public class ProductController {

    // Контроллер - это четвёртый слой, и он должен взаимодействовать с третьим.
    // Контроллер никогда не должен напрямую обращаться ко второму слою, т.е. репозиторию.
    // Поэтому в контроллере мы определяем поле, которое будет содержать объект
    // сервиса, чтобы контроллер в своём коде мог к нему обращаться и вызывать его методы.
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public Product save(String title, double price) {
        Product product = new Product(title, price);
        return service.save(product);
    }

    public List<Product> getAll() {
        return service.getAllActiveProducts();
    }

    public Product getById(Long id) {
        return service.getById(id);
    }

    public void update(Long id, double newPrice) {
        Product product = new Product(id, newPrice);
        service.update(product);
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }

    public void deleteByTitle(String title) {
        service.deleteByTitle(title);
    }

    public void restoreById(Long id) {
        service.restoreById(id);
    }

    public long getProductsCount() {
        return service.getActiveProductsTotalCount();
    }

    public double getProductsTotalCost() {
        return service.getActiveProductsTotalCost();
    }

    public double getProductsAveragePrice() {
        return service.getActiveProductsAveragePrice();
    }
}

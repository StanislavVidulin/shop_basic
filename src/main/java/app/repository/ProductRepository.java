package app.repository;

import app.domain.Product;

import java.util.List;

// Это интерфейс, который определяет, какое поведение
// должно быть у репозитория продуктов.
// Интерфейсы и классы репозиториев составляют
// второй слой приложения.
// Их задача - простой доступ к данным.
// На этом слое не должно быть никакого описания бизнес-объектов
// и никакой бизнес-логики
public interface ProductRepository {

    // CRUD - Create, Read, Update, Delete (создать, прочитать, изменить, удалить)
    // Репозиторий должен обеспечивать эти четыре типа операций

    Product save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void update(Product product);
    void deleteById(Long id);
}

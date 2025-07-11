package app.domain;

import java.util.Objects;

// Это класс Product.
// Он является частью домена - первого слоя нашего приложения.
// Домен не содержит бизнес логики, а также он не содержит
// кода доступа к базе данных.
// Домен - просто описывает объекты, с которыми работает приложение.
public class Product {

    private Long id;
    private String title;
    private double price;
    private boolean active;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Product(Long id, double price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %.2f, active - %s.",
                id, title, price, active ? "yes" : "no");
    }
}

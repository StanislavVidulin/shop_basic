package client;

import app.controller.CustomerController;
import app.controller.ProductController;
import app.domain.Customer;
import app.domain.Product;
import app.repository.CustomerRepository;
import app.repository.CustomerRepositoryMap;
import app.repository.ProductRepository;
import app.repository.ProductRepositoryList;
import app.service.CustomerService;
import app.service.CustomerServiceImpl;
import app.service.ProductService;
import app.service.ProductServiceImpl;

import java.util.Scanner;

// Это имитация клиентской части.
// Представим, что наш Магазин - это бэк-енд, и он полностью
// лежит в пакете app.
// А это клиентская часть, представим, что это например фронт-енд
// или какое-то оконное приложение, запущенное на компьютере пользователя.
// Это приложение умеет обращаться на наш бэк-енд.
// Этот процесс мы будем имитировать при помощи вызова методов контроллера.
public class Client {

    private static Scanner scanner;
    private static ProductController productController;
    private static CustomerController customerController;

    public static void main(String[] args) {

        // Создаём объект репозитория
        ProductRepository productRepository = new ProductRepositoryList();
        CustomerRepository customerRepository = new CustomerRepositoryMap();

        // Создаём объект сервиса и в конструктор передаём ему объект репозитория,
        // чтобы сервис мог к нему обращаться
        ProductService productService = new ProductServiceImpl(productRepository);
        CustomerService customerService = new CustomerServiceImpl(customerRepository, productService);

        // Создаём объект контроллера и в конструктор передаём ему объект сервиса,
        // чтобы контроллер мог к нему обращаться
        productController = new ProductController(productService);
        customerController = new CustomerController(customerService);

        scanner = new Scanner(System.in);

        while (true) {
            try {

                System.out.println("Выберите действие:");
                System.out.println("1. Операции с продуктами");
                System.out.println("2. Операции с покупателями");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void productOperations() {
        while (true) {
            try {
                System.out.println("Выберите действие с продуктами:");
                System.out.println("1. Сохранение продукта");
                System.out.println("2. Получение всех продуктов");
                System.out.println("3. Получение одного продукта");
                System.out.println("4. Изменение одного продукта");
                System.out.println("5. Удаление продукта по идентификатору");
                System.out.println("6. Удаление продукта по наименованию");
                System.out.println("7. Восстановление продукта по идентификатору");
                System.out.println("8. Получение количества продуктов");
                System.out.println("9. Получение общей стоимости продукта");
                System.out.println("10. Получение средней стоимости продукта");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите наименование продукта:");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену продукта:");
                        double price = Double.parseDouble(scanner.nextLine());
                        // Вызов метода у контроллера - это имитация процесса, который происходит
                        // в реальных приложениях - отправка http-запроса через интернет
                        // от клиента на наше приложение
                        Product savedProduct = productController.save(title, price);
                        System.out.println("Сохранённый продукт:");
                        System.out.println(savedProduct);
                        break;
                    case 2:
                        productController.getAll().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        System.out.println("Введите идентификатор продукта:");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("Найденный продукт:");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новую цену продукта:");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        productController.update(id, newPrice);
                        break;
                    case 5:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите наименование продукта:");
                        title = scanner.nextLine();
                        productController.deleteByTitle(title);
                        break;
                    case 7:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Количество продуктов - " + productController.getProductsCount());
                        break;
                    case 9:
                        System.out.println("Общая стоимость продуктов - " + productController.getProductsTotalCost());
                        break;
                    case 10:
                        System.out.println("Средняя стоимость продукта - " + productController.getProductsAveragePrice());
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void customerOperations() {
        while (true) {
            try {
                System.out.println("Выберите действие с покупателями:");
                System.out.println("1. Сохранение покупателя");
                System.out.println("2. Получение всех покупателей");
                System.out.println("3. Получение одного покупателя");
                System.out.println("4. Изменение одного покупателя");
                System.out.println("5. Удаление покупателя по идентификатору");
                System.out.println("6. Удаление покупателя по имени");
                System.out.println("7. Восстановление покупателя по идентификатору");
                System.out.println("8. Получение количество покупателей");
                System.out.println("9. Получение общей суммы корзины покупателя");
                System.out.println("10. Получение средней цены продукта в корзине покупателя");
                System.out.println("11. Сохранение продукта в корзину покупателя");
                System.out.println("12. Удаление одного продукта из корзины покупателя");
                System.out.println("13. Удаление всех продуктов из корзины покупателя");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите имя покупателя:");
                        String name = scanner.nextLine();
                        Customer savedCustomer = customerController.save(name);
                        System.out.println("Сохранённый покупатель:");
                        System.out.println(savedCustomer);
                        break;
                    case 2:
                        customerController.getAllActiveCustomers().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        System.out.println("Введите идентификатор покупателя:");
                        Long id = Long.parseLong(scanner.nextLine());
                        Customer foundCustomer = customerController.getById(id);
                        System.out.println("Найденный покупатель:");
                        System.out.println(foundCustomer);
                        break;
                    case 4:
                        System.out.println("Введите идентификатор покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новое имя покупателя:");
                        String newCustomer = scanner.nextLine();
                        customerController.update(id, newCustomer);
                        break;
                    case 5:
                        System.out.println("Введите идентификатор покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите имя покупателя:");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        break;
                    case 7:
                        System.out.println("Введите идентификатор покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Количество покупателей - " + customerController.getActiveCustomersNumber());
                        break;
                    case 9:
                        System.out.println("Введите идентификатор покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Общая стоимость корзины покупателя - " +
                                customerController.getCustomersCartTotalCostById(id));
                        break;
                    case 10:
                        System.out.println("Введите идентификатор покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Средняя цена продукта в корзине покупателя - "
                                + customerController.getAveragePriceOfCustomersCart(id));
                        break;
                    case 11:
                        System.out.println("Введите идентификатор покупателя:");
                        Long customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта:");
                        Long productId = Long.parseLong(scanner.nextLine());
                        customerController.addProductToCustomersCart(customerId, productId);
                        System.out.println("Сохранённый продукт:");
                        System.out.println(productController.getById(productId));
                        break;
                    case 12:
                        System.out.println("Введите идентификатор покупателя:");
                        customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта:");
                        productId = Long.parseLong(scanner.nextLine());
                        customerController.removeProductFromCustomersCart(customerId, productId);
                        break;
                    case 13:
                        System.out.println("Введите идентификатор покупателя:");
                        customerId = Long.parseLong(scanner.nextLine());
                        customerController.clearCustomersCart(customerId);
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

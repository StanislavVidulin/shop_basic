package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.*;
import app.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ProductService productService;

    public CustomerServiceImpl(CustomerRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public Customer save(Customer customer) {

        if (customer == null) {
            throw new CustomerSaveException("Customer cannot be null");
        }

        String name = customer.getName();
        if(name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new CustomerSaveException("Customer name should be at least 3 characters long");
        }

        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Customer getActiveCustomerById(Long id) {
        Customer customer = repository.findById(id);

        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        return customer;
    }

    @Override
    public void update(Customer customer) {

        if (customer == null) {
            throw new CustomerUpdateException("Customer cannot be null");
        }

        Long id = customer.getId();
        if (id == null || id < 1) {
            throw new CustomerUpdateException("Customer id should be positive");
        }

        String name = customer.getName();
        if(name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new CustomerUpdateException("Customer name should be at least 3 characters long");
        }

        repository.update(customer);
    }

    @Override
    public void deleteById(Long id) {
        getActiveCustomerById(id).setActive(false);
    }

    @Override
    public void deleteByName(String name) {
        Customer customer = getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer with name " + name + " not found ");
        }

        customer.setActive(false);
    }

    @Override
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        customer.setActive(true);
    }

    @Override
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCustomersCartTotalCostById(Long customerId) {
        Customer customer = getActiveCustomerById(customerId);
        return customer.getActiveProductsTotalCost();
    }

    @Override
    public double getAveragePriceOfCustomersCart(Long customerId) {
        Customer customer = getActiveCustomerById(customerId);
        return customer.getActiveProductsAveragePrice();
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getById(productId);
        customer.addProduct(product);
    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {
        Customer customer = getActiveCustomerById(customerId);
        customer.removeProductById(productId);
    }

    @Override
    public void clearCustomersCart(Long customerId) {
        Customer customer = getActiveCustomerById(customerId);
        customer.clearCart();
    }
}

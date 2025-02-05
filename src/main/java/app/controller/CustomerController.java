package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    public Customer save(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    public List<Customer> getAllActiveCustomers() {
        return service.getAllActiveCustomers();
    }

    public Customer getById(Long id) {
        return service.getActiveCustomerById(id);
    }

    public void update(Long id, String name) {
        Customer customer = new Customer(id, name);
        service.update(customer);
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }

    public void deleteByName(String name) {
        service.deleteByName(name);
    }

    public void restoreById(Long id) {
        service.restoreById(id);
    }

    public int getActiveCustomersNumber() {
        return service.getActiveCustomersNumber();
    }

    public double getCustomersCartTotalCostById(Long customerId) {
        return service.getCustomersCartTotalCostById(customerId);
    }

    public double getAveragePriceOfCustomersCart(Long customerId) {
        return service.getAveragePriceOfCustomersCart(customerId);
    }

    public void addProductToCustomersCart(Long customerId, Long productId) {
        service.addProductToCustomersCart(customerId, productId);
    }

    public void removeProductFromCustomersCart(Long customerId, Long productId) {
        service.removeProductFromCustomersCart(customerId, productId);
    }

    public void clearCustomersCart(Long customerId) {
        service.clearCustomersCart(customerId);
    }
}


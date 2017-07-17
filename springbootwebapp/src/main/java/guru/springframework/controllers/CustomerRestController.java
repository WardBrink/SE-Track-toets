package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerServiceImpl;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class CustomerRestController {
    private CustomerServiceImpl customerService;

    @Autowired
    public void setCustomerSerivce(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Transactional
    @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
    public Customer add(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }

    @Transactional
    @RequestMapping(value = "/api/customer/edit", method = RequestMethod.POST)
    public boolean edit(@RequestBody Integer id, Customer customer) {

        boolean success = false;
        if (customerService.getCustomerById(id) != null) {
            customer.setId(id);
            customerService.saveCustomer(customer);
            success = true;
        }
        return success;
    }

    @Transactional
    @RequestMapping(value = "/api/customer/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam Integer id) {
        boolean success = false;
        if (customerService.getCustomerById(id) != null) {
            customerService.deleteCustomer(id);
            success = true;
        }
        return success;
    }

    @RequestMapping(value = "/api/customer/showall", method = RequestMethod.GET)
    public Iterable<Customer> list() {
        return customerService.listAllCustomers();
    }

    @RequestMapping(value = "/api/customer/show", method = RequestMethod.GET)
    public Customer showProduct(int productId) {
        return customerService.getCustomerById(productId);
    }
}
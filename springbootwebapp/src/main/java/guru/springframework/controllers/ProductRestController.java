package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setServiceInterface(ProductService serviceInterface) {
        this.productService = serviceInterface;
    }

    @Transactional
    @RequestMapping(value = "/api/product/add", method = RequestMethod.POST)
    public Product add(@RequestBody Product product) {
        productService.saveProduct(product);
        return product;
    }

    @Transactional
    @RequestMapping(value = "/api/product/edit", method = RequestMethod.POST)
    public boolean edit(@RequestBody Integer id, Product product) {

        boolean success = false;
        if (productService.getProductById(id) != null) {
            product.setId(id);
            productService.saveProduct(product);
            success = true;
        }
        return success;
    }

    @Transactional
    @RequestMapping(value = "/api/product/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam Integer id) {
        boolean success = false;
        if (productService.getProductById(id) != null) {
            productService.deleteProduct(id);
            success = true;
        }
        return success;
    }

    @RequestMapping(value = "/api/product/showall", method = RequestMethod.GET)
    public Iterable<Product> list() {
        return productService.listAllProducts();
    }

    @RequestMapping(value = "/api/product/show", method = RequestMethod.GET)
    public Product showProduct(int productId) {
        return productService.getProductById(productId);
    }
}
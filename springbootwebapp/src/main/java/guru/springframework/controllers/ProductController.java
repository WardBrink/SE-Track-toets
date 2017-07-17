package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private ProductService serviceInterface;

    @Autowired
    public void setProductService(ProductService serviceInterface) {
        this.serviceInterface = serviceInterface;
    }


    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){

        serviceInterface.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", serviceInterface.getProductById(id));
        return "productform";
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id){
        serviceInterface.deleteProduct(id);
       return "/products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", serviceInterface.listAllProducts());
        System.out.println("Returning products:");
        // TODO: lijst met producten terugsturen
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", serviceInterface.getProductById(id));
        return "productshow";
    }
}

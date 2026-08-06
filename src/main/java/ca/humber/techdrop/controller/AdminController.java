package ca.humber.techdrop.controller;
import ca.humber.techdrop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "admin";
    }
}

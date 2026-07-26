package ca.humber.techdrop.controller;

import ca.humber.techdrop.model.Product;
import ca.humber.techdrop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            Model model) {

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("products", productService.searchProducts(keyword));
        } else if (category != null && !category.isEmpty()) {
            model.addAttribute("products", productService.getProductsByCategory(category));
        } else if (sort != null && !sort.isEmpty()) {
            model.addAttribute("products", productService.sortProducts(sort));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }

        return "index";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "add-product";
        }

        productService.saveProduct(product);
        return "redirect:/";
    }
}
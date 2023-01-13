package com.services.userinterface.controllers;

import com.services.userinterface.dtos.ProductDto;
import com.services.userinterface.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController
{
    private final ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

//  Ok
    @GetMapping("/")
    public String getAllProducts(Model model)
    {
        model.addAttribute("products", productService.readAllProducts());
        return "products/index";
    }

//   Ok
    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model)
    {
        ProductDto product = new ProductDto();
        model.addAttribute("product", product);
        return "products/new";
    }

//  Ok
    @PostMapping("/save")
    public String addProduct(@ModelAttribute ProductDto productDto)
    {
        productService.createProduct(productDto);
        return "redirect:/";
    }
//  Ok
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") String id, Model model)
    {
        ProductDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products/update";
    }

//    Ok
    @PostMapping("/update-product/{id}")
    public String updateUser
            (@PathVariable("id") String id, @Validated ProductDto productDto, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            productDto.setId(id);
            return "products/update";
        }

        productService.updateProductById(id, productDto);
        return "redirect:/";
    }

//    Ok
    @GetMapping("/deleteProduct/{id}")
    public String deleteById(@PathVariable(value = "id") String id)
    {
        productService.deleteProductById(id);
        return "redirect:/";
    }
}

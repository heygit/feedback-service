package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import project.service.ProductService;

import java.util.Collections;
import java.util.Map;

import static project.constants.ParamNames.CATEGORIES_KEY;
import static project.constants.ParamNames.PRODUCTS_KEY;
import static project.constants.ParamNames.PRODUCT_KEY;

@Controller
@RequestMapping("/api/v1/productManagement")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProducts() {
        return Collections.singletonMap(PRODUCTS_KEY, productService.getProcucts());
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCategories() {
        return Collections.singletonMap(CATEGORIES_KEY, productService.getCategories());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProduct(@PathVariable("id") long productId) {
        return Collections.singletonMap(PRODUCT_KEY, productService.getProcuctById(productId));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductsByCategory(@PathVariable("id") long categoryId) {
        return Collections.singletonMap(PRODUCTS_KEY, productService.getProcuctsByCategory(categoryId));
    }
}

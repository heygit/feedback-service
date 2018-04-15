package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.converter.ProductConverter;
import project.entity.ProductEntity;
import project.model.external.Category;
import project.model.external.Product;
import project.repository.CategoryRepository;
import project.repository.ProductRepository;
import project.utils.ConverterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getProcucts() {
        return ConverterHelper.convert(productRepository.findAll(), ProductConverter::createProductFromEntity);
    }

    public List<Category> getCategories() {
        return ConverterHelper.convert(categoryRepository.findAll(), ProductConverter::createCategoryFromEntity);
    }

    public Product getProcuctById(long productId) {
        return ProductConverter.createProductFromEntity(productRepository.findOne(productId));
    }

    public List<Product> getProcuctsByCategory(long categoryId) {
        return ConverterHelper.convert(productRepository.findByCategory_Id(categoryId),
                ProductConverter::createProductFromEntity);
    }
}

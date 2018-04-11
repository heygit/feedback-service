package project.converter;

import project.entity.CategoryEntity;
import project.entity.ProductEntity;
import project.model.external.Category;
import project.model.external.Product;

public class ProductConverter {

    public static Product createProductFromEntity(ProductEntity source) {
        return new Product(source.getId(), source.getName(), source.getDescription(), source.getImageUrl(),
                source.getCategory().getId());
    }

    public static Category createCategoryFromEntity(CategoryEntity source) {
        return new Category(source.getId(), source.getName());
    }
}

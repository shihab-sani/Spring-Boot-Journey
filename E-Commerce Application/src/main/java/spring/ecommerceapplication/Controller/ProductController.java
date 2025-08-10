package spring.ecommerceapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.ecommerceapplication.DTOS.ProductDtos;
import spring.ecommerceapplication.Entities.Products;
import spring.ecommerceapplication.Mappers.ProductMapper;
import spring.ecommerceapplication.Repositories.CategoryRepository;
import spring.ecommerceapplication.Repositories.ProductRepository;

import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private CategoryRepository categoriesRepository;

    @GetMapping
    public Iterable<ProductDtos> getAllProducts(@RequestParam(required = false, name = "categoryId") Byte categoryId) {
        List<Products> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategory_Id(categoryId);
        } else {
            products = productRepository.findAllByCategory();
        }
        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtos> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDtos> createProduct(@RequestBody ProductDtos request, UriComponentsBuilder uriBuilder) {
        var category = categoriesRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(request);
        product.setCategory(category);
        productRepository.save(product);
        request.setId(product.getId());
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }
}

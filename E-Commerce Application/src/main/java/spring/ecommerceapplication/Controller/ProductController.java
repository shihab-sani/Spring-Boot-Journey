package spring.ecommerceapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerceapplication.DTOS.ProductDtos;
import spring.ecommerceapplication.Entities.Products;
import spring.ecommerceapplication.Mappers.ProductMapper;
import spring.ecommerceapplication.Repositories.ProductRepository;

import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

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
}

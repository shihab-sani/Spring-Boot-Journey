package testproject.store.testing.Services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import testproject.store.testing.entities.Addresses;
import testproject.store.testing.entities.Products;
import testproject.store.testing.entities.User;
import testproject.store.testing.repositories.*;
import testproject.store.testing.repositories.Specification.ProductSpecification;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressesRepository addressesRepository;
    private final EntityManager entityManager;
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;

    @Transactional
    public void EntityState() {
        var user = User.builder().name("Shihab")
                .email("2320590@iub.edu.bd")
                .password("Shihab123").build();

        if (entityManager.contains(user)){
            System.out.println("persistence");
        } else {
            System.out.println("transient or detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)){
            System.out.println("persistence");
        } else {
            System.out.println("transient or detached");
        }
    }

    @Transactional
    public void showRelatedEntities() {
//        var profile = profileRepository.findById(1L).orElseThrow();
//        System.out.println(profile.getBio());
        var address = addressesRepository.findById(1L).orElseThrow();
        System.out.println(address.getStreet());
    }

    public void persistEntity() {
        var user = User.builder().name("Sani")
                .email("mdsani633@gmail.com")
                .password("shihab14032001").build();

        var address = Addresses.builder().street("123 Main St").city("Anytown").zip(12345).build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteEntity() {
//        userRepository.deleteById(1L);
//        var user = userRepository.findById(6L).orElseThrow();
//        var address = user.getAddresses().getFirst();
//        user.removeAddress(address);
//        userRepository.save(user);
        productsRepository.deleteById(4L);
    }

    @Transactional
    public void manageProducts() {
//        var category = Categories.builder().name("Electronics").build();
        var category = categoriesRepository.findById((byte)3L).orElseThrow();
        var product = Products.builder()
                .name("Smartphone")
                .price(new java.math.BigDecimal("999.99"))
                .category(category)
                .build();

        productsRepository.save(product);
    }

    @Transactional
    public void userWishList() {
        var user = userRepository.findById(2L).orElseThrow();
        var products = productsRepository.findAll();
        products.forEach(user::addFavorite);
        userRepository.save(user);
    }

    @Transactional
    public void updatePrice() {
        productsRepository.updatePrice(new BigDecimal("899.00"), (byte) 3);
    }

    @Transactional
    public void fetchProducts() {
//        var products = productsRepository.findCategory(new Categories((byte) 3));
//        products.forEach(System.out::println);
//        var products = productsRepository.findProducts(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//        products.forEach(System.out::println);

        var products = new Products();
        products.setName("Laptop");
        var match = ExampleMatcher.matching().
                withIncludeNullValues().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(products, match);
        productsRepository.findAll(example).forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productsRepository.findProductsByCriteria(null, BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecification(String name, BigDecimal min, BigDecimal max) {
        Specification<Products> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpecification.byName(name));
        }
        if (min != null) {
            spec = spec.and(ProductSpecification.byPriceGreaterThanOrEqualTo(min));
        }
        if (max != null) {
            spec = spec.and(ProductSpecification.byPriceLessThanOrEqualTo(max));
        }
        productsRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchProductsBySort() {
        Sort sort = Sort.by("name").and(
                Sort.by("price").descending()
        );
        productsRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pagrNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pagrNumber, pageSize);
        Page<Products> productsPage = productsRepository.findAll(pageRequest);

        var products = productsPage.getContent();
        products.forEach(System.out::println);

        var totalPages = productsPage.getTotalPages();
        var totalElements = productsPage.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }

    @Transactional
    public void fetchUser() {
        var users = userRepository.findByEmail("mdsani633@gmail.com");
        System.out.println(users);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddress();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void fetchProfile() {
//        var profile = profileRepository.findByLoyaltyPointsGreaterThan(10);
//        profile.forEach(p -> {
//            System.out.println(p.getId() + ": " + p.getUser().getEmail());
//        });
//        var profile = profileRepository.findLoyaltyPointsByOrder(2);
//        profile.forEach(p -> {
//            System.out.println(p.getId() + ": " + p.getEmail());
//        });

        var profile = userRepository.findLoyalUser(2);
        profile.forEach(p -> {
            System.out.println(p.getId() + ": " + p.getEmail());
        });
    }
}

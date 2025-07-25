package testproject.store.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import testproject.store.testing.Services.UserService;
import testproject.store.testing.entities.Categories;
import testproject.store.testing.entities.Products;
import testproject.store.testing.repositories.CategoriesRepository;
import testproject.store.testing.repositories.ProductsRepository;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingApplication.class, args);

		var service = context.getBean(UserService.class);
//		service.manageProducts();
//		service.userWishList();
//		service.deleteEntity();
//		service.updatePrice();
//		service.fetchProducts();
//		service.fetchUser();
//		service.fetchUsers();
//		service.fetchProfile();
//		service.fetchProductsByCriteria();
//		service.fetchProductsBySpecification("laptop",null,null);
//		service.fetchProductsBySort();
		service.fetchPaginatedProducts(0, 2);

//		var repository = context.getBean(ProductsRepository.class);
//		var categoriesRepository = context.getBean(CategoriesRepository.class);

//		var products = Products.builder()
//				.name("Laptop")
//				.price(new java.math.BigDecimal("999.99"))
//				.build();
//		var products2 = Products.builder()
//				.name("Smartphone")
//				.price(new java.math.BigDecimal("499.99"))
//				.build();
//		var category = Categories.builder()
//				.name("Electronics")
//				.build();

//		categoriesRepository.save(category);
//		products2.setCategory(category);
//		repository.save(products2);

//		repository.deleteById(1L);

//		var service = context.getBean(UserService.class);

//		service.showRelatedEntities();
//		service.EntityState();
//		service.persistEntity();
//		service.deleteEntity();

//		var user = User.builder()
//				.name("John Doe")
//				.email("ssani@kent.edu")
//				.password("password123")
//				.build();
//		repository.save(user);

//		var addresses = Addresses.builder()
//				.street("123 Main St")
//				.city("Anytown")
//				.zip(12345)
//				.build();
//
//		user.addAddress(addresses);

//		user.addTag("admin");

//		var profile = Profile.builder()
//						.bio("Software Engineer")
//				        .build();
//
//		user.setProfile(profile);
//		profile.setUser(user);
//		System.out.println(user);
	}
}

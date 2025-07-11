package testproject.store.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import testproject.store.testing.Services.UserService;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingApplication.class, args);

		var service = context.getBean(UserService.class);

//		service.showRelatedEntities();
//		service.EntityState();
//		service.persistEntity();
		service.deleteEntity();

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

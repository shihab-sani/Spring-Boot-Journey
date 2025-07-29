package spring.ecommerceapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.ecommerceapplication.Controller.HomeController;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);

        var beanNames = context.getBean(HomeController.class);
    }

}

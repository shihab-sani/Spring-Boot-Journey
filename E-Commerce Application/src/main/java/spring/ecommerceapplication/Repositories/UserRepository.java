package spring.ecommerceapplication.Repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.ecommerceapplication.Entities.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}

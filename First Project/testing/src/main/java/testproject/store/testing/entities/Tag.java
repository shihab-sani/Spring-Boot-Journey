package testproject.store.testing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

   @Column(name = "name")
    private String name;

   @ManyToMany(mappedBy = "tags")
   private Set<User> users = new HashSet<>();
}

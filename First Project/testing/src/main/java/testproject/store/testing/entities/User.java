package testproject.store.testing.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "passward")
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Addresses> addresses = new ArrayList<>();

    public void addAddress(Addresses address) {
        addresses.add(address);
    }

}

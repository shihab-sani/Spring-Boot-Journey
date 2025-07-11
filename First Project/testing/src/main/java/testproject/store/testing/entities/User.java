package testproject.store.testing.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<Addresses> addresses = new ArrayList<>();

    public void addAddress(Addresses address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Addresses address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(String tagName) {
        tags.removeIf(tag -> tag.getName().equals(tagName));
    }

    @ManyToMany
    @JoinTable(
        name = "user_tags",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private Set<Products> products = new HashSet<>();
}

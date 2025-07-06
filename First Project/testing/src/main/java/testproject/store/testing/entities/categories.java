package testproject.store.testing.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private byte id;

    @Column(name = "name")
    private String name;
}

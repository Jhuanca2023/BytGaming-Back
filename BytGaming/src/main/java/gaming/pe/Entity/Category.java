package gaming.pe.Entity;

import jakarta.persistence.*;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //jola
    private String description;

    public Category() {
    }

    public Category(Long id, String nombre, String description) {
        this.id = id;
        this.name = nombre;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


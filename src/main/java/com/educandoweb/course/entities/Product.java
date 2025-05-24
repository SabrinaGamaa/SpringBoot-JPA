package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // Relacionamento muitos-para-muitos entre Product e Category.
    // Isso significa que um produto pode pertencer a várias categorias e uma categoria pode conter vários produtos.
    @ManyToMany
    // Define a tabela intermediária (ou de associação) que vai mapear a relação entre produtos e categorias.
    // Essa tabela será criada com o nome "tb_product_category".
    @JoinTable(
            name = "tb_product_category", // Nome da tabela intermediária no banco de dados
            joinColumns = @JoinColumn(name = "product_id"), // Define a chave estrangeira (foreign key) que aponta para a entidade "Product" (dona do relacionamento).
            inverseJoinColumns = @JoinColumn(name = "category_id") // Define a chave estrangeira inversa que aponta para a entidade "Category".
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "orderItemPK.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product(){
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items){
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}

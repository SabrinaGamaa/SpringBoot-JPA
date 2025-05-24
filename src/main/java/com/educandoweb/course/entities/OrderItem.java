package com.educandoweb.course.entities;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    // A serialização permite que os objetos sejam convertidos em bytes para serem salvos ou transmitidos.
    @Serial
    private static final long serialVersionUID = 1L;

    // Esta é a chave composta da entidade (PK = Primary Key).
    // Em vez de usar um ID numérico, a chave primária aqui é composta por duas referências:
    // uma para Order e outra para Product. Essa classe OrderItemPK é uma classe auxiliar embutida (embeddable).
    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(){
    }

    // Construtor personalizado que recebe um pedido, um produto, a quantidade e o preço.
    // Associa esses valores à chave composta e aos campos da entidade.
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
//        this.orderItemPK = new OrderItemPK(); // Evita NullPointerException, instanciando a chave composta.
        orderItemPK.setOrder(order); // Define o pedido dentro da chave composta.
        orderItemPK.setProduct(product); // Define o produto dentro da chave composta.
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder(){
        return orderItemPK.getOrder();
    }

    public void setOrder(Order order){
        orderItemPK.setOrder(order);
    }

    public Product getProduct(){
        return orderItemPK.getProduct();
    }

    public void setProduct(Product product){
        orderItemPK.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal(){
        return getPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(orderItemPK, orderItem.orderItemPK);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderItemPK);
    }
}

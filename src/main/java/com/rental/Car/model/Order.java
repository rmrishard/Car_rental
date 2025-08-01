package com.rental.Car.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
//@NoArgsConstructor
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal total_amount;
    @Convert(converter = OrderStatusConverter.class)
    @Column(name = "status")
    private OrderStatus status;
    private Date order_date;
    private Timestamp created_at;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();


    public Long getOrder_id() {
        return order_id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Set<CartItem> getOrderItems() {
        return cartItems;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setOrderItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Order(){}

    public Order(Long order_id, User user, BigDecimal total_amount, OrderStatus status, Date order_date, Timestamp created_at, Set<CartItem> cartItems) {
        this.order_id = order_id;
        this.user = user;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
        this.created_at = created_at;
        this.cartItems = cartItems;
    }
}

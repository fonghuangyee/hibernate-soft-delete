package com.fhy.spring.hibernate.soft_delete.entity;

import com.fhy.spring.hibernate.soft_delete.listener.OrderListener;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "t_order")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE t_order SET deleted = true WHERE id = ?")
@EntityListeners(OrderListener.class)
public class Order extends BaseEntity {

    public int viewCount;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Product getProduct() {
        return proxy(product);
    }
}

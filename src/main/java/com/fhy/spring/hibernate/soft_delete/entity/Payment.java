package com.fhy.spring.hibernate.soft_delete.entity;

import com.fhy.spring.hibernate.soft_delete.listener.PaymentListener;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE payment SET deleted = true WHERE id = ?")
@EntityListeners(PaymentListener.class)
public class Payment extends BaseEntity {

    public int viewCount;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Product getProduct() {
        return unproxy(product, FlushModeType.COMMIT);
    }
}

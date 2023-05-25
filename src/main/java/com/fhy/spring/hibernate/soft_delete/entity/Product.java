package com.fhy.spring.hibernate.soft_delete.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
public class Product extends BaseEntity {

    private String name;
}

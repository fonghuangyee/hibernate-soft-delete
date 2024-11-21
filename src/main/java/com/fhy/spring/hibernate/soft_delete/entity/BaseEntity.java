package com.fhy.spring.hibernate.soft_delete.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @JsonIgnore
    transient Logger log = LoggerFactory.getLogger(BaseEntity.class);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    protected <T> T proxy(T object) {
        try {
            if (object != null) {
                final LazyInitializer lazyInitializer = HibernateProxy.extractLazyInitializer(object);
                if (lazyInitializer != null) {
                    object = (T) Hibernate.unproxy(lazyInitializer.getSession()
                            .createNativeQuery(findByIdQuery(lazyInitializer), lazyInitializer.getPersistentClass())
                            .setParameter("id", lazyInitializer.getIdentifier())
                            .setFlushMode(FlushModeType.COMMIT)
                            .getSingleResult());
                    return object;
                }
            }
            return object;
        } catch (Exception e) {
            log.warn("Failed to load proxy {}: {}", object.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }

    private String findByIdQuery(LazyInitializer lazyInitializer) {
        EntityPersister entityPersister = lazyInitializer.getSession().getSessionFactory().getMappingMetamodel().findEntityDescriptor(lazyInitializer.getPersistentClass());
        String tableName = null;
        if (entityPersister != null && entityPersister instanceof SingleTableEntityPersister)
            tableName = ((SingleTableEntityPersister) entityPersister).getTableName();
        if (!StringUtils.hasText(tableName)) {
            Table table = lazyInitializer.getPersistentClass().getAnnotation(Table.class);
            tableName = table != null ? table.name() : lazyInitializer.getPersistentClass().getSimpleName();
        }
        return "SELECT * FROM " + tableName + " WHERE id = :id";
    }
}

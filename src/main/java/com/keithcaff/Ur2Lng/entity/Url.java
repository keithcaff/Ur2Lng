package com.keithcaff.Ur2Lng.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity @Table(name = "url")
@Getter @Setter @EqualsAndHashCode
public class Url implements Serializable {

    public Url() {}

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }

    public Url(Long id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
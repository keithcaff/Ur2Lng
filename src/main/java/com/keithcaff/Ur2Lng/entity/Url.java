package com.keithcaff.Ur2Lng.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity @Table(name = "url", indexes =  @Index(
        name = "long_url_idx",
        columnList = "longUrl",
        unique = true
))
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
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
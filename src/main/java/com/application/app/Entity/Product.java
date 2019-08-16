package com.application.app.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Embeddable
public class Product{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    private String name;

    private String description;

    private Float price;

    private Integer quantity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isDeleted;


    @Column(name = "created_at", updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @UpdateTimestamp
    private Date updatedAt;
}

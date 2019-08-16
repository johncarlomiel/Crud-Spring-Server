package com.application.app.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Data
public class ProductMongo {
    @Id
    private String _id;
    private String name;
    private String description;
    private float price;
    private Integer quantity;
}

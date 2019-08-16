package com.application.app.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDTO {
    @Embedded
    private Product product;

}

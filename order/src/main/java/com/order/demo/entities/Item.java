package com.order.demo.entities;

import com.order.demo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private int quantity;
    @Embedded
    private Product product;

}

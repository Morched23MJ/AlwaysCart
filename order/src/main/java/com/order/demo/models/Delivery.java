package com.order.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private Long id;
    private Long idOrder;
    private Object address;
}

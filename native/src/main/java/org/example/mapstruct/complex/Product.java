package org.example.mapstruct.complex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private ProductDetail productDetail;
}

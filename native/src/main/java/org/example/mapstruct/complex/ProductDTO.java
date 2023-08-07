package org.example.mapstruct.complex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductDTO
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productId;
    private ProductDetailDTO productDetail;
}

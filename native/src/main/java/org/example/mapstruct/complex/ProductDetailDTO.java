package org.example.mapstruct.complex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductDetailDTO
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
  private String productId;
  private String detailId;
}
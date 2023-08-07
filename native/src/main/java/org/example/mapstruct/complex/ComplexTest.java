package org.example.mapstruct.complex;
/**
 * ComplexTest
 *
 * @author: Diammd99
 * @since: 2023/8/7
 */
public class ComplexTest {
  public static void main(String[] args) {
    ProductDetail productDetail = new ProductDetail("11");
    Product product = new Product("wer", productDetail);
    ProductDTO dto = Demo7Assembler.INSTANCE.toDTO(product);
    System.out.println(dto);
  }
}

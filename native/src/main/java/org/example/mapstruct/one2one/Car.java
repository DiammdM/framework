package org.example.mapstruct.one2one;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Car
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
@Data
@AllArgsConstructor
public class Car {
    private String name;
    private String type;
    private Integer size;
}

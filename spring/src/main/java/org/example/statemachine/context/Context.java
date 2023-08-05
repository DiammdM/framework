package org.example.statemachine.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Context
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Context {
    public String operator;
    public String entityId;

    @Override
    public String toString() {
        return "Context{" + "operator='" + operator + '\'' + ", entityId='" + entityId + '\'' + '}';
    }
}

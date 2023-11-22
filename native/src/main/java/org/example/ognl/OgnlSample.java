package org.example.ognl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import ognl.Ognl;
import ognl.OgnlException;

/**
 * OgnlSample
 *
 * @author: Diammd
 * @create: 2023/11/22 17:36
 */
public class OgnlSample {
    public static void main(String[] args){
        // 示例的 JSON 数据
        String jsonData = "{\"name\":\"John\",\"age\":25,\"address\":{\"city\":\"New York\",\"zip\":\"10001\"},\"remark\":[\"bad boy\",\"new bee\",\"basketballer\"]}";

        try {
            // 创建 OGNL 表达式
            Object expression = Ognl.parseExpression("remark[1]");

            // 解析 JSON 数据
            JSONObject jsonObject = JSON.parseObject(jsonData);
            Object parsedData = Ognl.getValue(expression, jsonObject);

            // 输出结果
            System.out.println("Result: " + parsedData);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }
}

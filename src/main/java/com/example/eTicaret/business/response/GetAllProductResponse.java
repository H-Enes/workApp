package com.example.eTicaret.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Data
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor public class GetAllProductResponse {
 * 
 * private int id; private String name; private int category_id;
 * 
 * }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {
    private int id;
    private String name;
    private int stock_quantity;
}
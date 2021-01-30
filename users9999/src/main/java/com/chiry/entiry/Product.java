package com.chiry.entiry;

import lombok.Data;

import java.util.Date;

/**
 * @program: first
 * @description:
 * @author: Chiry
 * @create: 2021-01-30 14:48
 **/

@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private Date update;
}

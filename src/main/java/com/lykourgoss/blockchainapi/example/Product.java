package com.lykourgoss.blockchainapi.example;

import lombok.Builder;

@Builder
public class Product {
    private String code;
    private String title;
    private float price;
    private String description;
    private String category;
}

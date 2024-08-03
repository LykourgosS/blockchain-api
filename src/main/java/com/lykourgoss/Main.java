package com.lykourgoss;

import com.google.gson.Gson;
import com.lykourgoss.blockchainapi.Blockchain;
import com.lykourgoss.example.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Product product = new Product.Builder("123")
                .title("Test product")
                .description("Only for testing")
                .category("dairy")
                .price(1.05f)
                .build();

        System.out.println(product.getBlockData());

        Blockchain<Product> chain = new Blockchain<>();
        chain.addBlock(product);

        System.out.println(new Gson().toJson(chain.getBlocks().getFirst()));
    }
}
package com.lykourgoss;

import com.google.gson.Gson;
import com.lykourgoss.blockchainapi.Blockchain;
import com.lykourgoss.blockchainapi.miners.SingleThreadMiner;
import com.lykourgoss.example.Product;

import java.text.SimpleDateFormat;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Product product = new Product.Builder("123")
                .title("Test product")
                .description("Only for testing")
                .category("dairy")
                .price(1.05F)
                .build();

        Product product2 = new Product.Builder("1234")
                .title("Test product 2")
                .description("Only for testing the previous one")
                .category("not dairy")
                .price(1)
                .build();

        Blockchain<Product> chain = new Blockchain<>(new SingleThreadMiner(), 5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        chain.addBlock(product);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        chain.addBlock(product2);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));

//        System.out.println(chain.toJson());
        System.out.println(chain.validate());
    }
}
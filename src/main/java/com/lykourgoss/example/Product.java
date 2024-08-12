package com.lykourgoss.example;

import com.lykourgoss.blockchainapi.core.Blockable;
import com.lykourgoss.blockchainapi.helpers.jsonizer.GsonJsonizer;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product implements Blockable {
    @Id
    private String code;
    private String title;
    private float price;
    private String description;
    private String category;

    public Product(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.price = builder.price;
        this.description = builder.description;
        this.category = builder.category;
    }

    public Product() {

    }

    public static final class Builder{
        private final String code;
        private String title;
        private float price;
        private String description;
        private String category;

        public Builder(String code) {
            this.code = code;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder price(float price){
            this.price = price;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder category(String category){
            this.category = category;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }

    @Override
    public String getBlockData() {
        return GsonJsonizer.INSTANCE.toJson(this);
    }
}

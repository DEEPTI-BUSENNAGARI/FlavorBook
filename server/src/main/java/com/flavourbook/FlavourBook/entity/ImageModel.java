package com.flavourbook.FlavourBook.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image_model")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;

    @Column(length = 50000000) // Adjust the length as needed
    private byte[] picByte;

    public ImageModel() {}

    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
}

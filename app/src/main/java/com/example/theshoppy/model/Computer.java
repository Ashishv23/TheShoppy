package com.example.theshoppy.model;

public class Computer {
    public enum Type {
        LAPTOP,
        DESKTOP
    }

    private Type type;
    private String brand;
    private double basePrice;

    // Static constants for brand prices
    public static final double DELL_LAPTOP_PRICE = 1249.0;
    public static final double LENOVA_LAPTOP_PRICE = 1549.0;
    public static final double HP_LAPTOP_PRICE = 1150.0;
    public static final double DELL_DESKTOP_PRICE = 475.0;
    public static final double LENOVA_DESKTOP_PRICE = 450.0;
    public static final double HP_DESKTOP_PRICE = 400.0;

    public Computer(Type type, String brand, double basePrice) {
        this.type = type;
        this.brand = brand;
        this.basePrice = basePrice;
    }

    public Type getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Static method to get base price based on type and brand
    public static double getBasePrice(Type type, String brand) {
        switch (type) {
            case LAPTOP:
                switch (brand) {
                    case "Dell":
                        return DELL_LAPTOP_PRICE;
                    case "Lenova":
                        return LENOVA_LAPTOP_PRICE;
                    case "HP":
                        return HP_LAPTOP_PRICE;
                    default:
                        return 0.0; // Handle unknown brand if needed
                }
            case DESKTOP:
                switch (brand) {
                    case "Dell":
                        return DELL_DESKTOP_PRICE;
                    case "Lenova":
                        return LENOVA_DESKTOP_PRICE;
                    case "HP":
                        return HP_DESKTOP_PRICE;
                    default:
                        return 0.0; // Handle unknown brand if needed
                }
            default:
                return 0.0; // Handle unknown type if needed
        }
    }
}

package ch.heigvd.gen2019;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
    private int id;

    public Order(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return id;
    }

    public int getProductsCount() {
        return products.size();
    }

    public Product getProduct(int j) {
        return products.get(j);
    }

    public void AddProduct(Product product) {
        products.add(product);
    }

    public static class Product {
        public static final int SIZE_NOT_APPLICABLE = -1;
        private String code;
        private Color color;
        private int size;
        private double price;
        private String currency;

        public Product(String code, Color color, int size, double price, String currency) {
            this.code = code;
            this.color = color;
            this.size = size;
            this.price = price;
            this.currency = currency;
        }

        public String getCode() {
            return code;
        }

        public Color getColor() {
            return color;
        }

        public int getSize() {
            return size;
        }

        public double getPrice() {
            return price;
        }

        public String getCurrency() {
            return currency;
        }

        enum Color {
            blue,
            yellow,
            red,
            no_color;

            @Override
            public String toString() {
                if (this == no_color) {
                    return "no color";
                }
                return this.name();
            }
        }
    }
}

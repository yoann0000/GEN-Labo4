package ch.heigvd.gen2019;

import java.util.List;

public class OrdersWriter {
    private List<Order> orders;

    public OrdersWriter(List<Order> orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuilder sb = new StringBuilder("{\"orders\": [");
        printOrders(sb, orders);
        return sb.append("]}").toString();
    }

    private void printOrders(StringBuilder sb, List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            sb.append("{");
            printOrderId(sb, order);
            sb.append(", ");
            printProducts(sb, order);
            sb.append("}, ");
        }
        removeComma(sb, orders.size());
    }

    private void printProducts(StringBuilder sb, Order order) {
        sb.append("\"products\": [");
        for (int j = 0; j < order.getProductsCount(); j++) {
            Order.Product product = order.getProduct(j);
            printProduct(sb, product);
            sb.append(", ");
        }

        removeComma(sb, order.getProductsCount());
        sb.append("]");
    }

    private void printOrderId(StringBuilder sb, Order order) {
        sb.append("\"id\": ").append(order.getOrderId());
    }

    private void printProduct(StringBuilder sb, Order.Product product) {
        sb.append("{");
        printProductCode(sb, product);
        printProductColor(sb, product);

        if (product.getSize() != Order.Product.SIZE_NOT_APPLICABLE) {
            printProductSize(sb, product);
        }

        printProductPrice(sb, product);
        printProductCurrency(sb, product);
    }

    private void printProductCurrency(StringBuilder sb, Order.Product product) {
        sb.append("\"currency\": \"").append(product.getCurrency()).append("\"}");
    }

    private void printProductPrice(StringBuilder sb, Order.Product product) {
        sb.append("\"price\": ").append(product.getPrice()).append(", ");
    }

    private void printProductSize(StringBuilder sb, Order.Product product) {
        sb.append("\"size\": \"").append(getSizeFor(product)).append("\", ");
    }

    private void printProductColor(StringBuilder sb, Order.Product product) {
        sb.append("\"color\": \"").append(getColorFor(product)).append("\", ");
    }

    private void printProductCode(StringBuilder sb, Order.Product product) {
        sb.append("\"code\": \"").append(product.getCode()).append("\", ");
    }

    private void removeComma(StringBuilder sb, int ordersCount) {
        if (ordersCount > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
    }
    
    private String getSizeFor(Order.Product product) {
        String[] sizes = {"Invalid size", "XS", "S", "M", "L", "XL"};
        
        if(product.getSize() >= 1 && product.getSize() <=6)
            return sizes[product.getSize()];
        else
            return sizes[0];
    }

    private String getColorFor(Order.Product product) {
        return product.getColor().toString();
    }
}
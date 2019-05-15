package ch.heigvd.gen2019;

public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuilder sb = new StringBuilder("{\"orders\": [");
        printOrders(sb, orders);
        return sb.append("]}").toString();
    }

    private void printOrders(StringBuilder sb, Orders orders) {
        for (int i = 0; i < orders.getOrdersCount(); i++) {
            Order order = orders.getOrder(i);
            sb.append("{");
            printOrderId(sb, order);
            sb.append(", ");
            printProducts(sb, order);
            sb.append("}, ");
        }
        removeComma(sb, orders.getOrdersCount());
    }

    private void printProducts(StringBuilder sb, Order order) {
        sb.append("\"products\": [");
        for (int j = 0; j < order.getProductsCount(); j++) {
            Product product = order.getProduct(j);
            printProduct(sb, product);
            sb.append(", ");
        }

        removeComma(sb, order.getProductsCount());
        sb.append("]");
    }

    private void printOrderId(StringBuilder sb, Order order) {
        sb.append("\"id\": ").append(order.getOrderId());
    }

    private void printProduct(StringBuilder sb, Product product) {
        sb.append("{");
        printProductCode(sb, product);
        printProductColor(sb, product);

        if (product.getSize() != Product.SIZE_NOT_APPLICABLE) {
            printProductSize(sb, product);
        }

        printProductPrice(sb, product);
        printProductCurrency(sb, product);
    }

    private void printProductCurrency(StringBuilder sb, Product product) {
        sb.append("\"currency\": \"").append(product.getCurrency()).append("\"}");
    }

    private void printProductPrice(StringBuilder sb, Product product) {
        sb.append("\"price\": ").append(product.getPrice()).append(", ");
    }

    private void printProductSize(StringBuilder sb, Product product) {
        sb.append("\"size\": \"").append(getSizeFor(product)).append("\", ");
    }

    private void printProductColor(StringBuilder sb, Product product) {
        sb.append("\"color\": \"").append(getColorFor(product)).append("\", ");
    }

    private void printProductCode(StringBuilder sb, Product product) {
        sb.append("\"code\": \"").append(product.getCode()).append("\", ");
    }

    private void removeComma(StringBuilder sb, int ordersCount) {
        if (ordersCount > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
    }
    
    private String getSizeFor(Product product) {
        String[] sizes = {"Invalid size", "XS", "S", "M", "L", "XL"};
        
        if(product.getSize() >= 1 && product.getSize() <=6)
            return sizes[product.getSize()];
        else
            return sizes[0];
    }

    private String getColorFor(Product product) {
        switch (product.getColor()) {
            case 1:
                return "blue";
            case 2:
                return "red";
            case 3:
                return "yellow";
            default:
                return "no color";
        }
    }
}
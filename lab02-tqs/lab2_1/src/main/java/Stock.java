public class Stock {
    private String label;
    private final double quantity;

    public Stock(String label, double quantity) {
        this.label = label;
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

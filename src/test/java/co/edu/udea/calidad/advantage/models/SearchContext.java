package co.edu.udea.calidad.advantage.models;

public class SearchContext {

    private static String product;

    public static void setProduct(String value) {
        product = value;
    }

    public static String getProduct() {
        return product;
    }
}

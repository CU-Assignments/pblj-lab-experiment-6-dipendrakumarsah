To develop a Java program that processes a large dataset of products using Streams class to:
  - Group products by category
  - Find the most expensive product in each category
  - Calculate the average price of all products


//CODE
  import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = readProductsFromCSV("products.csv");

        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))));

        double averagePrice = products.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));

        System.out.println("Products grouped by category:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList.stream().map(Product::getName).collect(Collectors.joining(", ")));
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensiveByCategory.forEach((category, product) ->
                System.out.println(category + ": " + product.map(Product::getName).orElse("No product")));

        System.out.println("\nAverage Price of All Products: " + averagePrice);
    }

    private static List<Product> readProductsFromCSV(String fileName) {
        List<Product> products = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            products = lines.map(line -> line.split(","))
                    .filter(parts -> parts.length == 3)
                    .map(parts -> new Product(parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return products;
    }
}



  
    Test Cases
    Test Case	                                     Input Data	                                                                           Expected Output
    Case 1: Normal Case             	     Sample dataset with Electronics, Clothing, and Footwear	                      Grouped products, Most Expensive per category, Average price
    Case 2: Single Category Only           All products in "Electronics"	                                                One category, Most Expensive in Electronics, Average of all
    Case 3: Same Price in a Category	     Two products with the same highest price in "Footwear"	                        Any of the most expensive ones is displayed
    Case 4: Only One Product	             One product "Laptop" in "Electronics"	                                        Laptop as the most expensive, Laptop as the only average
    Case 5: Empty List	                   No products	                                                                  No grouping, No most expensive product, Average price = 0

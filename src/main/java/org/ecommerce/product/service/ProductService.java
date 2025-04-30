package org.ecommerce.product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.product.entity.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    DataSource dataSource;

    public void addProduct(Product p) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT add_product(?, ?, ?, ?, ?)")) {
            stmt.setString(1, p.name);
            stmt.setString(2, p.description);
            stmt.setString(3, p.price);
            stmt.setString(4, p.stock);
            stmt.setString(5, p.category);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: " + e.getMessage(), e);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.id = Integer.valueOf(String.valueOf(rs.getInt("id")));
                p.name = rs.getString("name");
                p.description = rs.getString("description");
                p.price = rs.getString("price");
                p.stock = rs.getString("stock");
                p.category = rs.getString("category");
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: " + e.getMessage(), e);
        }
        return products;
    }
}


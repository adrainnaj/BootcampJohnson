package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProductDao extends MySqlDaoBase implements ProductDao {
    public MySqlProductDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public List<Product> getAllProducts()
    {
        // get all categories
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products " +
                "WHERE (? IS NULL OR category_id = ?)" +
                "   AND (? IS NULL OR price >= ?)" +
                "   AND (? IS NULL OR price <= ?) " +
                "   AND (? IS NULL OR color = ?)";


        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))

            {

            statement.setObject(1, categoryId);
            statement.setObject(2, categoryId);

            statement.setObject(3, minPrice);
            statement.setObject(4, minPrice);

            statement.setObject(5, maxPrice);
            statement.setObject(6, maxPrice);

            statement.setObject(7, color);
            statement.setObject(8, color);

            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                products.add(mapRow(row));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public List<Product> listByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE category_id = ? ";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                Product product = mapRow(row);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    @Override
    public Product getById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            ResultSet row = statement.executeQuery();

            if (row.next()) {
                return mapRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean create(Product product) {

        String sql = "INSERT INTO products(name, price, category_id, description, color, image_url, stock, featured) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getColor());
            statement.setString(6, product.getImageUrl());
            statement.setInt(7, product.getStock());
            statement.setBoolean(8, product.isFeatured());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                    // Retrieve the auto-incremented ID
                    // get the newly inserted category
                }
                    return true;
                }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(int productId, Product product) {
        String sql = "UPDATE products " +
                " SET name = ? " +
                "   , price = ? " +
                "   , category_id = ? " +
                "   , description = ? " +
                "   , color = ? " +
                "   , image_url = ? " +
                "   , stock = ? " +
                "   , featured = ? " +
                " WHERE product_id = ?;";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getColor());
            statement.setString(6, product.getImageUrl());
            statement.setInt(7, product.getStock());
            statement.setBoolean(8, product.isFeatured());
            statement.setInt(9, productId);

            int rowsAf = statement.executeUpdate();
            return rowsAf > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        @Override
        public boolean delete(int productId)
        {

            String sql = "DELETE FROM products " +
                    " WHERE product_id = ?";

            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {

                statement.setInt(1, productId);
                int rowsAF = statement.executeUpdate();
                return rowsAF > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected static Product mapRow (ResultSet row) throws SQLException
        {
            int productId = row.getInt("product_id");
            String name = row.getString("name");
            BigDecimal price = row.getBigDecimal("price");
            int categoryId = row.getInt("category_id");
            String description = row.getString("description");
            String color = row.getString("color");
            int stock = row.getInt("stock");
            boolean isFeatured = row.getBoolean("featured");
            String imageUrl = row.getString("image_url");

            Product product = new Product()
            {{
                setProductId(productId);
                setName(name);
                setPrice(price);
                setCategoryId(categoryId);
                setDescription(description);
                setColor(color);
                setStock(stock);
                setFeatured(isFeatured);
                setImageUrl(imageUrl);
            }};

            return new Product(productId,name, price,categoryId,description,color,stock,isFeatured,imageUrl);
        }
    }
package ru.job4j.grabber;

import ru.job4j.grabber.utils.Post;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private final Connection cnn;

    public PsqlStore(Properties cfg) throws Exception {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
         cnn = DriverManager.getConnection(cfg.getProperty("url"),
                 cfg.getProperty("username"),
                 cfg.getProperty("password"));
    }

    private static void tryStatementBlock(String sql, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection cnn) {
        String createTableSQL = "create table if not exists post (\n"
               + "id serial primary key,\n"
               + "name varchar(255),\n"
               + "description text,\n"
               + "link varchar(255) NOT NULL\n"
               + "      CONSTRAINT name_unique UNIQUE,\n"
               + "dateCreated varchar(255));";
        tryStatementBlock(createTableSQL, cnn);
    }

    private static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream in = PsqlStore.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO post(name, description, link, dateCreated) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = cnn.prepareStatement(sql)) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setString(3, post.getLink());
            preparedStatement.setString(4, post.getDateCreated());
            int rows = preparedStatement.executeUpdate();
            System.out.println("rows added: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post";
        try (Statement statement = cnn.createStatement()) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                Post post = new Post(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getString("description"),
                        set.getString("link"),
                        set.getString("dateCreated")
                );
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(String id) {
        Post post = new Post();
        String sql = "SELECT * FROM post WHERE id=?";
        try (PreparedStatement statement = cnn.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            set.next();
            post = new Post(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("description"),
                    set.getString("link"),
                    set.getString("dateCreated")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    private void drop() {
        String sql = "DROP TABLE IF EXISTS post";
        try (Statement statement = cnn.createStatement()) {
           statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = getProperties("jdbc.properties");
        PsqlStore store = new PsqlStore(properties);
        store.drop();
        createTable(store.cnn);
        Post testPost = new Post(999, "Test", "Test", "Test", "Test");

        store.save(testPost);
        System.out.println(store.getAll() + " --- getAll");
        System.out.println(store.findById("1") + " --- findById");
    }
}
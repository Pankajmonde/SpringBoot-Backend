import java.sql.*;

public class JDBCDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/JdbcDemo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection estabalish");
            //insertStudent(conn,"alice","pankaj@gmail.com");
            updateStudent(conn, 1, "bob", "bob@gmail.com");
            seleteStudent(conn);
            deleteStudent(conn, 1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    private static void insertStudent(Connection conn, String name, String email) {
        String sql = "INSert into student (name , email) values('" + name + "','" + email + "')";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Inserted " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void seleteStudent(Connection conn) {
        String sql = "SELECT * FROM STUDENT";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("Student List ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(id + " : " + name + " : " + email + " : ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateStudent(Connection conn, int id, String name, String email) {

        String sql = "UPDATE STUDENT SET NAME = ?, EMAIL = ? WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();

            System.out.println("updated " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static void deleteStudent(Connection conn, int id) {

        String sql = "DELETE FROM STUDENT WHERE ID = " + id;

        try (Statement stmt = conn.createStatement()) {

            int rows = stmt.executeUpdate(sql);

            System.out.println("Deleted " + rows + " row(s)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
 Connection conn=null;
        try {
           conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection estabalish");
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

 */
import java.sql.*;

public class TransactionDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/JdbcDemo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection to database");
            //turned off auto saved
            conn.setAutoCommit(false);
            try{  // Order , OrderItems
                // Insert into order

                int orderId = insertOrder(conn, 101, "alice", 2000.0);

                // Insert into order Item
                insertOrderItem(conn, orderId,"laptop",1,2000.0);
                //manual commit
                conn.commit();
                System.out.println("transaction committed Successfully");

            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                System.out.println("Operation rollback successfuly");

            }finally {
             conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    private static void insertOrderItem(Connection conn, int orderId, String productName, int  quantity, double price) {
        String sql="INSERT INTO orders_items(order_id, product_name, quantity, price)  " +"VALUE(?,?,?,?)";
        try(PreparedStatement pstmt =conn.prepareStatement(sql)) {
            pstmt.setInt(1,orderId);
            pstmt.setString(2,productName);
            pstmt.setInt(3,quantity);
            pstmt.setDouble(4,price);

            int rows=pstmt.executeUpdate();
            System.out.println("INSERTED INTO orders items:"+ rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int insertOrder(Connection conn, int  customerId, String customerName, double price) {
    String sql="INSERT INTO orders(user_id, customer_name, total_amount)  " +"VALUE(?,?, ?)";
    try(PreparedStatement pstmt =conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
        pstmt.setInt(1,customerId);
        pstmt.setString(2,customerName);
        pstmt.setDouble(3,price);


        int rows=pstmt.executeUpdate();
        System.out.println("INSERTED INTO orders :"+ rows);
        try(ResultSet rs= pstmt.getGeneratedKeys()) {
          if(rs.next()){
              int orderId=rs.getInt(1);
              System.out.println("Order id"+ orderId);
              return orderId;

          }else {
            throw  new SQLException("Order id not generated");
          }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    }
}
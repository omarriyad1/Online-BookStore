import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bookstore2_schema",
                    "root",
                    "Omar12345");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ADMIN");

            while(resultSet.next()){
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("name"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}

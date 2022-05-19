package dao;





import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private  static  Connection connection;




    public CrudUtil(Connection connection) {
        this.connection = connection;



    }

    private   static PreparedStatement getPreparedStatement(String sql , Connection connection, Object... args) throws SQLException, ClassNotFoundException {

            //Connection connection =
            //Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for(int i =0 ; i < args.length; i++){
                preparedStatement.setObject(i+1, args[i]);

            }


            return preparedStatement;






    }


    public static  boolean executeUpdate(String sql,Connection connection, Object... args) throws SQLException, ClassNotFoundException {
        int i = getPreparedStatement(sql, connection, args).executeUpdate();

        return i>0;



    }


    public static ResultSet executeQuery(String sql,Connection connection, Object... args) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getPreparedStatement(sql, connection, args).executeQuery();


        return resultSet;

    }










}

package clients.db;

import clients.beans.Category;
import clients.beans.Coupon;

import java.sql.*;
import java.util.Map;

public class DBTools {

//    public static boolean runQuery(String sql) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.execute();
//
//            return true;
//
//        } catch (InterruptedException err) {
//            System.out.println(err.getMessage());
//            return false;
//        } finally {
//            ConnectionPool.getInstance().restoreConnection(connection);
//        }
//    }

    public static boolean runQuery(String sql, Map<Integer, Object> params) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    } else if (value instanceof Category) {
                        preparedStatement.setString(key, String.valueOf((Category) value));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().restoreConnection(connection);
        }

        return false;
    }

    public static ResultSet runQueryForResult(String sql, Map<Integer, Object> params) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    } else if (value instanceof Category) {
                        preparedStatement.setString(key, String.valueOf((Category) value));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });

            return preparedStatement.executeQuery();

        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {

            ConnectionPool.getInstance().restoreConnection(connection);
        }
    }

}

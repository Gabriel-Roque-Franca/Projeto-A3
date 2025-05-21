// src/db/DBConnector.java
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

    // !!! MUDE ESTES VALORES PARA OS DO SEU BANCO DE DADOS !!!
    private static final String URL = "jdbc:mysql://localhost:3306/bazar_db"; // Ou jdbc:postgresql, jdbc:h2, etc.
    private static final String USER = "root"; // Seu usuário do banco
    private static final String PASSWORD = "sua_senha"; // Sua senha do banco

    public static Connection getConnection() throws SQLException {
        // Para MySQL a partir do Java 6+, não é estritamente necessário Class.forName()
        // mas pode ser útil para compatibilidade.
        // Se usar, descomente e adapte ao seu driver (ex: com.mysql.cj.jdbc.Driver)
        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver");
        // } catch (ClassNotFoundException e) {
        //     System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        //     throw new SQLException("Driver JDBC não encontrado.");
        // }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Métodos para fechar recursos do banco de dados de forma segura
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public static void closeConnection(Connection conn, Statement stmt) {
        closeConnection(conn); // Chama o método anterior para fechar a conexão
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar statement: " + e.getMessage());
            }
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        closeConnection(conn, stmt); // Chama o método anterior para fechar conexão e statement
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
    String url;
    public Connection conectarBD(){
        Connection conex = null;

        try {
            String url = "jdbc:mysql://localhost:3306/atividadebanco?user=root&password=";
            conex =  DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO!");
        }
        return conex;
    }
    public void inserirUsuario(String nome,String email) throws SQLException{
        Connection conexao = conectarBD();
        PreparedStatement instrucao = null;

        try {
            instrucao = conexao.prepareStatement("INSERT INTO usuarios (nome, email) VALUES (?, ?)");
            instrucao.setString(1, nome);
            instrucao.setString(2, email);


            instrucao.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + e.getMessage());
        } finally {

            if (instrucao != null) {
                instrucao.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
    }


    public void consultarUsuario(String nome) throws SQLException {
        Connection conexao = conectarBD();
        PreparedStatement instrucao = null;
        ResultSet resultado = null;
        try {
            // Cria uma instrução preparada para consultar o banco de dados
            instrucao = conexao.prepareStatement("SELECT * FROM usuarios WHERE nome = ?");
            instrucao.setString(1, nome);

            // Executa a consulta e armazena os resultados
            resultado = instrucao.executeQuery();

            // Processa os resultados (se houver)
            if (resultado.next()) {
                String email = resultado.getString("email");
                JOptionPane.showMessageDialog(null, "Usuário encontrado:\nNome: " + nome + "\nEmail: " + email);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuário: " + e.getMessage());
        } finally {
            // Fecha os recursos
            if (resultado != null) {
                resultado.close();
            }
            if (instrucao != null) {
                instrucao.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
    }


}
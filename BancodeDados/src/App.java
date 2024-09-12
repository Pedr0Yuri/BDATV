import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();

        try {
            // Inserir um novo usuário
            conexao.inserirUsuario("Pedro Yuri", "pyuri.lima@gmail.com");

            // Consultar o usuário inserido
            conexao.consultarUsuario("Pedro Yuri");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
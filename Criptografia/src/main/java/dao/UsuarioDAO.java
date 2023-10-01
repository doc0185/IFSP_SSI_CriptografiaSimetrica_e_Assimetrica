package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
	String serverName="localhost";
	String dataBasePort="3306";
	String mydatabase="criptografia";
	String url="jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + mydatabase;
	String usernameb="root";
	String passwordb="";
	
	public int cadastrarUsuario(Usuario u) throws ClassNotFoundException{
		String INSERT_USERS_SQL = "INSERT INTO usuario" + 
                "(nome, email, password, password_criptografada, password_criptografada_rsa, private_key, public_key) VALUES " +
                "(?,?,?,?,?,?,?);";
        
        int result = 0;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
        	preparedStatement.setString(1, u.getNome());
        	preparedStatement.setString(2, u.getEmail());
        	preparedStatement.setString(3, u.getSenha());
        	preparedStatement.setBytes(4, u.getSenha_criptografada());
        	preparedStatement.setBytes(5, u.getSenha_criptografada_rsa());
        	preparedStatement.setString(6, u.getPrivateKey());
        	preparedStatement.setString(7, u.getPublicKey());
        	System.out.println(preparedStatement);
        	
        	result = preparedStatement.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
	}
	
	public Usuario buscarNome(String nome) throws ClassNotFoundException {
		//String SELECT_USERS_SQL = "SELECT id, password, password_criptografada, password_criptografada_string, nome, email FROM usuario WHERE nome = ? and password_criptografada_string = ?";
		String SELECT_USERS_SQL = "SELECT * FROM `usuario` WHERE `nome` = ?";
		
        Class.forName("com.mysql.jdbc.Driver");
        
        Usuario u = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, nome);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		u = new Usuario();
        		u.setSenha(rs.getString("password"));
        		u.setSenha_criptografada(rs.getBytes("password_criptografada"));
        		u.setSenha_criptografada_rsa(rs.getBytes("password_criptografada_rsa"));
        		u.setNome(rs.getString("nome"));
        		u.setEmail(rs.getString("email"));
        		u.setPrivateKey(rs.getString("private_key"));
        		u.setPublicKey(rs.getString("public_key"));
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return u;
	}
	
	public Usuario buscarUsuario(String nome, byte[] senha_criptografada) throws ClassNotFoundException {
		System.out.println(senha_criptografada);
		//String SELECT_USERS_SQL = "SELECT * FROM usuario WHERE nome = ? and password_criptografada_rsa = ?";
		String SELECT_USERS_SQL = "SELECT * FROM `usuario` WHERE `password_criptografada_rsa` = ? and `nome` = ?";
		
        Class.forName("com.mysql.jdbc.Driver");
        
        Usuario u = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setBytes(1, senha_criptografada);
        	//preparedStatement.setBytes(2, password);
        	preparedStatement.setString(2, nome);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		u = new Usuario();
        		u.setSenha(rs.getString("password"));
        		u.setSenha_criptografada(rs.getBytes("password_criptografada"));
        		u.setSenha_criptografada_rsa(rs.getBytes("password_criptografada_rsa"));
        		u.setNome(rs.getString("nome"));
        		u.setEmail(rs.getString("email"));
        		u.setPrivateKey(rs.getString("private_key"));
        		u.setPublicKey(rs.getString("public_key"));
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return u;
	}
}

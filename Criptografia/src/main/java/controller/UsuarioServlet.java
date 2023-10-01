package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AesCriptografia;
import model.RsaCriptografia;
import model.Usuario;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dao.UsuarioDAO;

@WebServlet("/UserServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senha = request.getParameter("senha");
		String confirma_senha = request.getParameter("confirma_senha");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		if(!senha.equals(confirma_senha)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_senha_incorreta.jsp");
			dispatcher.forward(request, response);
		}
		
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setNome(nome);
		u.setSenha(senha);
		
		try {
			u.setSenha_criptografada(AesCriptografia.encrypt(senha));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		KeyPair keyPair = null;
		byte[] assinaturaDigital = null;
		try {
			keyPair = RsaCriptografia.geraRSAKeyPair();
			System.out.println(keyPair.getPublic());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String privateEncode = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
		u.setPrivateKey(privateEncode);
		String publicEncode = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		u.setPublicKey(publicEncode);
		
		try {
			u.setSenha_criptografada_rsa(RsaCriptografia.encripta(senha, keyPair.getPublic()));
			assinaturaDigital = RsaCriptografia.geraAssinaturaDigital(senha.getBytes(), keyPair.getPrivate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			usuarioDAO.cadastrarUsuario(u);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastrado.jsp");
		dispatcher.forward(request, response);
	}

}

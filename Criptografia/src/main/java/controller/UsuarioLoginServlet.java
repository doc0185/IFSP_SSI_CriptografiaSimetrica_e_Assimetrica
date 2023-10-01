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
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dao.UsuarioDAO;

@WebServlet("/UserLoginServlet")
public class UsuarioLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    public UsuarioLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		byte[] senha_criptografada = null;
		byte[] senha_criptografada_rsa = null;
		
		try {
			senha_criptografada = AesCriptografia.encrypt(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Usuario u = null;
		try {
			u = usuarioDAO.buscarNome(nome);
			if(u != null) {
				byte[] privateBD = Base64.getDecoder().decode(u.getPrivateKey());
				KeyFactory keyFactory = KeyFactory.getInstance("RSA"); 
				PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBD);
				PrivateKey privateKeyDoUsuario = keyFactory.generatePrivate(keySpec);
				if(RsaCriptografia.decripta(u.getSenha_criptografada_rsa(), privateKeyDoUsuario).equals(senha)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/welcome.jsp");
					dispatcher.forward(request, response);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			if(usuarioDAO.buscarUsuario(nome, senha_criptografada_rsa) != null) {
				ServletContext sc = getServletContext();
				sc.setAttribute("nome", nome);
				sc.setAttribute("senha", senha);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/welcome.jsp");
				dispatcher.forward(request, response);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}

}

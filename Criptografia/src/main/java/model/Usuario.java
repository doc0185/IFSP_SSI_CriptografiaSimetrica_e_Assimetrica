package model;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private byte[] senha_criptografada;
	private byte[] senha_criptografada_rsa;
	private String privateKey;
	private String publicKey;
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public byte[] getSenha_criptografada() {
		return senha_criptografada;
	}
	public void setSenha_criptografada(byte[] senha_criptografada) {
		this.senha_criptografada = senha_criptografada;
	}
	public byte[] getSenha_criptografada_rsa() {
		return senha_criptografada_rsa;
	}
	public void setSenha_criptografada_rsa(byte[] senha_criptografada_rsa) {
		this.senha_criptografada_rsa = senha_criptografada_rsa;
	}

	
	
	
	
	

}

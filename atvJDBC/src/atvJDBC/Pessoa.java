package atvJDBC;

import java.util.Objects;

public class Pessoa {
	private int id;
	private String nome;
	private String sobrenome;
	private String email;
	private int idade;
		
	public Pessoa(int id, String nome, String sobrenome, String email, int idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.idade = idade;
	}
	
	public Pessoa() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, idade, nome, sobrenome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(email, other.email) && idade == other.idade && Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", idade=" + idade + "]";
	}

	
	
}

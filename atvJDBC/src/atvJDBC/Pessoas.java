package atvJDBC;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Pessoas {
	List<Pessoa> Pessoas = new ArrayList<>();
	int i=0;
	String nomePessoa;
	String idadePessoa;
	String sobrenomePessoa;
	String emailPessoa;
	String impressao = "";
	String url;
	String user;
	String password;
	String sqlInsert;
	String sqlSelect;
	String sqlDelete;
	String sqlUpdate;
	Connection conexao = null;
	PreparedStatement ps;

	public void inserirPessoa(){
		try {
			url = JOptionPane.showInputDialog(null, "Digite a url do banco de dados: ", null, 1);				
			user = JOptionPane.showInputDialog(null, "Digite usuário do banco de dados: ", "Qual o usuário do banco de dados?", 1);					
			password = JOptionPane.showInputDialog(null, "Digite a senha do usuário: ", null, 1);
			Class.forName("com.mysql.cj.jdbc.Driver");			
			conexao = DriverManager.getConnection(url, user, password);

			sqlInsert = "INSERT INTO Pessoas.pessoa(nome, sobrenome, idade, email) VALUES(?,?,?,?);";
			ps = conexao.prepareStatement(sqlInsert);

			nomePessoa = JOptionPane.showInputDialog(null, "Digite o nome: ", "Nome"  , 1);
			
			if (nomePessoa != null) {
				sobrenomePessoa = JOptionPane.showInputDialog(null, "Digite o sobrenome: ", "Sobrenome", 1);
				idadePessoa = JOptionPane.showInputDialog(null, "Digite a Idade: ", "Idade", 1);
				emailPessoa = JOptionPane.showInputDialog(null, "Digite o email: ", "Email", 1);
				ps.setString(1, nomePessoa);
				ps.setString(2, sobrenomePessoa);
				ps.setInt(3, Integer.parseInt(idadePessoa));
				ps.setString(4, emailPessoa);
				ps.execute();
			}

		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("ERRO! DIGITE UM NÚMERO!\n");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void listarPessoas() {

		try {
			url = JOptionPane.showInputDialog(null, "Digite a url do banco de dados: ", null, 1);				
			user = JOptionPane.showInputDialog(null, "Digite usuário do banco de dados: ", "Qual o usuário do banco de dados?", 1);					
			password = JOptionPane.showInputDialog(null, "Digite a senha do usuário: ", null, 1);
			Class.forName("com.mysql.cj.jdbc.Driver");			
			conexao = DriverManager.getConnection(url, user, password);
			
			sqlSelect = "select * from Pessoas.pessoa";
			ps = conexao.prepareStatement(sqlSelect);
			ResultSet retorno = ps.executeQuery();
			
			impressao = "";
			while(retorno.next()) {
				impressao += "ID: " + retorno.getString(1) + "\nNome: " + retorno.getString(2) + "\nSobrenome: " + retorno.getString(3) + "\nIdade: " + retorno.getInt(4) + "\nEmail: " + retorno.getString(5) + "\n=========================\n";				
			}
			JOptionPane.showMessageDialog(null, impressao, "Lista de pessoas", 1);

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void removerPessoa() {
		try {
			url = JOptionPane.showInputDialog(null, "Digite a url do banco de dados: ", null, 1);				
			user = JOptionPane.showInputDialog(null, "Digite usuário do banco de dados: ", "Qual o usuário do banco de dados?", 1);					
			password = JOptionPane.showInputDialog(null, "Digite a senha do usuário: ", null, 1);
			Class.forName("com.mysql.cj.jdbc.Driver");			
			conexao = DriverManager.getConnection(url, user, password);

			sqlDelete = "DELETE FROM Pessoas.pessoa WHERE id = ?;";

			String remocao;
			remocao = JOptionPane.showInputDialog(null, "Digite o id da pessoa que deseja remover: ", "Remoção", 1);

			PreparedStatement psSelect = conexao.prepareStatement("SELECT * FROM Pessoas.pessoa WHERE id = ?");
			psSelect.setInt(1, Integer.parseInt(remocao));
			ResultSet retorno = psSelect.executeQuery();

			if (retorno.next()) {
				impressao = "ID: " + retorno.getString(1) + "\nNome: " + retorno.getString(2) + "\nSobrenome: " + retorno.getString(3);
				String[] opcoes = {"Sim", "Não"};
				int escolha = JOptionPane.showOptionDialog(null, impressao, "Tem certeza que deseja deletar esta pessoa?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

				if (escolha == 0) {
					PreparedStatement psDelete = conexao.prepareStatement(sqlDelete);
					psDelete.setInt(1, Integer.parseInt(remocao));
					psDelete.executeUpdate();
					JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!");
				} else {
					System.out.println("Ok! Esta pessoa não será deletada!.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
			}			
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void atualizarPessoa() {
		try {
			url = JOptionPane.showInputDialog(null, "Digite a url do banco de dados: ", null, 1);				
			user = JOptionPane.showInputDialog(null, "Digite usuário do banco de dados: ", "Qual o usuário do banco de dados?", 1);					
			password = JOptionPane.showInputDialog(null, "Digite a senha do usuário: ", null, 1);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(url, user, password);

			String escolhaAtualizacao;
			escolhaAtualizacao = JOptionPane.showInputDialog(null, "Digite o id da pessoa que deseja atualizar: ", "Atualização", 1);
			PreparedStatement psSelect = conexao.prepareStatement("SELECT * FROM Pessoas.pessoa WHERE id = ?");

			psSelect.setInt(1, Integer.parseInt(escolhaAtualizacao));
			ResultSet retorno = psSelect.executeQuery();

			if (retorno.next()) {
				impressao = "ID: " + retorno.getString(1) + "\nNome: " + retorno.getString(2) + "\nSobrenome: " + retorno.getString(3);
				String[] opcoes = {"Sim", "Não"};
				int escolha = JOptionPane.showOptionDialog(null, impressao, "Esta é a pessoa que deseja atualizar? ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

				if (escolha == 0) {
					String atributos[] = {"Nome", "Sobrenome", "Idade", "Email"};
					int escolhaAtributos = JOptionPane.showOptionDialog(null, impressao, "Qual atributo deseja atualizar? ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, atributos, atributos[0]);

					if (escolhaAtributos == 0) {
						String novoNome;
						sqlUpdate = "UPDATE pessoas.pessoa SET nome = ? WHERE id = ?;";
						PreparedStatement psUpdate = conexao.prepareStatement(sqlUpdate);
						psUpdate.setInt(2, Integer.parseInt(escolhaAtualizacao));
						novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome para atualizar: ", "Atualização", 1);
						psUpdate.setString(1, novoNome);
						psUpdate.executeUpdate();
						JOptionPane.showMessageDialog(null, "Nome atualizado com sucesso!");

					} else if (escolhaAtributos == 1) {
						String novoSobrenome;
						sqlUpdate = "UPDATE pessoas.pessoa SET sobrenome = ? WHERE id = ?;";
						PreparedStatement psUpdate = conexao.prepareStatement(sqlUpdate);
						psUpdate.setInt(2, Integer.parseInt(escolhaAtualizacao));
						novoSobrenome = JOptionPane.showInputDialog(null, "Digite o novo sobrenome para atualizar: ", "Atualização", 1);
						psUpdate.setString(1, novoSobrenome);
						psUpdate.executeUpdate();
						JOptionPane.showMessageDialog(null, "Sobrenome atualizado com sucesso!");

					} else if (escolhaAtributos == 2) {
						String novaIdade;
						sqlUpdate = "UPDATE pessoas.pessoa SET idade = ? WHERE id = ?;";
						PreparedStatement psUpdate = conexao.prepareStatement(sqlUpdate);
						psUpdate.setInt(2, Integer.parseInt(escolhaAtualizacao));
						novaIdade = JOptionPane.showInputDialog(null, "Digite a nova idade para atualizar: ", "Atualização", 1);
						psUpdate.setInt(1, Integer.parseInt(novaIdade));
						psUpdate.executeUpdate();
						JOptionPane.showMessageDialog(null, "Idade atualizada com sucesso!");

					} else if (escolhaAtributos == 3) {
						String novoEmail;
						sqlUpdate = "UPDATE pessoas.pessoa SET email = ? WHERE id = ?;";
						PreparedStatement psUpdate = conexao.prepareStatement(sqlUpdate);
						psUpdate.setInt(2, Integer.parseInt(escolhaAtualizacao));
						novoEmail = JOptionPane.showInputDialog(null, "Digite o novo email para atualizar: ", "Atualização", 1);
						psUpdate.setString(1, novoEmail);
						psUpdate.executeUpdate();
						JOptionPane.showMessageDialog(null, "Email atualizado com sucesso!");

					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
			}

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}		
}

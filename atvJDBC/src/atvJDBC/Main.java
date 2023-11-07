package atvJDBC;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Pessoas pessoas = new Pessoas();
		
		do {
			String[] opcoes = { "Inserir Pessoa", "Listar Pessoas", "Apagar Pessoa", "Atualizar Pessoa", "Finalizar" };
			int escolha = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
			if (escolha == 0) {
				pessoas.inserirPessoa();
			} else if (escolha == 1) {
				pessoas.listarPessoas();
			} else if (escolha == 2) {
				pessoas.removerPessoa();
			} else if (escolha == 3) {
				pessoas.atualizarPessoa();
			} else if (escolha == 4) {
				break;
			} 
		} while (true);


	}
}

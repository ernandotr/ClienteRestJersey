package br.com.ernandorezende.ClientRest;

import javax.swing.JOptionPane;

import br.com.ernandorezende.ClientRest.Entity.Pessoa;
import br.com.ernandorezende.ClientRest.Entity.Pessoas;
import br.com.ernandorezende.ClientRest.service.ServiceClient;

/**
 * Cliente Rest - Cadastro de Pessoas!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String opcao = JOptionPane.showInputDialog(null,
				"Escolha uma opção: \n" + " 1-CADASTRAR | 2-CONSULTAR | 3-EXCLUIR | 4-ALTERAR | 5-LISTAR TODOS",
				"OPÇÕES", JOptionPane.PLAIN_MESSAGE);
 
		// SAI DO PROGRAMA SE NÃO INFORMAR NENHUMA OPÇÃO
		if (opcao == null)
			System.exit(0);
 
		switch (Integer.parseInt(opcao)) {
		case 1:
			Cadastrar();
			break;
		case 2:
			Consultar();
			break;
		case 3:
			Excluir();
			break;
		case 4:
			Alterar();
			break;
		case 5:
			ListarTodos();
		default:
			JOptionPane.showMessageDialog(null, "Opção inválida!");
			main(null);
			break;
		}
 
	}
 
	public static void Cadastrar() {
 
		// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
		ServiceClient client = new ServiceClient();
 
		// CAPTURA AS INFORMAÇÕES PARA CADASTRO
		String nome = JOptionPane.showInputDialog(null, "Nome:", "Novo Cadastro", JOptionPane.PLAIN_MESSAGE);
		String sexo = JOptionPane.showInputDialog(null, "Sexo: (M ou F)", "Novo Cadastro", JOptionPane.PLAIN_MESSAGE);
 
		// SETA OS VALORES NO NOSSO JAVABEANS
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
 
		// EFETUA O CADASTRO DE UMA NOVA PESSOA
		String resultado = client.cadastrarPessoa(pessoa);
 
		// MENSAGEM COM O RESULTADO
		JOptionPane.showMessageDialog(null, resultado);
 
		// CHAMANDO O MÉTODO PRINCIPAL PARA COMEÇAR A EXECUTAR AS OPÇÕES
		// NOVAMENTE
		main(null);
 
	}
 
	public static void Consultar() {
 
		// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
		ServiceClient client = new ServiceClient();
 
		// CAPTURA O CÓDIGO A SER CONSULTADO
		String codigo = JOptionPane.showInputDialog(null, "Informe o código para consulta:", "Consultar",
				JOptionPane.PLAIN_MESSAGE);
 
		// CONSULTA UMA PESSOA PELO CÓDIGO
		Pessoa pessoa = client.consultarPessoaPorCodigo(Integer.parseInt(codigo));
 
		if (pessoa == null) {
 
			JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
 
			// CHAMANDO A FUNÇÃO PRINCIPAL DO NOSSO SISTEMA
			main(null);
		} else {
 
			String resultado = null;
 
			// MONTA O RESULTADO PARA MOSTRARMOS NA MENSAGEM DE SAIDA
			resultado = "Código: " + String.valueOf(pessoa.getCodigo()) + "\n";
			resultado += "Nome:   " + pessoa.getNome() + "\n";
			resultado += "Sexo:   " + pessoa.getSexo();
 
			// MENSAGEM COM O RESULTADO
			JOptionPane.showMessageDialog(null, resultado);
 
			// CHAMANDO A FUNÇÃO PRINCIPAL DO NOSSO SISTEMA
			main(null);
		}
 
	}
 
	public static void Excluir() {
 
		// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
		ServiceClient client = new ServiceClient();
 
		// CAPTURA O CÓDIGO A SER EXCLUIDO
		String codigo = JOptionPane.showInputDialog(null, "Informe o código para excluir:", "Excluir",
				JOptionPane.PLAIN_MESSAGE);
 
		// EXCLUI UM REGISTRO PELO CÓDIGO
		String resultado = client.excluirPessoaPorCodigo(Integer.parseInt(codigo));
 
		// MENSAGEM COM O RESULTADO
		JOptionPane.showMessageDialog(null, resultado);
 
		// CHAMANDO O MÉTODO PRINCIPAL PARA COMEÇAR A EXECUTAR AS OPÇÕES
		// NOVAMENTE
		main(null);
 
	}
 
	public static void Alterar() {
 
		// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
		ServiceClient client = new ServiceClient();
 
		// CAPTURA O CÓDIGO A SER CONSULTADO
		String codigo = (String) JOptionPane.showInputDialog(null, "Informe o código para alteração:", "Alterar",
				JOptionPane.PLAIN_MESSAGE);
 
		// CONSULTA UMA PESSOA PELO CÓDIGO
		Pessoa pessoa = client.consultarPessoaPorCodigo(Integer.parseInt(codigo));
 
		if (pessoa == null) {
 
			JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
 
			// CHAMANDO A FUNÇÃO PRINCIPAL DO NOSSO SISTEMA
			main(null);
		} else {
 
			// PEGA O NOME INFORMADO PARA ALTERAÇÃO
			String nome = (String) JOptionPane.showInputDialog(null, "Nome:",
					"Alterar registro - Código:" + pessoa.getCodigo(), JOptionPane.PLAIN_MESSAGE, null, null,
					pessoa.getNome());
 
			// PEGA O SEXO INFORMADO PARA ALTERAÇÃO
			String sexo = (String) JOptionPane.showInputDialog(null, "Sexo:",
					"Alterar registro - Código:" + pessoa.getCodigo(), JOptionPane.PLAIN_MESSAGE, null, null,
					pessoa.getSexo());
 
			// ATUALIZANDO OS DADOS DA PESSOA QUE CONSULTAMOS
			pessoa.setNome(nome);
			pessoa.setSexo(sexo);
			String resultado = client.alterarPessoa(pessoa);
 
			// MENSAGEM COM O RESULTADO
			JOptionPane.showMessageDialog(null, resultado);
 
			// CHAMANDO O MÉTODO PRINCIPAL PARA COMEÇAR A EXECUTAR AS OPÇÕES
			// NOVAMENTE
			main(null);
		}
 
	}
 
	public static void ListarTodos() {
 
		// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
		ServiceClient client = new ServiceClient();
 
		// CONSULTA TODAS AS PESSOAS CADASTRADAS
		Pessoas pessoas = client.consultarTodasPessoas();
 
		StringBuilder stringBuiderDetalhesPessoa = new StringBuilder();
 
		// MONTANDO A LSITA DE PESSOAS
		for (Pessoa pessoa : pessoas) {
 
			stringBuiderDetalhesPessoa.append("Código: ");
			stringBuiderDetalhesPessoa.append(pessoa.getCodigo());
			stringBuiderDetalhesPessoa.append(" Nome: ");
			stringBuiderDetalhesPessoa.append(pessoa.getNome());
			stringBuiderDetalhesPessoa.append(" Sexo: ");
			stringBuiderDetalhesPessoa.append(pessoa.getSexo());
			stringBuiderDetalhesPessoa.append("\n\n");
 
		}
 
		// MOSTRA A LISTA DE PESSOAS ENCONTRADAS NA BASE
		JOptionPane.showMessageDialog(null, stringBuiderDetalhesPessoa.toString());
 
		// CHAMANDO O MÉTODO PRINCIPAL PARA COMEÇAR A EXECUTAR AS OPÇÕES
		// NOVAMENTE
		main(null);
    }
}

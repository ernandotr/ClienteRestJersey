package br.com.ernandorezende.ClientRest.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.ernandorezende.ClientRest.Entity.Pessoa;
import br.com.ernandorezende.ClientRest.Entity.Pessoas;

public class ServiceClient {

	private Client client;
	
	private WebTarget webTarget;
	
	private final String URL_SERVICE = "http://localhost:8081/WebServiceRest/rest/service/";
	
	public ServiceClient() {
		this.client = ClientBuilder.newClient();
	}
	
	/**CADASTRA UMA NOVA PESSOA ATRAVÉS DA OPERAÇÃO cadastrar(MÉTODO HTTP: POST) */
	public String cadastrarPessoa(Pessoa pessoa) {
		this.webTarget = this.client.target(URL_SERVICE).path("cadastrar");
		
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		
		Response response = invocationBuilder.post(Entity.entity(pessoa, "application/json;charset=UTF-8"));
		return response.readEntity(String.class);
	}
	
	/**ALTERA UM REGISTRO JÁ CADASTRADO ATRAVÉS DA OPERAÇÃO alterar(MÉTODO HTTP:PUT)*/
	public String alterarPessoa(Pessoa pessoa){
 
		this.webTarget = this.client.target(URL_SERVICE).path("alterar");
 
		Invocation.Builder invocationBuilder =  this.webTarget.request("application/json;charset=UTF-8");
 
		Response response = invocationBuilder.put(Entity.entity(pessoa, "application/json;charset=UTF-8"));
 
		return response.readEntity(String.class);
 
	}
 
	/**CONSULTA TODAS AS PESSOAS CADASTRADAS NO SERVIÇO ATRAVÉS DA OPERAÇÃO todasPessoas(MÉTODO HTTP:GET)*/
	public Pessoas consultarTodasPessoas(){
 
		this.webTarget = this.client.target(URL_SERVICE).path("todasPessoas");
 
		Invocation.Builder invocationBuilder =  this.webTarget.request("application/json;charset=UTF-8");
 
		Response response = invocationBuilder.get();
 
		return response.readEntity(Pessoas.class);
 
	}
 
	/**CONSULTA UMA PESSOA PELO CÓDIGO ATRAVÉS DA OPERAÇÃO getPessoa(MÉTODO HTTP: GET)*/
	public Pessoa consultarPessoaPorCodigo(int codigo){
 
		this.webTarget = this.client.target(URL_SERVICE).path("getPessoa").path(String.valueOf(codigo));
 
		Invocation.Builder invocationBuilder =  this.webTarget.request("application/json;charset=UTF-8");
 
		Response response = invocationBuilder.get();
 
		return response.readEntity(Pessoa.class);
 
	}
 
 
	/**EXCLUI UM REGISTRO CADASTRADO PELO CÓDIGO ATRAVÉS DA OPERAÇÃO excluir(MÉTODO HTTP:delete)*/
	public String excluirPessoaPorCodigo(int codigo){
 
		this.webTarget = this.client.target(URL_SERVICE).path("excluir").path(String.valueOf(codigo));
 
		Invocation.Builder invocationBuilder =  this.webTarget.request("application/json;charset=UTF-8");
 
		Response response = invocationBuilder.delete();
 
		return response.readEntity(String.class);
 
	}
}

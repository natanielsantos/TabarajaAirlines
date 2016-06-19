package Testes;
import static org.junit.Assert.*;

import org.junit.Test;
import model.Cliente;
import DAO.ClienteDAO;

public class testeCliente {
	Cliente cli = new Cliente();
	ClienteDAO CliDao = new ClienteDAO();
	int id;

	@Test
	public void testCadastrar() {
		
		
		cli.setNome("Nataniel Santos");
		cli.setLogradouro("Rua Amélia Gotilib Leite");
		cli.setNumero(470);
		cli.setBairro("Novo Horizonte");
		cli.setEstado("Minas Gerais");
		cli.setTelefone("(38) 999817033");
		cli.setMunicípio("Paracatu");
		cli.setCep("38600-000");
		cli.setCpf("073.xxx.xxx-01");
		
		CliDao.inserirNoBanco(cli);
		CliDao.excluirDoBanco(cli);
	}
	
	@Test
	public void testAlterar() {
		
		
		cli.setNome("Natan Santos");
		cli.setLogradouro("Rua Amélia G. Leite");
		cli.setNumero(470);
		cli.setBairro("Novo Horizonte");
		cli.setEstado("Distrito Federal");
		cli.setTelefone("(38) 999817033");
		cli.setMunicípio("Brasilia");
		cli.setCep("38600-000");
		cli.setCpf("xx.xxx.xxx-xx");
		
		CliDao.alterarNoBanco(cli);
	}
	
	@Test
	public void testRelatorio(){
		CliDao.relatorio();
	}
	
	@Test
	public void testExcluir(){
		
	}

}

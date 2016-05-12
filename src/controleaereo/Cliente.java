package controleaereo;

public class Cliente {

	private int identificacao;
	private String nome;
	private String logradouro;
	private String numero;
	private String bairro;
	private String municipio;
	private String estado;
	private String cep;
	private String telefone;

	public Cliente(int identificacao, String nome, String logradouro, String numero, String bairro, String municipio,
			String estado, String cep, String telefone) {

		this.identificacao = identificacao;
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
	}

	public int getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(int identificacao) {
		this.identificacao = identificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public void imprimir() {
		System.out.println("Identificação    : " + identificacao);
		System.out.println("Nome             : " + nome);
		System.out.println("Logradouro       : " + logradouro);
		System.out.println("Numero           : " + numero);
		System.out.println("Bairro           : " + bairro);
		System.out.println("Municipio        : " + municipio);
		System.out.println("Estado           : " + estado);
		System.out.println("CEP              : " + cep);
		System.out.println("Telefone         : " + telefone);
	}

}

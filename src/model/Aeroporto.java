package model;
public class Aeroporto {
    
    private String identificacao;
    private String nome;
    private Cidade cidade;

    public Aeroporto() {
    }
    
    public Aeroporto(String identificacao) {
    	this.identificacao = identificacao;
    }
    
    public Aeroporto(String identificacao, String nome, Cidade cidade) {
        this.identificacao = identificacao;
        this.nome = nome;
        this.cidade = cidade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
         
    public void consultar(){
        System.out.println("Identificação: "+ identificacao); 
        System.out.println("Nome: "+ nome); 
        System.out.println("-- Cidade --");
        cidade.consultar();
       
    }
    
    public void consultarA(){
    	System.out.println("Identificação: "+ identificacao);
        System.out.println("Nome: "+ nome); 
        cidade.consultarP();
       
    }
}

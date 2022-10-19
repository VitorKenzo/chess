/*
    Classe que representa os jogadores que esto jogando o jogo de xadrez, que
    contem seus nomes, suas cores, a quantidade de pecas que eles possuem e 
    um vetor com essas pecas
 */
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Jogador {
    
    //variaveis da classe
    private final String nome;
    private char cor;
    private int qnt_pecas;
    private Peca pecas[];
    
    //contrutor
    public Jogador(String nome, char cor){
        this.nome = nome;
        if(cor == 'X' || cor == 'O'){
            this.cor = cor;
        }
        this.qnt_pecas = 16;
        pecas = new Peca[16];
    } 

    //setters e getters
    public String getNome() {
        return nome;
    }

    public char getCor() {
        return cor;
    }

    public int getQnt_pecas() {
        return qnt_pecas;
    }

    public void setQnt_pecas(int qnt_pecas) {
        if(qnt_pecas <= 16 && qnt_pecas >= 0){
            this.qnt_pecas = qnt_pecas;
        }
    }
    
    //funcao que captura uma peca no vetor de pecas do jogador
    public void capturarPeca(Peca p){
        for(int i = 0; i < 16; i ++){
            if(this.pecas[i] == p){
                this.pecas[i].setCapturada(true);
                this.setQnt_pecas(this.getQnt_pecas()-1);
            }
        }
    }

    //funcao que retorna uma peca de vetor do jogador    
    public Peca getPeca(int i){
        if(i >= 0 && i < 16 && this.pecas[i] != null){
            return this.pecas[i];
        }
        return null;
    }
    
    //funcao que insere uma peca no vetor de pecas do jogador
    public void inserirPecaVetor(Peca p, int i){
        if(i >= 0 && i < 16){
            this.pecas[i] = p;
        }
    }
    
}

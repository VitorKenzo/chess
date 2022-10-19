/*
    Classe abstrata que representa a peca que esta em uma posicao no tabuleiro e
    no vetor dos jogadores. Possui atributos e metodos que podem dizer se ela esta
    capturada ou nao, sua cor e se esta no inicio de sua jogada
 */
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
abstract public class Peca {
    
    //variaveis de classe
    private boolean capturada;
    protected final char cor;
    protected boolean inicio;
    
    //construtor da classe
    protected Peca(char cor){
        
        this.capturada = false;
        this.inicio = true;
        
        if(cor == 'X'|| cor == 'O'){
            this.cor = cor;
        }else{
            this.cor = 'E';
        }
    
    }

    //getters e setters
    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }
    
    public boolean isCapturada() {
        return capturada;
    }
    
    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }
    
    public char getCor() {
        return cor;
    }
    
    //metodos que tem que ser sobrescritos pelas classes que extentem essa
    abstract char desenha();
    abstract boolean checaMovimento(int linInicial, char colInicial, int linFinal, char colFinal);

}

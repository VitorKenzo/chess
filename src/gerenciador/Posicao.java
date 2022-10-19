/*
    Classe posicao que representa uma posicao no tabuleiro, que possui uma cor,
    uma variavel se diz se ela esta ocupada ou nao, uma variavel para a Peca que
    pode estar na posicao, um inteiro representando a linha e um caractere 
    representando a coluna
 */
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Posicao {
    
    //variaveis da classe
    private final char cor;
    private boolean ocupada;
    private Peca peca;
    private final int linha;
    private final char coluna;

    //construtor
    public Posicao(int linha, char coluna){
        
        //coluna e linha vem como entradas
        
        if(linha >= 1 && linha <= 8){
            this.linha = linha;
        }else{
            this.linha = -1; 
        }
        
        if(coluna >= 'A' && coluna <= 'H'){
            this.coluna = coluna;
        }else{
            this.coluna = '1';
        }
        
        //cor eh definida pelo modulo da soma sendo O preto e X branco
        if((int)(linha + coluna - 64) % 2 == 0){//preto
            this.cor = '-';
        }else{//branco
            this.cor = '*';
        }
        
        this.ocupada = false;
    }
    
    //setters e getters
    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    public Peca getPeca() {
        //so iremos devolver uma peca se a posicao estiver ocupada
        if(this.ocupada){
            return peca;
        }else{
            return null;
        }
    }
    
    public void setPeca(Peca p) {
        //so colocaremos uma peca se aquela posicao nao estiver ocupada
        if(!this.ocupada){
            this.peca = p;
        }
    }
    
    //vamos ter apenas getters dessas variaveis
    //pois nao vemos alterar seus valores durante a execucao
    public int getLinha() {
        return linha;
    }

    public char getCor() {
        return cor;
    }

    public char getColuna() {
        return coluna;
    }
    
}

/*
    Classe que representa um tabuleiro de xadrez, contem 64 posicoes que podem ou
    nao ter pecas, assim como funcoes para colocar pecas no tabuleiro e movimenta-las
 */
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Tabuleiro{
    
    //variavel que eh uma matriz de posicoes
    private final Posicao[][] tabuleiro;
    
    //construtor do tabuleiro
    public Tabuleiro(){
        
        //declarando uma matriz 8 por para conter as 64 posicoes
        this.tabuleiro = new Posicao[8][8];
        
        //uma vez que o tabuleiro eh instanciado, cada posicao eh preenchida
        //passando para o construtor de posicao as coordenadas de cada uma
        for(int linha = 1; linha <= 8; linha ++){
            for(char coluna = 'A'; coluna <= 'H'; coluna ++){
                tabuleiro[linha - 1][(int)coluna - 65] = new Posicao(linha,coluna);
            }
        }
    }
    
    //funcao que imprimi o tabuleiro
    public void printTabuleiro(){
        
        System.out.print("  ");
        //printa as colunas de A a H em cima
        for(char c = 'A'; c <= 'H'; c++){
            System.out.print(" " + c + "  ");
        }
        System.out.println("");
        
        //printa a linha e a cor de cada posicao da linha dada
        for(int i = 7; i >= 0; i --){
            System.out.print((i+1) + " ");
            for(int j = 0; j < 8; j ++){
                //imprimi a cor da posicao se nao estiver ocupada ou a peca
                //que esta ocupando a posicao no momento
                if(!(tabuleiro[i][j].isOcupada())){
                    System.out.print("|" + (tabuleiro[i][j]).getCor() + "| ");
                }else{
                    System.out.print("|" + (tabuleiro[i][j]).getPeca().desenha() + "| ");
                }
            }
            System.out.print(i+1);
            System.out.println("");
        }
        
        System.out.print("  ");
        for(char c = 'A'; c <= 'H'; c++){
            System.out.print(" " + c + "  ");
        }
        System.out.println("");
    }
    
    //funcao que retorna uma posicao do tabuleiro
    public Posicao getPosicao(int linha, int coluna){
        if((linha < 8 && linha >= 0) &&(coluna < 8 && coluna >= 0)){
            return tabuleiro[linha][coluna];
        }else{
            return null;
        }
    }
    
    //funcao que coloca uma peca em uma posicao do tabuleiro
    public void colocarPeca(Posicao p, Peca pe){
        if(p != null && pe != null){
            p.setPeca(pe);
            p.setOcupada(true);
        }
    }
    
    //funcao que retorna a posicao do rei
    public Posicao posicaoRei(char cor){
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                if(this.getPosicao(i, j).getPeca() instanceof Rei && this.getPosicao(i, j).getPeca().getCor() == cor){
                    return this.getPosicao(i, j);
                }
            }
        }
        return null;
    }
    
    //funcao que checa o caminho especifico de cada peca
    public boolean checaCaminho(int inLinha, int inColuna, int fimLinha, int fimColuna){
        
        Peca p = this.getPosicao(inLinha, inColuna).getPeca();
        int linhaDif = inLinha - fimLinha;
        int colunaDif = inColuna - fimColuna;
        
        //caso do rei e do cavalo nao precisao ser checados
        if(p instanceof Cavalo || p instanceof Rei){
            return true;
        }
        
        //CASO DE PECA NO CAMINHO COM MOVIMENTO APENAS NA LINHA
        if(colunaDif == 0){
            if(inLinha > fimLinha){
                for(int i = inLinha - 1; i > fimLinha; i --){
                    if(this.getPosicao(i, inColuna).isOcupada()){
                        return false;
                    }
                }
            }else{
                for(int i = inLinha + 1; i < fimLinha; i ++){
                    if(this.getPosicao(i, inColuna).isOcupada()){
                        return false;
                    }
                }
            }
        //CASO DE PECA NO CAMINHO COM MOVIMENTO APENAS NA COLUNA
        }else if(linhaDif == 0){
            if(inColuna > fimColuna){
                for(int i = inColuna - 1; i > fimColuna; i --){
                    if(this.getPosicao(inLinha, i).isOcupada()){
                        return false;
                    }
                }
            }else{
                for(int i = inColuna + 1; i < fimColuna; i ++){
                    if(this.getPosicao(inLinha, i).isOcupada()){
                        return false;
                    }
                }
            }
        //DIAGONAL INFERIOR ESQUERDA
        }else if(linhaDif > 0 && colunaDif > 0){
            for(int i = inLinha - 1, j = inColuna - 1; i > fimLinha && j > fimColuna; i--, j--){
                if(this.getPosicao(i, j).isOcupada()){
                    return false;
                }
            }
        //DIAGONAL SUPERIOR ESQUERDA
        }else if(linhaDif < 0 && colunaDif > 0){
            for(int i = inLinha + 1, j = inColuna - 1; i < fimLinha && j > fimColuna; i++, j--){
                if(this.getPosicao(i, j).isOcupada()){
                    return false;
                }
            }
        //DIAGONAL INFERIOR DIREITA
        }else if(linhaDif > 0 && colunaDif < 0){
            for(int i = inLinha - 1, j = inColuna + 1; i > fimLinha && j < fimColuna; i--, j++){
                if(this.getPosicao(i, j).isOcupada()){
                    return false;
                }
            }
        //DIAGONAL SUPERIOR DIREITA
        }else if(linhaDif < 0 && colunaDif < 0){
            for(int i = inLinha + 1, j = inColuna + 1; i < fimLinha && j < fimColuna; i++, j++){
                if(this.getPosicao(i, j).isOcupada()){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    //funcao que checa o movimento baseado no jogo inteiro
    public boolean checaMovimento(int contador, int inLinha, char inColuna, int fimLinha, char fimColuna){
        //verifica se o usuario esta realmente mexendo uma peca
        if(this.getPosicao((inLinha - 1), (int)(inColuna - 65)).isOcupada()){
            //caso do branco e preto
            if(contador % 2 == 0){//branco
                //checa se o usuario esta mexendo sua propria peca
                if(this.getPosicao((inLinha - 1), (int)(inColuna - 65)).getPeca().getCor() == 'X'){
                    //checa o movimento especifico da peca
                    if(this.getPosicao((inLinha - 1), (int)(inColuna - 65)).getPeca()
                    .checaMovimento(inLinha,inColuna,fimLinha,fimColuna)){
                        return true;
                    }else{//CASO DO MOVIMENTO ESPECIFICO ERRADO
                        return false;
                    }
                }else{//CASO DE MEXER PECA DO OPONENTE
                    return false;
                }
            }else{//preto
                //repeticao do codigo do branco
                if(this.getPosicao((inLinha - 1), (int)(inColuna - 65)).getPeca().getCor() == 'O'){
                    if(this.getPosicao((inLinha - 1), (int)(inColuna - 65)).getPeca()
                    .checaMovimento(inLinha, inColuna, fimLinha, fimColuna)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }//CASO DE NAO TER UMA PECA NA POSICAO
        return false;
    }
    
    //funcao que faz o movimento da peca
    public void movimento(int inLinha, char inColuna, int fimLinha, char fimColuna){
        Peca p = this.getPosicao((inLinha - 1), (int)(inColuna - 65)).getPeca();
        if(p.isInicio()){
            p.setInicio(false);
        }
        this.getPosicao((inLinha - 1), (int)(inColuna - 65)).setOcupada(false);
        this.getPosicao((fimLinha - 1),(int)(fimColuna - 65)).setPeca(p);
        this.getPosicao((inLinha - 1), (int)(inColuna - 65)).setPeca(null);
        this.getPosicao((fimLinha - 1),(int)(fimColuna - 65)).setOcupada(true);
    }
}

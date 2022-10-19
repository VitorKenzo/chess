/*
    Classe especifica da peca Peao, que sobrescreve as funcoes abstratas
    que estao na classe peca
*/
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Peao extends Peca{
    
    //metodos construtor getters e setters
    public Peao(char cor){
        super(cor);
    }
    
    //metodos da classe
    @Override
    public char desenha(){
        if(this.getCor() == 'O'){
            return 'P';
        }else if(this.getCor() == 'X'){
            return 'p';
        }
        //retorno para se algum erro aconteca
        return 'E';
    };

    @Override
    public boolean checaMovimento(int linInicial, char colInicial, int linFinal, char colFinal){
        //variaveis auxiliares para guardar a diferenca na linha e coluna
        int difLinha = linFinal - linInicial;
        //como o movimento nas colunas pode ser para a esquerda ou direita
        //podemos usar o valor absoluto
        int difColuna = Math.abs((int)colFinal - (int)colInicial);
        
        //checando se ha um movimento de fato
        //nao se movimentar eh invalido
        if(difLinha == 0 && difColuna == 0){
            return false;
        }
        
        //no caso do peao temos que checar o movimento de acordo com a cor dele
        //nesse projeto os brancos sempre estarao em baixo e os pretos em cima
        if(this.getCor() == 'O'){
            //movimento de comer uma peca para as diagonais
            if(difColuna == 1 && difLinha == -1){
                return true;
            //um para frente, movimento padrao do peao
            }else if(difColuna == 0 && difLinha == -1){
                return true;
            //movimento que so pode ser feito no comeco do jogo, para os
            //peoes pretos, essa condicao acontece quando ele estao nalinha 7
            }else if(inicio == true && (linInicial == 7 && difColuna == 0 && difLinha == -2)){
                return true;
            }
            return false;
        }else if(this.getCor() == 'X'){
            //movimento de comer uma peca para as diagonais
            if(difColuna == 1 && difLinha == 1){
                return true;
            //um para frente,movimento padrao do peao
            }else if(difColuna == 0 && difLinha == 1){
                return true;
            //movimento que so pode ser feito no comeco do jogo, para os
            //peoes brancos, essa condicao acontece quando ele estao na linha 2
            }else if(inicio == true && (linInicial == 2 && difColuna == 0 && difLinha == 2)){
                return true;
            }
            return false;
        }
        
        return false;
    };
}

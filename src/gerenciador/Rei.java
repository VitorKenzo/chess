/*
    Classe especifica da peca Rei, que sobrescreve as funcoes abstratas
    que estao na classe peca
*/
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Rei extends Peca{
    
    //metodos construtor getters e setters
    public Rei(char cor){
        super(cor);
    }

    //metodos da classe
    @Override
    public char desenha(){
        if(this.getCor() == 'O'){
            return 'R';
        }else if(this.getCor() == 'X'){
            return 'r';
        }
        //retorno para se algum erro aconteca
        return 'E';
    };
    
    @Override
    public boolean checaMovimento(int linInicial, char colInicial, int linFinal, char colFinal){
        //variaveis auxiliares para guardar a diferenca na linha e coluna
        int difLinha = Math.abs(linFinal - linInicial);
        int difColuna = Math.abs((int)colFinal - (int)colInicial);
        
        //checando se ha um movimento de fato
        //nao se movimentar eh invalido
        if(difLinha == 0 && difColuna == 0){
            return false;
        }
        
        //checando o movimento para as quatro diagonais em uma unidade
        if(difLinha == 1 && difColuna == 1){
            return true;
        //checando movimento na linha de uma unidade
        }else if(difLinha == 1 && difColuna == 0){
            return true;
        //checando movimento na coluna de uma unidade
        }else if(difLinha == 0 && difColuna == 1){
            return true;
        }
        //senao for os de cima, nao eh valido para o rei
        return false;
    };
}

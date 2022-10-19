/*
    Classe especifica da peca Cavalo, que sobrescreve as funcoes abstratas
    que estao na classe peca
*/
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Cavalo extends Peca{
    
    //metodos construtor getters e setters
    public Cavalo(char cor){
        super(cor);
    }
    
    //metodos da classe
    @Override
    public char desenha(){
        if(this.getCor() == 'O'){
            return 'C';
        }else if(this.getCor() == 'X'){
            return 'c';
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
        
        //checa se o cavalo anda 2 na linha e 1 na coluna
        if(difLinha == 2 && difColuna == 1){
            return true;
        //checa se o cavalo anda 1 na linha e 2 na coluna
        }else if(difLinha == 1 && difColuna == 2){
            return true;
        //qualquer outro movimento para o cavalo eh invalido
        }else{
            return false;
        }
    };
}

/*
    Classe especifica da peca Dama, que sobrescreve as funcoes abstratas
    que estao na classe peca
*/
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Dama extends Peca{
    
    //metodos construtor getters e setters
    public Dama(char cor){
        super(cor);
    }
    
    //metodos da classe
    @Override
    public char desenha(){
        if(this.getCor() == 'O'){
            return 'D';
        }else if(this.getCor() == 'X'){
            return 'd';
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
        
        //checando se o movimento foi na diagonal
        if(difLinha == difColuna){
            return true;
        //checando se o movimento foi na linha
        }else if(difLinha == 0 && difColuna != 0){
            return true;
        //checando se o movimento foi na coluna
        }else if(difLinha != 0 && difColuna == 0){
            return true;
        }
        //se nao for nenhum dos casos anterirores nao eh valido para rainha
        return false;
        
    };

}

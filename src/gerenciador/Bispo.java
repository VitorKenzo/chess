/*
    Classe especifica da peca Bispo, que sobrescreve as funcoes abstratas
    que estao na classe peca
*/
package gerenciador;

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Bispo extends Peca{
    
    //metodos construtor getters e setters
    public Bispo(char cor){
        super(cor);
    }
    
    //metodos da classe
    @Override
    public char desenha(){
        if(this.getCor() == 'O'){
            return 'B';
        }else if(this.getCor() == 'X'){
            return 'b';
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
        
        //o valor absoluto andado na linha tem que ser igual ao valor da coluna
        //para que o movimento seja na diagonal
        //essa condicao ira cobrir as quatro direcoes possiveis
        return(difLinha == difColuna);
            
    };
}

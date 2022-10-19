/*
    Classe que gerencia o jogo de xadrez, permite apenas um jogo de xadrez por vez.
    Dois jogadores podem escolher comecar um novo jogo ou iniciar o jogo salvo no
    .txt especifico
 */
package gerenciador;
        
import java.util.Scanner;
        
/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Gerenciador {

    public static void main(String[] args) {
        
        //variaveis auxiliares
        Scanner in = new Scanner(System.in);
        int escolha = 0;
        String branco, preto;
        
        System.out.println("Bem vindo ao jogo de xadrez");
        System.out.println("Por favor digite o nome ou nick do jogador branco");
        System.out.println("Suas peças são as letras minúsculas");
        branco = in.nextLine();
        //caso o usuario nao forneca nada
        if(branco.isEmpty()){
            branco = "nome1";
        }
       
        System.out.println("Por favor digite o nome ou nick do jogador preto");
        System.out.println("Suas peças são as letras maiúsculas");
        preto = in.nextLine();
        //caso o usuario nao forneca nada
        if(preto.isEmpty()){
            preto = "nome2";
        }
        
        try{
            do{//eh repetido ate um numero valido ser informado
                
                System.out.println("\nMENU PRINCIPAL\n");
                System.out.println("1 - Começar novo jogo");
                System.out.println("2 - Começar último jogo salvo");
                System.out.println("Digite como quer começar");
                
                escolha = in.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Vocês escolheram começar um novo jogo");
                        Jogo novo = new Jogo(branco,preto);
                        novo.novoJogo();
                        break;
                    case 2:
                        System.out.println("Vocês escolheram começar o jogo que está salvo");
                        Jogo carrega = new Jogo(branco,preto);
                        carrega.CarregaJogo();
                        break;
                    default:
                        System.out.println("Entrada inválida!");
                        break;
                }
            }while(escolha < 1 || escolha > 2);
            
        }catch(Exception e){
            //se um inteiro nao for informado o programa ira acabar
            System.out.println("Por favor, reinicie o programa e digite o número que represente a opção desejada");
        
        }
        
    }    
}

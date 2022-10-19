/*
    Classe que representa o jogo de xadrez, possui 2 jogadores e um tabuleiro,
    assim como funcoes para começar o jogo salvo no .txt ou começar um novo jogo
    e todas as funcoes para fazer o jogo funcionar
 */
package gerenciador;

import java.util.Scanner;
import java.io.File;   
import java.io.FileWriter;
import java.io.IOException; 

/**
 * @author Vitor Kenzo Fukuhara Pellegatti
 */
public class Jogo {
    
    //variaveis da classe
    Jogador branco, preto;
    private final Tabuleiro tab;
    
    //Construtor
    public Jogo(String branco, String preto){
        this.tab = new Tabuleiro();
        this.branco = new Jogador(branco,'X');
        this.preto = new Jogador(preto,'O');
    }
    
    //Metodos da classe
    //funcao que mantem o loop do jogo ate um dos jogadores perder 
    private void loopJogo(int nRodadas){
        
        //variaveis auxiliares para a entrada do usuario
        //e manutencao da vez
        int contRodadas = nRodadas;
        Scanner in = new Scanner(System.in);
        String input;
        int inLinha;
        char inColuna;
        int fimLinha;
        char fimColuna;
        Posicao reiBranco,reiPreto;
        boolean xequeMateBranco = false;
        boolean xequeMatePreto = false;
        reiBranco = tab.posicaoRei('X');
        reiPreto = tab.posicaoRei('O');
        
        System.out.println("Jogo entre " + this.branco.getNome() +"(Branco) e " + this.preto.getNome() + "(Preto) iniciado:");
        System.out.println("Para sair e salvar o estado do jogo insira S! na entrada");
        System.out.println("Pode-se desistir do jogo escrevendo DES");
        System.out.println("Pode-se declarar empate escrevendo EMP");
        System.out.println("Faça suas jogadas informando a linha e a coluna");
        System.out.println("A linha é um número de 1 a 8");
        System.out.println("A coluna é uma letra de A até H");
        
        //loop acontece ate um xeque mate 
        while(!(xequeMateBranco || xequeMatePreto)){
            
            tab.printTabuleiro();   
            System.out.println("Peças do jogador branco: " + this.branco.getQnt_pecas());
            System.out.println("Peças do jogador preto: " + this.preto.getQnt_pecas());
            
            //Dependendo do contador sera a vez de um dos jogadores
            //assim como ele so eh atualizado se a jogada for valida
            if(contRodadas % 2 == 0 ){
                System.out.println("\nVEZ DO BRANCO");
            }else if(contRodadas % 2 == 1){
                System.out.println("\nVEZ DO PRETO");
            }
              
            
            /*-------------ENTRADA PADRAO PARA OS DOIS JOGADORES-------------------*/
            System.out.println("Insira a posição da peça a ser mexida:");
            //iremos pegar a string e parsear corretamente em seus valores
            input = in.nextLine();
                
            //caso para salvar o jogo
            if(input.equals("S!")){
                System.out.println("Salvando o jogo...");
                this.SalvaJogo(contRodadas, branco.getQnt_pecas(), preto.getQnt_pecas());
                
                break;
            }
            
            //EMPATE ENTRE OS JOGADORES
            if(input.equals("EMP")){
                
                System.out.println("Empate entre " + branco.getNome() + " e " + preto.getNome());
                
                break;
            }
            
            //DESISTENCIA DE UM DOS JOGADORES
            if(input.equals("DES")){
                if(contRodadas % 2 == 0){
                    System.out.println("O jogador " + branco.getNome() + " desistiu");
                    break;
                }else{
                    System.out.println("O jogador " + preto.getNome() + " desistiu");
                    break;
                }
            }
            
            //A entrada so estara correta se houver 2 valores na string
            if(input.length() == 2){
                inLinha = Character.getNumericValue(input.charAt(0));
                inColuna = input.charAt(1);
            //caso contrario a entrada sempre sera invalida
            }else{
                inLinha = -1;
                inColuna = '1';
            }
                    
            //garante que se o usuario tenha passado uma letra minuscula
            //ela se torne maiuscula antes da checagem
            if(Character.isLowerCase(inColuna)){
            inColuna = Character.toUpperCase(inColuna);
            }
                
            //a mesma coisa que o trecho acima relacionado a saida
            System.out.println("Insira a posição de destino:");
            input = in.nextLine();
            
            //caos para salvar o jogo
            if(input.equals("S!")){
                System.out.println("Salvando o jogo...");
                this.SalvaJogo(contRodadas, branco.getQnt_pecas(), preto.getQnt_pecas());
                
                break;
            }
            
            //EMPATE ENTRE OS JOGADORES
            if(input.equals("EMP")){
                
                System.out.println("Empate entre " + branco.getNome() + " e " + preto.getNome());
                
                break;
            }
            
            //DESISTENCIA DE UM DOS JOGADORES
            if(input.equals("DES")){
                if(contRodadas % 2 == 0){
                    System.out.println("O jogador " + branco.getNome() + " desistiu");
                    break;
                }else{
                    System.out.println("O jogador " + preto.getNome() + " desistiu");
                    break;
                }
            }
                
            if(input.length() == 2){
                fimLinha = Character.getNumericValue(input.charAt(0));
                fimColuna = input.charAt(1);
            }else{
                fimLinha = -1;
                fimColuna = '1';
            }
                    
            if(Character.isLowerCase(fimColuna)){
                fimColuna = Character.toUpperCase(fimColuna);
            }
            
            /*-------------CHECAGEM DAS JOGADAS-------------------*/
            //checa se as entradas sao validas para depois checar
            //o movimento das pecas especificas e o caminho
            if(checaEntrada(inLinha, inColuna) && checaEntrada(fimLinha,fimColuna)){
                if(tab.checaMovimento(contRodadas, inLinha, inColuna, fimLinha, fimColuna) 
                && tab.checaCaminho((inLinha-1), (int)(inColuna-65), (fimLinha-1), (int)(fimColuna-65))){
                    
                    //CASO DO PEAO
                    if(tab.getPosicao((inLinha-1),(int)(inColuna-65)).getPeca() instanceof Peao){
                        //caso ele ande ou tente capturar uma peca
                        if((inColuna == fimColuna) && !(tab.getPosicao((fimLinha-1), (int)(fimColuna-65)).isOcupada())){
                            System.out.printf("Movimento de (%d,%c) para (%d,%c)\n",inLinha,inColuna,fimLinha,fimColuna);
                            tab.movimento(inLinha, inColuna, fimLinha, fimColuna);
                            contRodadas ++;
                        }else if((inColuna != fimColuna) && tab.getPosicao((inLinha-1),(int)(inColuna-65)).getPeca().getCor()
                                    != tab.getPosicao((fimLinha-1),(int)(fimColuna-65)).getPeca().getCor()){
                            System.out.println("Captura de peça");
                            this.captura(inLinha, inColuna, fimLinha, fimColuna);
                            contRodadas ++;
                        }else{
                            System.out.println("Jogada invalida");
                        }
         
                    //MOVIMENTO SEM CAPTURA
                    }else if(!(tab.getPosicao((fimLinha-1), (int)(fimColuna-65)).isOcupada())){
                        System.out.printf("Movimento de (%d,%c) para (%d,%c)\n",inLinha,inColuna,fimLinha,fimColuna);
                        if(tab.getPosicao((inLinha-1),(int)(inColuna-65)).getPeca() instanceof Rei){
                            if(contRodadas % 2 == 0){
                                reiBranco = tab.getPosicao((fimLinha-1), (int)(fimColuna-65));
                            }else{
                                reiPreto = tab.getPosicao((fimLinha-1), (int)(fimColuna-65));
                            }
                        }
                        tab.movimento(inLinha, inColuna, fimLinha, fimColuna);
                        contRodadas ++;   
                        
                    //MOVIMENTO COM CAPTURA
                    }else if(tab.getPosicao((fimLinha-1), (int)(fimColuna-65)).getPeca().getCor()
                            != tab.getPosicao((inLinha-1), (int)(inColuna-65)).getPeca().getCor()){
                        System.out.println("Captura de peça");
                        if(tab.getPosicao((inLinha-1),(int)(inColuna-65)).getPeca() instanceof Rei){
                            if(contRodadas % 2 == 0){
                                reiBranco = tab.getPosicao((fimLinha-1), (int)(fimColuna-65));
                            }else{
                                reiPreto = tab.getPosicao((fimLinha-1), (int)(fimColuna-65));
                            }
                        }
                        this.captura(inLinha, inColuna, fimLinha, fimColuna);
                        contRodadas ++;
                    
                    //FALHA NO MOVIMENTO 
                    }else{
                        System.out.println("Peça no final, movimento inválido!");
                    }
                   
                }else{
                    System.out.println("Jogada inválida!");
                }
            }else{
                System.out.println("Entrada inválida!");
            }
            
            //CHECAGEM DE XEQUE
            if(this.xeque(reiBranco, 'X', preto.getQnt_pecas())){
                System.out.println("O jogador branco está em xeque!");
            }
            if(this.xeque(reiPreto, 'O', branco.getQnt_pecas())){
                System.out.println("O jogador preto está em xeque!");
            }
            
            //CHECAGEM DE XEQUE MATE
            xequeMateBranco = this.xequeMate(branco.getPeca(0));
            if(xequeMateBranco){
                System.out.println("O jogador " + preto.getNome() + " venceu!");
            }
            xequeMatePreto = this.xequeMate(preto.getPeca(0));
            if(xequeMatePreto){
                System.out.println("O jogador " + branco.getNome() + " venceu!");
            }
            
        }
    }
    
    //funcao que cria um novo jogo e o coloca em loop
    public void novoJogo(){
        // a funcao inicia as pecas no tabuleiro e no vetor dos jogadores para o
        //inico de um novo jogo qualquer
        /*-------------------------Criando pecas do jogador preto-----------------------------*/
        Posicao p = tab.getPosicao(7, 4);
        Rei rp = new Rei('O');
        this.preto.inserirPecaVetor(rp, 0);
        tab.colocarPeca(p, rp);
        
        p = tab.getPosicao(7, 3);
        Dama dp = new Dama('O');
        this.preto.inserirPecaVetor(dp, 1);
        tab.colocarPeca(p, dp);
        
        p = tab.getPosicao(7, 2);
        Bispo bp1 = new Bispo('O');
        this.preto.inserirPecaVetor(bp1, 2);
        tab.colocarPeca(p, bp1);
        
        p = tab.getPosicao(7, 5);
        Bispo bp2 = new Bispo('O');
        this.preto.inserirPecaVetor(bp2, 3);
        tab.colocarPeca(p, bp2);
        
        p = tab.getPosicao(7, 1);
        Cavalo cp1 = new Cavalo('O');
        this.preto.inserirPecaVetor(cp1, 4);
        tab.colocarPeca(p, cp1);
        
        p = tab.getPosicao(7, 6);
        Cavalo cp2 = new Cavalo('O');
        this.preto.inserirPecaVetor(cp2, 5);
        tab.colocarPeca(p, cp2);
        
        p = tab.getPosicao(7, 0);
        Torre tp1 = new Torre('O');
        this.preto.inserirPecaVetor(tp1, 6);
        tab.colocarPeca(p, tp1);
        
        p = tab.getPosicao(7, 7);
        Torre tp2 = new Torre('O');
        this.preto.inserirPecaVetor(tp2, 7);
        tab.colocarPeca(p, tp2);
        
        Peao pp;
        for(int i = 0,j = 8; i < 8; i ++, j ++){
            p = tab.getPosicao(6, i);
            pp = new Peao('O');
            this.preto.inserirPecaVetor(pp, j);
            tab.colocarPeca(p, pp);
        }
        
        
        /*-------------------------Criando pecas do jogador branco-----------------------------*/
        p = tab.getPosicao(0, 4);
        Rei rb = new Rei('X');
        this.branco.inserirPecaVetor(rb, 0);
        tab.colocarPeca(p, rb);
        
        p = tab.getPosicao(0, 3);
        Dama db = new Dama('X');
        this.branco.inserirPecaVetor(db, 1);
        tab.colocarPeca(p, db);
        
        p = tab.getPosicao(0, 2);
        Bispo bb1 = new Bispo('X');
        this.branco.inserirPecaVetor(bb1, 2);
        tab.colocarPeca(p, bb1);
        
        p = tab.getPosicao(0, 5);
        Bispo bb2 = new Bispo('X');
        this.branco.inserirPecaVetor(bb2, 3);
        tab.colocarPeca(p, bb2);
        
        p = tab.getPosicao(0, 1);
        Cavalo cb1 = new Cavalo('X');
        this.branco.inserirPecaVetor(cb1, 4);
        tab.colocarPeca(p, cb1);
        
        p = tab.getPosicao(0, 6);
        Cavalo cb2 = new Cavalo('X');
        this.branco.inserirPecaVetor(cb2, 5);
        tab.colocarPeca(p, cb2);
        
        p = tab.getPosicao(0, 0);
        Torre tb1 = new Torre('X');
        this.branco.inserirPecaVetor(tb1, 6);
        tab.colocarPeca(p, tb1);
        
        p = tab.getPosicao(0, 7);
        Torre tb2 = new Torre('X');
        this.branco.inserirPecaVetor(tb2, 7);
        tab.colocarPeca(p, tb2);
        
        Peao pb;
        for(int i = 0, j = 8; i < 8; i ++, j++){
            p = tab.getPosicao(1, i);
            pb = new Peao('X');
            this.branco.inserirPecaVetor(pb, j);
            tab.colocarPeca(p, pb);
        }
        
        //chama o loop do jogo começando do zero
        this.loopJogo(0);
    }
    
    //funcao que carrega um jogo do arquivo, qualquer erro encontrado, iremos
    //gerar um novo jogo
    public void CarregaJogo(){
        
        int contRodadas;
        
        try{
            
            File jogo = new File("jogoSalvo.txt");
            Scanner leitor = new Scanner(jogo);
            
            //lemos primeiro o contador de rodadas e quantidades de pecas
            //de ambos os jogadores
            contRodadas = leitor.nextInt();
            branco.setQnt_pecas(leitor.nextInt());
            preto.setQnt_pecas(leitor.nextInt());

            //variaveis auxiliares
            String linha;
            char peca;
            int T = 6;
            int t = 6;
            int C = 4;
            int c = 4;
            int B = 2;
            int b = 2;
            int P = 8;
            int p = 8;
            Posicao pos;
            //vamos escanear cada posicao guardada
            for(int i = 0; i < 8; i ++){
                for(int j = 0; j < 8; j ++){
                    linha = leitor.next();
                    //vemos se a posicao esta vazia
                    if(!(linha.equals("V"))){
                        peca = linha.charAt(0);
                        pos = tab.getPosicao(i, j);
                        //defini se a peca eh branca ou preta
                        if(Character.isUpperCase(peca)){//PRETO
                            //caso de cada peca
                            switch(peca){
                                case 'R':
                                    Rei R = new Rei('O');
                                    tab.colocarPeca(pos, R);
                                    preto.inserirPecaVetor(R, 0);
                                    break;
                                case 'D':
                                    Dama D = new Dama('O');
                                    tab.colocarPeca(pos, D);
                                    preto.inserirPecaVetor(D, 1);
                                    break;
                                case 'B':
                                    Bispo Bi = new Bispo('O');
                                    tab.colocarPeca(pos,Bi);
                                    preto.inserirPecaVetor(Bi, B);
                                    B ++;
                                    break;
                                case 'C':
                                    Cavalo Ca = new Cavalo('O');
                                    tab.colocarPeca(pos, Ca);
                                    preto.inserirPecaVetor(Ca,C);
                                    C ++;
                                    break;
                                case 'T':
                                    Torre To = new Torre('O');
                                    tab.colocarPeca(pos, To);
                                    preto.inserirPecaVetor(To, T);
                                    T ++;
                                    break;
                                case 'P':
                                    Peao Pe = new Peao('O');
                                    tab.colocarPeca(pos, Pe);
                                    preto.inserirPecaVetor(Pe, P);
                                    P ++;
                                    break;
                            }
                        }else{//BRANCO
                            switch(peca){
                                case 'r':
                                    Rei r = new Rei('X');
                                    tab.colocarPeca(pos, r);
                                    branco.inserirPecaVetor(r, 0);
                                    break;
                                case 'd':
                                    Dama d =  new Dama('X');
                                    tab.colocarPeca(pos, d);
                                    branco.inserirPecaVetor(d, 1);
                                    break;
                                case 'b':
                                    Bispo bi = new Bispo('X');
                                    tab.colocarPeca(pos, bi);
                                    branco.inserirPecaVetor(bi, b);
                                    b ++;
                                    break;
                                case 'c':
                                    Cavalo ca = new Cavalo('X');
                                    tab.colocarPeca(pos, ca);
                                    branco.inserirPecaVetor(ca, c);
                                    c ++;
                                    break;
                                case 't':
                                    Torre to = new Torre('X');
                                    tab.colocarPeca(pos, to);
                                    branco.inserirPecaVetor(to, t);
                                    t ++;
                                    break;
                                case 'p':
                                    Peao pe = new Peao('X');
                                    tab.colocarPeca(pos, pe);
                                    branco.inserirPecaVetor(pe, p);
                                    p ++;
                                    break;
                            }
                        }
                    }
                }
            }
            System.out.println("\nO jogo foi carregado com sucesso");
            this.loopJogo(contRodadas);
            
        }catch(Exception e){
            System.out.println("\nNão foi possivel carregar o jogo, começaremos um novo jogo");
            this.novoJogo();
        
        }
        
    }
    
    //funcao que salva o jogo em um .txt
    public void SalvaJogo(int contRodadas, int qntBranco, int qntPreto){
        try{
            File jogo = new File("jogoSalvo.txt");
            //se o arquivo foi criado ou ja existe
            if((jogo.createNewFile() && jogo.canWrite()) || (jogo.exists() && jogo.canWrite())){
                FileWriter escritor = new FileWriter("jogoSalvo.txt");
                //escreve-se o contador de rodadas o numero de pecas do branco e do preto
                escritor.write(contRodadas + "\n");
                escritor.write(qntBranco + "\n");
                escritor.write(qntPreto + "\n");
                //grava cada posicoa do tabuleiro indicando se ela esta vazia
                //ou o desenho da peca que ocupa a posicao especifica
                for(int i = 0; i < 8; i ++){
                    for(int j = 0; j < 8; j ++){
                        Posicao p = tab.getPosicao(i, j);
                        if(p.isOcupada()){
                            escritor.write(p.getPeca().desenha() + " ");
                        }else{
                            escritor.write("V ");
                        }
                    }
                    escritor.write("\n");
                }
                System.out.println("Jogo salvo com sucesso!");
                escritor.close();

            }else{
                throw new IOException("Impossivel escrever no arquivo");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    //funcao que faz o movimento com a captura de uma peca no final
    private void captura(int inLinha, char inColuna, int fimLinha, char fimColuna){
        
        //parte para capturar a peca correta
        Peca p = tab.getPosicao((fimLinha - 1),(int)(fimColuna - 65)).getPeca();
        if(p.getCor() == 'X'){
            branco.capturarPeca(p);
        }else if(p.getCor() == 'O'){
            preto.capturarPeca(p);
        }
        
        //fazendo o movimento utilizando a funcao do tabuleiro
        tab.getPosicao((fimLinha - 1),(int)(fimColuna - 65)).setOcupada(false);
        
        tab.movimento(inLinha, inColuna, fimLinha, fimColuna);
    
    }
    
    //o xeque mate so acontece quando o rei de um dos jogadores for capturado
    private boolean xequeMate(Peca rei){
        return rei.isCapturada();
    }
    
    //funcao que checa se o rei de algum dos jogadores esta em xeque
    private boolean xeque(Posicao rei, char corRei, int qntPecas){
        
        int linhaRei = rei.getLinha();
        char colunaRei = rei.getColuna();
        int contPecas = 0;
        
        //checa-se cada posicao do tabuleiro por peças que são inimigas
        //e verifica se o movimento ate o rei eh possivel
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                if(tab.getPosicao(i, j).isOcupada() && tab.getPosicao(i, j).getPeca().getCor() != corRei){
                    contPecas ++;
                    if(tab.getPosicao(i, j).getPeca() instanceof Peao){
                        if(tab.getPosicao(i, j).getPeca().checaMovimento(i+1, (char)(j+65), linhaRei, colunaRei)
                            && j != (int)(colunaRei - 65)){
                            return true;
                        }
                    }else if(tab.getPosicao(i, j).getPeca().checaMovimento(i+1, (char)(j+65), linhaRei, colunaRei)
                       && tab.checaCaminho(i, j, (linhaRei-1), (int)(colunaRei-65))){
                        return true;
                    }
                }
            }
            //faremos isso ate encontrarmos todos as pecas do inimigo
            if(contPecas >= qntPecas){
                    break;
            }
        }
        
        return false;
        
    }
    
    //funcao que checa se a entrada do usuario foi valida ao tabuleiro ou nao
    private boolean checaEntrada(int linha, char coluna){
        //checagem da entrada numerica
        if(linha < 1 || linha > 8){
            return false;
        }
        
        //checagem do caractere
        if(coluna < 'A' || coluna > 'H'){
            return false;
        }
        
        return true;
    }
}

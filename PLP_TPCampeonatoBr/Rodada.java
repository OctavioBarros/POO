import java.util.Scanner;//entrada

public class Rodada{
   private int rodada;
   private Partida[] jogos;
  
   private boolean[] participa; // Responsavel por verificar os times que ja foram selecionados no campeonato
   
   //Variaveis globais
   public static Scanner ler = new Scanner(System.in);
   public static int mandante;//Inteiro que corresponde a um endereco do vetor
   public static int visitante;//Inteiro que corresponde a um endereco do vetor
   public static Time[] time;//Vetor de time
   
   //Construtor
   public Rodada(int rodada, int numTimes){
      this.rodada = rodada;
      jogos = new Partida[numTimes/2];
      participa = new boolean[numTimes];
      
   }
   
   public void rodadaSistema(Time[] time){
      this.time = time;
      
      
      setParticipa();
      System.out.println("//......................................................................................//");
      System.out.printf("Times participantes da rodade de numero: %d\n",rodada);
      System.out.println("Caso digite o resultado de algum time de forma errada, voce podera editar a partida novamente depois!");
      for(int i = 0; i<time.length;i++){
         System.out.print(i+1);
         System.out.print(" - ");
         System.out.print(time[i].getNome());
         System.out.print("\n");
      }
      System.out.println("Insira o numero correspondente para selecionar o time desejado.");
      for(int i = 0; i<jogos.length; i++){
         System.out.printf("\nSelecione os 2 times que irao disputar o jogo de numero %d da rodada.\n",i+1);
         
         boolean select = false; //Select ira verificar os time.
         
         while(select == false){
            System.out.printf("Selecione o mandante: \n");
            mandante = ler.nextInt();
            select = selectTime(mandante,select,i);
                     
         }
         
         select = false;
         
         while(select == false){
            System.out.printf("Selecione o visitante: \n");
            visitante = ler.nextInt();
            select = selectTime(visitante,select,i);
         }
         setTimesPartida(i); //Instancia os times do vetor de da partida
         setPlacar(i); //Seta o placar do jogo   
      }   
      System.out.println("//......................................................................................//");
      
   }
   
   private void setParticipa(){//atribuindo valores padroes para participa
      for(int i=0; i< participa.length;i++){
         participa[i] = false; //participa vai verificar se um time ja foi escolhido para jogar na rodada.
      }
   }
   
   private boolean selectTime(int timeSelecionado,boolean select, int i){//Seleciona um time que jogara na partida
      if(timeSelecionado-1 >= 0 && timeSelecionado-1 < time.length){// verifica se o time se localiza dentro do vetor
         if(participa[timeSelecionado -1] == false){//Verifica se o time ja foi selecionando
            System.out.printf(time[timeSelecionado - 1].getNome());
            System.out.printf(" selecionado para o jogo %d da rodada de numero %d\n", i+1,rodada);
            participa[timeSelecionado - 1] = true;//Marca que o time correspondente ja foi selecionado nesta rodada
            return select = true;
         }
         else{
            System.out.println("Time ja foi selecionado nessa rodada!");
            return select;
         }
      }
      else{
         System.out.println("Numero nao corresponde a um time.");
         return select;
      } 
   }
   
   private void setTimesPartida(int i){
      jogos[i] = new Partida();
      jogos[i].setMandante(time[mandante -1]);
      jogos[i].setVisitante(time[visitante -1]);
      jogos[i].setTitularA();
      jogos[i].setTitularB();
   }
   
   private void setPlacar(int i){
      System.out.print("\nDigite o numero de gols marcados pelo mandante: ");
      int golsM = ler.nextInt();
      jogos[i].setGolsA(golsM);
      
      System.out.print("\nDigite o numero de gols marcados pelo visitante: ");
      int golsV = ler.nextInt();
      jogos[i].setGolsB(golsV);
      
      pontuacaoTimes(jogos[i]);
   
      System.out.println("\n //......................Confronto......................//\n");
      System.out.print("           ");
      jogos[i].printResultadoRegistrado();//Imprime resultado
      System.out.println("//......................................................//\n");
      
   }
   
   private void pontuacaoTimes(Partida jogo){//Acrescenta a pontuacao dos times baseado no resultado
      
      int resultado = jogo.getResultado();
      if(resultado == -1){
         jogo.getVisitante().addPontos(3);
      }
      else if(resultado == 1){
         jogo.getMandante().addPontos(3);
      }
      else{
         jogo.getMandante().addPontos(1);
         jogo.getVisitante().addPontos(1);
      }
   }
   
   public void editarRodada(){
      int option; //Switch
      boolean verificador = false; //para o while 
      System.out.println("Jogos da " + rodada + " Rodada!");
      for(int i = 0; i< jogos.length; i++){
         System.out.println(i+1+ " jogo");
         jogos[i].printResultadoRegistrado();
      }
      
      while( verificador == false){
      
         System.out.println("Escolha uma opcao: ");
         System.out.println("1 - Editar primeiro jogo");
         System.out.println("2 - Editar segundo jogo");
         System.out.println("3 - Editar terceiro jogo");
         System.out.println("4 - Finalizar!");
         option = ler.nextInt();

         switch(option){
         case 1:
            resetarJogo(option -1);
            break;
         case 2:
            resetarJogo(option -1);
            break;
         case 3:
            resetarJogo(option -1);
            break;
         case 4:
            verificador = true;
            break;
         default:
            break;               
         }
   
      }
   }
   
   private void resetarJogo(int i){
      removerPontuacao(jogos[i]);
      System.out.println("Mandante: " + jogos[i].getMandante().getNome());
      System.out.println("Visitante: " +  jogos[i].getVisitante().getNome());
      setPlacar(i);
      
   }
   
   private void removerPontuacao(Partida jogo){//Remove a pontuacao que receberam na ultima rodada;
      int resultado = jogo.getResultado();
      if(resultado == -1){
         jogo.getVisitante().addPontos(-3);
      }
      else if(resultado == 1){
         jogo.getMandante().addPontos(-3);
      }
      else{
         jogo.getMandante().addPontos(-1);
         jogo.getVisitante().addPontos(-1);
      }
   }
}   
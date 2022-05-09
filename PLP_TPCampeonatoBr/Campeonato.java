import java.util.Scanner;

public class Campeonato{
   private Time[] serieA;
   private int ano;
   private Tabela tabela;
   private Rodada[] rodadas;
   private int numTimesAdicionados;
   private int numTimes;
   private int aux;
   Scanner sc = new Scanner(System.in);
   
   public Campeonato(int ano){
      this.ano = ano;
      numTimesAdicionados = 0;
      numTimes = 6;
      int numRodadas = (numTimes-1)*2;
      serieA = new Time[numTimes];
      rodadas =  new Rodada[numRodadas];
      tabela = new Tabela();
      setTimes();
      aux = 0;
   }
   
   public void addTime(Time novo){
      if(aux >= numTimes)
         System.out.println("Tabela completada");
      else{
         serieA[aux] = novo;
         System.out.println(novo.getNome() + " adicionado no campeonato!");
         aux++;
      }
   }
   
   public void printTimes(){
      System.out.print("Times do campeonato: ");
      for(int i = 0 ;i < serieA.length; i++){
         if(serieA[i].getNome().equals("-111"))//Caso o time seja o default, n ira imprimir
            continue;
         else{
            if( i+1>= serieA.length)
               System.out.print(serieA[i].getNome() + ".");
            else
               System.out.print(serieA[i].getNome() + ", ");   
         }
      } 
   }
   
   public void novaRodada(int novaRodada){
      
      boolean rodadaImplementada = false; //Verifica se a nova rodada foi implementada
      int option;//Switch
      
      while(rodadaImplementada == false){ //Garante que a nova rodada deve ser implementada
         System.out.println("Escolha uma opcao abaixo.");
         System.out.println("1 - Editar a " + novaRodada + " rodada.");
         System.out.println("2 - Editar uma rodada anterior.");
         option = sc.nextInt();
         switch(option){
            case 1:
               rodadas[novaRodada - 1] = new Rodada(novaRodada,numTimes);//Estanciando
               rodadas[novaRodada - 1].rodadaSistema(serieA);
               rodadaImplementada = true;
            break;
            case 2:
               System.out.println("Digite o numero da rodada que deseja editar: ");
               int rodadaEditavel = sc.nextInt();
               if((rodadaEditavel -1 < novaRodada -1) && (rodadaEditavel - 1>= 0)){//Verifica se q rodada editavel existe
                  rodadas[rodadaEditavel -1].editarRodada();
               }
               else
                  System.out.println("Rodada nao foi jogada ainda");
               break;
            default:
               break;   
         }
      }
      
      
      
   }

   
   public void checaFraudes(){
      Jogador auxiliar = new Jogador();//Será usado para comparações
      
      for(int a=0; a<serieA.length;a++){
      
         Jogador[] teste = serieA[a].getElenco(); //Recebe o vetor de jogadores do time de indice especificado
         
         for(int b=0; b<serieA[a].getElenco().length;b++){
         
            auxiliar = teste[b]; //Recebe o jogador do indice do vetor de jogadores
            
            for(int i=a; i<serieA.length;i++){
            
               Jogador[] vetor = serieA[i].getElenco();
               
               //Esses dois for's são para percorrer os comparadores, os 2 primeiros para percorrer o comparado, então não há nescessidade de retornar aos indices que ja foram comparados anteriormente.
               for(int j=b; j<vetor.length;j++){   //Por isso eles recebem os indices do Jogador auxiliar.            
                  if((a==i) && (b==j)){ //Se os indices são iguais, não há motivos de comparação
                     continue;
                  }
                  if(auxiliar.getCPF() == -3145296){//Se o CPF eh o valor padrao, não há motivos para alertar
                     continue;
                  }
                  else if(auxiliar.getCPF() == vetor[j].getCPF()){
                     System.out.println("Fraude, CPF clonado ");
                     System.out.print("Jogadores: \n" + auxiliar.getNome() + ": " + auxiliar.getCPF() +"\n"+vetor[j].getNome()+": "+ vetor[j].getCPF()+"\n");
                  }
               }
            }   
         }
      }
      
            
   }

   public void transferencia() {
     String jogador;
     String timeAtual;
     String timeDestino;
     boolean encontrado= true; //Verifica se o time foi encontrado 
     double salario; //Salario Jogador
     int auxA = -1; //Endereco time atual
     int auxC = -1; //Endereco time contratante
     Jogador auxJ = new Jogador();//Jogador axuiliar
     
     System.out.println("Para transferencia precisamos que diga nome do jogador: ");
     jogador = sc.next();
     System.out.println("Diga o salario que deseja");
     salario = sc.nextDouble();
     System.out.println("Diga agora para de qual time ele pertence?");
     timeAtual = sc.next();
     System.out.println("Diga para qual time o jogador vai");
     timeDestino = sc.next();
     
     for(int i=0; i < serieA.length ; i++){
       if (serieA[i].getNome().equals(timeAtual)){
         auxA = i;
         break;
       }
       if(i + 1 >= serieA.length){
         System.out.println("Time do jogador nao encontrado!");
         encontrado = false;
       } 
     }
     
     for(int i=0; i < serieA.length ; i++){
       if (serieA[i].getNome().equals(timeDestino)){
         auxC = i;
         break;
       }
       if(i+1 >= serieA.length){
         System.out.println("Time para tranferencia nao encontrado!");
          encontrado = false;
       }    
     }

     if(encontrado == true){  
      Jogador[] jg = serieA[auxA].getElenco();
     
      for(int i = 0; i < serieA[auxA].getElenco().length; i++){//Tamanho do vetor de jogadores do time Atual
       
        if(jg[i].getNome().equals(jogador)){
         
          auxJ.pacoteSet(jg[i].getNome(),jg[i].getCPF(),jg[i].getDataString());//Setando novo jogador
          serieA[auxC].contrataJogador(auxJ, salario); //Novo salario
          System.out.println(jg[i].getNome() + " Transferido para " + serieA[auxC].getNome());
          //Setando valores padroes
          jg[i].setNome("-111");
          jg[i].setSalario(-111.11);
          jg[i].setCPF(-3145296);
          serieA[auxA].diminuirContador();
          break;
        }
        if(i+1>= serieA[auxA].getElenco().length){
         System.out.println("Jogar nao encontrado em " +serieA[auxA].getNome()+ " !");
        }   
      }
     }
  }   
   
   public void listaCidades() {
   
      String[] vetor = new String[serieA.length];
      for(int i=0; i< vetor.length;i++){//Preenche o vetor com uma palavra padrao
         vetor[i] = "---";
      }
      
      for(int i=0;i<vetor.length;i++){
         boolean verificador = true;//Verifica a cidade repetida
         
         for(int j=0;j<vetor.length;j++){
            if(serieA[i].getCidade().equals(vetor[j])) {//Compara se a cidade tem o mesmo nome que o nome guardado em um dos indices do vetor, se verdade verificador recebe false.
               verificador = false;           
            }
         }   
         if(verificador == true){//Se verificador não foi modificado, o vetor eh preenchido.
            vetor[i] = serieA[i].getCidade();
         }   
      }
      
      //printando vetor
      System.out.print("Cidades dos times: ");
      for(int i=0;i<vetor.length;i++){
         if(vetor[i].equals("---")){//Não printa valores Padrão
            continue;
         }
         if(i+1 >= vetor.length)
            System.out.print(vetor[i] + ".\n");
         else
            System.out.print(vetor[i] + ", ");
      }
   }
   
   public void classificacao() {
      tabela.tabelaClassificacao(serieA);
   }
   
   public Time[] getTimes(){//retorna vetor de times
      return serieA;
   } 
   
   public void setTimes(){//para verificar depois se o vetor foi preenchido por um time valido ou n 
      for(int i=0; i < numTimes; i++){
         Time novo = new Time();
         novo.setNome("-111");
         novo.setCidade("-111");
         novo.setPontos(0);
         serieA[i] = novo;
      }   
   }  
}

//Foram implementados os metodos A,B,C,D,F e G.
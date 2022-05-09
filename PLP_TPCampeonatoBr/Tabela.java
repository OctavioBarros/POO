public class Tabela{
   String classificacao;
   
   public static Time[] time; //Vetor de time
   
   public Tabela(){
   }
   
   public void setClassificacao(String classificacao){
      this.classificacao = classificacao;
   }
   
   public String getClassificacao(){
      return classificacao;
   }
   
   //Imprime a tabela de classificacao
   public void tabelaClassificacao(Time[] time){
      this.time = time;
      //teste
      
      quickSortTimes(time,0,time.length -1);
      
      System.out.println("//.........................Classificacao.........................//");
      System.out.println("Class | Pts | Time");
      //teste
      for(int i = 0; i<time.length;i++){
         if(i-1 >= 0){
            if(time[i].getPontos() == time[i-1].getPontos())
               System.out.print(" - ");
            else
               System.out.print(" "+ (i+1) +" ");
         }
         else{
            System.out.print(" "+ (i+1) + " ");
         }
         System.out.print("   |  ");
         System.out.print(time[i].getPontos());
         System.out.print("  | ");
         System.out.print(time[i].getNome());
         System.out.print("\n");
      }
      System.out.println("//..............................................................//");

   }
   
   private void quickSortTimes(Time[] time, int inicio, int fim){
      if(inicio < fim){
         int posicaoPivo = separar(time, inicio, fim);
         quickSortTimes(time, inicio, posicaoPivo -1);
         quickSortTimes(time, posicaoPivo + 1, fim);
      }
   }
   
   private int separar(Time[] time, int inicio, int fim){
      Time pivo = time[inicio];
      int i = inicio + 1, f = fim;
      while(i <= f){
         if(time[i].getPontos() >= pivo.getPontos())
            i++;
         else if(pivo.getPontos() > time[f].getPontos())
            f--;
         else{
            Time troca = time[i];
            time[i] = time[f];
            time[f] = troca;
            i++;
            f--;
         }      
      }
      time[inicio] = time[f];
      time[f] = pivo;
      return f;
   }
}
import java.io.*;
import java.text.DecimalFormat;//casas decimais
public class Principal{
   static FileInputStream stream;
   static InputStreamReader reader;
   static BufferedReader br;
   static FileWriter myWriter;
   
   static int contador;
   static int cidade = 1; //Imprimir as cidades
   static int somaX = 0;  //Somatorio de todos os X do calculo
   static int somaY = 0;  //Somatorio de todos os Y do calculo
   static int aux = 0;    //
   
   
   //Método que ordena um vetor
   public void ordena(int arr[], int begin, int end) {
      if (begin < end) {
         int partitionIndex = partition(arr, begin, end);
         ordena(arr, begin, partitionIndex-1);
         ordena(arr, partitionIndex+1, end);
      }
   }
   
   private int partition(int arr[], int begin, int end) {
       int pivot = arr[end];
       int i = (begin-1);
       for (int j = begin; j < end; j++) {
           if (arr[j] <= pivot) {
               i++;
               int swapTemp = arr[i];
               arr[i] = arr[j];
               arr[j] = swapTemp;
           }
       }
       int swapTemp = arr[i+1];
       arr[i+1] = arr[end];
       arr[end] = swapTemp;
       return i+1;
   }
   
   public static void criaArquivo(){
      try {
         File myObj = new File("saida.txt");
         if (myObj.createNewFile()) {
            System.out.println("Arquivo criado: " + myObj.getName());
         }else{
            System.out.println("Arquivo ja existe.");
         }
      }catch(IOException e){
         System.out.println("Um erro ocorreu");
         e.printStackTrace();
      }      
   }
   
   //Metodo que escreve uma nova linha no arquivo de saida.txt
   public static void escreveLinha(String linha){
      try {
         myWriter.write(linha + '\n');
         System.out.println("Linha escrita com sucesso.");
      }catch(IOException e){
         System.out.println("Um erro ocorreu.");
         e.printStackTrace();
       }
   }
   
   //Metodo que gerencia calculos e organiza a escrita do arquivo
   public static void escreveArquivo(String linha){
      int x = -1,y = -1;
      
      String[] palavra = linha.split(" ");
         if(palavra.length == 1){
            
               contador = Integer.parseInt(palavra[0]);
               //Resetando todas variaveis do caso passado.
               somaX = 0;
               somaY = 0;
               aux = 0;
            
            
               System.out.println("IMPOSSIVEL CONVERTER LETRA PRA NUMERO");
            
         }
         else{
            try{
               x = Integer.parseInt(palavra[0]);
               y = Integer.parseInt(palavra[1]);
               
               somaX += x;
               somaY += y;
               
            }   
            catch(Exception e){
               System.out.println("IMPOSSIVEL CONVERTER LETRA PRA NUMERO");
            }
         }
         
         //Escrevendo a cidade testada.
         if((aux == 0)&&(contador !=0)){
            try{
               myWriter.write("Cidade#"+cidade + "\n");
               aux ++;
               cidade ++;
            }
            catch (Exception e){
               System.out.println("Error ao escrever no arquivo");
            }
         }

      
      if((contador != 0) && (x != -1 ) && ( y != -1)){
         calcularMediaPessoa(x,y);
      }
      
      
      
   }
   //Escreve no arquivo o numero de pessoas e a sua media de consumo por pessoa na residencia
   public static void calcularMediaPessoa(int x,int y){
      if((x >= 1) && (x <= 10) && (y >= 1) && (y <= 200)){
         int media = y/x;
         if(aux == contador){
            try{
                myWriter.write(x + "-" + media + "\n");
                calcularMediaConsumo();
            }
            catch (Exception e){
               System.out.println("Erro ao escrever no arquivo!");
            }    
         }
         else{
            try{
               myWriter.write(x + "-" + media + " ");
               aux++;
            }
            catch (Exception e){
               System.out.println("Erro ao escrever no arquivo!");
            }   
         }
      }
      
      else{
         System.out.println("Valores invalidos");
      }
   }
   
   //Escreve a media de consumo da cidade
   public static void calcularMediaConsumo(){
      float x = somaX, y = somaY;
      float media = y/x;
      try{
         DecimalFormat df = new DecimalFormat("0.00");
         myWriter.write("Consumo medio:" + df.format(media) + "m3" + "\n\n");
      }
      catch (Exception e){
         System.out.println("Erro ao escrever no arquivo!");
      }   
   }  
   
   
   public static void main(String[] args) throws IOException{
      
      stream = new FileInputStream("1.txt");
      reader = new InputStreamReader(stream);
      br = new BufferedReader(reader);
      myWriter = new FileWriter("saida.txt");
      criaArquivo();
      

      String linha = br.readLine();//Leitura da primeira linha
      while(linha != null) { //Repeticao enquanto tiver linhas no arquivo
         escreveArquivo(linha);
         System.out.println(linha);
         linha = br.readLine();//Leitura das outras linhas
      }
      myWriter.close();//Deixar esse método no fim   
   }   
}
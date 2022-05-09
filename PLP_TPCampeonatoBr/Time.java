public class Time{
   private String nome;
   private String cidade;
   private Jogador[] elenco = new Jogador[6];
   private int pontos;
   private  int contador;
 
   public void Time(){
      contador = 0;
      setVetor();
   }
      
   public void Time(String nome, String cidade){
      this.nome = nome;
      this.cidade = cidade;
      setVetor();
      contador = 0;
   }
   
   public void pacoteSet(String nome, String cidade){
      this.nome = nome;
      this.cidade = cidade;
      this.pontos = 0;
      setVetor();
      contador = 0;
   }
   
   public void setPontos(int pts){
      this.pontos = pts;
   }
     public void setNome(String nome){
      this.nome = nome;
   }
     
   public void setCidade(String cidade){
      this.cidade = cidade;
   }
   
      
   public int getPontos(){
      return pontos;
   }
   
   public String getNome(){
      return this.nome;
   }
   
   public String getCidade(){
      return this.cidade;
   }
   
   public Jogador[] getElenco(){
      return this.elenco;
   }
   
   public boolean contrataJogador(Jogador comprado,double salario){
     if(contador < elenco.length){
        comprado.setSalario(salario);
        comprado.setTime(this.nome);
        comprado.setContrato(true);
        elenco[contador] = comprado;
        contador++;
        return true;
     }
     System.out.println("Elenco completado");
     return false;
   }
   
   public void setVetor(){//Preenche todo o vetor com valores padrões para futuras checagens
      for(int i=0; i<elenco.length; i++){
         Jogador novo = new Jogador();
         novo.setNome("-111");
         novo.setSalario(-111.11);
         novo.setCPF(-3145296);
         elenco[i] = novo;
      }   
   }
   
   public void addPontos(int pts){
      this.pontos += pts;
   }
   
   public void diminuirContador(){
      this.contador --;
   }

}
public class Jogador extends Pessoa{
  
   private String time;
   private double salario;
   private boolean contratado;
   public String data;
   
   public Jogador(){
   }
   
   
   public Jogador(String nome, int cpf, String data){//Esta dando erro de tipo, o método pacoteSet corrige. Obs. Todos os construtores com parametros deu o mesmo erro.
      super.Pessoa(nome,cpf,data);
   }

   public void pacoteSet(String nome, int cpf, String data){ // Tivemos que cirar esse método, pois o construtor com paramêtros estava alegando erro de tipo, 
       this.data = data;         
       super.Pessoa(nome,cpf,data);                          //  mesmo com os tipos corretos.
   }
   
   //Set's do Jogador
   public void setIdade(int dia, int mes, int ano){
      super.setNascimento(dia,mes,ano);
   }
   
   public void setTime(String time){
      this.time = time;
   }
   
   public void setSalario(double salario){
      this.salario = salario;
   }
   
   public void setContrato(boolean contratado){
      this.contratado = contratado;
   }
   
   //Get's do Jogador
   public int getIdade(){
      return super.calculaIdade();   
   }
   
   public String getTime(){
      return time;
   }
   
   public double getSalario(){
      return salario;
   }
   
   public boolean getContrato(){
      return contratado;
   }
   
   public String getDataString(){
      return this.data;
   }
}
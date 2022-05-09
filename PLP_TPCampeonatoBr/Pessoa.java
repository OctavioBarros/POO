
import java.util.Calendar;

public abstract class Pessoa{
  
  private String nome;
  private int CPF;
  private Calendar nascimento = Calendar.getInstance();
  private double altura;
  private double peso;
  
    
  public void Pessoa(String nome,int cpf, String data){
     int dia,mes,ano; 
     this.nome = nome;
     this.CPF = cpf;
       
     
     String[] calendario = data.split("/");
     try{
        int i = 0;
        dia = Integer.parseInt(calendario[i]);
        mes = Integer.parseInt(calendario[i+1]);
        ano = Integer.parseInt(calendario[i+2]);
        setNascimento(dia,mes,ano);
     }
     catch(Exception e){
        System.out.println("Data não esta no formato padrão");
     }
  }

  public String getNome(){
    return nome;
  }
  
  public void setNome(String nome){
    this.nome = nome;
  }
  
  public int getCPF(){
    return CPF;
  }
  
  public void setCPF(int CPF){
    this.CPF = CPF;
  }
  
  public void setNascimento(int dia, int mes, int ano){
    this.nascimento.set(Calendar.DAY_OF_MONTH,dia);
    this.nascimento.set(Calendar.MONTH,mes-1);
    this.nascimento.set(Calendar.YEAR,ano);
  }
  
  public double getAltura(){
    return altura;
  }
  
  public void setAltura(double altura){
    this.altura = altura;
  }
  
  public double getPeso(){
    return peso;
  }
  
  public void setPeso(double peso){
    this.peso = peso;
  }
  
  public int calculaIdade(){
   int idade;
   Calendar atual = Calendar.getInstance();
   atual.getTime();
   idade = atual.get(Calendar.YEAR)-nascimento.get(Calendar.YEAR);
   if(nascimento.get(Calendar.MONTH) > atual.get(Calendar.MONTH)){
      return idade;
   }
   else if(nascimento.get(Calendar.MONTH) == atual.get(Calendar.MONTH)){
      
      if(nascimento.get(Calendar.DAY_OF_MONTH) >= atual.get(Calendar.DAY_OF_MONTH)){
         return idade;
      }
      else{
         return idade-1;
      }
      
   }
   else
      return idade-1;
  }

}


public class Partida  {

  Time mandante = new Time();
  Time visitante = new Time();
  Jogador[] titularA = new Jogador[6];
  Jogador[] titularB = new Jogador[6];
  private int golsA;
  private int golsB;
  
  
  public Partida(){
  }

  public void setMandante(Time mandante) {
    this.mandante = mandante;
  }

  public Time getMandante() {
    return mandante;
  }

  public void setVisitante(Time visitante) {
    this.visitante = visitante;
  }

  public Time getVisitante() {
    return visitante;
  }

  public void setGolsA(int golsA) {
    this.golsA = golsA;
  }

  public int getGolsA() {
    return golsA;
  }

  public void setGolsB(int golsB) {
    this.golsB = golsB;
  }

  public int getGolsB() {
    return golsB;
  }
  public void setTitularA(){
    this.titularA = mandante.getElenco();
  }
  public void setTitularB(){
    this.titularB = visitante.getElenco();
  }
  public String getcidadeJogo(){
    return mandante.getCidade();
  }
  public int getResultado(){
    //CASO O TIME VISITANTE GANHE:
    if (getGolsA()< getGolsB()){
      return -1;

    //CASO O TIME MANDANTE GANHE:
    }else if (getGolsA()> getGolsB()){
      return 1;

     //CASO DE IMPATE:
    }else{
      return 0;
    }
  }
  public void printResultadoRegistrado(){
    System.out.print(mandante.getNome()+" "+ getGolsA()+ " x "+ getGolsB() + " "+ visitante.getNome() + "\n");
  }

  
}
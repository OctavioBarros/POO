

public class Main{
   public static void main(String[] args){

     Jogador Joao = new Jogador();
     Jogador Pedro = new Jogador();
     Jogador Victor = new Jogador();
     Jogador Pablo = new Jogador();
     Jogador Neymar = new Jogador();
     Jogador Messi = new Jogador();
     
     //Joao.pacoteSet("Joao", 115544, "01/01/2000");
     Pedro.pacoteSet("Pedro", 223344, "21/11/2001");
     Victor.pacoteSet("Victor", 555544, "04/03/1999");
     Pablo.pacoteSet("Pablo", 125544, "08/05/1998");
     Neymar.pacoteSet("Neymar", 115544, "15/10/2005");
     Messi.pacoteSet("Messi", 515544, "28/02/2004");

     Time t1 = new Time();
     Time t2 = new Time();
     
     t1.pacoteSet("carandiru","Cuba do Sul");
     t2.pacoteSet("PCC","Cuba do Norte");
     
     Partida loucura = new Partida();
             
     t1.contrataJogador(Messi,898797456);
     t1.contrataJogador(Pedro,35454);
     t1.contrataJogador(Joao,846);
     t1.contrataJogador(Victor,54);
     t1.contrataJogador(Pablo,21);
     t1.contrataJogador(Neymar,01);
     
     
     loucura.setMandante(t1);
     loucura.setVisitante(t2);
     
     loucura.setGolsA(3);
     loucura.setGolsB(2);
     
     int result = loucura.getResultado();
     loucura.printResultadoRegistrado();
     System.out.println(result);
   }
}   
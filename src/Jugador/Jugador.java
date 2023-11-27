package Jugador;

import Estructura.Posicio;

public class Jugador implements Comparable<Jugador> {

    int puntuacio;
    Posicio pos;

    public Jugador(int puntuacio, int pos){
     this.puntuacio=puntuacio;
     Posicio[] posicions= Posicio.values();
     if(pos>=0 && pos< posicions.length){
         this.pos=posicions[pos];
     }else {
         System.out.println("Invalid position value");
     }
    }


    public int compareTo(Jugador a ){
       int posicionComparison = pos.compareTo(a.pos);
       if(posicionComparison==0){
           return Integer.compare(this.getPuntuacio(),a.getPuntuacio());
       }else{
           return posicionComparison;
       }
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public Posicio getPos() {
        return pos;
    }

    public String toString() {
        return pos + "-" + puntuacio + "\n";
    }

}

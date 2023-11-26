package Jugador;

import Estructura.Posicio;

public class Jugador implements Comparable<Jugador> {

    int puntuacio;
    Posicio pos;

    public Jugador(int puntuacio, int pos){
     this.puntuacio=puntuacio;
     Posicio[] posicions= Posicio.values();
     if(pos>=0||pos<=posicions.length){
         this.pos=posicions[pos];
     }else {
         System.out.println("Invalid position value");
     }
    }


    public int compareTo(Jugador a ){
        //如果两个位置一样，在比较分数，如果是1就是本地的大.如果不一样就直接返回，
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

    //se puede eliminar
    public String toString() {
        // 返回一个包含有用信息的字符串，例如：
        return pos + "-" + puntuacio + "\n";
        /*return "Jugador{" +
                "playerName='" + pos + '\'' +
                ", score=" + puntuacio +
                // 其他属性
                '}';*/
    }

}

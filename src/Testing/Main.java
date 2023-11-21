package Testing;

public class Main {
    public static enum Posicio{ Base, Escorta, Aler, AlerPivot, Pivot};

    public static void main(String args[]) {
        // creem una variable de la enumeració
        Posicio pos = Posicio.Escorta;
        int posINT = pos.ordinal(); // ordinal: retorna el seu enter
        String posString = pos.name(); // name: retorna un string amb el seu nom

        System.out.println(posINT + "-" + posString+"\n");

        // values: retorna tots els valors de la enumeració
        Posicio[] list = Posicio.values();
        for( int i = 0; i< list.length; i++)
            System.out.println(i+"-"+ list[i]);

        // fem una variable a partir d'un enter
        int index = 4; //caldrà utilitzar scanner o keyboard
        Posicio pos2 = list[index];
        System.out.println("\n"+ pos2);
    }
}
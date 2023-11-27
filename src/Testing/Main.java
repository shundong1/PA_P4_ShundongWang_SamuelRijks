package Testing;

import Estructura.AcbEnll;
import Estructura.ArbreException;
import Estructura.Posicio;
import Jugador.Jugador;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws ArbreException {

        AcbEnll<Jugador> tree = new AcbEnll<>();
        //AcbEnll<Jugador> tree = null;
        AcbEnll<Jugador> clonat = new AcbEnll<>();
        while(true) {
            System.out.println("Opcions:");
            int a = pregunta();
            if (a == 1) InserirJugador(tree);
            if (a == 2) EliminarJugador(tree);
            if (a == 3) Visualitzar(tree, clonat);
            if (a == 4) clonat = clonar(tree,clonat);
            if (a == 5) {System.out.println("Acabat!");
                tree.buidar();
                break;}


        }

    }

    private static AcbEnll<Jugador> clonar(AcbEnll<Jugador> tree, AcbEnll<Jugador> clonat) throws ArbreException {
        clonat=(AcbEnll<Jugador>) tree.clonar();
        clonat.printTree();
        return clonat;
    }


    private static void Visualitzar(AcbEnll tree, AcbEnll<Jugador> clonat) throws ArbreException {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        int userChoice2 = 0;
        boolean sentit= true;
        while (true) {
            System.out.println("Indica en quin arbre vols mostrar");
            System.out.println("1.- Actual");
            System.out.println("2.- Clonat");
            System.out.println("Tria l´ordre");
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice >= 1 && userChoice <= 2) {
                break;
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 1 0 2.");
            }
        }
        while(true){
            System.out.println("Indica en quin arbre vols mostrar");
            System.out.println("1. - Ascendent");
            System.out.println("2. - Descendent");
            System.out.println("Tria l´ordre[1,2]");
            userChoice2 = Integer.parseInt(scanner.nextLine());
            if (userChoice2 >= 1 && userChoice2 <= 2) {
                break;
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 1 0 2.");
            }
        }
        if(userChoice2==2){
        sentit=false;
        }
        if(userChoice==1){
        tree.iniRecorregut(sentit);
        while(!tree.finalRecorregut()){
            try {
                Jugador c=(Jugador)tree.segRecorregut();
                System.out.print(c);
            } catch (ArbreException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }
        else{
            clonat.iniRecorregut(sentit);
            while(!clonat.finalRecorregut()){
                try {
                    Jugador c=(Jugador)clonat.segRecorregut();
                    System.out.print(c);
                } catch (ArbreException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n");
        }

    }


    private static void EliminarJugador(AcbEnll tree) throws ArbreException {
        Scanner scanner = new Scanner(System.in);
        Posicio[] list = Posicio.values();
        int userChoice =0;
        int userChoice2;

        while(true) {
            for (int i = 1; i <= list.length; i++)
                System.out.println(i + "-" + list[i - 1]);
            System.out.println("Indica la seva posició [1,5] ");
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice >= 1 && userChoice <= 5) {
                break; // 如果用户输入在合法范围内，跳出循环
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 1 y 5.");
                System.out.println("Tria una opció [1,5]: ");
            }
        }

        while(true) {
            System.out.println("Indica la seva posició [0,1000] ");
            userChoice2 = Integer.parseInt(scanner.nextLine());
            if (userChoice2 >= 0 && userChoice2 <= 1000) {
                break; // 如果用户输入在合法范围内，跳出循环
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 0 y 1000.");

            }
        }

        Jugador Jordan = new Jugador(userChoice2,userChoice-1);

        if (tree.membre(Jordan)) {
            tree.esborrar(Jordan);
            System.out.println("S'ha eliminat el jugador " + Jordan.getPos() + " - " + Jordan.getPuntuacio());
        }

    }



    private static void InserirJugador(AcbEnll tree) throws ArbreException {
        Scanner scanner = new Scanner(System.in);
        Posicio[] list = Posicio.values();
        int userChoice =0;
        int userChoice2 =0;

        while(true) {
            for (int i = 0; i < list.length; i++)
                System.out.println(i+1 + "-" + list[i]);
            System.out.println("Indica la seva posició [1,5] ");
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice >= 1 && userChoice <= 5) {
                break; // 如果用户输入在合法范围内，跳出循环
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 1 y 5.");
                System.out.println("Tria una opció [1,5]: ");
            }
        }

        while(true) {
            System.out.println("Indica la seva posició [0,1000] ");
            userChoice2 = Integer.parseInt(scanner.nextLine());
            if (userChoice2 >= 0 && userChoice2 <= 1000) {
                break; // 如果用户输入在合法范围内，跳出循环
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 0 y 1000.");

            }
        }

        Jugador Jordan = new Jugador(userChoice2,userChoice-1);

        tree.inserir(Jordan);


        //先判断树是否为空，如果不是插入
        /*if(tree.abBuit())  {
            tree = new AcbEnll<Jugador>((Jugador) Jordan);
        } else{
            // 在这里插入逻辑
            try {
                tree.inserir((Jugador) Jordan);
            } catch (ArbreException e) {
                // 处理插入异常（如果有的话）
                e.printStackTrace();
            }
        }*/

    }

    private static int pregunta() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        while (true) {
            System.out.println("1.- Inserir Jugador");
            System.out.println("2.- Eliminar Jugador");
            System.out.println("3.- Visualitzar un dels dos arbres");
            System.out.println("4.- Clonar");
            System.out.println("5.- Acabar");
            System.out.print("\nTria una opció [1,5]: ");
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice >= 1 && userChoice <= 5) {
                break; // 如果用户输入在合法范围内，跳出循环
            } else {
                System.out.println("Entrada inválida. Por favor introduzca un número entre 1 y 5.");
                System.out.println("Tria una opció [1,5]: ");
            }
        }
        return userChoice;
    }
}

package Testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import Estructura.AcbEnll;
import Estructura.ArbreException;
import Jugador.Jugador;
import Estructura.Posicio;

@TestMethodOrder(MethodOrderer.MethodName.class)
class JUnit5TestPractica4 {
    private static AcbEnll<Jugador> arbre;

    @Test
    void testArbre0Null() {
        arbre = new AcbEnll<>();
        assertEquals(0,arbre.cardinalitat());
        assertThrows(ArbreException.class, () -> {
            arbre.arrel();
        });
    }

    @Test
    void testArbre1() {
        ArrayList<Jugador> unaLista = new ArrayList<Jugador> ();
        unaLista.add( new Jugador(30, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(20, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(33, Posicio.Pivot.ordinal()));
        unaLista.add( new Jugador(40, Posicio.Base.ordinal()));
        unaLista.add( new Jugador(35, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(25, Posicio.Aler.ordinal()));

        arbre = new AcbEnll<>();
        for (int i = 0; i < unaLista.size(); i++) {
            try {
                arbre.inserir(unaLista.get(i));
            } catch (ArbreException e) {
                fail(e.getMessage());
            }
            assertEquals(i+1,arbre.cardinalitat());
        }
        assertEquals(unaLista.size(), arbre.cardinalitat());
    }

    @Test
    void testArbre2QueueAscendent() throws ArbreException {
        ArrayList<Jugador> unaLista = new ArrayList<Jugador> ();
        unaLista.add( new Jugador(40, Posicio.Base.ordinal()));
        unaLista.add( new Jugador(20, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(25, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(30, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(35, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(33, Posicio.Pivot.ordinal()));

        // comprovem la queue
        assertTrue(arbre.finalRecorregut());
        arbre.iniRecorregut(true); //ascendent
        assertFalse(arbre.finalRecorregut());

        // comprovem els elements de la queue
        for (int i = 0; i < unaLista.size(); i++) {
            try {
                assertEquals(0,arbre.segRecorregut().compareTo(unaLista.get(i)));
            } catch (ArbreException e) {
                fail(e.getMessage());
            }
        }
        assertTrue(arbre.finalRecorregut());
    }

    @Test
    void testArbre3QueueDescendent() throws ArbreException {
        ArrayList<Jugador> unaLista = new ArrayList<Jugador> ();
        unaLista.add( new Jugador(40, Posicio.Base.ordinal()));
        unaLista.add( new Jugador(20, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(25, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(30, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(35, Posicio.Aler.ordinal()));
        unaLista.add( new Jugador(33, Posicio.Pivot.ordinal()));

        assertTrue(arbre.finalRecorregut());
        arbre.iniRecorregut(false); //descendent
        assertFalse(arbre.finalRecorregut());
        for (int i = unaLista.size()-1 ; i>=0; i--) {
            try {
                assertEquals(0,arbre.segRecorregut().compareTo(unaLista.get(i)));
            } catch (ArbreException e) {
                fail(e.getMessage());
            }
        }
        assertTrue(arbre.finalRecorregut());
    }

    @Test
    void testArbre4inserirDuplicat() {
        assertThrows(ArbreException.class, () -> {
            arbre.inserir(new Jugador( 20,Posicio.Aler.ordinal()));
        });
    }

    @Test
    void testArbre5eliminarInexistent() {
        assertThrows(ArbreException.class, () -> {
            arbre.esborrar(new Jugador(20,Posicio.Escorta.ordinal()));
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    void testArbre6clonat() {
        int cardinalitat = arbre.cardinalitat();
        AcbEnll<Jugador> arbreClonat =  (AcbEnll<Jugador>) arbre.clone();
        assertEquals(cardinalitat, arbreClonat.cardinalitat());
        try {
            arbre.inserir(new Jugador(10,Posicio.AlerPivot.ordinal()));
        } catch (ArbreException e) {
            fail(e.getMessage());
        }
        assertEquals(cardinalitat+1, arbre.cardinalitat());
        assertEquals(cardinalitat, arbreClonat.cardinalitat());
        try {
            arbre.esborrar(new Jugador(20,Posicio.Aler.ordinal()));
        } catch (ArbreException e) {
            fail(e.getMessage());
        }
        assertEquals(cardinalitat, arbre.cardinalitat());
        assertEquals(cardinalitat, arbreClonat.cardinalitat());
    }


}

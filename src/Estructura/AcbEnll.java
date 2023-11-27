package Estructura;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E>, Cloneable {

    private class Node implements Cloneable {
        E contingut;
        Node esq, dret;

        Node(E contingut) {
            this(contingut, null, null);
        }

        Node(E contingut, Node esq, Node dret) {
            this.contingut = contingut;
            this.esq = esq;
            this.dret = dret;
        }

        private int cardinalitat() {
            int h = 0;
            if (esq != null) {
                h += esq.cardinalitat();
            }
            if (dret != null) {
                h += dret.cardinalitat();
            }

            return 1 + h;
        }

        public Object clone() {
            Node copia;
            try {
                copia = (Node) super.clone();

                if (esq != null) {
                    copia.esq = (Node) esq.clone();
                }
                if (dret != null) {
                    copia.dret = (Node) dret.clone();
                }

            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("No s'ha pogut realitzar la clonació", e);
            }
            return copia;
        }

        private void inserir(E einf) throws ArbreException {
            if (contingut.compareTo(einf) < 0) {
                if (esq != null) {
                    esq.inserir(einf);
                } else {
                    esq = new Node(einf);
                }
            } else if (contingut.compareTo(einf) > 0) {
                if (dret != null) {
                    dret.inserir(einf);
                } else {
                    dret = new Node(einf);
                }
            } else {
                throw new ArbreException("element ja hi es");
            }

        }

        private Node esborrar(E einf) throws ArbreException {
            if (contingut.compareTo(einf) < 0) {
                if (esq != null) {
                    esq = esq.esborrar(einf);
                    return this;
                } else {
                    throw new ArbreException("No hi es el jugador");
                }
            } else if (contingut.compareTo(einf) > 0) {
                if (dret != null) {
                    dret = dret.esborrar(einf);
                    return this;
                } else {
                    throw new ArbreException("No hi es el jugador");
                }
            } else if (esq != null && dret != null) {
                contingut = dret.buscarMinim();
                dret = dret.esborrar(contingut);
                return this;
            } else if (esq == null && dret == null) {
                return null;
            } else {
                if (esq == null) {
                    return dret;
                } else {
                    return esq;
                }
            }
        }

        private E buscarMinim() {
            if (esq == null){
                return contingut;
            }
            Node node = esq;
            while (node.esq != null){
                node = node.esq;
            }
            return node.contingut;
        }

        private boolean hiEs(E einf) {
            if (contingut.compareTo(einf) < 0) {
                return esq != null && esq.hiEs(einf);
            } else if (contingut.compareTo(einf) > 0) {
                return dret != null && dret.hiEs(einf);
            } else {
                return true;
            }
        }

        public void inordre(boolean sentit, Queue<E> cua) {
            Node node1, node2;
            if (sentit) {
                node1 = dret;
                node2 = esq;
            } else {
                node1 = esq;
                node2 = dret;
            }

            if (node1 != null){
                node1.inordre(sentit, cua);
            }
            cua.add(contingut);
            if (node2 != null){
                node2.inordre(sentit, cua);
            }
        }
    }

    private Node arrel;
    private Queue<E> cua;

    public AcbEnll(Node a) {
        arrel = a;
        cua = null;
    }

    public AcbEnll() {
        this(null);
    }

    @Override
    public E arrel() throws ArbreException {
        if (abBuit()) {
            throw new ArbreException("No es pot obtenir l'arrel d'un arbre buit.");
        }

        return (E) arrel;
    }

    @Override
    public Acb<E> fillEsquerre() {
        if (arrel.esq == null) {
            return null;
        }
        return (Acb<E>) arrel.esq;
    }

    @Override
    public Acb<E> fillDret() {
        if (arrel.dret == null) {
            return null;
        }
        return (Acb<E>) arrel.dret;
    }

    @Override
    public boolean abBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        this.arrel = null;
        this.cua = null;
    }


    @Override
    public void inserir(E e) throws ArbreException {
        if (arrel == null) {
            arrel = new Node(e, null, null);
        } else {
            arrel.inserir(e);
        }

    }

    @Override
    public void esborrar(E e) throws ArbreException {
        if (cardinalitat() == 0) {
            throw new ArbreException("No es pot eliminar, l'arbre és buit");
        }
        if (arrel == null){
            throw new ArbreException("l'arbre es buit");
        }
        arrel = arrel.esborrar(e);

    }

    @Override
    public boolean membre(E e) throws ArbreException {
        if (arrel == null) {
            throw new ArbreException("L'arbre és buit");
        }
        // Verifica si el elemento está en el árbol, si no está, lanza una excepción

        if (!arrel.hiEs(e)) {
            throw new ArbreException("L'element no es troba a l'arbre");
        }
        return true;
    }

    public void iniRecorregut(boolean sentit) throws ArbreException {

        if (cardinalitat() == 0) {
            throw new ArbreException("No es pot visualitzar, l'arbre és buit");
        }

        if (arrel != null) {
            cua = new LinkedList<E>();
            arrel.inordre(sentit, cua);
        }
    }

    public boolean finalRecorregut() {
        return arrel == null || cua.isEmpty();
    }

    public E segRecorregut() throws ArbreException {

        if (cua == null) {
            throw new ArbreException("Mètode iniRecorregut no cridat abans d'invocar aquest mètode");
        }

        if (finalRecorregut()) {
            throw new ArbreException("Aquest mètode ja ha retornat l'últim element del recorregut");
        }

        if (cua.isEmpty()) {
            throw new ArbreException("S'ha produït una modificació de l'arbre entre iniRecorregut i segRecorregut");
        }

        return cua.remove();
    }

    public Object clonar() throws ArbreException {
        if (cardinalitat() == 0) {
            throw new ArbreException("No es pot clonar, l'arbre és buit");
        }
        return clone();
    }

    public Object clone() {
        AcbEnll<E> c;
        try {
            c = (AcbEnll<E>) super.clone();
            if (arrel == null) {
                c.arrel = null;
            } else {
                c.arrel = (Node) arrel.clone();
            }
            if (cua == null) {
                c.cua = null;
            } else {
                c.cua = (Queue<E>) ((LinkedList<E>) cua).clone();
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No s'ha pogut realitzar la clonació", e);
        }
        return c;
    }

    public int cardinalitat() {
        if (abBuit()) {
            return 0;
        } else {
            return arrel.cardinalitat();
        }
    }


}

package Estructura;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E> {

    private class Node implements Cloneable{
        public E getContingut() {
            return contingut;
        }

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

        private int cardinalitat(){
            int h=0;
            if(esq!=null) h+=esq.cardinalitat();
            if(dret!=null) h+=dret.cardinalitat();
            return 1+h;
        }
        public Object clone(){
            //拷贝的方法
            Node copia=null;
            try{
                copia=(Node) super.clone();
                if(esq!=null) copia.esq=(Node) esq.clone();
                if(dret!=null) copia.dret=(Node) dret.clone();
            }catch(CloneNotSupportedException e){e.printStackTrace();}
            return copia;
        }
        private Node inserir(E einf) throws ArbreException{
            if(contingut.compareTo(einf)<0){
                if(esq!=null) esq.inserir(einf);
                else esq=new Node(einf);
            } else if(contingut.compareTo(einf)>0){
                if(dret!=null) dret.inserir(einf);
                else dret=new Node(einf);
            } else throw new ArbreException("element ja hi es");
            return null;
        }

        private Node esborrar(E einf) throws ArbreException{
            if(contingut.compareTo(einf)<0){
                if(esq!=null){
                    esq=esq.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(contingut.compareTo(einf)>0){
                if(dret!=null){
                    dret=dret.esborrar(einf); return this;
                } else throw new ArbreException("no hi es");
            }else if(esq!=null&&dret!=null){
                contingut=dret.buscarMinim();
                dret=dret.esborrar(contingut);
                return this;
            }else
                return(
                        (esq==null&&dret==null)?
                                null:
                                ((esq==null)?dret:esq)
                );
        }
        private E buscarMinim(){
            if(esq==null) return contingut;
            Node a=esq; while(a.esq!=null) a=a.esq;
            return a.contingut;
        }


        private boolean hiEs(E einf){
            if(contingut.compareTo(einf)<0) return (esq==null)?false:esq.hiEs(einf);
            else if (contingut.compareTo(einf)>0) return (dret==null)?false:dret.hiEs(einf);
            else return true;
        }
    }

    public Node getArrel() {
        return arrel;
    }

    private Node arrel;
        private Queue<E> cua;


        public  AcbEnll(E contingut ){
            arrel = new Node(contingut);
            cua=new LinkedList<>();
        }
        public AcbEnll(){this(null);}




    @Override public E arrel() throws ArbreException {
        // 如果请求空树的根，则抛出异常
        if(abBuit()){
            throw new ArbreException("No es pot obtenir l'arrel d'un arbre buit.");
        }

        return (E) arrel;
    }

    @Override
    public Acb<E> fillEsquerre() {
        // 如果没有左子树，则返回一棵空树,如果有返回左子树
        if(arrel.esq==null){
            return null;
        }
        return (Acb<E>) arrel.esq;
    }

    @Override
    public Acb<E> fillDret() {
        // 如果没有左子树，则返回一棵空树,如果有返回左子树
        if(arrel.dret==null){
            return null;
        }
        return (Acb<E>) arrel.dret;
    }

    @Override
    public boolean abBuit(){return arrel==null;}

    @Override
    public void buidar() {
        // 清空树
        this.arrel=null;
        this.cua=null;
    }


    @Override
    public void inserir(E e) throws ArbreException{
        if(arrel==null) arrel=new Node(e, null, null);
        else arrel.inserir(e);
        cua=null;
    }




    @Override
    public void esborrar(E e) throws ArbreException{
        if(arrel==null) throw new ArbreException("l'arbre es buit");
        arrel=arrel.esborrar(e);
        cua=null;
    }


    @Override
    public boolean membre(E e) {
        // 如果元素存在于树中，则返回true
        return (arrel==null)?false:arrel.hiEs(e);
    }

    public void iniRecorregut(boolean sentit) {
        // 该方法进行准备工作，需要用树的元素填充属性队列，顺序取决于输入参数sentit：
        //遍历的开始

        if(sentit){
            //- true：按照在理论课上学过的中序遍历进行排序，得到升序排列。
            RecorrerYAgregarRlementos(cua,arrel);
        }else {
            //- false：按照中序遍历，交换左右子树的处理方式，得到降序排列。
            RecorrerYAgregarRlementosDescendente(cua,arrel);
        }
        }

    private void RecorrerYAgregarRlementosDescendente(Queue<E> cua, Node arrel) {
        if(arrel.contingut!=null){
            RecorrerYAgregarRlementos(cua,arrel.dret);
            cua.add(arrel.contingut);
            RecorrerYAgregarRlementos(cua,arrel.esq);
        }
        }

    private void RecorrerYAgregarRlementos(Queue<E> cua, Node arrel) {
            //中序遍历
            if(arrel.contingut!=null){
                RecorrerYAgregarRlementos(cua,arrel.esq);
                cua.add(arrel.contingut);
                RecorrerYAgregarRlementos(cua,arrel.dret);
            }

    }

    public boolean finalRecorregut(){
            /* 如果已经到达树的中序遍历的末尾，则返回true。条件包括：
   - 树为空
   - 上一次调用segRecorregut方法时，该方法已经返回了树的中序遍历的最后一个元素。
   这等同于在没有调用segRecorregut方法的情况下返回true。
*/
         if(arrel==null||cua.isEmpty()){
             return true;
         }return false;
    }


    public E segRecorregut() throws ArbreException{
/* 如果有的话，返回中序遍历中的下一个元素。
   如果发生以下情况，则引发异常：
   - 在调用它之前没有调用iniRecorregut方法
   - 上一次调用已经返回了遍历的最后一个元素（finalRecorregut将返回true）
   - 在调用iniRecorregut和该方法之间对树进行了修改，即使用了插入、删除、清空等方法。
*/
        if(cua.isEmpty()){
            throw new ArbreException("Método iniRecorregut no llamado");
        }
        return cua.poll();
    }

    //可删
    public void printTree() {
        printTree(arrel, 0);
    }

    private void printTree(Node arrel, int nivel) {
        if (arrel != null) {
            printTree(arrel.dret, nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.print("    "); // 打印缩进，用于显示层次结构
            }
            System.out.println(arrel.contingut); // 打印节点值

            printTree(arrel.esq, nivel + 1);
        }
    }
    public Object clone(){
        AcbEnll<E> c=null;
        try{
            c=(AcbEnll<E>) super.clone();
            c.arrel=(arrel==null)? null : (Node)arrel.clone();
            c.cua=(cua==null)? null : (Queue<E>) ((LinkedList<E>) cua).clone();
        }catch(CloneNotSupportedException e){e.printStackTrace();}
        return c;
    }
    //计算并返回基数，即树的节点数
    public int cardinalitat(){return(abBuit())?0:arrel.cardinalitat();}


}

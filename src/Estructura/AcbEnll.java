package Estructura;

import Jugador.Jugador;

import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E> {

    private class Node {
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

    }
        private Node arrel;
        private Queue<E> cua;



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
    public boolean abBuit() {
        // 告诉我们树是否为空
        if(arrel==null){
        return true;
        }
        return false;
    }

    @Override
    public void buidar() {
        // 清空树
        this.arrel=null;

    }


    @Override
    public void inserir(E e) throws ArbreException {
        arrel=inserir1(arrel,e);
    }

    private Node inserir1(Node arrel, E e) throws ArbreException{
        // 如果插入的元素重复，则抛出异常
        if(arrel==null){
            return (Node) e;
        }
        if(arrel.contingut.compareTo(e)==0){
            throw new ArbreException("Duplicar elementos insertados");
        } else if (arrel.contingut.compareTo(e) > 0) {
            inserir1(arrel.esq,e);
        }else{
            inserir1(arrel.dret,e);
        }
        return arrel;
    }


    @Override
    public void esborrar(E e) throws ArbreException {
        // 如果元素不存在，则抛出异常
        arrel=esborrar1(e,arrel);
    }

    private Node esborrar1(E e, Node arrel) throws ArbreException {
        if(arrel == null){
            throw new ArbreException("Element not found"); // 如果元素不存在，则抛出异常;
        }
        if(arrel.contingut.compareTo(e)>0){
            esborrar1(e,arrel.esq);
        }else if(arrel.contingut.compareTo(e)<0){
            esborrar1(e,arrel.dret);
        }else{

        }
    }

    @Override
    public boolean membre(E e) {
        // 如果元素存在于树中，则返回true
        return false;
    }

        public void iniRecorregut(boolean sentit) {
            //遍历的开始
        if(sentit){

        }


/* 该方法进行准备工作，需要用树的元素填充属性队列，顺序取决于输入参数sentit：
   - true：按照在理论课上学过的中序遍历进行排序，得到升序排列。
   - false：按照中序遍历，交换左右子树的处理方式，得到降序排列。

*/
        }
        //public boolean finalRecorregut()
/* 如果已经到达树的中序遍历的末尾，则返回true。条件包括：
   - 树为空
   - 上一次调用segRecorregut方法时，该方法已经返回了树的中序遍历的最后一个元素。
   这等同于在没有调用segRecorregut方法的情况下返回true。
*/

        //public E segRecorregut() throws ArbreException
/* 如果有的话，返回中序遍历中的下一个元素。
   如果发生以下情况，则引发异常：
   - 在调用它之前没有调用iniRecorregut方法
   - 上一次调用已经返回了遍历的最后一个元素（finalRecorregut将返回true）
   - 在调用iniRecorregut和该方法之间对树进行了修改，即使用了插入、删除、清空等方法。
*/


}
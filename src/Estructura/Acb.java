package Estructura;

public interface Acb<E extends Comparable<E>> extends Cloneable {
    public E arrel() throws ArbreException;
    // 如果请求空树的根，则抛出异常

    public Acb<E> fillEsquerre();
    // 如果没有左子树，则返回一棵空树

    public Acb<E> fillDret();
    // 如果没有右子树，则返回一棵空树

    public boolean abBuit();
    // 告诉我们树是否为空

    public void buidar();
    // 清空树

    public void inserir(E e) throws ArbreException;
    // 如果插入的元素重复，则抛出异常

    public void esborrar(E e) throws ArbreException;
    // 如果元素不存在，则抛出异常

    public boolean membre(E e) throws ArbreException;
    // 如果元素存在于树中，则返回true
}

package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            super(elemento);
            color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override public String toString() {
            if(color == Color.NEGRO)
                return String.format("N{%s}", elemento);
            return String.format("R{%s}", elemento);
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
                VerticeRojinegro vertice = (VerticeRojinegro)objeto;
            return (vertice.color == color) && super.equals(vertice);
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() { super(); }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        return ((VerticeRojinegro) vertice).color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
        VerticeRojinegro v = (VerticeRojinegro)ultimoAgregado;
        v.color = Color.ROJO;
        balanceaAgregar(v);

    }

    private void balanceaAgregar(VerticeRojinegro v){
        if(v.padre == null){
            v.color = Color.NEGRO;
            return;
        }
        VerticeRojinegro p = (VerticeRojinegro)v.padre;
        if (p.color == Color.NEGRO)
            return;
        VerticeRojinegro a = (VerticeRojinegro) p.padre;
        VerticeRojinegro t;

        if (esIzq(p))
            t = (VerticeRojinegro) a.derecho;
        else
            t = (VerticeRojinegro) a.izquierdo;

        if (t != null && t.color == Color.ROJO) {
            t.color = Color.NEGRO;
            p.color = Color.NEGRO;
            a.color = Color.ROJO;
            balanceaAgregar(a);
            return;
        }
        if ((p.padre.izquierdo == p) != (v.padre.izquierdo == v)) {
            if (esIzq(p))
                super.giraIzquierda(p);
            else
                super.giraDerecha(p);

            VerticeRojinegro aux = p;
            p = v;
            v = aux;
        }
        p.color = Color.NEGRO;
        a.color = Color.ROJO;
        if(esIzq(v))
            super.giraDerecha(a);
        else
            super.giraIzquierda(a);
    }


    private boolean esIzq(Vertice v){
        if(v.padre == null || v.padre.derecho == v)
            return false;
        return true;
    }


    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        VerticeRojinegro v = (VerticeRojinegro)busca(elemento);
        if(v == null)
            return;
        elementos--;
        if(v.izquierdo != null && v.derecho != null)
            v = (VerticeRojinegro)intercambiaEliminable(v);
        VerticeRojinegro h = (VerticeRojinegro)hijo(v);
        if(h == null){
            h = (VerticeRojinegro)nuevoVertice(null);
            v.izquierdo = h;
            h.padre = v;
            h.color = Color.NEGRO;
        }

        eliminaVertice(v);
        if(h.color == Color.ROJO){
            h.color = Color.NEGRO;
            return;
        }
        if(h.color == Color.NEGRO && v.color == Color.NEGRO)
            balanceaEliminar(h);
        if(h.elemento == null)
            if(h.padre == null)
                raiz = null;
            else if(esIzq(h))
                h.padre.izquierdo = null;
            else
                h.padre.derecho = null;

    }

    private Vertice hijo(Vertice v){
        return v.izquierdo != null? v.izquierdo: v.derecho;
    }

    private void balanceaEliminar(VerticeRojinegro v){
        if(v.padre == null)
            return;
        VerticeRojinegro p = (VerticeRojinegro)v.padre;
        VerticeRojinegro h = (VerticeRojinegro)hermano(v);

        if(h.color == Color.ROJO){
            p.color = Color.ROJO;
            h.color = Color.NEGRO;
            if(esIzq(v))
                super.giraIzquierda(p);
            else
                super.giraDerecha(p);
        }
        h = (VerticeRojinegro)hermano(v);
        VerticeRojinegro hi = (VerticeRojinegro)h.izquierdo;
        VerticeRojinegro hd = (VerticeRojinegro)h.derecho;
        if (sonNegros(hi, hd) && h.color == Color.NEGRO && p.color == Color.NEGRO) {
            h.color = Color.ROJO;
            balanceaEliminar(p);
            return;
        }
        if (sonNegros(hi, hd) && h.color == Color.NEGRO && p.color == Color.ROJO) {
            h.color = Color.ROJO;
            p.color = Color.NEGRO;
            return;
        }

        if ((esIzq(v) && !negro(hi) && negro(hd))
                || (!esIzq(v) && negro(hi) && !negro(hd))) {
            h.color = Color.ROJO;
            if (!negro(hi))
                hi.color = Color.NEGRO;
            if (!negro(hd))
                hd.color = Color.NEGRO;
            if(esIzq(v))
                super.giraDerecha(h);
            else
                super.giraIzquierda(h);
        }

        h = (VerticeRojinegro)hermano(v);
        h.color = p.color;
        p.color = Color.NEGRO;
        hi = (VerticeRojinegro)h.izquierdo;
        hd = (VerticeRojinegro)h.derecho;
        if(esIzq(v)){
            if(hd != null)
                hd.color = Color.NEGRO;
            super.giraIzquierda(p);
        }else{
            if(hi != null)
                hi.color = Color.NEGRO;
            super.giraDerecha(p);
        }

    }


    private boolean negro(VerticeRojinegro v) {
        return (v == null || v.color == Color.NEGRO);
    }

    private boolean sonNegros(VerticeRojinegro hi, VerticeRojinegro hd){
        if(hi == null && hd == null)
            return true;
        if(hi != null && hd != null)
            return hi.color == Color.NEGRO && hd.color == Color.NEGRO;
        if(hi == null)
            return hd.color == Color.NEGRO;
        if(hd == null)
            return hi.color == Color.NEGRO;
        return false;

    }

    private Vertice hermano(Vertice v){
        return esIzq(v)? v.padre.derecho: v.padre.izquierdo;
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la izquierda " +
                                                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la derecha " +
                                                "por el usuario.");
    }
}

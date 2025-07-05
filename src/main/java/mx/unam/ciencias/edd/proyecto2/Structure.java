package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Pila;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.proyecto2.drawables.Drawable;
import mx.unam.ciencias.edd.proyecto2.drawables.ListaDrawable;
import mx.unam.ciencias.edd.proyecto2.drawables.MeteSacaDrawable;
import mx.unam.ciencias.edd.proyecto2.drawables.ABDrawable;
import mx.unam.ciencias.edd.proyecto2.drawables.ArreglosDrawable;
import mx.unam.ciencias.edd.proyecto2.drawables.GraficaDrawable;

public class Structure {

    private Lista<Integer> elements;
    private Clase clas;

    public Structure(Clase clas, Lista<Integer> elements) {
        this.elements = elements;
        this.clas = clas;
    }

    public Lista<Integer> getElements() {
        return elements;
    }

    public Clase getClase() {
        return clas;
    }

    public Drawable getColeccion() throws IllegalArgumentException{
        int e = elements.getElementos();
        switch (clas) {
            case LISTA:
                Lista<Integer> l = new Lista<>();
                for (Integer i : elements)
                    l.agrega(i);
                return new ListaDrawable(l);
            case PILA:
                Pila<Integer> p = new Pila<>();
                for (Integer i : elements)
                    p.mete(i);
                return new MeteSacaDrawable(p, e);
            case COLA:
                Cola<Integer> c = new Cola<>();
                for (Integer i : elements)
                    c.mete(i);
                return new MeteSacaDrawable(c, e);
            case ABCOMPLETO:
                ArbolBinarioCompleto<Integer> abc = new ArbolBinarioCompleto<>(elements);
                return new ABDrawable(abc);
            case ABORDENADO:
                ArbolBinarioOrdenado<Integer> abo = new ArbolBinarioOrdenado<>(elements);
                return new ABDrawable(abo);
            case ABROJINEGRO:
                ArbolRojinegro<Integer> abrn = new ArbolRojinegro<>(elements);
                return new ABDrawable(abrn);
            case ABAVL:
                ArbolAVL<Integer> aavl = new ArbolAVL<>(elements);
                return new ABDrawable(aavl);
            case ARREGLO:
                Integer array [] = new Integer[e];
                int x = 0;
                for(Integer i: elements){
                    array[x] = i;
                    x++;
                }
                return new ArreglosDrawable(array, e);
            case GRAFICA:
                if(elements.getLongitud()%2 != 0)
                    throw new IllegalArgumentException("Grafica invalida");
                Grafica<Integer> g = new Grafica<>();
                Lista<Integer> vertices = new Lista<>();
                for(Integer i: elements){
                    if(vertices.contiene(i))
                        continue;
                    g.agrega(i);
                    vertices.agrega(i);
                }
                for (int i = 0; i < elements.getLongitud()-1; i+=2){
                    Integer first = elements.get(i);
                    Integer second = elements.get(i+1);
                    if(first.equals(second))
                        continue;
                    g.conecta(first, second);
                }
                return new GraficaDrawable(g);
            default:
                return null;
        }
    }
}

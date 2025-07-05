package mx.unam.ciencias.edd.proyecto2.drawables;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.VerticeArbolBinario;

public class ABDrawable implements Drawable {

    private ArbolBinario<Integer> structure;
    private String header = "<svg ";
    private String end = "</svg>\n";
    private int altura;

    public ABDrawable(ArbolBinario<Integer> structure) {
        this.structure = structure;
        this.altura = structure.altura();
    }

    public String getDrawableString() {
        String result = "<g>\n";
        if (structure.esVacia())
            return header + ">" + result + end;
        int elems = (int) Math.pow(2, altura + 1) - 1;
        int anch2 = elems * 51 + 20;
        int hei = altura * 166 + 50;
        header += String.format("width=\'%d\' height=\'%d\'>\n", anch2, hei);
        int x = anch2 / 2;
        int y = 40;
        result += subtreelines(structure.raiz(), x, y, altura);
        result += subtree2(structure.raiz(), x, y, altura);
        result += "</g>\n";
        return header + result + end;
    }

    private String subtree2(VerticeArbolBinario<Integer> v, int x, int y, int i) {
        int hojas = (int) Math.pow(2, i);
        int p = v.profundidad();
        int h = y + 160 * p;
        Integer e = Integer.valueOf(v.get());
        String color = getColor(v);
        String txtcolor = "black";
        String s = "";
        if (color != "white") {
            txtcolor = "white";
        }
        if (v.hayIzquierdo()) {
            int desizq = x - hojas * 25;
            s += subtree2(v.izquierdo(), desizq, y, i - 1);
        }
        String data = "";
        String ab = "";
        if (structure instanceof ArbolAVL) {
            data = getBalance(v);
            int xb = 0;
            if (isIzq(v))
                xb = x - 15;
            if (!isIzq(v))
                xb = x + 15;
            if (p == 0)
                xb = x;
            ab = drawdata(xb, h - 23, data);
        }
        String r = Basic.circle(e, 20, x, h, color, txtcolor, x, h + 5);
        if (v.hayDerecho()) {
            int desder = x + 25 * hojas;
            s += subtree2(v.derecho(), desder, y, i - 1);
        }
        r += ab;
        return r += s;
    }

    private String subtreelines(VerticeArbolBinario<Integer> v, int x, int y, int i) {
        int hojas = (int) Math.pow(2, i);
        int h = y + 160 * v.profundidad();
        int h1 = y + 160 * (v.profundidad() + 1);
        String color = getColor(v);
        String linec = "black";
        String s = "";
        if (color != "white") {
            linec = "blue";
        }
        if (v.hayIzquierdo()) {
            int desizq = x - 25 * hojas;
            s += subtreelines(v.izquierdo(), desizq, y, i - 1);
            s += Basic.lineBB(x, h, desizq, h1, linec);
        }
        if (v.hayDerecho()) {
            int desder = x + 25 * hojas;
            s += subtreelines(v.derecho(), desder, y, i - 1);
            s += Basic.lineBB(x, h, desder, h1, linec);
        }
        return s;
    }

    private boolean isIzq(VerticeArbolBinario<Integer> v) {
        if (v.hayPadre())
            if (v.padre().hayIzquierdo())
                return v.padre().izquierdo() == v;
        return false;
    }

    private int hojas(VerticeArbolBinario<Integer> v) {
        /*
         * if(!v.hayIzquierdo() && v.hayDerecho())
         * return hojas(v.derecho());
         * if(v.hayIzquierdo() && !v.hayDerecho())
         * return hojas(v.izquierdo());
         *
         * if(!v.hayIzquierdo() && !v.hayDerecho())
         * return 1;
         */
        return (int) Math.pow(2, v.altura());
    }

    private int anch(VerticeArbolBinario<Integer> v) {
        int k = 0;
        Cola<VerticeArbolBinario<Integer>> c = new Cola<>();
        c.mete(v);
        while (!c.esVacia()) {
            VerticeArbolBinario<Integer> w = c.saca();
            k++;
            if (w.hayIzquierdo())
                c.mete(w.izquierdo());
            if (w.hayDerecho())
                c.mete(w.derecho());
        }
        return k;
    }

    private int anchIzq(VerticeArbolBinario<Integer> v) {
        if (!v.hayIzquierdo() && !v.hayDerecho())
            return 1;
        int r = 0;
        if (v.hayIzquierdo())
            r += anchIzq(v.izquierdo());
        return r + 1;
    }

    private int anchDer(VerticeArbolBinario<Integer> v) {
        if (v.altura() == 0)
            return 1;
        int r = 0;
        if (v.hayDerecho())
            r += anchDer(v.derecho());
        return r + 1;
    }

    private int anch2(VerticeArbolBinario<Integer> v) {
        Cola<VerticeArbolBinario<Integer>> c = new Cola<>();
        int k = 0;
        c.mete(v);
        int h = v.altura();
        while (!c.esVacia()) {
            VerticeArbolBinario<Integer> w = c.saca();
            if (!w.hayIzquierdo() || !w.hayDerecho())
                k++;
            if (w.hayIzquierdo())
                c.mete(w.izquierdo());
            if (w.hayDerecho())
                c.mete(w.derecho());
        }
        return k;
    }

    private String getBalance(VerticeArbolBinario<Integer> v) {
        if (!(structure instanceof ArbolAVL))
            return "";
        String data = v.toString().split(" ")[1];
        String altura = data.split("/")[0];
        String balance = data.split("/")[1];
        String r = String.format("{%s/%s}", altura, balance);
        return r;
    }

    private String drawdata(int x, int y, String d) {
        String text = String.format("<text x=\'%d\' y=\'%d\' ", x, y);
        String font = "font-family=\'sans-serif\' ";
        String textend = String.format("font-size=\'%d\' fill=\'black\' ", 20);
        String textanch = "text-anchor='middle'>";
        String elementS = String.format("%s</text>\n", d);
        return text + font + textend + textanch + elementS;
    }

    private String getColor(VerticeArbolBinario<Integer> v) {
        if (!(structure instanceof ArbolRojinegro))
            return "white";
        return v.toString().contains("R") ? "red" : "black";
    }
}

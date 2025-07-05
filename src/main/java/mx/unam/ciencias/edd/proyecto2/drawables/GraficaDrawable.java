package mx.unam.ciencias.edd.proyecto2.drawables;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeGrafica;

public class GraficaDrawable implements Drawable {
    private Grafica<Integer> g;
    private String header = "<svg ";
    private String end = "</svg>\n";

    public GraficaDrawable(Grafica<Integer> g) {
        this.g = g;
    }

    public String getDrawableString() {
        double dim = 40*g.getElementos() + 102;
        String dims = String.format("height = \'%f\' width = \'%f\' >\n", dim, dim);
        header += dims;
        return header + "<g>\n" + drawEdges() + dibujaVertices() + "</g>\n" + end;
    }

    private String drawEdges() {
        String[] cords = getCoords();
        Lista<Integer> vertices = getVertices();
        Lista<VerticeGrafica<Integer>> visitados = new Lista<>();
        if (g.esVacia())
            return header + end;
        String result = "";
        int i = 0;
        for (Integer e : g) {
            VerticeGrafica<Integer> v = g.vertice(e);
            if (visitados.contiene(v)) {
                i++;
                continue;
            }
            String xy1[] = cords[i].split(",");
            double x1 = Double.valueOf(xy1[0]);
            double y1 = Double.valueOf(xy1[1]);
            visitados.agrega(v);
            for (VerticeGrafica<Integer> vecino : v.vecinos()) {
                if (visitados.contiene(vecino))
                    continue;
                int index = vertices.indiceDe(vecino.get());
                String xy2[] = cords[index].split(",");
                double x2 = Double.valueOf(xy2[0]);
                double y2 = Double.valueOf(xy2[1]);
                result += Basic.lineBB(x1, y1, x2, y2, "black");
            }
            i++;
        }
        return result;
    }

    private String dibujaVertices(){
        String [] cords = getCoords();
        String result = "";
        int i = 0;
        for(Integer e:g){
            String xy1 [] = cords[i++].split(",");
            double x1 = Double.valueOf(xy1[0]);
            double y1 = Double.valueOf(xy1[1]);
            result+=Basic.circle(e, 20, x1, y1, "white", "black", x1, y1+5);
        }
        return result;
    }

    private String [] getCoords(){
        int n = g.getElementos();
        String [] coords = new String [n];
        int r = 20*n;
        String s = "";
        for (int k = 0; k < n; k++) {
            double x = r * Math.cos(2 * Math.PI / n * k);
            double y = r * Math.sin(2 * Math.PI / n * k);
            coords[k] = String.format("%f, %f", x+r+25, y+r+25);
            //System.out.println("(" + x + ", " + y + ")");
        }
        return coords;
    }

    private Lista<Integer> getVertices(){
        Lista<Integer> l = new Lista<>();
        for(Integer e:g)
            l.agrega(e);
        return l;
    }
}

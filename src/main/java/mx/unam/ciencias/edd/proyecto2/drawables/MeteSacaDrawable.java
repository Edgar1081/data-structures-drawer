package mx.unam.ciencias.edd.proyecto2.drawables;

import mx.unam.ciencias.edd.MeteSaca;
import mx.unam.ciencias.edd.Pila;
import mx.unam.ciencias.edd.Cola;

public class MeteSacaDrawable implements Drawable{
    private String header = "<svg ";
    private String end = "</svg>\n";
    private int scale = 1;
    private int elementos;
    private MeteSaca<Integer> structure;

    public MeteSacaDrawable(MeteSaca<Integer> structure, int elementos){
        this.structure = structure;
        this.elementos = elementos;
        if(elementos >= 10)
            scale = 2;
        if(elementos >= 20)
            scale = 3;
        if(elementos >= 30)
            scale = 4;
    }

    public String getDrawableString(){
        return structure instanceof Pila? drawPila(): drawCola();
    }

    private String drawPila(){
        int heightdigit = (int)(30/scale);
        String result = "";
        int blockheight = 12+ heightdigit;
        int blockwidth = (int)10*22/scale +40;
        int total = elementos*blockheight;
        int width = blockwidth;
        int y = 0;
        int x= 0;
        int elen = 0;
        for(int i = 0; i < elementos; i++){
            Integer e = structure.saca();
            elen = 22/scale*((int)Math.log10(e) + 1);
            int midw = (blockwidth-elen)/2;
            int midh = (blockheight-heightdigit)/2+heightdigit;
            result += Basic.getRect(e, 0, y, blockheight,
                    blockwidth, x+midw, y+midh, 40/scale);
            y+=blockheight;
        }
        this.header += String.format("width=\"%d\" height=\"%d\">\n", width, total);
        return header+result+end;
    }

    private String drawCola(){
        int heightdigit = (int)(30/scale);
        int y = 0;
        int x = 0;
        String result = "";
        int blockwidth = 0;
        int blockheight = heightdigit+12;
        int width = 0;
        int mid = (int)(12+(int)(30/scale))/2;
        int elen = 0;
        for(int i = 0; i < elementos; i++){
            Integer e = structure.saca();
            elen = (22/scale)*((int)Math.log10(e) + 1);
            blockwidth = elen + 40;
            int midw = (blockwidth-elen)/2;
            int midh = (blockheight-heightdigit)/2+heightdigit;
            result += Basic.getRect(e, x, y, blockheight,
                    blockwidth, x+midw, y+midh, 40/scale);
            x += blockwidth;
            result += Basic.rightArrow(x, mid);
            x+= 50;
        }
        width += x+50;
        this.header += String.format("width=\"%d\" height=\"%d\">\n", width, 50);
        return header + result + end;
    }
}

package mx.unam.ciencias.edd.proyecto2.drawables;

import mx.unam.ciencias.edd.Lista;

public class ListaDrawable implements Drawable {

    private Lista<Integer> structure;
    private String header = "<svg ";
    private String end = "</svg>\n";
    private int scale = 1;
    private int elementos;

    public ListaDrawable(Lista<Integer> structure) {
        this.structure = structure;
        elementos = structure.getElementos();
        if (elementos >= 10)
            scale = 2;
        if (elementos >= 20)
            scale = 3;
        if (elementos >= 30)
            scale = 4;
    }

    public String getDrawableString() {
        int heightdigit = (int) (30 / scale);
        int y = 0;
        int x = 0;
        int index = 0;
        String result = "";
        int blockwidth = 0;
        int blockheight = 12 + heightdigit;
        int widthSVG = 0;
        int mid = (int) blockheight / 2;
        int elen = 0;
        for (Integer e : structure) {
            elen = 22 / scale * ((int) Math.log10(e) + 1);
            index++;
            blockwidth = elen + 40;
            int midw = (blockwidth - elen) / 2;
            int midh = (blockheight - heightdigit) / 2 + heightdigit;
            result += Basic.getRect(e, x, y, blockheight, blockwidth,
                    x + midw, y + midh, 40 / scale);
            x += blockwidth;
            if (index == elementos)
                break;
            result += Basic.doubleArrow(x, mid);
            x += 50;
        }
        widthSVG = x + 50;
        this.header += String.format("width=\"%d\" height=\"%d\">\n", widthSVG, 50);
        return header + result + end;
    }
}

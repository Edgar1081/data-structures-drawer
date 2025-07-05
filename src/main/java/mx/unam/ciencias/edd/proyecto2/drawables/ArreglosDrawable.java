package mx.unam.ciencias.edd.proyecto2.drawables;

public class ArreglosDrawable implements Drawable {

    private Integer[] structure;
    private String header = "<svg ";
    private String end = "</svg>\n";
    private int scale = 1;
    private int elementos;

    public ArreglosDrawable(Integer[] structure, int elementos) {
        this.structure = structure;
        this.elementos = elementos;
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
        String result = "";
        int blockwidth = (int) 10 * 22 / scale + 40;
        int blockheight = 12 + (int) (30 / scale);
        int widthSvg = 0;
        for (Integer e : structure) {
            int elen = (22 / scale) * ((int) Math.log10(e) + 1);
            int midw = (blockwidth - elen) / 2;
            int midh = (blockheight - heightdigit) / 2 + heightdigit;
            result += Basic.getRect(e, x, y,
                    blockheight, blockwidth, x + midw, y + midh, 40 / scale);
            x += blockwidth;
        }
        widthSvg += x + 50;
        this.header += String.format("width=\"%d\" height=\"%d\">\n", widthSvg, 50);
        return header + result + end;
    }
}

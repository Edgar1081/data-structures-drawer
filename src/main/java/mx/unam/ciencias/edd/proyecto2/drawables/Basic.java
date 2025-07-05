package mx.unam.ciencias.edd.proyecto2.drawables;


public class Basic{

    public static String getRect(Integer element, int posx, int posy,
                                 int height, int width, int txtx, int txty, int size){
        //int len = (int) (Math.log10(element) + 1);
        //40+(int)(len*22/scale)
        //12+(int)(30/scale)
        //
        String head = "<g>\n";
        String rect = "<rect ";
        String x = String.format("x=\"%d\" ", posx);
        String y = String.format("y=\"%d\" ", posy);
        String dim = String.format("width=\"%d\" height=\"%d\" ", width, height);
        String recend = "fill=\"white\" stroke=\"black\" stroke-width=\"2\"></rect>\n";
        String text = String.format("<text x=\"%d\" y=\"%d\" ", txtx, txty);
        String font = "font-family=\"sans-serif\" ";
        String textend = String.format("font-size=\"%d\" fill=\"black\"> ", size);
        String elementS = String.format("%d</text>\n", element);
        String end = "</g>\n";
        String box = head+rect+x+y+dim+recend+text+font+textend+elementS+end;
        return box;
    }
    public static String line(int x, int y, int w){
        String linehead = "<line ";
        String xline1 = String.format("x1='%d' ", x);
        String yline1 = String.format("y1='%d' ", y);
        String x2line2 = String.format("x2='%d' ", x+w);
        String y2line2 = String.format("y2 ='%d' ", y);
        String linened = "stroke='black' stroke-width='2'/>\n";
        String line = linehead+xline1+yline1+x2line2+y2line2+linened;
        return line;
    }
    public static String leftArrow(int x, int y){
        String g = "<g>\n";
        String line = Basic.line(x+10, y, 40);
        String poly = leftTriangle(x, y);
        String gend = "</g>\n";
        return g + line + poly + gend;
    }
    public static String leftTriangle(int x, int y){
        String polyhead = "<polygon points=\"";
        String p1 = String.format("%d,%d ", x+10, y-2);
        String p2 = String.format("%d,%d ", x, y);
        String p3 = String.format("%d,%d", x+10, y+2);
        String polyend = "\" fill=\"black\"/>\n";
        String poly = polyhead+p1+p2+p3+polyend;
        return poly;
    }
    public static String rightArrow(int x, int y){
        String g = "<g>\n";
        String line = Basic.line(x, y, 40);
        String poly = rightTriangle(x+40, y);
        String gend = "</g>\n";
        return g + line + poly + gend;
    }

    public static String rightTriangle(int x, int y){
        String polyhead = "<polygon points=\" ";
        String p1 = String.format("%d,%d ", x, y-2);
        String p2 = String.format("%d,%d ", x+10, y);
        String p3 = String.format("%d,%d ", x, y+2);
        String polyend = "\" fill=\"black\"/>\n";
        String poly = polyhead+p1+p2+p3+polyend;
        return poly;
    }

    public static String doubleArrow(int x, int y) {
        String g = "<g>\n";
        String line = Basic.line(x + 10, y, 30);
        String left = Basic.leftTriangle(x, y);
        String right = Basic.rightTriangle(x + 40, y);
        String gend = "</g>\n";
        return g + left + line + right + gend;

    }

    public static String circle(Integer e, int r, double x, double y
                                , String c, String txtc, double txtx, double txty){
        String bord = "black";
        if(txtc == "white")
            bord = c;
        //String g = "<g>\n";
        String circle = "<circle ";
        String cx = String.format("cx=\'%f\' ", x);
        String cy = String.format("cy=\'%f\' ", y);
        String radious = String.format("r=\'%d\' ", r);
        String stroke = String.format("stroke=\'%s\' ", bord);
        String strokew = "stroke-width=\'3\' ";
        String fill = String.format("fill=\'%s\' />\n", c);
        String text = String.format("<text fill=\'%s\' ", txtc);
        String font = "font-family=\'sans-serif\' font-size=\'20\' ";
        String txtxy = String.format("x=\'%f\'  y=\'%f\' ", txtx, txty);
        String txtf =  String.format("text-anchor=\'middle\'>%d</text>\n", e);
        return circle+cx+cy+radious+stroke+strokew+fill+text+font+txtxy+txtf;
    }

    public static String lineBB(double x1, double y1, double x2, double y2, String c){
        String p1 = String.format("<line x1=\'%f' y1=\'%f\' ", x1, y1);
        String p2 = String.format("x2=\'%f\' y2=\'%f\' ", x2, y2);
        String stroke = String.format("stroke=\'%s\' stroke-width=\'3\' />\n", c);
        return p1+p2+stroke;
    }

}

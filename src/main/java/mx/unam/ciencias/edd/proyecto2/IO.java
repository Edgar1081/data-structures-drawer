package mx.unam.ciencias.edd.proyecto2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import mx.unam.ciencias.edd.Lista;


public class IO {

    public static Structure read(BufferedReader in) throws Exception {
        String integer = "";
        String clas = "";
        int e = -1;
        Lista<Integer> result = new Lista<>();
        while ((e = in.read()) != -1) {

            if (Filter.isBlank(e) && integer != "") {
                result.agrega(Integer.valueOf(integer));
                integer = "";
                continue;
            }
            if (Filter.validCharNumber(e) && clas == "")
                throw new IllegalArgumentException("Falta la calse");

            if (Filter.validCharClass(e) && !result.esVacia())
                throw new IllegalArgumentException("Caracter invalido");

            if (Filter.validCharClass(e)){
                clas += (char) e;
                continue;
            }
            if (Filter.validCharNumber(e)){
                integer += (char) e;
                continue;
            }
            if (Filter.isAl(e)){
                in.readLine();
                continue;
            }
            if(Filter.invalid(e))
                throw new NumberFormatException("Numero invalido");
        }
        Clase clase = Clase.getClase(clas);
        if(clase == Clase.NINGUNA)
            throw new IOException("Invalid Class");
        return new Structure(clase, result);
    }

    public static Structure fileOrStandar(String route) {
        try {
            if (route == "")
                return read(new BufferedReader(new InputStreamReader(System.in)));
            File file = new File(route);
            FileInputStream fileIn = new FileInputStream(file);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            return read(in);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}

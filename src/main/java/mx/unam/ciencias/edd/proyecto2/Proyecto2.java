package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.proyecto2.drawables.Drawable;


public class Proyecto2 {

        private static void uso() {
                System.err.println("Uso: java -jar practica5.jar N");
                System.exit(1);
        }

        public static void main(String[] args) {
                String route = "";
                if(args.length == 1)
                        route = args[0];
                Structure result = IO.fileOrStandar(route);
                Drawable d = result.getColeccion();
                System.out.print(d.getDrawableString());
        }
}

package mx.unam.ciencias.edd.proyecto2;

public enum Clase{
    NINGUNA,
    LISTA,
    PILA,
    COLA,
    ABCOMPLETO,
    ABORDENADO,
    ABROJINEGRO,
    ARREGLO,
    ABAVL,
    GRAFICA;

    public static Clase getClase(String s) {
        switch (s) {
            case "Lista":
                return LISTA;
            case "Pila":
                return PILA;
            case "Cola":
                return COLA;
            case "ArbolBinarioCompleto":
                return ABCOMPLETO;
            case "ArbolBinarioOrdenado":
                return ABORDENADO;
            case "ArbolRojinegro":
                return ABROJINEGRO;
            case "ArbolAVL":
                return ABAVL;
            case "Arreglo":
                return ARREGLO;
            case "Grafica":
                return GRAFICA;
            default:
                return NINGUNA;
        }

    }
}

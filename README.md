# Visualizador de Estructuras de Datos en SVG (Java + Docker)

Este proyecto es una aplicación escrita en Java que genera representaciones gráficas en formato **SVG** de diversas estructuras de datos clásicas. La entrada puede ser proporcionada desde la terminal o desde un archivo, y la salida se produce por `stdout`, lo que permite redireccionarla fácilmente a un archivo `.svg`.

## Estructuras de Datos Soportadas

- `Lista`
- `Pila`
- `Cola`
- `ArbolBinarioCompleto`
- `ArbolBinarioOrdenado`
- `ArbolRojinegro`
- `Arreglo`
- `ArbolAVL`
- `Grafica`

## Formato de Entrada

El programa espera dos líneas como entrada:

1. **Nombre de la estructura** (por ejemplo: `LISTA`, `PILA`, etc.)
2. **Datos comparables** separados por espacios (números, letras o cadenas)

Ejemplos válidos:

```txt
Lista
1 2 3 4 5 6
```

```txt
ArbolBinarioCompleto
1 2 3 4 5 6
```

```txt
Grafica 
1 2 3 4 5 6
```
**El caso de las gráficas requiere un número par de vértices, pues se relacionan dos a dos en el orden proporcionado**

---

## 🐳 Uso con Docker

### ✅ Construcción de la imagen

Asegúrate de tener Docker corriendo.

Desde la raíz del proyecto, ejecuta:

``` bash
docker build -t visualizador-svg .
```

Puedes usar el programa pasando la entrada por stdin, o desde un archivo montado como volumen.

- Opción 1: Entrada por stdin

``` bash
echo -e "LISTA\n1 2 3 4 5" | docker run --rm -i visualizador-svg > lista.svg
```

- Opción 2: Entrada desde archivo

Crea un archivo input.txt con:

```
ArbolRojinegro
7 3 18 10 22 8 11 26
```

Después ejecuta:
``` bash
docker run --rm -i visualizador-svg < input.txt > example.svg
```

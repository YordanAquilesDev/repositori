package Dominio.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceGenerico<T,ID> {

    int save(T beans);

    int update(T beans);

    int delete(ID id);

    Optional<T> finById(ID id);

    List<T> finAll();

    int saveAndFinId(T beans);
}

 class EjemplosRecursividad {
        public static void main(String[] args) {
            System.out.println("--- EJEMPLO 1: CUENTA REGRESIVA RECURSIVA ---");
            cuentaRegresiva(5); // Iniciamos la cuenta desde 5

            System.out.println("\n--- EJEMPLO 2: SUMA DE ARREGLO RECURSIVA ---");
            int[] miArreglo = {10, 20, 30, 40, 50};

            // Llamamos al método pasando el arreglo y empezando desde el índice 0
            int resultadoSuma = sumarArreglo(miArreglo, 0);
            System.out.println("La suma de los elementos es: " + resultadoSuma);
        }

        /**
         * Método recursivo directo para realizar una cuenta regresiva.
         * No devuelve nada (void), solo imprime en consola.
         */
        public static void cuentaRegresiva(int numero) {
            if (numero == 0) {
                System.out.println("¡Despegue!");
            }
            else {
                System.out.println(numero + "...");
                cuentaRegresiva(numero - 1);
            }
        }

        public static int sumarArreglo(int[] arreglo, int indice) {
            if (indice == arreglo.length) {
                return 0;
            } else {

                return arreglo[indice] + sumarArreglo(arreglo, indice + 1);
            }
        }
    }

class RecursividadIndirecta {

    public static void main(String[] args) {
        System.out.println("--- EJEMPLO 1: PAR O IMPAR MUTUO ---");
        int numeroEvaluar = 7;
        // Iniciamos el flujo llamando a esPar
        if (esPar(numeroEvaluar)) {
            System.out.println("El número " + numeroEvaluar + " es PAR.");
        } else {
            System.out.println("El número " + numeroEvaluar + " es IMPAR.");
        }

        System.out.println("\n--- EJEMPLO 2: SARTA DE TEXTO (A y B alternados) ---");
        System.out.print("Resultado de la secuencia: ");
        generarSecuenciaA(4); // Iniciamos con el método A pasando 4 ciclos
        System.out.println();
    }

    // =================================================================
    // EJEMPLO 1: Determinar si un número es Par o Impar sin usar '%'
    // =================================================================


    public static boolean esPar(int n) {
        if (n == 0) {
            return true;
        }
        return esImpar(n - 1);
    }
    public static boolean esImpar(int n) {
        if (n == 0) {
            return false;
        }
        return esPar(n - 1);
    }


    // =================================================================
    // EJEMPLO 2: Generar una secuencia alternada (Imprimir "A" y "B")
    // =================================================================

    /**
     * Imprime una 'A' y le pasa el turno a generarSecuenciaB.
     */

    public static void generarSecuenciaA(int turnos) {
        if (turnos == 0) {
            return;
        }
        System.out.print("A");
        generarSecuenciaB(turnos - 1);
    }

    public static void generarSecuenciaB(int turnos) {
        if (turnos == 0) {
            return;
        }
        System.out.print("B");
        generarSecuenciaA(turnos);
    }
}

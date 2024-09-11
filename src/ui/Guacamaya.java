package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    public static double[] preciosparciales;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    calcularTotalUnidadesVendidas();
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    calcularPrecioPromedio();
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    calcularVentasTotales();
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    consultarReferenciasSobreLimite(limite);
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];
        preciosparciales = new double[referencias]; //agregado

    }

     /**
     * Descripcion: Este metodo se encarga de solicitar al cliente el precio de los prodcutos y la cantidad de ellos, para ser almacenados y utilizados más adelante
     * pre: El arreglo debe estar inicializado
     * pos:el arreglo queda modificado con los valores ingresados
     */

    public static void solicitarDatos(){

        for (int i=0; i<precios.length;i++){
            System.out.println("\ndigite el precio de la referencia "+ (i+1));
            double parcialp = reader.nextDouble();
            precios[i] = parcialp;

            System.out.println("\ndigite la cantidad de unidades vendidas "+ (i+1));
            int parcialU = reader.nextInt();
            unidades[i] = parcialU;

        }



     
    }

    /**
     * Descripcion: Este metodo calcula el total de unidades vendidas sumando el arreglo que los contiene hasta llegar al tamaño del arreglo y devuelve una variable donde se va sumando
     * pre:El arreglo debe estar inicializado para hacer operaciones con este
     */

    public static int calcularTotalUnidadesVendidas(){

        int suma = 0;
        for (int totalunidades : unidades) { // se usa para que calificacion vaya tomando el valor de los elementos del arreglo y los vaya somando a la variable suma (Se inesvtigó en internet)
             suma += totalunidades;

        }

        
        return suma;

    }

     /**
     * Descripcion: Este metodo se encarga de calcular el promedio multiplicando los arreglos, almacenandolos en una variable y sumando la misma,  y dividiendolos entre ellos 
     * pre:El arreglo debe estar inicializado igual que en el caso anterior para hacer una operación
     */

    public static double calcularPrecioPromedio(){

        double sumaprecio = 0;
        for (double totalprecio : precios) { // se usa para que calificacion vaya tomando el valor de los elementos del arreglo y los vaya somando a la variable suma (Se inesvtigó en internet)
             sumaprecio += totalprecio;

        }

        double promedioprecio = sumaprecio/precios.length;


        return promedioprecio;

    }

    public static double calcularVentasTotales(){

        double totalventasparcial = 0;

        for (int i=0; i<precios.length;i++){
            double p = precios[i];
            int u = unidades[i];
            double parcial = p*u;
            preciosparciales[i] = parcial; //agregado
            totalventasparcial += parcial; 

        }


        return totalventasparcial;

    }

    public static int consultarReferenciasSobreLimite(double limite){

        calcularVentasTotales();

        int contador = 0;

        for (int i=0; i<preciosparciales.length;i++){
            if (preciosparciales[i]>limite){

               contador ++;

            }

        }



        return contador;

    }

}

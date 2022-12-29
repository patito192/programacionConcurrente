/*
 * Rubén Ramírez Cervantes
 * 2203007463
 * bicinrubenbr@gmail.com
 *
 * Compilar: javac practicaSumaArregloFinal.java
 * Ejecutar: java practicaSumaArregloFinal
 */

//package sumaMatriz;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


//se declaran varibles globales
public class practicaSumaMatricesHilosFinal extends Thread{

    //Se crean las matrices e hilos
    static int HILOS;
    static int matrizA[][];
    static int matrizB[][];
    static int matrizC[][];
    static int n;


    static String str[] = null, str2[] = null;
    //Viejas
    static Scanner in = new Scanner(System.in);
    static int mv = 0, thread=1;

    int id = -1;

    static int[] solution = null;
    //Se recibe el tamaño del arreglo

    // Constructor
    practicaSumaMatricesHilosFinal(int id){
        this.id = id;
    }


    //Caratula
    public static void muestraCaratula(){
        System.out.println("\t**************************************");
        System.out.println("\t*      PROGRAMACION CONCURRENTE      *");
        System.out.println("\t*   ALUMNO: Rubén Ramírez Cervantes  *");
        System.out.println("\t*        MATRICULA: 2203007463       *");
        System.out.println("\t*          SUMA DE ARREGLOS          *");
        System.out.println("\t**************************************\n");
    }


    public static void main(String[] args) {

        /*
        https://www.cs.vu.nl/~ast/books/mos2/sample-10.pdf
        https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_process_creation_termination.htm
        https://www.geeksforgeeks.org/fork-system-call/matrizA = Iniciar(matrizA);
         */
        //Se ingresa el tamaño del arreglo
        //System.out.println("ingresa el tamaño del arreglo");
        //n = in.nextInt();


        muestraCaratula();


        //tamaño de la matriz
        Scanner in = new Scanner(System.in);
        System.out.println("Ingresa el tamaño de la matriz deseada:");
        n = in.nextInt();
        System.out.println();

        //Número de hilos
        System.out.println("Ingresa el número de hilos deseados:");
        HILOS = in.nextInt();
        System.out.println();

        //Se crea la lista de hilos con los datos dados anteriormente
        Thread[] listHilos = new Thread[HILOS];

        //Se llenan las matrices
        matrizA = new int[n][n];
        matrizB = new int[n][n];
        matrizC = new int[n][n];

        fullyMatrices(matrizA, n);
        fullyMatrices(matrizB, n);

        //Se imprimen las matrices
        System.out.println("Matriz A:");
        printing(matrizA, n);
        System.out.println();

        System.out.println("Matriz B:");
        printing(matrizB, n);
        System.out.println();


        //Se crea cada hilo y después se lanza
        for(int i = 0; i < HILOS; i++){
            // Se crea el hilo i
            listHilos[i] = new practicaSumaMatricesHilosFinal(i);
            // Se lanzan el hilo i (lo inicializa)
            listHilos[i].start();
        }

        // Se esperan a los hilos (como el wait)
        System.out.println("Tus hilos hicieron...");
        try{
            for(int i = 0; i < HILOS; i++){
                listHilos[i].join();
            }
        }catch(InterruptedException e){
            System.out.println("Fallo un hilo");
        }
        System.out.println();

        //Matriz C:
        System.out.println("La suma final de las matrices A y B es:");
        printing(matrizC, n);


    }// Fin main

    //Se realiza la suma de la matriz

    public void run(){
        // Calcular el rango de bloques
        int bloque = n / HILOS;
        int inicio = id * bloque;//  id; // bloque () id
        int fin = inicio + bloque;

        int i;

        System.out.println("H"+ id + ": Inicio: " + inicio + " Fin: " + fin);


        for (i = inicio; i < fin; i++) {
            for (int j = 0; j < n; j++) {
                matrizC[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }

        //Se identifica el útlomo hilo usado
        if (id == HILOS-1){
            for (i = i; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizC[i][j] = matrizA[i][j] + matrizB[i][j];
                }
            }
        }
    }


        //Se suman las matrices
    public static void plus ( int[][] A, int[][] B, int[][] C, int tam){
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }


    //Se imprime la matriz
    public  static void printing ( int[][] matriz, int tam){
        //imprime la matriz
        for (int i = 0; i < tam; i++) {
            System.out.println(Arrays.toString(matriz[i]));
        }
    }


    //metodo de llenado de la matriz
    public static void fullyMatrices ( int[][] matrizG, int tam){
        //se llena la matriz con núemros aleatorios entre 10 y 20
        Random numRan = new Random();
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                matrizG[i][j] = numRan.nextInt(10) + 11;
            }
        }
    }
} // Fin de la clase
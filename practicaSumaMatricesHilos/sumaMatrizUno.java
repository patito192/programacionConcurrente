//package sumaMatriz;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//se declaran varibles globales
public class sumaMatrizUno extends Thread{

    //Nuevas
    static int n;
    static int matrizA[][];
    static int matrizB[][];
    static int matrizC[][];

    static String str[] = null, str2[] = null;
    //Viejas
    static Scanner in = new Scanner(System.in);
    static int mv = 0, thread=1, id=-1;
    static int[] solution = null;
    //Se recibe el tamaño del arreglo

    sumaMatrizUno(int id){
        this.id = id;

        n = 5;

        matrizA = new int[n][n];
        matrizB = new int[n][n];
        matrizC = new int[n][n];
    }
    public static void main(String[] args) {
        Thread[] Hilos = new Thread[1];
        /*
        https://www.cs.vu.nl/~ast/books/mos2/sample-10.pdf
        https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_process_creation_termination.htm
        https://www.geeksforgeeks.org/fork-system-call/matrizA = Iniciar(matrizA);
         */
        //Se ingresa el tamaño del arreglo
        //System.out.println("ingresa el tamaño del arreglo");
        //n = in.nextInt();
        n = 5;
        matrizA = new int[n][n];
        matrizB = new int[n][n];

        fullyMatrices(matrizA, n);
        fullyMatrices(matrizB, n);

        System.out.println("Matriz A: ");
        printing(matrizA, n);
        System.out.println("Matriz B: ");
        printing(matrizB, n);

        Hilos[0] = new sumaMatrizUno(0);
        Hilos[0].start();

        try{
            Hilos[0].join();
        }catch(InterruptedException e){
            System.out.println("Fallo el hilo " + id);
        }

        System.out.println("Matriz C");
        printing(matrizC, n);


    }// Fin main

    //Se realiza la suma de la matriz

    public void run(){
        System.out.println("Soy un hilo con id " + id);
        System.out.println("Matriz A: ");
        printing(matrizA, n);
        System.out.println("Matriz B: ");
        printing(matrizB, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizC[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }

        /*int i = 0, j = 0, k;
        int tam = matrizA.length / thread;
        int tam2 = matrizB.length / thread;
        int resto = (matrizA.length % thread);
        int ini = (id * tam);
        int fin = ini + tam;
        for (k = ini; k<fin; k++){
            suma = matrizA[id][i] + matrizB[id][i];
        }
        System.out.println("Thread local id "+id+" Thread Id: "+this.getId()+" Suma Local:"+suma);
        solution[id] = suma;*/
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
        public static void printing ( int[][] matriz, int tam){
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
}

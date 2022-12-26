//preguntar ésto-------------
package com.JournalDev;
//---------------------------
public class NReinasInvestigada {
    static final int N = 4;


    public static void main(String args[]){
        int tablero[][] = { { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 } };

        if (!solReina(tablero, 0)) {
            System.out.print("No se encontró la solución");
            return;
        }

        sol(tablero);

    }

    //Se imprime la suolucion
    static void sol(int tablero[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + tablero[i][j]
                        + " ");
            System.out.println();
        }
    }

    //Se verifica la posicion
    static boolean aSalvo(int tablero[][], int ver, int rem) {
        int i, j;
        for (i = 0; i < rem; i++)
            if (tablero[ver][i] == 1)
                return false;


        for (i = ver, j = rem; i >= 0 && j >= 0; i--, j--)
            if (tablero[i][j] == 1)
                return false;

        for (i = ver, j = rem; j >= 0 && i < N; i++, j--)
            if (tablero[i][j] == 1)
                return false;

        return true;
    }

    //Se resuelve con bactrack
    public static boolean solReina(int tablero[][], int rem)
    {
        if (rem >= N)
            return true;

        for (int i = 0; i < N; i++) {
            //sie está a salvo la reina en la posioson i, entonces rem -> lo reemplaza
            if (aSalvo(tablero, i, rem)) {
                tablero[i][rem] = 1;

                if (solReina(tablero, rem + 1))
                    return true;

                //es backtrack si la condicion es falas
                tablero[i][rem] = 0;
            }
        }
        return false;
    }
}
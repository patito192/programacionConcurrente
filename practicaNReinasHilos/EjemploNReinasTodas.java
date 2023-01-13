import java.util.Arrays;

public class EjemploNReinasTodas {
    public static final int N=10;





//public class EjemploNReinasTodas implements Runnable {


  /*static int N = 10;
  static int soluciones;
  private int[] tablero = null;
  private ReentrantLock candado;*/

    
  // Constructor
  /*ReinasHilos(int columna, ReentrantLock candado) {
    tablero = new int[N];
    tablero[0] = columna;
    this.candado = candado;

  }*/

  // Modificar para que no reciba tablero y manejar un arreglo en lugar de una matriz
  public static boolean esComida(int[][] tablero, int r, int c){
    
    int auxR,auxC;
    
    auxR=r-1;
    auxC=c;
    while(auxR>=0){
      if(tablero[auxR][auxC]==1){
        return true;
      }
      auxR--;
    }
    auxR=r-1;
    auxC=c+1;
    while(auxR>=0 && auxC< tablero.length){
      if(tablero[auxR][auxC]==1){ 
        return true;
      }
      auxR--; 
      auxC++;
    }
    
    auxR=r-1;
    auxC=c-1;
    while(auxR>=0 && auxC>=0){
      if(tablero[auxR][auxC]==1){
        return true;
      }
      auxR--;
      auxC--;
    }
    return false;
    
  }
  
  // Modificar para que no reciba tablero y manejar un arreglo en lugar de una matriz
  public static int backtrackReinas(int[][] tablero, int r){
      int c;
      int soluciones=0;
      if(r==tablero.length)
         return 1;
    
      for(c=0;c<tablero.length;c++){
          
          if(!esComida(tablero,r, c)){
            
            tablero[r][c]=1;
            soluciones = soluciones+backtrackReinas(tablero,r+1);
            tablero[r][c]=0;
          }
          
       }
       return soluciones;
  }

  /*@Override
  public void run() {

    candado.lock();
    
    // Inicio Seccion Critica
    soluciones += backtrackReinas(1);
    // Fin Seccion Critica
    
    candado.unlock();
  }*/

  public static void main(String[] args){

    

    System.out.println(arg[0]+" "+arg[1]);    
    
    
    int[][] tablero = new int[N][N];
    int total;        
    total=backtrackReinas(tablero,0);
    System.out.println("Tablero "+N+" Reinas Soluciones "+total);


    // Crear los hilos con Runnable y lanzarlos
    // trabajadores[i] = new Thread(new ReinasHilos(i, candado));
    // trabajadores[i].start();

    // Esperar a los hilos
    // trabajadores[i].join();

     
  }
}

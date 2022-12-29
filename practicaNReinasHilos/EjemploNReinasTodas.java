import java.util.Arrays;
public class EjemploNReinasTodas {
    public static final int N=10;
    
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

  public static void main(String[] arg){

    System.out.println(arg[0]+" "+arg[1]);    
    
    
    int[][] tablero = new int[N][N];
    int total;        
    total=backtrackReinas(tablero,0);
    System.out.println("Tablero "+N+" Reinas Soluciones "+total);

             
     
  }
}

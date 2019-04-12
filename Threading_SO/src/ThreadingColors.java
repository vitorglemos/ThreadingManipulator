
import java.util.concurrent.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

class Shared  
{ 
    static int count = 0; 
}

class threadRed extends ThreadingColors{
    private int n;
    threadRed(QueueClass q, int n){
        super(q);
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.n; i++){
            QueueClass.Red();    
        }
    }
}
class threadBlue extends ThreadingColors{
    private int n;
    threadBlue(QueueClass q, int n){
        super(q);
        this.n = n;
    }

    @Override
    public void run() {
          for (int i = 0; i < this.n; i++){
            QueueClass.Blue();    
        }
    }
}
class threadGreen extends ThreadingColors{
    private int n;
    threadGreen(QueueClass q, int n){
        super(q);
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.n; i++){
            QueueClass.Green();    
        }
    }
}
abstract class ThreadingColors implements Runnable{
    QueueClass queueThreading; 
    ThreadingColors(QueueClass q){
        this.queueThreading = q;
        new Thread(this, "threadRed").start();
        //new Thread(this, "threadBlue").start();
        //new Thread(this, "threadGreen").start();
    }
    
}
class QueueClass{
    static int idx;
    static Semaphore Red = new Semaphore(1);
    static Semaphore Blue = new Semaphore(0);
    static Semaphore Green = new Semaphore(0);
    
    public static int timeSleeping(){
        int min = 0; 
        int max = 9;
        int timeRandom = ThreadLocalRandom.current().nextInt(min, max + 1);
        return timeRandom;  
    }
    public static void RecursoImprimir(String t, String color){
        System.out.println(String.format("     id:[%s] %s ----------> [%s]",idx++,t,color));
    }
    public static void Red(){
        try{
            Red.acquire();
            System.out.println("     Execução concedida = THREAD[A]");
            int time = timeSleeping();
            System.out.println(String.format("     Dormindo THREAD[A] por [%s] segundos",time));
            Thread.sleep(time * 1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(QueueClass.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            RecursoImprimir("THREAD[A]","VERMELHO");
            //System.out.println(String.format("     id:[%s] THREAD[A] ---------> [VERMELHO]",idx++));
            Blue.release();
            System.out.println("     Recurso Liberado !");
        }
        
    }
    public static void Blue(){
        try{
            Blue.acquire();
            System.out.println("    Execução concedida = THREAD[B]");
            int time = timeSleeping();
            System.out.println(String.format("     Dormindo THREAD[B] por [%s] segundos",time));;
            Thread.sleep(time * 1000);
             
        } catch (InterruptedException ex) {
            Logger.getLogger(QueueClass.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            RecursoImprimir("THREAD[B]","AZUL");
            Green.release();  
            System.out.println("     Recurso Liberado !");
        }
    }
     public static void Green(){
        try{
            Green.acquire();
            System.out.println("    Execução concedida = THREAD[C]");
            int time = timeSleeping();
            System.out.println(String.format("     Dormindo THREAD[C] por [%s] segundos",time));
            Thread.sleep(time * 1000);
             
        } catch (InterruptedException ex) {
            Logger.getLogger(QueueClass.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            RecursoImprimir("THREAD[C]","VERDE");
            Red.release(); 
            System.out.println("     Recurso Liberado !");
        }
         
    }
 
}

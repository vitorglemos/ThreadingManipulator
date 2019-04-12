
import javax.swing.JOptionPane;

public class Trabalho2_SemaforoThreading_SO {

    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog(" Digite o número de vezes (n) de execução: "));
        QueueClass k = new QueueClass();
        new threadRed(k, n);
        new threadBlue(k, n);
        new threadGreen(k, n);
     
    }  
}



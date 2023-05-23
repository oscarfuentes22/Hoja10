import java.util.Scanner;
import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        controller miControlador = new controller();
        while (true){

            System.out.println("calculadora infix , Escribir archivo archivo datos.txt\n");

            System.out.println("1. Stack ArrayList\n2. Stack Vector\n3. Stack LinkedList\n4. Stack DoubleLinkedList\n5. Salir\n");

            String opcion = sc.nextLine();

            Controller.importFile();

            switch (opcion){
                case "1":
                    System.out.println("El resultado es: " + Controller.calculate("ArrayList"));
                    break;
                case "2":
                    System.out.println("El resultado es: " + Controller.calculate("Vector"));
                    break;
                case "3":
                    System.out.println("El resultado es: " + Controller.calculate("LinkedList"));
                    break;
                case "4":
                    System.out.println("El resultado es: " + Controller.calculate("DoubleLinkedList"));
                    break;
                case "5":
                    System.out.println("USTED HA SALIDO DEL PROGRAMA");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingrese la opcion que desea");
                    break;
            }
    }
}


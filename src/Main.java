
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int menu = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Ingrese el numero de la opcion que desea realizar:");
            System.out.println("1. Encontrar la ruta mas corta entre dos ciudades");
            System.out.println("2. Ciudad en el centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");

            menu = scanner.nextInt();
            controller control = new controller();

            switch (menu) {
                case 1 -> {
                    //se calcula la ruta mas corta entre dos ciudades
                    System.out.println("Ingrese la ciudad de origen:");
                    String originCity = scanner.next();
                    System.out.println("Ingrese la ciudad de destino:");
                    String destinyCity = scanner.next();
                    StringBuilder message = new StringBuilder();
                    ArrayList<String> shortRute = control.getShot(originCity, destinyCity);
                    for (int i = 0; i < shortRute.size(); i++) {
                        message.append((i + 1)).append(" ").append(shortRute.get(i)).append("\n");
                    }
                    System.out.println(message);
                }
                case 2 ->
                    //se calcula la ciudad en el centro del grafo
                        System.out.println("Centro del grafo: " + control.getCentroDelGrafo());
                case 3 -> {
                    //se modifica el grafo de las ciudades
                    int opcion = 0;
                    while (opcion != 4) {
                        System.out.println("Ingrese el numero de la opcion que desea realizar:");
                        System.out.println("1. Interrupción de ciudades");
                        System.out.println("2. Agregar conexion entre ciudades");
                        System.out.println("3. Cambio de temp");
                        System.out.println("4. Salir");

                        opcion = scanner.nextInt();
                        String city_1 = "";
                        String city_2 = "";
                        int tiempos[] = new int[4];
                        String temp = "";
                        String line = "";

                        switch (opcion) {
                            case 1 -> {
                                //se interrumpe una conexion entre dos ciudades
                                System.out.println("Ingrese la ciudad de origen:");
                                city_1 = scanner.next();
                                System.out.println("Ingrese la ciudad de destino:");
                                city_2 = scanner.next();
                                line = city_1 + " " + city_2;
                                control.mod(opcion, line, "");
                            }
                            case 2 -> {
                                //se agrega una conexion entre dos ciudades
                                System.out.println("Ingrese la ciudad de origen:");
                                city_1 = scanner.next();
                                System.out.println("Ingrese la ciudad de destino:");
                                city_2 = scanner.next();
                                //se piden los tiempos de viaje  tiempoNormal tiempoLluvia tiempoNieve tiempoTormenta
                                System.out.println("Ingrese el tiempo de viaje normal:");
                                tiempos[0] = scanner.nextInt();
                                System.out.println("Ingrese el tiempo de viaje en lluvia:");
                                tiempos[1] = scanner.nextInt();
                                System.out.println("Ingrese el tiempo de viaje en nieve:");
                                tiempos[2] = scanner.nextInt();
                                System.out.println("Ingrese el tiempo de viaje en tormenta:");
                                tiempos[3] = scanner.nextInt();
                                line = city_1 + " " + city_2 + " " + tiempos[0] + " " + tiempos[1] + " " + tiempos[2] + " " + tiempos[3];
                                control.mod(opcion, line, "");
                            }
                            case 3 -> {
                                //se cambia el temp de una ciudad
                                System.out.println("Ingrese la ciudad que desea cambiar el temp:");
                                city_1 = scanner.next();
                                System.out.println("Ingrese la ciudad que desea cambiar el temp:");
                                city_2 = scanner.next();
                                line = city_1 + " " + city_2;
                                //se pide el nuevo temp tiempoNormal tiempoLluvia tiempoNieve tiempoTormenta
                                System.out.println("Eliga el nuevo temp de la ciudad:");
                                System.out.println("1. Normal");
                                System.out.println("2. Lluvia");
                                System.out.println("3. Nieve");
                                System.out.println("4. Tormenta");
                                temp = scanner.next();
                                control.mod(opcion, line, temp);
                            }
                            case 4 ->
                                //se sale del menu de modificación
                                    System.out.println("Saliendo del menu de modificacion");
                            default ->
                                //opcion invalida
                                    System.out.println("Opcion invalida");
                        }
                    }
                }
                case 4 -> {
                    //se sale del programa
                    System.out.println("Gracias por usar el programa");
                    System.exit(0);
                }
                default ->
                    //opción invalida
                        System.out.println("Opcion invalida");
            }
        }

    }
}

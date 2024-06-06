
package energyproject2;
import java.util.Scanner;
import java.util.ArrayList;

public class EnergyProject2 {

    
    public static void main(String[] args) {
       
        // Definir los vectores de precios del kWh
        double[] precios2024 = {1165.56, 1164.92, 1211.29, 1172.07, 1192.21095, 1208.75404, 1225.29713, 1241.84022, 1258.38331, 1274.9264, 1291.469489, 1308.012579};
        double[] precios2025 = {1324.555669, 1341.098759, 1357.641849, 1374.184939, 1390.728029, 1407.271119, 1423.814209, 1440.357299, 1456.900389, 1473.443478, 1489.986568, 1506.529658};
        double[] precios2026 = {1523.072748, 1539.615838, 1556.158928, 1572.702018, 1589.245108, 1605.788198, 1622.331288, 1638.874377, 1655.417467, 1671.960557, 1688.503647, 1705.046737};
        double[] precios2027 = {1721.589827, 1738.132917, 1754.676007, 1771.219097, 1787.762187, 1804.305276, 1820.848366, 1837.391456, 1853.934546, 1870.477636, 1887.020726, 1903.563816};

        // Definir los factores de estrato
        double[] factoresEstrato = {0.599998, 0.5, 0.15, 0.0};
        //Estrato 1 -- Estrato 2 -- Estrato 3 -- Estrato 4

        // Crear un objeto Scanner para la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Variables para el bucle
        int año = -1, mes = -1, estrato = -1, opcionConsumo = -1;
        double consumo = 0;

        // Bucle principal
        while (true) {
            recuerda(scanner);

            // Pedir al usuario el año
            while (true) {
                System.out.print("Digite el año dentro del rango (2024-2027): ");
                año = scanner.nextInt();
                if (año == 0) {
                    System.out.print("¿Desea salir del programa? (1-Sí, 2-No): ");
                    int salir = scanner.nextInt();
                    if (salir == 1) {
                        System.out.println("Saliendo del programa...");
                        return;
                    }
                }
                if (año >= 2024 && año <= 2027) break;
                System.out.println("Año no valido. Intente de nuevo.");
            }

            // Pedir al usuario el mes
            while (true) {
                System.out.print("Digite el mes dentro del rango (1-12): ");
                mes = scanner.nextInt();
                if (mes == 0) {
                    System.out.print("¿Desea volver al menu anterior (año) o salir del programa? (1-Volver, 2-Salir): ");
                    int volver = scanner.nextInt();
                    if (volver == 1) {
                        año = -1;
                        break;
                    } else if (volver == 2) {
                        System.out.println("Saliendo del programa...");
                        return;
                    }
                }
                if (mes >= 1 && mes <= 12) break;
                System.out.println("Mes no valido. Intente de nuevo.");
            }

            if (año == -1) continue;

            // Pedir al usuario la opción de consumo
            while (true) {
                System.out.println("Seleccione de que manera calcular su consumo: ");
                System.out.println("[1] Consumo del mes (en kWh) dato de factura\n[2] Consumo del mes mediante lectura ");
                opcionConsumo = scanner.nextInt();
                if (opcionConsumo == 0) {
                    System.out.print("¿Desea volver o salir del programa? (1-Volver, 2-Salir): ");
                    int volver = scanner.nextInt();
                    if (volver == 1) {
                        mes = -1;
                        break;
                    } else if (volver == 2) {
                        System.out.println("Saliendo del programa...");
                        return;
                    }
                }
                if (opcionConsumo == 1 || opcionConsumo == 2) break;
                System.out.println("Opcion no valida. Intente de nuevo.");
            }

            if (mes == -1) continue;

            // Pedir al usuario el consumo
            if (opcionConsumo == 1) {
                while (true) {
                    System.out.print("Digite el consumo del mes (en kWh): ");
                    consumo = scanner.nextDouble();
                    if (consumo == 0) {
                        System.out.print("¿Desea volver o salir del programa? (1-Volver, 2-Salir): ");
                        int volver = scanner.nextInt();
                        if (volver == 1) {
                            opcionConsumo = -1;
                            break;
                        } else if (volver == 2) {
                            System.out.println("Saliendo del programa...");
                            return;
                        }
                    }
                    if (consumo > 0) break;
                    System.out.println("Consumo no valido. Intente de nuevo.");
                }
            } else if (opcionConsumo == 2) {
                while (true) {
                    System.out.print("Digite la lectura actual: ");
                    double lecturaInicio = scanner.nextDouble();
                    if (lecturaInicio == 0) {
                        System.out.print("¿Desea volver o salir del programa? (1-Volver, 2-Salir): ");
                        int volver = scanner.nextInt();
                        if (volver == 1) {
                            opcionConsumo = -1;
                            break;
                        } else if (volver == 2) {
                            System.out.println("Saliendo del programa...");
                            return;
                        }
                    }
                    System.out.print("Digite la lectura anterior: ");
                    double lecturaFin = scanner.nextDouble();
                    if (lecturaFin == 0) {
                        System.out.print("¿Desea volver o salir del programa? (1-Volver, 2-Salir): ");
                        int volver = scanner.nextInt();
                        if (volver == 1) {
                            opcionConsumo = -1;
                            break;
                        } else if (volver == 2) {
                            System.out.println("Saliendo del programa...");
                            return;
                        }
                    }
                    consumo = lecturaInicio - lecturaFin;
                    if (consumo > 0) break;
                    System.out.println("Lectura no valida. Intente de nuevo.");
                }
            }

            if (opcionConsumo == -1) continue;

            // Pedir al usuario el estrato
            while (true) {
                System.out.print("Digite su estrato (1-4): ");
                estrato = scanner.nextInt();
                if (estrato == 0) {
                    System.out.print("¿Desea volver o salir del programa? (1-Volver, 2-Salir): ");
                    int volver = scanner.nextInt();
                    if (volver == 1) {
                        opcionConsumo = -1;
                        break;
                    } else if (volver == 2) {
                        System.out.println("Saliendo del programa...");
                        return;
                    }
                }
                if (estrato >= 1 && estrato <= 4) break;
                System.out.println("Estrato no valido. Intente de nuevo.");
            }

            if (opcionConsumo == -1) continue;

            // Seleccionar el vector de precios adecuado según el año ingresado
            double[] precios;
            switch (año) {
                case 2024:
                    precios = precios2024;
                    break;
                case 2025:
                    precios = precios2025;
                    break;
                case 2026:
                    precios = precios2026;
                    break;
                case 2027:
                    precios = precios2027;
                    break;
                default:
                    System.out.println("Año no válido.");
                    continue;
            }

            // Obtener el precio del kWh para el mes ingresado
            double preciokwh = precios[mes - 1];
            System.out.println(preciokwh);

            // Obtener el factor de estrato
            double factorestrato = factoresEstrato[estrato - 1];

            // Calcular subsidio según el estrato
            double sub = factorestrato * preciokwh;

            // Calcular el costo total del subsidio
            double cossub = preciokwh - sub;

            // Calcular el total a pagar
            double totalapagar = cossub * consumo;

            // Mostrar el total a pagar
            System.out.printf("El total a pagar por el consumo de energia en %d/%d es: $%.2f%n", mes, año, totalapagar);
            
            //Funcion que muestra consejos sobre el ahorro energetico.
            sugerenciasConsumo(scanner);

            // Preguntar si desea calcular el costo de electrodomesticos
            int otraConsultaElectrodomesticos=0; //Variable para volver a calcular
            int opcionElectrodomesticosModo = 0;  
            System.out.println("¿Desea calcular el costo o consumo de sus electrodomesticos? (1-Si, 2-No)");
            int consultaElectrodomesticos = scanner.nextInt();

            do{
          

                if (consultaElectrodomesticos == 1){
                    System.out.println("Bienvenido a la calculadora de consumo de electrodomesticos.\nSigue las siguientes instrucciones");
                    double costokwh = cossub;

                    System.out.println("¿(1) Desea calcular mediante promedio de consumo por hora o (2) mediante su electrodomestico especifico?\n[3]Consejos");
                    
                    opcionElectrodomesticosModo = scanner.nextInt();
                    
                    ArrayList<String> electrodomesticosUser = new ArrayList<>();
                    ArrayList<Double> horasUsoUser = new ArrayList<>();

                    double totalConsumoElectrodomesticos = 0;
                    int cantidad = 0;
                    double horasUso=0;
                    double consumoDiario = 0;
                    double costoTotalElectrodomesticos = 0;

                    if(opcionElectrodomesticosModo == 1){

                    double consumoElectrodomesticos[] = {0.02,0.06,0.10,0.11,0.33,1.20,1.78,5.60,0.30,0.60,1.4,0.2}; //Kwh de los electrodomesticos
                    //Bombillo--Nevera--Computador--Televisor--Lavadora--Plancha--Secador de pelo--Secadora--Licuadora--Microondas
                    String nombresElectrodomesticos[] = {"Bombillo","Nevera","Computador","Televisor","Lavadora","Plancha","Secador de pelo","Secadora","Licuadora","Microondas","Aire acondicionado","Ventilador"};
                        while(true){
                            System.out.println("MENU ELECTRODOMESTICOS:\n[1] Bombillo\n[2] Nevera\n[3] Computador\n[4] Televisor\n[5] Lavadora\n[6] Plancha\n[7] Secador de pelo\n[8] Secadora\n[9] Licuadora\n[10] Microondas\n[11]Aire acondicionado\n[12]Ventilador\n[0] Finalizar selección");
                            int opcionElectrodomesticos = scanner.nextInt();
                            if (opcionElectrodomesticos == 0) {
                                    break;
                                } else if (opcionElectrodomesticos >= 1 && opcionElectrodomesticos <= 12) {
                                    System.out.print("Horas de uso del electrodomestico (" + nombresElectrodomesticos[opcionElectrodomesticos - 1] + "): ");
                                    horasUso = scanner.nextDouble();
                                    electrodomesticosUser.add(nombresElectrodomesticos[opcionElectrodomesticos - 1]);
                                    horasUsoUser.add((double) horasUso);
                                    System.out.println("Cantidad de dicho electrodomestico: ");
                                    cantidad = scanner.nextInt();
                                } else {
                                    System.out.println("Opción no valida.");
                                }
                            }
                            for (int i = 0; i < electrodomesticosUser.size(); i++) {
                                int index = -1;
                                for (int j = 0; j < nombresElectrodomesticos.length; j++) {
                                    if (electrodomesticosUser.get(i).equals(nombresElectrodomesticos[j])) {
                                        index = j;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    consumoDiario = consumoElectrodomesticos[index] * horasUsoUser.get(i)*cantidad;
                                    totalConsumoElectrodomesticos += consumoDiario * 30; // Asumiendo un mes de 30 días
                                    System.out.printf("El consumo del electrodomestico %s por las horas ingresadas es: %.2f kWh diarios y el consumo mensual por el momento(%.2f kWh mensuales)%n", electrodomesticosUser.get(i), consumoDiario, totalConsumoElectrodomesticos);
                                    System.out.printf("El costo por el uso de dicho electrodomestico es de: $%.2f%n", consumoDiario*costokwh);
                               }
                            }
                             costoTotalElectrodomesticos = totalConsumoElectrodomesticos * costokwh;
                            System.out.printf("El costo total por el uso de sus electrodomesticos mensual es: $%.2f%n", costoTotalElectrodomesticos);

                    }else if(opcionElectrodomesticosModo== 2){
                        while(true){    
                            System.out.println("Ingrese el nombre del electrodomestico: o '0' para salir ");
                               scanner.nextLine();
                               String nombreElectrodomestico = scanner.nextLine();

                               if(nombreElectrodomestico.equalsIgnoreCase("0")){
                                   break;
                               }

                                System.out.println("Ingrese la cantidad de dicho electrodomestico: ");
                                cantidad = scanner.nextInt();

                                System.out.println("Ingrese el consumo por hora del electrodomestico [" + nombreElectrodomestico + "] (en kWh)");
                                double consumoPorHora = scanner.nextDouble();

                                electrodomesticosUser.add(nombreElectrodomestico);
                                horasUsoUser.add(consumoPorHora);

                            }  
                            for(int i=0;i<electrodomesticosUser.size();i++){
                            System.out.printf("¿Cuantas horas al dia usa el [%s?]: ", electrodomesticosUser.get(i));
                            horasUso = scanner.nextDouble();
                            consumoDiario =  (horasUsoUser.get(i)*horasUso)*cantidad;
                            totalConsumoElectrodomesticos +=consumoDiario;
                            
                            System.out.printf("El consumo del electrodomestico %s por las horas ingresadas es: %.2f kWh diarios y el consumo mensual por el momento(%.2f kWh mensuales)%n", electrodomesticosUser.get(i), consumoDiario, totalConsumoElectrodomesticos);
                            System.out.printf("El costo por el uso de dicho electrodomestico es de: $%.2f%n", consumoDiario*costokwh);
                           
                            }
                            
                            costoTotalElectrodomesticos = totalConsumoElectrodomesticos * costokwh * 30; 
                            System.out.printf("El costo total por el uso de sus electrodomesticos mensual es de: $%.2f%n", costoTotalElectrodomesticos);



                        }else if(opcionElectrodomesticosModo == 3){
                           sugerenciasElectrodomesticos(scanner);
                        }else{
                           System.out.println("Opcion no valida");
                        }

                        System.out.println("¿Desea volver a calcular el costo de sus electrodomesticos?(1-Si, 2-No)");
                        otraConsultaElectrodomesticos = scanner.nextInt();



                }
            }while(otraConsultaElectrodomesticos == 1);

            
            
            
            // Preguntar si desea realizar otra consulta
            System.out.print("¿Desea realizar otra consulta de consumo general? (1-Si, 2-No): ");
            int otraConsulta = scanner.nextInt();
            if (otraConsulta != 1) {
                System.out.println("Saliendo del programa...");
                break;
            }
         }   
     }
   


            
       
    public static void sugerenciasConsumo(Scanner scanner){
        // Se muestra al momento de darle al usuario el consumo de energia.
        System.out.println("RECUERDA seguir los siguientes consejos para el ahorro de energia:");
        System.out.println("");
        System.out.println("1.Utiliza bombillas LED.\n2.Desconecta los electrodomesticos en stand-by.\n3.Aprovecha la luz natural.\n4.Aisla termicamente tu hogar.\n5.Elige electrodomesticos eficientes: Al comprar nuevos electrodomesticos, busca aquellos con etiquetas de eficiencia energetica como A++ o A+++.\n6.Optimiza el uso de la lavadora: Usa la lavadora con cargas completas y selecciona programas de lavado eficientes. Ademas, emplea agua fria siempre que sea posible.\n7.Regula la temperatura de la nevera y el congelador: Asegurate de que esten ajustados correctamente. Unos grados de diferencia pueden influir significativamente en el consumo electrico.\n8.Instala paneles solares: Considera la instalacion de paneles solares para el calentamiento de agua o para generar electricidad, lo cual puede ser una excelente opcion para ahorrar energia.\n9.Apaga las luces cuando no las necesites.\n10.Reduce el consumo de agua caliente: Intenta reducir su uso optando por duchas cortas en lugar de baños prolongados y utiliza programas de lavado en frio en la lavadora.");
    } 
    public static void sugerenciasElectrodomesticos(Scanner scanner){
        //Se muestra al terminar de usar la calculadora
        System.out.println("Sigue estos consejos para lograr un mejor ahorro de energia en tus electrodomesticos: ");
        
        System.out.println("1.Nevera: Evita dejar la puerta de la nevera abierta por tiempo prolongado. Asegurate de que selle bien y no introduzcas alimentos calientes\n2.Aire Acondicionado: Mantén limpios los filtros y usa el aire acondicionado a temperaturas moderadas, preferiblemente alrededor de 24°C.\n3.Lavadora: Utiliza la lavadora con carga completa y selecciona ciclos de lavado con agua fria para ahorrar energía.4.Secadora: Evita el uso de la secadora electrica colgando la ropa al aire libre para que se seque naturalmente.\n5.Cocina: Cocina con ollas a presion y tapas para reducir el tiempo y la energia necesarios para cocinar.\n6.Electrodomesticos en Stand-by: Desconecta los electrodomesticos que no estes utilizando para evitar el consumo fantasma.\n7.Iluminacion: Reemplaza las bombillas incandescentes por LED, que ahorran hasta un 80% de energia y duran 25 veces mas.\n8.Televisores y Monitores: Apaga los dispositivos cuando no los estes usando y considera ajustar el brillo a un nivel comodo que no sea excesivamente alto.\n9.Computadoras y Cargadores: Desconecta los cargadores de tus dispositivos electronicos cuando no estén en uso.\n10.Plancha: Acumula ropa para planchar en una sola sesión y utiliza la plancha con la temperatura adecuada para cada tipo de tela.");
        
    }
    public static void recuerda(Scanner scanner){
        System.out.println("Bienvenido a EnergyWise");
        System.out.println("Recuerda lo siguiente:\n1.Para el calculo del gasto de energia, no se tendra en cuento el alumbrado publico de tu factura y demas valores adicionales.\n2. Podras ver una serie de consejos para el ahorro de energia.");
        System.out.println("Recuerda usar 0 para salir del programa.");
    }
    
}

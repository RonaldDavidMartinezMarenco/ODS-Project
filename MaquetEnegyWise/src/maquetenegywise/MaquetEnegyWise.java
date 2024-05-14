package maquetenegywise;

import java.util.Scanner;

public class MaquetEnegyWise {
    public static double calcularConsumo(double potenica, double horas){
        return(potenica*horas)/1000;
    }
    public static double calcularCosto(double consumo,double tarifa){
        return consumo*tarifa;
    }
    public static boolean Solar(double consumoTotal, double generacionSolar){
        return consumoTotal<=generacionSolar;
    }
    
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Bienvenido a EnergyWise");
        
        System.out.println("Registre su usuario: ");
        String user=sc.nextLine();
        System.out.println("Registre su contraseña: ");
        String pass=sc.nextLine();
        
        int attempts=3;
        System.out.println("Bienvenido a nuestro inicio de sesion, sigue las siguientes instrucciones: ");
        
        while(attempts>0){
            System.out.println("Ingrese su usuario");
            String inputUser=sc.nextLine();
            System.out.println("Ingrese su contraseña");
            String inputPass=sc.nextLine();
            
            if(inputUser.equals(user)&& inputPass.equals(pass)){
                System.out.println("Bienvenido "+ user+ " a tu programa de confianza ¡EnergyWise!");
                break;
            }else{
                System.out.println("Usuario o contraseña Incorrectos. Intente de nuevo");
                attempts--;
                if(attempts>0){
                    System.out.println("Tienes "+ attempts+ "Intentos restantes.");
                    
                }else{
                    System.out.println("No le quedan intentos.");
                }
                
            }
        }
        System.out.println("[1]Estimado Consumo\n [2]Preciso Consumo \n [3]Salir");
        int choice= sc.nextInt();
        
        switch(choice){
            case 1 -> { 
                System.out.println("Cuantos Artefactos electricos desea ingresar?");
                int numArtefactos=sc.nextInt();
                String[]nombres= new String[numArtefactos];
                double []consumos=new double [numArtefactos];
                
                for(int i=0;i<numArtefactos;i++){
                    
                    System.out.println("Ingrese el nombre del artefacto" + (i+1)+ ": ");
                    nombres[i]=sc.next();
                    
                    System.out.println("Ingrese la potencia del artefacto"+ nombres[i]+"en vatios(W): ");
                    double potencia=sc.nextDouble();
                    
                    System.out.println("Ingrese las horas de uso por dia del artefacto: "+ nombres[i]+": ");
                    double horas=sc.nextDouble();
                    
                    consumos[i]=calcularConsumo(potencia,horas);
                    System.out.println("El consumo del artefacto es: "+ nombres[i]+"es: "+ consumos[i]+ "kWh");
                    
                }
                double consumoTotal=0;
                for (double consumo : consumos){
                    consumoTotal += consumo;
                }
                System.out.println("El consumo total de los artefactos es de: "+ consumoTotal+"kWh");
                System.out.println(" ");
                
                System.out.println("¿Desea calcular el costo del consumo eléctrico? (si/no): ");
                String respuesta = sc.next();
                
                if (respuesta.equalsIgnoreCase("si")) {
                    System.out.println("Ingrese la tarifa eléctrica en su área (por kWh): ");
                    double tarifa = sc.nextDouble();
                    
                    double costoTotal = calcularCosto(consumoTotal, tarifa);
                    System.out.println("El costo total del consumo eléctrico es: " + costoTotal);
                }
                
                System.out.println("¿Desea comparar el consumo con la generación de un panel solar? (si/no): ");
                respuesta = sc.next();
                
                if (respuesta.equalsIgnoreCase("si")) {
                    System.out.println("Ingrese la generación diaria del panel solar en kWh: ");
                    double generacionSolar = sc.nextDouble();
                    
                    if (Solar(consumoTotal, generacionSolar)) {
                        System.out.println("El uso de paneles solares sería factible para cubrir el consumo eléctrico.");
                    }else {
                        System.out.println("El uso de paneles solares no sería suficiente para cubrir el consumo eléctrico.");
                    }
                }
            }
            case 2 -> System.out.println("Preciso");
            case 3 -> System.out.println("Gracias");
            default -> System.out.println("Opcion Invalida");
    
        }
    }
}


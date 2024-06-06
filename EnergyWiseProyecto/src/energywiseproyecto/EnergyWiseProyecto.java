
package energywiseproyecto;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class EnergyWiseProyecto {

    
    public static void main(String[] args) {
      
        ImageIcon icono = null;
        
        try {
            icono = new ImageIcon(EnergyWiseProyecto.class.getResource("/image/23.png"));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW); // Cambia Color.GREEN por el color que desees
        panel.add(new JLabel(icono));
        
         Object[] opcionesInicio = {"Comenzar"};
         JOptionPane.showOptionDialog(
            null, 
            panel, 
            "EnergyWise", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            opcionesInicio, 
            opcionesInicio[0]
        );
         
        
        //Variables de seleccion
        int opcion;
        //Vectores
        double tarifaEstrato=0;
        // Definir los factores de estrato
        double[] factoresEstrato = {0.599998, 0.5, 0.15, 0.0};
        double[] precios2024 = {1165.56, 1164.92, 1211.29, 1172.07, 1192.21095, 1208.75404, 1225.29713, 1241.84022, 1258.38331, 1274.9264, 1291.469489, 1308.012579};
        double[] precios2025 = {1324.555669, 1341.098759, 1357.641849, 1374.184939, 1390.728029, 1407.271119, 1423.814209, 1440.357299, 1456.900389, 1473.443478, 1489.986568, 1506.529658};
        double[] precios2026 = {1523.072748, 1539.615838, 1556.158928, 1572.702018, 1589.245108, 1605.788198, 1622.331288, 1638.874377, 1655.417467, 1671.960557, 1688.503647, 1705.046737};
        double[] precios2027 = {1721.589827, 1738.132917, 1754.676007, 1771.219097, 1787.762187, 1804.305276, 1820.848366, 1837.391456, 1853.934546, 1870.477636, 1887.020726, 1903.563816};
        tarifasEnergia objetoVector = new tarifasEnergia(12);

        //Bucle de Menu Principal.
        
        

        do {
            String[] options = {"Calcular consumo de energía", "Calcular consumo de electrodomésticos", "Consejos","Tarifas ","Salir"};
            opcion = JOptionPane.showOptionDialog(
                null, 
                "MENÚ PRINCIPAL", 
                "EnergyWise", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                options, 
                options[0]
            );
            

            switch (opcion) {
                case 0: // Calcular consumo de energía
                    String[] opcionesConsumo = {"Consumo promedio", "Consumo mediante lectura"};
                    int opcionConsumo = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el tipo de cálculo:",
                        "EnergyWise",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesConsumo,
                        opcionesConsumo[0]
                        );
                    tarifaEstrato = tarifas(objetoVector,precios2024, precios2025, precios2026, precios2027,factoresEstrato);
                    System.out.println(tarifaEstrato);
                    
                    switch(opcionConsumo){
                        case 0://consumo promedio
                        String consumo = JOptionPane.showInputDialog(null, "Ingrese la cantidad de kWh consumida en el mes:");
                        double kwh = Double.parseDouble(consumo); // Cantida de kwh
                        double valor = kwh*tarifaEstrato;
                        JOptionPane.showMessageDialog(null, valor, "Precio a pagar:", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 1://Lectura
                        String lecturaActual = JOptionPane.showInputDialog(null, "Ingrese la lectura actual de su contador:");
                        double lecturaActualKWh = Double.parseDouble(lecturaActual); // Cantida de kwh
                        String lecturaAnterior = JOptionPane.showInputDialog(null, "Ingrese la lectura anterior de su contador:");
                        double lecturaAnteriorKWh = Double.parseDouble(lecturaAnterior); // Cantida de kwh
                        
                        double consumo2 = lecturaActualKWh-lecturaAnteriorKWh;
                        double valor2= consumo2*tarifaEstrato;
                        JOptionPane.showMessageDialog(null, valor2, "Precio a pagar", JOptionPane.INFORMATION_MESSAGE);    
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                    }
                    
                    break;
                case 1: // Calcular consumo de electrodomésticos
                    electrodomesticos objetoElectrodomesticos = new electrodomesticos(50);
                    String electrodomesticos;
                 do{
                    String[] electrodomesticosOpciones = {"Agregar Electrodomestico","Calcular Consumo","Eliminar Electrodomestico","Actualizar Electrodomestico","Mostrar Electrodomestico","Volver"};
                    opcion = JOptionPane.showOptionDialog(
                                null, 
                                "MENÚ ELECTRODOMESTICOS", 
                                "EnergyWise", 
                                JOptionPane.DEFAULT_OPTION, 
                                JOptionPane.PLAIN_MESSAGE, 
                                null, 
                                electrodomesticosOpciones, 
                                electrodomesticosOpciones[0]
                            );
                    switch(opcion){
                        case 0:
                            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del electrodoméstico:");
                            double consumo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el consumo en kWh:"));
                            objetoElectrodomesticos.insertarElectrodomestico(objetoElectrodomesticos.n, nombre, consumo);
                            break;
                        case 1://CALCULAR CONSUMO
                            double tarifa;
                            if(objetoElectrodomesticos.n == 0){
                               JOptionPane.showMessageDialog(null, "No hay electrodomésticos registrados.");
                            }else{
                                tarifa=tarifaEstrato;
                                if(tarifa!=-1){
                                    double consumoTotal=0;
                                    double costoTotal=0;
                                    StringBuilder mensaje = new StringBuilder("Consumo de electrodomésticos:\n");
                                    String[] nombres = objetoElectrodomesticos.getNombresElectrodomesticos();
                                    double[] consumos = objetoElectrodomesticos.getConsumos();
                                    for (int i = 0; i < objetoElectrodomesticos.n; i++) {
                                        double consumoIndividual = consumos[i];
                                        double valorIndividual = consumoIndividual * tarifaEstrato;
                                        consumoTotal+=consumoIndividual;
                                        costoTotal += valorIndividual;
                                        mensaje.append(nombres[i])
                                        .append(": ")
                                        .append(consumoIndividual)
                                        .append(" kWh - Valor: ")
                                        .append(valorIndividual)
                                        .append("\n");
                                    }
                                    mensaje.append("\nConsumo total: ").append(consumoTotal).append(" kWh");
                                    mensaje.append("\nCosto total: ").append(costoTotal);
                                    JOptionPane.showMessageDialog(null, mensaje.toString());
                                }
                            }break;
                      
                        
                        case 2:
                            int indiceEliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "[0,1,2,3,4,5...]Ingrese el índice del electrodoméstico a eliminar:"));
                            objetoElectrodomesticos.eliminarElectrodomestico(indiceEliminar);
                            break;
                        case 3:
                            int indiceActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "[0,1,2,3,4,5...]Ingrese el índice del electrodoméstico a actualizar:"));
                            String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre:");
                            double nuevoConsumo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo consumo en kWh:"));
                            objetoElectrodomesticos.actualizarElectrodomestico(indiceActualizar, nuevoNombre, nuevoConsumo);
                            break;
                        case 4:
                            objetoElectrodomesticos.mostrarTodosLosElectrodomesticos();
                            break;
                        case 5:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                    }
                     System.out.println(tarifaEstrato);
                 }while(opcion !=5);
                    
                    
                    break;
                case 2: // Consejos
                    System.out.println(tarifaEstrato);
                    mostrarConsejos();
                    break;
                case 3:
                    String[] opcionesT = {"2024", "2025", "2026","2027"};
                    opcion = JOptionPane.showOptionDialog(
                        null, 
                        "Seleccione el año de las tarfias que desea ver", 
                        "EnergyWise", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.PLAIN_MESSAGE, 
                        null, 
                        opcionesT, 
                        opcionesT[0]
                    );
                    switch(opcion){
                        case 0:
                            objetoVector.inicializar(precios2024);
                            objetoVector.mostrarTodosLosPrecios();
                            
                            break;
                        case 1:
                            objetoVector.inicializar(precios2025);
                            objetoVector.mostrarTodosLosPrecios();
                            break;
                        case 2:
                            objetoVector.inicializar(precios2026);
                            objetoVector.mostrarTodosLosPrecios();
                            break;
                        case 3:
                            objetoVector.inicializar(precios2027);
                            objetoVector.mostrarTodosLosPrecios();
                            break;
                        default:
                             JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                        
                    }
                   
                      
                    break;
                case 4: // Salir
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break;
            }
        } while (opcion != 4); // Salir cuando se elige la opción "Salir" (índice 4)
    }
  
       public static void mostrarConsejos() {
        String consejos = "RECUERDA seguir los siguientes consejos para el ahorro de energía:\n\n" +
                          "1. Utiliza bombillas LED.\n" +
                          "2. Desconecta los electrodomésticos en stand-by.\n" +
                          "3. Aprovecha la luz natural.\n" +
                          "4. Aisla térmicamente tu hogar.\n" +
                          "5. Elige electrodomésticos eficientes.\n" +
                          "6. Optimiza el uso de la lavadora.\n" +
                          "7. Regula la temperatura de la nevera y el congelador.\n" +
                          "8. Instala paneles solares.\n" +
                          "9. Apaga las luces cuando no las necesites.\n" +
                          "10. Reduce el consumo de agua caliente.\n"+
                          "11.Usa electrodomesticos con categoria A++";
                                  

        JOptionPane.showMessageDialog(null, consejos, "Consejos para ahorrar energía", JOptionPane.INFORMATION_MESSAGE);
    }
       public static double tarifas(tarifasEnergia objetoVector,double precios2024[],double precios2025[],double precios2026[],double precios2027[],double estratoFactores[]){
           String añoStr = JOptionPane.showInputDialog(null, "Ingrese el año (2024-2027):");
            int año = Integer.parseInt(añoStr);

            // Solicitar mes al usuario
            String mesStr = JOptionPane.showInputDialog(null, "Ingrese el mes (1-12):");
            int mes = Integer.parseInt(mesStr) - 1; // Ajustar a índice de arreglo (0-11)
            
            String estratoStr = JOptionPane.showInputDialog(null, "Ingrese el estrato (1-4):");
            int estrato = Integer.parseInt(estratoStr) - 1; // Ajustar a índice de arreglo (0-3)
            
            
            if(año>=2024 && año <= 2027 && mes>=0 &&mes<12 && estrato>=0 && estrato<4){
                double tarifa =0;
                double sub=0;
                double costo_subsidio=0;
                switch(año){
                    case 2024:
                         objetoVector.inicializar(precios2024);
                         tarifa = objetoVector.obtenerPrecio(mes);
                         sub = estratoFactores[estrato]*tarifa;
                         costo_subsidio = tarifa-sub;
                         
                        break;
                    case 2025:
                        objetoVector.inicializar(precios2025);
                        tarifa = objetoVector.obtenerPrecio(mes);
                        sub = estratoFactores[estrato]*tarifa;
                        costo_subsidio = tarifa-sub;
                        break;
                    case 2026:
                        objetoVector.inicializar(precios2026);
                        tarifa = objetoVector.obtenerPrecio(mes);
                        sub = estratoFactores[estrato]*tarifa;
                        costo_subsidio = tarifa-sub;
                        break;
                    case 2027:
                        objetoVector.inicializar(precios2027);
                        tarifa = objetoVector.obtenerPrecio(mes);
                        sub = estratoFactores[estrato]*tarifa;
                        costo_subsidio = tarifa-sub;
                        break;     
                }
                  JOptionPane.showMessageDialog(null, "La tarifa para " + mesStr + "/" + añoStr + " es: " + costo_subsidio);
                  return costo_subsidio;
            }else{
                 JOptionPane.showMessageDialog(null, "Año o mes inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return -1;
       }
      
       
 
    
}

  


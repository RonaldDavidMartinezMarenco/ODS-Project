
package energywiseproyecto;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EnergyWiseProyecto {

    
    public static void main(String[] args) {
      
        ImageIcon icono = null;
        
        try {
            icono = new ImageIcon(EnergyWiseProyecto.class.getResource("/image/23.png"));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN); // Cambia Color.GREEN por el color que desees
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
        NumberFormat formateadorMoneda = NumberFormat.getCurrencyInstance();

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
                        String valorFormateado = formateadorMoneda.format(valor);

                        JOptionPane.showMessageDialog(null, valorFormateado, "Precio a pagar:", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 1://Lectura
                        String lecturaActual = JOptionPane.showInputDialog(null, "Ingrese la lectura actual de su contador:");
                        double lecturaActualKWh = Double.parseDouble(lecturaActual); // Cantida de kwh
                        String lecturaAnterior = JOptionPane.showInputDialog(null, "Ingrese la lectura anterior de su contador:");
                        double lecturaAnteriorKWh = Double.parseDouble(lecturaAnterior); // Cantida de kwh
                        
                        double consumo2 = lecturaActualKWh-lecturaAnteriorKWh;
                        double valor2= consumo2*tarifaEstrato;
                        String valorFormateado1 = formateadorMoneda.format(valor2);
                        JOptionPane.showMessageDialog(null, valorFormateado1, "Precio a pagar", JOptionPane.INFORMATION_MESSAGE);    
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                    }
                    
                    break;
                case 1: // Calcular consumo de electrodomésticos
                    
                    electrodomesticos objetoElectrodomesticos = new electrodomesticos(100);
                    String electrodomesticos;
                    String[] electrodomesticosLista = {
                        "Refrigerador", "Estufa (gas)", "Estufa (eléctrica)", "Estufa (inducción)",
                        "Horno (eléctrico)", "Horno (gas)", "Microondas", "Lavavajillas", 
                        "Campana extractora", "Licuadora", "Batidora", "Cafetera (goteo)",
                        "Cafetera (espresso)", "Cafetera (cápsulas)", "Tostadora", "Hervidor eléctrico",
                        "Olla arrocera", "Freidora (aceite)", "Freidora (aire)", "Horno tostador",
                        "Procesador de alimentos", "Exprimidor de cítricos", "Máquina para hacer pan",
                        "Yogurtera", "Lavadora", "Secadora de ropa", "Plancha", "Centro de planchado",
                        "Aspiradora (convencional)", "Aspiradora (escoba)", "Aspiradora (robot)",
                        "Vaporeta", "Secador de pelo", "Plancha de pelo", "Afeitadora eléctrica",
                        "Depiladora eléctrica", "Cepillo de dientes eléctrico", "Irrigador bucal",
                        "Televisor", "Sistema de sonido", "Reproductor de DVD/Blu-ray",
                        "Consola de videojuegos", "Proyector", "Aire acondicionado", "Ventilador (de pie)",
                        "Ventilador (techo)", "Ventilador (sobremesa)", "Calefactor (eléctrico)",
                        "Calefactor (gas)", "Calefactor (aceite)", "Humidificador", "Deshumidificador",
                        "Purificador de aire", "Calentador de agua (eléctrico)", "Calentador de agua (gas)",
                        "Bomba de agua", "Teléfono inalámbrico", "Intercomunicador", "Abrepuertas eléctrico",
                        "Alarma", "Cámara de seguridad", "Termostato inteligente", "Enchufe inteligente","Bombillo"
                    };
                 do{
                    String[] electrodomesticosOpciones = {"Agregar Electrodomestico","Calcular Consumo","Eliminar Electrodomestico","Actualizar Electrodomestico","Mostrar Electrodomestico","Insertar mediante indice","Volver"};
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
                            String electrodomesticoSeleccionado = (String) JOptionPane.showInputDialog(
                                null, 
                                "Selecciona un electrodoméstico:", 
                                "Electrodomésticos", 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, 
                                electrodomesticosLista, 
                                electrodomesticosLista[0]
                                );
                                
                                
                            if(electrodomesticoSeleccionado!=null){                   
                            double consumo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el consumo en kWh:"));
                            objetoElectrodomesticos.insertarElectrodomestico(objetoElectrodomesticos.n, electrodomesticoSeleccionado, consumo);
                            }else{
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
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
                                        String valorFormateado2 = formateadorMoneda.format(valorIndividual);
                                        consumoTotal+=consumoIndividual;
                                        costoTotal += valorIndividual;
                                        mensaje.append(nombres[i])
                                        .append(": ")
                                        .append(consumoIndividual)
                                        .append(" kWh - Valor: ")
                                        .append(valorFormateado2)
                                        .append("\n");
                                    }
                                    String valorFormateadoCostoTotal = formateadorMoneda.format(costoTotal);
                                    mensaje.append("\nConsumo total: ").append(consumoTotal).append(" kWh");
                                    mensaje.append("\nCosto total: ").append(valorFormateadoCostoTotal);
                                    JOptionPane.showMessageDialog(null, mensaje.toString());
                                }
                            }break;
                      
                        
                        case 2:
                            int indiceEliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "[0,1,2,3,4,5...]Ingrese el índice del electrodoméstico a eliminar:"));
                            objetoElectrodomesticos.eliminarElectrodomestico(indiceEliminar);
                            break;
                        case 3:
                            int indiceActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "[0,1,2,3,4,5...]Ingrese el índice del electrodoméstico a actualizar:"));
                            String electrodomesticoSeleccionado1 = (String) JOptionPane.showInputDialog(
                                null, 
                                "Selecciona un electrodoméstico:", 
                                "Electrodomésticos", 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, 
                                electrodomesticosLista, 
                                electrodomesticosLista[0]
                                );
                                
                                
                            if(electrodomesticoSeleccionado1!=null){   
                            double nuevoConsumo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo consumo en kWh:"));
                            objetoElectrodomesticos.actualizarElectrodomestico(indiceActualizar, electrodomesticoSeleccionado1, nuevoConsumo);
                            
                            }else{
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            break;
                        case 4:
                            objetoElectrodomesticos.mostrarTodosLosElectrodomesticos();
                            break;
                        case 5:
                            int indiceInsertar =Integer.parseInt(JOptionPane.showInputDialog(null, "[0,1,2,3,4,5...]Ingrese el índice donde desea insertar el electrodomestico"));
                            String electrodomesticoSeleccionado2 = (String) JOptionPane.showInputDialog(
                                null, 
                                "Selecciona un electrodoméstico:", 
                                "Electrodomésticos", 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, 
                                electrodomesticosLista, 
                                electrodomesticosLista[0]
                                );
                                
                                
                            if(electrodomesticoSeleccionado2!=null){   
                            double nuevoConsumo2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo consumo en kWh:"));
                            objetoElectrodomesticos.insertarElectrodomestico(indiceInsertar, electrodomesticoSeleccionado2, nuevoConsumo2);
                            
                            }else{
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case 6:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                    }
                     System.out.println(tarifaEstrato);
                 }while(opcion !=6);
                    
                    
                    break;
                case 2: // Consejos
                    System.out.println(tarifaEstrato);
                    mostrarConsejos();
                    break;
                case 3:
                    String[] opcionesT = {"2024", "2025", "2026","2027"};
                    opcion = JOptionPane.showOptionDialog(
                        null, 
                        "Seleccione el año de las tarifas que desea ver", 
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
                default:
                     JOptionPane.showMessageDialog(null, "Opción inválida.");
                    
                    break;
            }
        } while (opcion != 4); // Salir cuando se elige la opción "Salir" (índice 4)
    }
  
       public static void mostrarConsejos() {
           JTextArea textArea = new JTextArea(10, 40);
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
                          "11.Usa electrodomesticos con categoria A++.\n"+
                          "12.Mide el tiempo de uso de los electrodomesticos al momento del uso.\n"+
                          "\n"+"CONSEJOS COCINA:\n"+"1.Cocina con tapas: Al cocinar con ollas y sartenes tapadas, reduces el tiempo de cocción y el consumo de energía.\n"+
                           "2.Usa el microondas: El microondas consume menos energía que el horno convencional para calentar o cocinar pequeñas porciones.\n"+
                           "3.Descongela los alimentos en el refrigerador: Evita usar el microondas o agua caliente para descongelar alimentos, ya que consume energía adicional.\n"+
                           "4.Aprovecha el calor residual: Apaga el horno o la estufa unos minutos antes de terminar la cocción y aprovecha el calor residual para finalizar el proceso.\n"+
                           "5.No abras el horno innecesariamente: Cada vez que abres el horno, se pierde calor y se necesita más energía para volver a calentarlo.\n"+
                           "6. Mantén limpios los filtros de la campana extractora: Los filtros sucios reducen la eficiencia de la campana y aumentan el consumo de energía.\n"+
                           "\n"+"Consejos LAVANDERIA:\n"+"1.Lava con agua fría: La mayor parte de la energía que consume la lavadora se utiliza para calentar el agua. Lavar con agua fría puede ahorrar hasta un 90% de energía.\n"+
                           "2.Seca la ropa al aire libre: Aprovecha los días soleados para secar la ropa al aire libre en lugar de usar la secadora.\n"+
                           "3.Llena la lavadora: Lava cargas completas en lugar de varias cargas pequeñas para optimizar el consumo de agua y energía.\n"+
                           "4.Limpia el filtro de la secadora: Un filtro obstruido reduce la eficiencia de la secadora y aumenta el tiempo de secado.\n"+
                           "\n"+"Consejos ILUMINACION Y ELECTRONICA:\n"+"1.Usa regletas con interruptor: Conecta varios dispositivos a una regleta y apágala cuando no los estés utilizando para evitar el consumo en modo de espera.\n"+
                            "2.Ajusta el brillo de las pantallas: Reduce el brillo de las pantallas de tus dispositivos electrónicos para ahorrar energía.\n"+
                            "3.Utiliza sensores de movimiento para la iluminación exterior: Los sensores de movimiento encienden las luces solo cuando es necesario, ahorrando energía.\n"+
                            "4.Apaga los dispositivos electrónicos completamente: No los dejes en modo de espera, ya que siguen consumiendo energía."+
                            "\n"+"Consejos CALEFACCION Y REFRIGERACION:\n"+"1.Utiliza cortinas y persianas: Cierra las cortinas y persianas durante el día en verano para bloquear el calor del sol y mantener la casa fresca. Ábrelas en invierno para aprovechar el calor del sol y reducir la necesidad de calefacción.\n"+
                             "2.Ventila la casa en las horas más frescas: Abre las ventanas por la mañana temprano o por la noche para refrescar la casa de forma natural.\n"+
                             "3.Mantén limpios los filtros del aire acondicionado: Los filtros sucios reducen la eficiencia del aire acondicionado y aumentan el consumo de energía.\n"+
                             "4.No bloquees los radiadores: Asegúrate de que los radiadores estén despejados para que el calor circule libremente.\n"+
                             "\n"+"Otros Consejos:\n"+"1.Planta árboles alrededor de tu casa: Los árboles pueden proporcionar sombra en verano y reducir la necesidad de aire acondicionado.\n"+
                              "2.Utiliza un cargador solar para tus dispositivos: Si pasas tiempo al aire libre, un cargador solar puede ser una excelente opción para cargar tus dispositivos sin consumir electricidad de la red.\n"+
                              "3.Realiza un mantenimiento regular de tus electrodomésticos: Asegúrate de que tus electrodomésticos estén funcionando correctamente para evitar un consumo excesivo de energía.\n"+
                              "4.Utiliza paneles solares para ahorrar energía y realizar una inversión.\n"+
                              "5.Revisa tu factura de electricidad: Analiza tu factura con el Software EnergyWise, para identificar de donde proviene el consumo de tu energia.";
        textArea.setText(consejos); 
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
                                  

        JOptionPane.showMessageDialog(null, scrollPane, "Consejos para ahorrar energia", JOptionPane.INFORMATION_MESSAGE);
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

  


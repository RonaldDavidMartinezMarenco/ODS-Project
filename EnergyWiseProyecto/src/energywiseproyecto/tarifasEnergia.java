
package energywiseproyecto;

import java.util.Arrays;
import javax.swing.JOptionPane;


public class tarifasEnergia {
    private double[] precios;
    public int n; // Número de elementos actuales
    public int capMax; // Capacidad máxima del vector
    
    public tarifasEnergia(int tamaño) {
        
    precios = new double[tamaño];
    this.capMax = tamaño;
    this.n=precios.length;
    }
    public void inicializar (double[]precio){
        for(int i=0;i<n;i++){
            precios[i] = precio[i];
        }
    }
    
    public double obtenerPrecio(int indice) {
        if (indice >= 0 && indice < n) {
            return precios[indice];
        } else {
            System.out.println("Error: Índice fuera de rango.");
            return -1; // Valor de error
        }
    }
  
     public void imprimirPrecios() {
        System.out.println(Arrays.toString(precios));
    } 
     public double calcularPromedio() {
        if (n == 0) {
            return 0;
        }

        double suma = 0;
        for (int i = 0; i < n; i++) {
            suma += precios[i];
        }
        return suma / n;
    } 
     // CRUD MOSTRAR
    public void mostrarTodosLosPrecios() {
       if (n == 0) {
        JOptionPane.showMessageDialog(null, "No hay precios registrados.");
        return;
    }

        String mensaje = "Precios:\n";
        for (int i = 0; i < n; i++) {
            mensaje += "Mes " + (i + 1) + ": " + precios[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
     // CRUD - Update (Actualizar)
    public void actualizarPrecio(int indice, double nuevoPrecio) {
        if (indice >= 0 && indice < n) {
            precios[indice] = nuevoPrecio;
        } else {
            System.out.println("Error: Índice fuera de rango.");
        }
    }
    //CRUD - Eliminar
     public void eliminarPrecio(int indice) {
        if (indice >= 0 && indice < n) {
            for (int i = indice; i < n - 1; i++) {
                precios[i] = precios[i + 1];
            }
            n--;
        } else {
            System.out.println("Error: Índice fuera de rango.");
        }
      
    }
    //CRUD -INSERTAR
     
     public void insertarPrecio(int indice, double nuevoPrecio) {
        if (indice < 0 || indice > n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        // Desplazar elementos hacia la derecha para hacer espacio
        for (int i = n; i > indice; i--) {
            precios[i] = precios[i - 1];
        }

        precios[indice] = nuevoPrecio;
        n++;
    }
    
    
    
    

    
    
}

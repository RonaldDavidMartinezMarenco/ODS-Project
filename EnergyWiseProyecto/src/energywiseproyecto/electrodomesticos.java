
package energywiseproyecto;

import javax.swing.JOptionPane;


public class electrodomesticos {
    private String nombresElectrodomesticos[];
    private double []consumos;
    public int n; // Número de elementos actuales
    public int capMax; // Capacidad máxima del vector
    
    public electrodomesticos(int tamaño){
        nombresElectrodomesticos = new String [tamaño];
        consumos = new double [tamaño];
        this.capMax = tamaño;
        this.n=0; //Inicialmente no hay elementos.
    }
    // CRUD MOSTRAR
    public void mostrarTodosLosElectrodomesticos() {
        if (n == 0) {
        JOptionPane.showMessageDialog(null, "No hay electrodomesticos registrados.");
        return;
     
    }
        String mensaje = "Electrodomesticos:\n";

        System.out.println("Electrodomésticos y sus consumos:");
        for (int i = 0; i < n; i++) {
           mensaje+=(i + 1) + ". " + nombresElectrodomesticos[i] + " - " + consumos[i] + " kWh";
        }
         JOptionPane.showMessageDialog(null, mensaje);
    }
     // CRUD - Update (Actualizar)
    public void actualizarElectrodomestico(int indice, String nuevoNombre,double nuevoConsumo) {
        // Validación de índice
      if (indice < 0 || indice >= n) {
          throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
      }

      // Validación de nombre (opcional)
      if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
          throw new IllegalArgumentException("El nuevo nombre del electrodoméstico no puede estar vacío.");
      }

      // Validación de consumo (opcional)
      if (nuevoConsumo <= 0) {
          throw new IllegalArgumentException("El nuevo consumo debe ser un valor positivo.");
      }

      // Actualizar los valores
      nombresElectrodomesticos[indice] = nuevoNombre;
      consumos[indice] = nuevoConsumo;
    }
    //CRUD - Eliminar
     public void eliminarElectrodomestico(int indice) {
        // Validación de índice
        if (indice < 0 || indice >= n) { // Usamos >= en lugar de > porque el índice máximo válido es n-1
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

        // Desplazar elementos hacia la izquierda
        for (int i = indice; i < n - 1; i++) {
            nombresElectrodomesticos[i] = nombresElectrodomesticos[i + 1];
            consumos[i] = consumos[i + 1];
        }

        // Limpiar la última posición (opcional)
        nombresElectrodomesticos[n - 1] = null;
        consumos[n - 1] = 0.0;

        // Disminuir el contador de elementos
        n--;
      
    }
    //CRUD -INSERTAR
     
     public void insertarElectrodomestico(int indice, String nombre,double consumo) {
            // Validación de índice
       if (indice < 0 || indice > n) {
           throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
       }

       // Validación de capacidad
       if (n == nombresElectrodomesticos.length) {
           throw new IllegalStateException("Arreglo lleno, no se pueden insertar más electrodomésticos.");
       }

       // Validación de nombre (opcional)
       if (nombre == null || nombre.trim().isEmpty()) {
           throw new IllegalArgumentException("El nombre del electrodoméstico no puede estar vacío.");
       }

       // Validación de consumo (opcional)
       if (consumo <= 0) {
           throw new IllegalArgumentException("El consumo debe ser un valor positivo.");
       }

       // Desplazar elementos hacia la derecha desde el final
       for (int i = n; i > indice; i--) {
           nombresElectrodomesticos[i] = nombresElectrodomesticos[i - 1];
           consumos[i] = consumos[i - 1];
       }

       // Insertar el nuevo electrodoméstico
       nombresElectrodomesticos[indice] = nombre;
       consumos[indice] = consumo;
       n++;

    }
    public String[] getNombresElectrodomesticos() {
        return nombresElectrodomesticos;
    }

    public double[] getConsumos() {
        return consumos;
    }

    

        
 
}

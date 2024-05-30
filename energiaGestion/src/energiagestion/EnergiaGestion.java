package energiagestion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;

public class EnergiaGestion extends Application {

    private Stage primaryStage;
    private double precioTarifa = 0.0; // Precio por kWh de la tarifa seleccionada

    private double[] precios2024 = {1165.56, 1164.92, 1211.29, 1172.07, 1192.21095, 1208.75404, 1225.29713, 1241.84022, 1258.38331, 1274.9264, 1291.469489, 1308.012579};
    private double[] precios2025 = {1324.555669, 1341.098759, 1357.641849, 1374.184939, 1390.728029, 1407.271119, 1423.814209, 1440.357299, 1456.900389, 1473.443478, 1489.986568, 1506.529658};
    private double[] precios2026 = {1523.072748, 1539.615838, 1556.158928, 1572.702018, 1589.245108, 1605.788198, 1622.331288, 1638.874377, 1655.417467, 1671.960557, 1688.503647, 1705.046737};
    private double[] precios2027 = {1721.589827, 1738.132917, 1754.676007, 1771.219097, 1787.762187, 1804.305276, 1820.848366, 1837.391456, 1853.934546, 1870.477636, 1887.020726, 1903.563816};
    private double[] factoresEstrato = {0.599998, 0.5, 0.15, 0.0};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("EnergyWise");

        showMainMenu();
        primaryStage.show();
    }

    private void showMainMenu() {
        VBox mainMenuBox = new VBox(10);
        mainMenuBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Bienvenido a EnergyWise");
        Button startButton = new Button("Comenzar");
        startButton.setOnAction(e -> showEnergyCalculator());

        mainMenuBox.getChildren().addAll(titleLabel, startButton);

        Scene mainMenuScene = new Scene(mainMenuBox, 400, 300);
        primaryStage.setScene(mainMenuScene);
    }
private void showEnergyCalculator() {
    VBox calculatorBox = new VBox(10);
    calculatorBox.setAlignment(Pos.CENTER);

    ComboBox<String> yearComboBox = new ComboBox<>(FXCollections.observableArrayList("2024", "2025", "2026", "2027"));
    yearComboBox.setPromptText("Seleccione el año");

    ComboBox<String> monthComboBox = new ComboBox<>(FXCollections.observableArrayList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
    monthComboBox.setPromptText("Seleccione el mes");

    TextField consumptionField = new TextField();
    consumptionField.setPromptText("Ingrese el consumo (kWh)");

    ComboBox<String> stratumComboBox = new ComboBox<>(FXCollections.observableArrayList("1", "2", "3", "4"));
    stratumComboBox.setPromptText("Seleccione el estrato");

    Button calculateButton = new Button("Calcular");
    calculateButton.setOnAction(e -> {
        calculateEnergyCost(yearComboBox.getValue(), monthComboBox.getValue(), consumptionField.getText(), stratumComboBox.getValue());
        // Configuración del precio de la tarifa para el cálculo del electrodoméstico
        precioTarifa = Double.parseDouble(consumptionField.getText()); // Precio por kWh
    });

    Button adviceButton = new Button("Consejos para Ahorrar Energía");
    adviceButton.setOnAction(e -> showEnergySavingTips());

    Button applianceButton = new Button("Calcular Electrodoméstico");
    applianceButton.setOnAction(e -> showApplianceCalculation());

    calculatorBox.getChildren().addAll(new Label("Calculadora de Consumo de Energía"), yearComboBox, monthComboBox, consumptionField, stratumComboBox, calculateButton, adviceButton, applianceButton);

    Scene calculatorScene = new Scene(calculatorBox, 400, 300);
    primaryStage.setScene(calculatorScene);
}


    private void showEnergySavingTips() {
        VBox tipsBox = new VBox(10);
        tipsBox.setAlignment(Pos.CENTER);
          TextArea tipsArea = new TextArea();
        tipsArea.setEditable(false);
        tipsArea.setWrapText(true);
        tipsArea.setText("Consejos para Ahorrar Energía:\n\n" +
                "1. Utiliza bombillas LED.\n" +
                "2. Desconecta los electrodomésticos en standby.\n" +
                "3. Aprovecha la luz natural.\n" +
                "4. Aísla térmicamente tu hogar.\n" +
                "5. Elige electrodomésticos eficientes.\n" +
                "6. Optimiza el uso de la lavadora.\n" +
                "7. Regula la temperatura de la nevera y el congelador.\n" +
                "8. Instala paneles solares.\n" +
                "9. Apaga las luces cuando no las necesites.\n" +
                "10. Reduce el consumo de agua caliente.");

        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> showEnergyCalculator());

        tipsBox.getChildren().addAll(tipsArea, backButton);

        Scene tipsScene = new Scene(tipsBox, 400, 300);
        primaryStage.setScene(tipsScene);
    }

    private void showApplianceCalculation() {
        VBox applianceBox = new VBox(10);
        applianceBox.setAlignment(Pos.CENTER);

        TextField applianceNameField = new TextField();
        applianceNameField.setPromptText("Nombre del electrodoméstico");

        TextField kWhField = new TextField();
        kWhField.setPromptText("Consumo de kWh");

        TextField hoursUsedField = new TextField();
        hoursUsedField.setPromptText("Horas de uso por día");

        Button calculateButton = new Button("Calcular");
        calculateButton.setOnAction(e -> calculateApplianceCost(applianceNameField.getText(), kWhField.getText(), hoursUsedField.getText()));

        applianceBox.getChildren().addAll(new Label("Cálculo de Costo de Electrodomésticos"), applianceNameField, kWhField, hoursUsedField, calculateButton);

        Scene applianceScene = new Scene(applianceBox, 400, 300);
        primaryStage.setScene(applianceScene);
    }

    private void calculateApplianceCost(String applianceName, String kWhStr, String hoursUsedStr) {
        try {
            double kWh = Double.parseDouble(kWhStr);
            double hoursUsed = Double.parseDouble(hoursUsedStr);

            double totalCost = kWh * hoursUsed * precioTarifa;
            
            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setTitle("Resultado del cálculo");
            resultAlert.setHeaderText(null);
            resultAlert.setContentText(String.format("El costo de energía para %s es: $%.2f", applianceName, totalCost));
            resultAlert.showAndWait();
        } catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Ingrese números válidos para kWh y horas de uso.");
            errorAlert.showAndWait();
        }
    }

    private void calculateEnergyCost(String yearStr, String monthStr, String consumptionStr, String stratumStr) {
        try {
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            double consumption = Double.parseDouble(consumptionStr);
            int stratum = Integer.parseInt(stratumStr);

            double[] precios;
            switch (year) {
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
                    return;
            }
             double preciokwh = precios[month - 1];
            double factorestrato = factoresEstrato[stratum - 1];
            double sub = factorestrato * preciokwh;
            double cossub = preciokwh - sub;
            double totalapagar = cossub * consumption;

            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setTitle("Resultado del cálculo");
            resultAlert.setHeaderText(null);
            resultAlert.setContentText(String.format("El total a pagar por el consumo de energía en %d/%d es: $%.2f", month, year, totalapagar));
            resultAlert.showAndWait();
        } catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Ingrese números válidos para año, mes, consumo y estrato.");
            errorAlert.showAndWait();
        }
    }
}
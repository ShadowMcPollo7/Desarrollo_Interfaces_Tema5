/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author raul-
 */
public class AppInformesViewController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem informe1MenuItem;
    @FXML
    private MenuItem informe2MenuItem;
    @FXML
    private MenuItem informe3MenuItem;
    @FXML
    private MenuItem informe4MenuItem;
    @FXML
    private MenuItem SalirMenuItem;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void desplegarInforme1(ActionEvent event) {
          
        try { 
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("/informes/facturas.jasper"));             
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion); 
            JasperViewer.viewReport(jp,false); 
        } catch (JRException ex) { 
            System.out.println("Error al recuperar el jasper"); 
            JOptionPane.showMessageDialog(null, ex); 
        } 
    
    }

    @FXML
    private void desplegarInforme2(ActionEvent event) {
        
        try { 
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("/informes/Ventas_Totales.jasper"));             
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion); 
            JasperViewer.viewReport(jp,false); 
        } catch (JRException ex) { 
            System.out.println("Error al recuperar el jasper"); 
            JOptionPane.showMessageDialog(null, ex); 
        }
         
    }

    @FXML
    private void desplegarInforme3(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("introducirDatosCliente.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("icon/icon.png"));
        stage.setTitle("FacturasCliente");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void desplegarInforme4(ActionEvent event) {
        
        try { 
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/subinforme_facturas.jasper")); 
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("/informes/subinforme3.jasper"));
            //Map de parámetros 
            Map parametros = new HashMap(); 
            parametros.put("subReportParameter", jsr); 
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion); 
            JasperViewer.viewReport(jp,false); 
        } catch (JRException ex) { 
            System.out.println("Error al recuperar el jasper"); 
            JOptionPane.showMessageDialog(null, ex); 
        }
        
    }
    
    

    @FXML
    private void salirAPPInformes(ActionEvent event) {
        System.exit(0);
    }
    
    
    
}

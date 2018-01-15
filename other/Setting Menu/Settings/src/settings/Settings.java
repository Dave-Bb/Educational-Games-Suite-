/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

/**
 *
 * @author 7M1SRVGT3
 */
public class Settings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GlobalSettingsFrame gsf = new GlobalSettingsFrame("Location","username","password");
        gsf.setVisible(true);
    }
    
}

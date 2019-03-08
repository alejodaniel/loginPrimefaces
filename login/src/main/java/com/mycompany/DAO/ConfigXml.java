package com.mycompany.DAO;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ConfigXml {

    public HashMap<String, String> Conection() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("host", "jdbc:mysql://localhost:3306/login");
        hm.put("user", "root");
        hm.put("password", "123456789");
        return hm;
    }
}

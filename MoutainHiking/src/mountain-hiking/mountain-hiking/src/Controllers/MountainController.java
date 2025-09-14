/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Mountain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hoade
 */
public class MountainController extends ArrayList<Mountain>{
    private final String FILE_PATH = "MountainList.csv";
    
    public ArrayList<Mountain> uploadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 4) {
                    int code = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String province = parts[2].trim();
                    String description = parts[3].trim();
                    Mountain mountain = new Mountain(code, name, province, description);
                    this.add(mountain);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}

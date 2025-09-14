
package Controller;

import Model.Moutain;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class MoutainList extends ArrayList<Moutain> {
    
    public void readFile(String fileName){
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(System.in);
            
            while(sc.hasNextLine()){
                String[] list = sc.nextLine().split(",");

                Moutain moutain = new Moutain(Integer.parseInt(list[0].trim()), list[1].trim(), list[2].trim(), list[3].trim());
                
                this.add(moutain);
            }
            
        } catch (Exception e) {
            System.out.println("Cannor read file " + fileName + ".txt");
        }
    }
}

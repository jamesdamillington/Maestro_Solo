package Maestro;

import java.io.File;
import java.io.IOException;

public class Maestro {

	public static void main(String[] args) throws IOException {
		System.out.println("hi");
		while(true){
			if(new File("C:/Users/k1076631/craftyworkspace/CRAFTY_TemplateCoBRA/data/updated.txt").isFile()){
				
			}
			else{
				File file = new File("C:/Users/k1076631/craftyworkspace/CRAFTY_TemplateCoBRA/data/updated.txt");
				file.createNewFile();
			}
		}
	}

}

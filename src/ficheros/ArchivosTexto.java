package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArchivosTexto {

	public static void main(String[] args) {
	File x = new File("HolaMundo.txt");
	try {
		FileWriter file= new FileWriter(x,true);
		PrintWriter out =new PrintWriter(file);
		out.println("HOla mundo");
		out.close();
		file.close();
		Scanner in = new Scanner(x);
		while(in.hasNextLine())
		System.out.println(in.nextLine());
		in.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}

}

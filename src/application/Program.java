package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter file path: ");
		String file = sc.nextLine();
		
		File path = new File(file);
		
		String dir = path.getParent();
		String fileOut = dir+"\\out\\summary.csv";
		
		if (!new File(dir + "\\out").mkdir()) {
			System.out.println("Falha ao tentar criar o diretório");
		}
		
		List<Product> list = new ArrayList<Product>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String line =  br.readLine();
			while (line !=null) {
				String[] produto = line.split(",");
				String name = produto[0];
				Double price = Double.parseDouble(produto[1]);
				Integer amount = Integer.parseInt(produto[2]);
				
				list.add(new Product(name, price, amount));
				
				line = br.readLine();		
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))){
				for (Product product : list) {
					bw.write(product.toString());
					
				}
			}
			catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}
			
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		sc.close();

	}

}

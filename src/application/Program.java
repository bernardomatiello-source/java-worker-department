package application;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {
	
    public static void main(String[] args) {
    	
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        // Digitar os primeiros dados do trabalhador.
        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();
        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String  workerLevel = sc.nextLine();
        System.out.print("Base salary: ");
        double baseSalary = sc.nextDouble();
        
        // Realizar a criação do objeto "worker" instanciando com o construtor os valores name, lavel, salary e department.
        // Realizando a criação do objeto "worker", estou criando uma referencia para o objeto Department. 
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel.toUpperCase()), baseSalary, new Department(departmentName));
        
        System.out.print("How many contracts to this worker? ");
        int n = sc.nextInt();
        
        // Criar um formato para utilizar no contractDate.
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Ler N contratos do worker, informando os dados desse contrato.
        for (int i = 1; i <= n; i++) {
        	System.out.println("Enter contract #" + i + " data: ");
        	System.out.print("Date (DD/MM/YYYY): ");
        	String contractDate = sc.next();
        	System.out.print("Value per hour: ");
        	double valuePerHour = sc.nextDouble();
        	System.out.print("Duration (hours): ");
        	int hoursDuration = sc.nextInt();
        	
        	// Converter a string "contractDate" para o fmt1.
        	LocalDate date = LocalDate.parse(contractDate, fmt1);
        	
        	// Instanciar um objeto "hourContract" com os dados do contrato utilizando o construtor.
        	HourContract contract = new HourContract(date, valuePerHour, hoursDuration);
        	
        	// Adcionar o objeto "contract" no worker.
        	worker.addContract(contract);
        }
        
        // Criar um formato para utilizar no incomeDate.
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("MM/yyyy");
        
        System.out.println();
        
        // Perguntar ao usuário a renda de uma data especifica.
        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        String incomeDate = sc.next();
        
        // Converter a string "incomeDate" para o fmt2.
        YearMonth date = YearMonth.parse(incomeDate, fmt2);
        
        // Atribuir os valores da data a outras variaveis para facilitar a impressão.
        int month = date.getMonthValue();
        int year = date.getYear();
        
        // Mostrar ao usuário os dados do trabalhador.
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        
        // Definir a renda em uma variavel para facilitar impressão.
        double income = worker.income(month, year);
        
        // Mostrar ao usuário a renda somando o mês do contrato + o salario base.
        System.out.println("Income for " + incomeDate + ": " + String.format("%.2f", income));
  
        sc.close();
    }
}

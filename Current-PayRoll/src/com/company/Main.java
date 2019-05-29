package com.company;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int i;

    public static void main(String[] args) {
        String[][] menu = new String[100][12];
        String[][][] undo = new String[50][100][12];
        String[][][] redo = new String[50][100][12];

        int i = 0;
        String test = "0";
        String second = "11";
        int zero = Integer.parseInt(test);
        System.out.println(zero);
//        while(true){
//            menu();
//            int op = input.nextInt();
//            switch (op){
//                case 0:
//                    break;
//                case 1:
//                    createEmployee(menu);
//                    i++;
//                    break;
//            }
//        }
    }
    public static void createEmployee(String[][] employee){
        System.out.println("Type the attributes of Employee: ");
        System.out.print("Name: ");
        employee[i][0] = input.nextLine(); // Name
        employee[i][1]= Integer.toString(++i); // ID
        System.out.print("Address- Street: ");
        employee[i][2]= input.nextLine(); // Street Address
        System.out.print("Address - number:");
        employee[i][3]= input.nextLine(); // Number Address
        System.out.print("Type: \n (1) hourly; \n (2) salaried; \n ");
        employee[i][4] = input.nextLine(); // Type
        if(employee[i][4].equals('2')){
            System.out.println("The Employee is Commissioned? [ (1)YES, (2)NO ]");
            employee[i][5]= input.nextLine();
            if(employee[i][5].equals("1")){
                employee[i][6] = input.nextLine(); // Commision Rate
            }
            else{
                employee[i][6] = "0";
            }
        }
        employee[i][3] = employee[i][2]; // Attribute
        employee[i][4] = Integer.toString(i); // ID
        employee[i][5] = input.nextLine(); // Salary
        employee[i][6] = "0"; // Total Time

    }
    public static void menu(){
        System.out.println("\nIndex of Options");
        System.out.println("(1) Add an Employee");
        System.out.println("(2) Remove an Employee");
        System.out.println("(3) Launch a Card Point");
        System.out.println("(4) Launch a Sale Result");
        System.out.println("(5) Launch a Service Rate");
        System.out.println("(6) Change Employee's data");
        System.out.println("(7) Run Payment Roll for today");
        System.out.println("(8) Undo/Redo");
        System.out.println("(9) Payment Schedule");
        System.out.println("(10) Creat New Payment Schedule");
        System.out.println("(0) End");
    }
}
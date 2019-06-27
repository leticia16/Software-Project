package main;
import Employee.*;
import utils.*;

import menu.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    Menu menu = new Menu();
    static Scanner input = new Scanner(System.in);
    public static Stack<Menu> undo = new Stack<>();
    public static Stack<Menu> redo = new Stack<>();

    public static void main(String[] args) {
        int option;
        boolean start = true;
        boolean flag = true;

        // MENU OF SYSTEM
        while (flag) {
            start = Menu.showMenuOptions(start);
            option = Exceptions.inputIntegerBounds(0, 12);
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:

                    Menu.addEmployee(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    System.out.println("successful operation");
                    break;
                case 2:
                    Menu.removeEmployee(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 3:
                    Menu.launchCardPoint(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 4:
                    Menu.launchSalesResult(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 5:
                    Menu.launchServiceRate(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 6:
//                  Menu.editEmployee();
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 7:
                    Menu.payRoll(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
                case 8: //Undo
                    ArrayList<Employee> copiedList = new ArrayList<Employee>();
                    copyArrayList(employeeList, copiedList);
                    redo.push(copiedList);
                    ArrayList<Employee> aux;
                    aux = undo.pop();
                    copyArrayList(aux, employeeList);
                    copySystemDate(systemDateStackUndo.pop());
                    break;
                case 9: //Redo
                    ArrayList<Employee> copiedList2 = new ArrayList<Employee>();
                    copyArrayList(employeeList, copiedList2);
                    undo.push(copiedList2);
                    ArrayList<Employee> poppedByRedo = redo.pop();
                    copyArrayList(poppedByRedo, employeeList);
                    copySystemDate(systemDateStackRedo.pop());
                    break;
                case 11:
                    Menu.printEmployeeList(employeeList);
                    break;
                case 12:
                    Menu.nextDay(employeeList);
                    undo.push(employeeList);
                    systemDateStackUndo.push(systemDate);
                    break;
            }
        }

    }

    private  static void copySystemDate(Day original){
        systemDate.setMonth(original.getMonth(true));
        systemDate.setYear(original.getYear());
        systemDate.setDay(original.getDay());
        systemDate.setWeekDay(original.getWeekDay(true));
    }


    public static void copyArrayList(ArrayList<Employee> original){
        for (Employee e: employeeList) { // Cleaning employeeList
            employeeList.remove(e);
        }
        for (Employee e: original) {
            System.out.println(e);
            if(e instanceof Hourly){
                Employee copy = new Hourly(e);
                copy.agenda.setWeekly(1,6);
                copy.agenda.setWeeDay(e.agenda.getWeeDay());
                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());

                employeeList.add(copy);
            }

            if(e instanceof Commissioned){
                Employee copy = new Commissioned(e);
                copy.agenda.setWeekly(2,6);
                copy.agenda.setWeeDay(e.agenda.getWeeDay());
                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());

                employeeList.add(copy);
            }

            if (e instanceof Salaried){
                Employee copy = new Salaried(e);
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthly(0);
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
                employeeList.add(copy);
            }

        }



    }
    public static void copyArrayList(ArrayList<Employee> original, ArrayList<Employee> copied){
        for (Employee e: copied) { // Cleaning employeeList
            copied.remove(e);
        }
        for (Employee e: original) {
//            System.out.println(e);
            if(e instanceof Hourly){
                Employee copy = new Hourly(e);
                copy.agenda.setWeekly(1,6);
                copy.agenda.setWeeDay(e.agenda.getWeeDay());
                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());

                copied.add(copy);
            }

            if(e instanceof Commissioned){
                Employee copy = new Commissioned(e);
                copy.agenda.setWeekly(2,6);
                copy.agenda.setWeeDay(e.agenda.getWeeDay());
                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());

                copied.add(copy);
            }

            if (e instanceof Salaried){
                Employee copy = new Salaried(e);
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthly(0);
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
                copied.add(copy);
            }

        }



    }
}

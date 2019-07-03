package main;
import Employee.*;
import utils.*;

import menu.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        Stack<Menu> undo = new Stack<Menu>();
        Stack<Menu> redo = new Stack<Menu>();
        ArrayList<int[]> agendasList = new ArrayList<int[]>();
        Menu menu = new Menu();
        int option;
        boolean start = true;
        boolean flag = true;
        int[] new_agenda = {1,1,6,1};
        agendasList.add(new_agenda);
        int[] new_agenda_2 = {1,2,6,2};
        agendasList.add(new_agenda_2);
        int[] new_agenda_3 = {2,0,0,3};
        agendasList.add(new_agenda_3);
        // MENU OF SYSTEM
        while (flag) {
            start = menu.showMenuOptions(start);
            option = Exceptions.inputIntegerBounds(0, 14);
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    undo.push(menu.copyMenu(menu));
//                    menu.printEmployeeList(true);
                    menu.addEmployee();
                    break;
                case 2:
                    undo.push(menu);
                    menu.removeEmployee();
                    break;
                case 3:
                    undo.push(menu);
                    menu.launchCardPoint();
                    break;
                case 4:
                    undo.push(menu);
                    menu.launchSalesResult();
                    break;
                case 5:
                    undo.push(menu);
                    menu.launchServiceRate();
                    break;
                case 6:
                    undo.push(menu);
                    menu.editEmployee();
                    break;
                case 7:
                    undo.push(menu);
                    menu.payRoll();
                    break;
                case 8: //Undo
//                    menu = new Menu();
//                    System.out.println("--> testing new Menu()");
//                    for (Employee e: menu.employeeList) {
//                        System.out.println(e.getName());
//                        System.out.println(e.getEmployeeType());
//                    }
                    Menu menuRedo = new Menu();
                    menuRedo = menuRedo.copyMenu(menu);
                    redo.push(menuRedo);
                    Menu menuUndo = undo.pop();
//                    menu = new Menu(menuUndo.systemDate, menuUndo.employeeList);
                    menu = menu.copyMenu(menuUndo);
                    for (Employee e: menu.employeeList) {
                        System.out.println(e.getName());
                        System.out.println(e.getEmployeeType());
                    }
//                    menuUndo =
//                    menu.copyMenu(menuUndo, menu);
                    break;
                case 9: //Redo
                    Menu menuRedo_ = new Menu();
                    Menu menuUndo_ = new Menu();
//                    menuUndo_
                    undo.push(menuUndo_);
                    menuRedo_ = redo.pop();
                    menu.copyMenu(menu);
                    break;
                case 11:
                    menu.printEmployeeList();
                    break;
                case 12:
                    undo.push(menu);
                    menu.nextDay();
                    break;
                case 13:
                    menu.chooseADifferentAgenda(agendasList);
                   break;
                case 14:
                    menu.newAgenda(agendasList);
                    break;
            }
        }

    }

//    private  static void copySystemDate(Day original){
//        systemDate.setMonth(original.getMonth(true));
//        systemDate.setYear(original.getYear());
//        systemDate.setDay(original.getDay());
//        systemDate.setWeekDay(original.getWeekDay(true));
//    }


//    public static void copyArrayList(ArrayList<Employee> original){
//        for (Employee e: employeeList) { // Cleaning employeeList
//            employeeList.remove(e);
//        }
//        for (Employee e: original) {
//            System.out.println(e);
//            if(e instanceof Hourly){
//                Employee copy = new Hourly(e);
//                copy.agenda.setWeekly(1,6);
//                copy.agenda.setWeeDay(e.agenda.getWeeDay());
//                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
//                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//
//                employeeList.add(copy);
//            }
//
//            if(e instanceof Commissioned){
//                Employee copy = new Commissioned(e);
//                copy.agenda.setWeekly(2,6);
//                copy.agenda.setWeeDay(e.agenda.getWeeDay());
//                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
//                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//
//                employeeList.add(copy);
//            }
//
//            if (e instanceof Salaried){
//                Employee copy = new Salaried(e);
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthly(0);
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//                employeeList.add(copy);
//            }
//
//        }



//    }
//    public static void copyArrayList(ArrayList<Employee> original, ArrayList<Employee> copied){
//        for (Employee e: copied) { // Cleaning employeeList
//            copied.remove(e);
//        }
//        for (Employee e: original) {
////            System.out.println(e);
//            if(e instanceof Hourly){
//                Employee copy = new Hourly(e);
//                copy.agenda.setWeekly(1,6);
//                copy.agenda.setWeeDay(e.agenda.getWeeDay());
//                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
//                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//
//                copied.add(copy);
//            }
//
//            if(e instanceof Commissioned){
//                Employee copy = new Commissioned(e);
//                copy.agenda.setWeekly(2,6);
//                copy.agenda.setWeeDay(e.agenda.getWeeDay());
//                copy.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator());
//                copy.agenda.setWeeklyType(e.agenda.getWeeklyType());
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//
//                copied.add(copy);
//            }
//
//            if (e instanceof Salaried){
//                Employee copy = new Salaried(e);
//                copy.agenda.setPayType(e.agenda.getPayType());
//                copy.agenda.setMonthly(0);
//                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
//                copy.agenda.setFlag(e.agenda.getFlag());
//                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
//                copied.add(copy);
//            }
//
//        }
//
//
//
//    }
}

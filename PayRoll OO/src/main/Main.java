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
//
                    Menu menuRedo = new Menu();
                    menuRedo = menuRedo.copyMenu(menu);
                    redo.push(menuRedo);
                    Menu menuUndo = undo.pop();
                    menu = menu.copyMenu(menuUndo);
                    break;
                case 9: //Redo
                    Menu menuRedo_ = new Menu();
                    Menu menuUndo_ = new Menu();
//                    menuUndo_
                    undo.push(menuUndo_);
                    menuRedo_ = redo.pop();
                    menu.copyMenu(menu);
                    break;
                case 10:
                    menu.printEmployeeList();
                    break;
                case 11:
                    undo.push(menu);
                    menu.nextDay();
                    break;
                case 12:
                    menu.chooseADifferentAgenda(agendasList);
                   break;
                case 13:
                    menu.newAgenda(agendasList);
                    break;
            }
        }

    }

}

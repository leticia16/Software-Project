package menu;

import java.util.ArrayList;
import java.util.Scanner;
import utils.*;
import Employee.*;

public class Menu {
    static Scanner input = new Scanner(System.in);
    public static Day systemDate;
    public static ArrayList<Employee> employeeList = new ArrayList<Employee>();

    public Menu() {
        this.systemDate = new Day();
        this.employeeList = new ArrayList<Employee>();
    }

    public static void addEmployee(){
        System.out.println("|||| ADD EMPLOYEE");
        System.out.println("Select the Employee type:");
        System.out.println("(1) Hourly | (2) Commissioned | (3) Salaried");
        int op = Exceptions.inputIntegerBounds(1,3);
        switch (op){
            case 1:
                Employee newHourly = new Hourly(systemDate, employeeList.size()+1);
                employeeList.add(newHourly);
                break;
            case 2:
                Employee newCommissioned = new Commissioned(systemDate, employeeList.size()+1);
                employeeList.add(newCommissioned);
                break;
            case 3:
                Employee newSalaried = new Salaried(systemDate,employeeList.size()+1);
                employeeList.add(newSalaried);
                break;
        }
//        printEmployeeList();
    }
    public static void removeEmployee(){
        int aux;
        boolean alert = true;
        System.out.println("|||| REMOVE EMPLOYEE");
        System.out.println("--> Employee List:");
        printEmployeeList(true);
        System.out.println("--> Id of Employee: ");
        int id = Exceptions.inputInteger();
        for (Employee e: employeeList) {
            aux = e.getId();
            if(aux == id){
                alert = false;
                employeeList.remove(e);
                break;
            }
        }
        if(alert){
            System.out.println("The employee was not found!");
        }
        else {
            System.out.println("successful operation");
        }
    }

    public static void nextDay(){
        // TO weekDay
//        System.out.println("Day: "+systemDate.getDay());
//        System.out.println("Week Day: "+systemDate.getWeekDay(true));
        int weekDay = systemDate.getWeekDay(true);
        int day = systemDate.getDay();
        int month = systemDate.getMonth(true);
        if(weekDay == 7){
            systemDate.setWeekDay(1);
        }
        if(weekDay >=1 && weekDay < 7){
            systemDate.setWeekDay(weekDay+1);
        }
        // To Day
        if(day == Day.monthsDays[month]){ // Last Month Day
            systemDate.setDay(1);
            if(month == 12){
                systemDate.setMonth(1);
                systemDate.setYear(systemDate.getYear()+1);
            }
            else{
                systemDate.setMonth(systemDate.getMonth(true)+1);
            }
        }
        else {
            systemDate.setDay(systemDate.getDay()+1); // New Day
        }
        for (Employee e:employeeList) {
//            System.out.println("PayType: "+e.agenda.getPayType());
            if(e.agenda.getPayType()==2){
                int payDay = 0;
                int[] array = e.agenda.getMonthsDays();
//                System.out.println("DIfer: "+(array[systemDate.getMonth(true)] - systemDate.getDay()));
//                System.out.println("MDay: "+e.agenda.getMonthlyDay());
//                System.out.println("total months days: "+array[systemDate.getMonth(true)]);
//                System.out.println("Day: "+systemDate.getDay());
                if((array[systemDate.getMonth(true)] - systemDate.getDay()) == 2) {
                    payDay = Agenda.LastWorkDay(systemDate);
                    e.setPayDay(payDay);
                }
            }
        }

    }

    public static void launchCardPoint(){
        int id;
        boolean alert = false;
        boolean flag = true;
        System.out.println("|||| CARD POINT");
        System.out.println("--> Hourly Employee List");
        printEmployeeList(true,1);
        System.out.println("--> ID of Hourly Employee: ");
        while (flag){
            id = Exceptions.inputInteger();
            for (Employee e: employeeList) {
                int aux = e.getId();
                int i = e.getEmployeeType();
                if(aux == id){
                    if(i != 1) {
                        System.out.println("The Employee isn't Hourly, type a valid ID: ");
                        alert = true;
                        break;
                    }
                    else{
                        Hourly b = (Hourly)e;
                        b.setCardPoint(Hourly.makeCardPoint()+ b.getCardPoint());
                        alert = true;
                        flag = false;
                    }
                }
            }
            if(!alert){
                System.out.println("The Employee isn't Hourly!");
                flag = false;
            }
            else {
                System.out.println("successful operation");
            }
        }
    }
    public static void launchSalesResult(){
        int id;
        boolean alert = false;
        boolean flag = true;
        System.out.println("|||| SALES RESULT");
        System.out.println("--> Commissioned Employee List");
        printEmployeeList(true,2);
        System.out.println("--> ID of Commissioned Employee: ");
        while (flag){
            id = Exceptions.inputInteger();
            for (Employee e: employeeList) {
                int aux = e.getId();
                int i = e.getEmployeeType();
                if(aux == id){
                    if(i != 2) {
                        System.out.println("The Employee isn't Commissioned, type a valid ID: ");
                        alert = true;
                        break;
                    }
                    else{
                        Commissioned b = (Commissioned) e;
                        Commissioned.salesResult(systemDate, b);
                        alert = true;
                        flag = false;
                    }
                }
            }
            if(!alert){
                System.out.println("The Employee isn't Commissioned! ");
                flag = false;
            }
            else {
                System.out.println("successful operation");
            }
        }
    }
    public static void launchServiceRate(){ // Syndicate
        boolean flag = true;
        System.out.println("|||| SERVICE RATE - SYNDICATE");
        System.out.println("--> Syndicate Employee List");
        printEmployeeList(0);
        System.out.println("--> Syndicate ID: ");
        while (flag){
           int id = Exceptions.inputInteger();
            for (Employee e: employeeList) {
                int aux = e.getSyndicateID();
                if(aux == id){
                        System.out.println("--> ExtraSyndicateRate (0.0 ~ 1.0): ");
                        e.setExtraSyndicateRate(Exceptions.inputDoubleBounds(0.0,1.0));
                        System.out.println("successful operation");
                        flag = false;
                }
            }
            System.out.println("The Employee isn't part of Syndicate!");
            flag = false;
        }

    }
    public static void editEmployee() {
        boolean flag = true;
        System.out.println("|||| EDIT EMPLOYEE");
        System.out.println("--> Employee List");
        printEmployeeList(true);
        System.out.println("--> ID of Employee: ");
        while (flag) {
            int id = Exceptions.inputInteger();
            for (Employee e : employeeList) {
                int aux = e.getId();
                if (aux == id) {
//                    showEditOptions();
                    int op = Exceptions.inputIntegerBounds(1, 5);
                    switch (op) {
                        case 1:
                            System.out.println("--> Name: ");
                            e.setName(Exceptions.inputString());
                            break;
                        case 2:
                            System.out.println("--> Street Address: ");
                            e.setAdress(Exceptions.inputString());
                            break;
                        case 3:
                            System.out.println("--> Salary: ");
                            e.setGrossSalary(Exceptions.inputDouble());
                            //Salary
                            break;
                        case 4:
                            System.out.println("--> Is employee part of Syndicate?");
                            System.out.println("type:   (0) NO  |  (1) Yes");
                            e.setSyndicate(Exceptions.inputIntegerBounds(0,1));
                            if(e.getSyndicate() == 1){
                                System.out.println("--> Union Fee (0.0 ~ 1.0):");
                                e.setUnionFee(Exceptions.inputDoubleBounds(0.0,1.0));
                                e.setSyndicateID(e.getId()*6);
                            }
                            break;
                        case 5:
                            System.out.println("Select the Employee type:");
                            System.out.println("(1) Hourly | (2) Commissioned | (3) Salaried");
                            int type = Exceptions.inputIntegerBounds(1,3);
//                            switch (type){
//                                case 1:
//                                    Employee newHourly = new Hourly(systemDate, employeeList.size()+1);
//                                    employeeList.add(newHourly);
//                                    break;
//                                case 2:
//                                    Employee newCommissioned = new Commissioned(systemDate, employeeList.size()+1);
//                                    employeeList.add(newCommissioned);
//                                    break;
//                                case 3:
//                                    Employee newSalaried = new Salaried(systemDate,employeeList.size()+1);
//                                    employeeList.add(newSalaried);
//                                    break;
//                            }                            break;
                    }
                    System.out.println("successful operation");
                    flag = false;
                }
            }
            System.out.println("The employee was not found!");
            break;
        }
    }

    public static void payRoll(){
        System.out.println("|||| PAYROLL");
        for (Employee e: employeeList) {
            switch (e.agenda.getPayType()){
                case 1:
//                    System.out.println("case 1");
//                    System.out.println("WT: "+e.agenda.getWeeklyType());
//                    System.out.println("WI: "+e.agenda.getWeeklyIterator());
//                    System.out.println("WD: "+e.agenda.getWeeDay());
//                    System.out.println("system WD: "+systemDate.getWeekDay());
                    if(((e.agenda.getWeeklyType() - e.agenda.getWeeklyIterator()) == 0 ) && e.agenda.getWeeDay() == systemDate.getWeekDay(true)){
//                        System.out.println("Case 1 : Pay()");
                        if(!e.getAlreadyPaid()){
                            pay(e);
                            e.agenda.setWeeklyIterator(1);
                            e.setAlreadyPaid(true);
                        }
                    }
                    else{
                        e.agenda.setWeeklyIterator(e.agenda.getWeeklyIterator()+1);
                    }
                    break;
                case 2:
//                    System.out.println("case 2");
                    int[] array = e.agenda.getMonthsDays();
//                    System.out.println("MDay: "+e.agenda.getMonthlyDay());
//                    System.out.println("total months days: "+array[systemDate.getMonth(true)]);
//                    System.out.println("Day: "+systemDate.getDay());
//                    System.out.println("DIfer: "+(array[systemDate.getMonth(true)] - systemDate.getDay()));
                    if(e.agenda.getMonthlyDay() == 0){ // Last work Day
                        int payDay = 0;
//                        if((array[systemDate.getMonth(true)] - systemDate.getDay()) == 2) {
//                            payDay = Agenda.LastWorkDay(systemDate);
//                            e.setPayDay(payDay);
//                        }
                        if(e.getPayDay() == systemDate.getDay()){
                          if(!e.getAlreadyPaid()){
                              pay(e);
                              e.setAlreadyPaid(true);
                          }
                        }
//                        System.out.println("PayDay: "+e.getPayDay());
                    }
                    if(e.agenda.getMonthlyDay()>=1 && e.agenda.getMonthlyDay()<=array[systemDate.getMonth(true)]){ // Others agendas
                        if(e.agenda.getMonthlyDay() == systemDate.getDay()){
                            if(!e.getAlreadyPaid()){
                                pay(e);
                                e.setAlreadyPaid(true);
                            }
                        }

                }
                    break;
            }
        }
    }

    public static void pay(Employee e){
        int type = e.getEmployeeType();
        switch (type){
            case 1:
                Hourly.payHourly(e);
                break;
            case 2:
                Commissioned.payCommissioned(e);
                break;
            case 3:
                Salaried.paySalaried(e);
                break;
        }
    }

    public static void printEmployeeList(boolean flag){
        if(flag){
            for (Employee a: employeeList) {
                System.out.println("____________________");
                System.out.println("Name: " + a.getName());
                System.out.println("ID: " + a.getId());
            }
        }
        System.out.println("____________________");
    }
    public static void printEmployeeList(boolean flag, int type){
        int aux;
        if(flag){
            for (Employee a: employeeList) {
                aux = a.getEmployeeType();
                if(aux == type) {
                    System.out.println("____________________");
                    System.out.println("Name: " + a.getName());
                    System.out.println("ID: " + a.getId());
                }
            }
        }
        System.out.println("____________________");
    }
    public static void printEmployeeList(int aux){
        for (Employee a: employeeList) {
            aux = a.getSyndicate();
            if(aux == 1) {
                System.out.println("____________________");
                System.out.println("Name: " + a.getName());
                System.out.println("Syndicate ID: " + a.getSyndicateID());
            }
        }
        System.out.println("____________________");
    }




    public static void printEmployeeList(){
        for (Employee a: employeeList) {
            System.out.println("____________________");
            System.out.println("Name: "+a.getName());
            System.out.println("Address: "+a.getAdress());
            System.out.println("ID: "+a.getId());
            System.out.println("Salary: "+a.getGrossSalary());
            System.out.println("Type: "+a.getEmployeeType());
            System.out.println("Syndicate: "+a.getSyndicate());
            System.out.println("Syndicate ID: "+a.getSyndicateID());
            System.out.println("UnionFee: "+a.getUnionFee());
            System.out.println("SyndiRate: "+a.getExtraSyndicateRate());
            //CAST
            switch (a.getEmployeeType()){
                case 1:
                    Hourly aux = (Hourly) a;
                    System.out.println("CardPoint: "+aux.getCardPoint());
                    break;
                case 2:
                    Commissioned aux2 = (Commissioned) a;
                    System.out.println("CommissionRate: "+aux2.comissionRate);
                    System.out.println("Value Sale: "+aux2.getValueSale());
                    System.out.println("Day Sale: "+aux2.getDateSale());
            }

        }
        System.out.println("____________________");
    }
    public  static boolean showMenuOptions(boolean start){
        System.out.println("___________________________________________________________");
        System.out.println(" ||||||||||||||||||    PAYROLL SYSTEM    ||||||||||||||||||");
        if(start){
            start = false;
            systemDate = Day.SystemDate();
            System.out.println("___________________________________________________________");
        }
        System.out.println("    "+ systemDate.getWeekDay() +", "+ systemDate.getMonth()+" "+ systemDate.getDay()+" of "+  systemDate.getYear()+ "     ");
        System.out.println(" %%%%%  System Features  %%%%%% ");
        System.out.println("(1)  Add Employee");
        System.out.println("(2)  Remove Employee");
        System.out.println("(3)  Launch a Card Point");
        System.out.println("(4)  Launch a Sales Result");
        System.out.println("(5)  Launch a Service Rate");
        System.out.println("(6)  Edit an Employee");
        System.out.println("(7)  Run PayRoll");
        System.out.println("(8)  Undo");
        System.out.println("(9)  Redo");
        System.out.println("(10) New Payment Schedule");
        System.out.println("(11) Show Employee's List");
        System.out.println("(12) End of the working day");
        System.out.println("(0)  Exit ");
        System.out.println("---> Choose a Feature:");
//        System.out.println("(9)  Payment Schedule"); Feature Included in "(6) Edit an Employee"  ### PUT IN  program DOCUMENTATION
        return start;
    }
}

package menu;

import java.util.ArrayList;
import java.util.Scanner;
import utils.*;
import Employee.*;

public class Menu {
     Scanner input = new Scanner(System.in);
    public  Day systemDate;
    public  ArrayList<Employee> employeeList = new ArrayList<Employee>();

    public Menu() {
        this.systemDate = new Day();
        this.employeeList = new ArrayList<Employee>();
    }
    public Menu(Menu old) {
        this.systemDate = old.systemDate;
        this.employeeList = old.employeeList;
    }
    public Menu(Day aux, ArrayList<Employee> auxEmployee) {
        this.systemDate = aux;
        this.employeeList = auxEmployee;
    }
    public Day getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Day systemDate) {
        this.systemDate = systemDate;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    //    public  Day getSystemDate() {
//        return systemDate;
//    }
//
//    public  void setSystemDate(Day systemDate) {
//        Menu.systemDate = systemDate;
//    }
//
//    public  ArrayList<Employee> getEmployeeList() {
//        return employeeList;
//    }
//
//    public  void setEmployeeList(ArrayList<Employee> employeeList) {
//        Menu.employeeList = employeeList;
//    }
//
    public static void copyArrayList(ArrayList<Employee> original, ArrayList<Employee> copied){
//        for (Employee e: copied) { // Cleaning employeeList
//            copied.remove(e);
//        }
        for (Employee e: original) {
//            System.out.println(e);
            if(e instanceof Hourly){
                Employee copy = new Hourly(((Hourly)e));
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
                Employee copy = new Commissioned(((Commissioned)e));
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
                Employee copy = new Salaried(true);
                copy.agenda.setPayType(e.agenda.getPayType());
                copy.agenda.setMonthly(0);
                copy.agenda.setMonthlyDay(e.agenda.getMonthlyDay());
                copy.agenda.setFlag(e.agenda.getFlag());
                copy.agenda.setMonthsDays(e.agenda.getMonthsDays());
                copied.add(copy);
            }

        }



    }
    public  Menu copyMenu(Menu old){
//        new_.employeeList.clear();
        Day aux = new Day();
        // Coping SystemDate
        aux.setMonth(old.systemDate.getMonth(true));
        aux.setWeekDay(old.systemDate.getWeekDay(true));
        aux.setDay(old.systemDate.getDay());
        aux.setYear(old.systemDate.getYear());
//        this.systemDate = aux;
//        System.out.println("Testing Copy Menu");
//        System.out.println(systemDate.getWeekDay());
//        System.out.println(systemDate.getDay());
//        System.out.println(systemDate.getYear());
//        System.out.println(systemDate.getMonth());
//        System.out.println("_______________________");
        // Coping ArrayList
//        for (Employee e: employeeList) {
//            employeeList.remove(e);
//        }
        ArrayList<Employee> auxEmployee = new ArrayList<Employee>();
        copyArrayList(old.employeeList,auxEmployee);
//        this.employeeList = auxEmployee;
//        for (Employee e: old.employeeList) {
//            Employee newEmployee = new Employee();
//            copyEmployee(e,newEmployee);
//            System.out.println(newEmployee.getName());
//            System.out.println(newEmployee.getEmployeeType());
//            auxEmployee.add(newEmployee);
//        }
        System.out.println("Testing Menu Employee List");
        for (Employee e: employeeList) {
            System.out.println(e.getName());
            System.out.println(e.getEmployeeType());
        }
        return new Menu(aux, auxEmployee);
//        System.out.println("_______________________");
    }
    public  void addEmployee(){
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
    public  void removeEmployee(){
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

    public  void nextDay(){
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
        if(day == systemDate.monthsDays[month]){ // Last Month Day
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

    public  void launchCardPoint(){
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
                        b.setCardPoint(b.makeCardPoint()+ b.getCardPoint());
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
    public  void launchSalesResult(){
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
                        b.salesResult(systemDate, b);
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
    public  void launchServiceRate(){ // Syndicate
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
    public  void editEmployee() {
        boolean flag = true, flag_aux = false;
        System.out.println("|||| EDIT EMPLOYEE");
        System.out.println("--> Employee List");
        printEmployeeList(true);
        System.out.println("--> ID of Employee: ");
        while (true) {
            int id = Exceptions.inputInteger();
            for (Employee e : employeeList) {
                int aux = e.getId();
                if (aux == id) {
                    showEditOptions();
                    int op = Exceptions.inputIntegerBounds(1, 5);
                    flag_aux = true;
                    switch (op) {
                        case 1:
                            System.out.println("__________");
                            System.out.println("--> Name: ");
                            e.setName(Exceptions.inputString());
                            break;
                        case 2:
                            System.out.println("____________________");
                            System.out.println("--> Street Address: ");
                            e.setAdress(Exceptions.inputString());
                            break;
                        case 3:
                            System.out.println("____________");
                            System.out.println("--> Salary: ");
                            e.setGrossSalary(Exceptions.inputDouble());
                            //Salary
                            break;
                        case 4:
                            System.out.println("__________________________________");
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
                            System.out.println("_________________________");
                            System.out.println("Select the new Employee type:");
                            System.out.println("(1) Hourly | (2) Commissioned | (3) Salaried");
                            int type = Exceptions.inputIntegerBounds(1,3);
                            switch (type){
                                case 1:
                                    Employee newHourly = new Hourly();
                                    copyEmployee(e,newHourly);
                                    newHourly.setEmployeeType(1);
//                                    Agenda a = new Agenda();
                                    newHourly.agenda.setWeekly(1,6);
//                                    newHourly.setAgenda(a);
                                    Hourly oo = (Hourly) newHourly;
                                    System.out.println("--> Hourly Salary:");
                                    oo.setGrossSalary(Exceptions.inputDouble());
                                    oo.setCardPoint(0);
                                    newHourly.setAlreadyPaid(false);
                                    employeeList.remove(e);
                                    employeeList.add(newHourly);
                                    break;
                                case 2:
                                    Employee newCommissioned = new Commissioned();
                                    copyEmployee(e,newCommissioned);
                                    newCommissioned.setEmployeeType(2);
//                                    Agenda aa = new Agenda();
                                    newCommissioned.agenda.setWeekly(2,6);
//                                    newCommissioned.setAgenda(a);
                                    Commissioned o = (Commissioned) newCommissioned;
                                    System.out.println("--> Commission Rate (0.0 ~ 1.0):");
                                    o.setComissionRate(Exceptions.inputDoubleBounds(0.0,1.0));
                                    System.out.println("--> Commissioned Salary:");
                                    o.setGrossSalary(Exceptions.inputDouble());
                                    newCommissioned.setAlreadyPaid(false);
                                    employeeList.remove(e);
                                    employeeList.add(newCommissioned);
                                    break;
                                case 3:
                                    Employee newSalaried = new Salaried();
                                    copyEmployee(e,newSalaried);
                                    System.out.println("--> Salaried Salary:");
                                    newSalaried.setGrossSalary(Exceptions.inputDouble());
                                    newSalaried.setEmployeeType(3);
//                                    Agenda aaa = new Agenda();
                                    newSalaried.agenda.setMonthly(0);
//                                    newSalaried.setAgenda(a);
                                    newSalaried.setAlreadyPaid(false);
                                    employeeList.remove(e);
                                    employeeList.add(newSalaried);
                                    break;
                            }
                            break;
                    }
                }
            }
            if(!flag_aux){
                System.out.println("The employee was not found!");
                break;
            }
            else {
                System.out.println("successful operation");
                return;
            }

        }
    }

    public  void showEditOptions(){
        System.out.println("--> Choose an option to edit:");
        System.out.println("(1) Name");
        System.out.println("(2) Street Address");
        System.out.println("(3) Salary");
        System.out.println("(4) Syndicate");
        System.out.println("(5) Type of Employee");
    }
    public  void copyEmployee(Employee old, Employee new_){
        new_.setName(old.getName());
        new_.setId(old.getId());
        new_.setAdress(old.getAdress());
        new_.setGrossSalary(old.getGrossSalary());
        new_.setSyndicate(old.getSyndicate());
        new_.setUnionFee(old.getUnionFee());
        new_.setExtraSyndicateRate(old.getExtraSyndicateRate());
        new_.setPayDay(old.getPayDay());
        new_.setFinalSalary(old.getFinalSalary());
        new_.setSyndicateID(old.getSyndicateID());
        new_.setAlreadyPaid(old.getAlreadyPaid());
        new_.setAgenda(old.getAgenda());
    }
    public  void copyAgenda(Employee old, Employee new_){
        new_.agenda.setWeeklyType(old.agenda.getWeeklyType());
        new_.agenda.setWeeDay(old.agenda.getWeeDay());
        new_.agenda.setFlag(old.agenda.getFlag());
        new_.agenda.setWeeklyIterator(old.agenda.getWeeklyIterator());
        new_.agenda.setPayType(old.agenda.getPayType());
        new_.agenda.setMonthlyDay(old.agenda.getPayType());
    }

    public  void payRoll(){
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

    public  void pay(Employee e){
        int type = e.getEmployeeType();
        switch (type){
            case 1:
                ((Hourly)e).payHourly(e);
                break;
            case 2:
                ((Commissioned)e).payCommissioned(e);
                break;
            case 3:
                ((Salaried)e).paySalaried(e);
                break;
        }
    }

    public  void printEmployeeList(boolean flag, Menu  aux){
        if(flag){
            for (Employee a: aux.employeeList) {
                System.out.println("____________________");
                System.out.println("Name: " + a.getName());
                System.out.println("ID: " + a.getId());
            }
        }
        System.out.println("____________________");
    }
    public  void printEmployeeList(boolean flag){
        if(flag){
            for (Employee a: employeeList) {
                System.out.println("____________________");
                System.out.println("Name: " + a.getName());
                System.out.println("ID: " + a.getId());
            }
        }
        System.out.println("____________________");
    }
    public  void printEmployeeList(boolean flag, int type){
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
    public  void printEmployeeList(int aux){
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




    public  void printEmployeeList(){
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
            System.out.println("Payment Type: "+a.agenda.getPayType());
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
    public void chooseADifferentAgenda(ArrayList<int[]> agendasList){
        System.out.println("|||| Choose A Different Agenda");
        System.out.println("--> List Of Agendas:");
        int i = 0;
        for (int[] aux: agendasList) {
            i+=1;
            if(aux[0] == 1){ // Weekly
                System.out.println("("+aux[3]+") Weekly | "+aux[1]+" per week | "+aux[2]+" Day Of week");
            }
            else if(aux[0] == 2){ // Monthly
                System.out.println("("+aux[3]+") Monthy | day "+aux[1]+" of all Months |");
            }

        }
        System.out.println("Choose a number of Agenda");
        int choice = Exceptions.inputIntegerBounds(1,i);
        int weeklyType = 0;
        int weekDay = 0;
        int monthDay = 0;
        for (int[] aux_:agendasList) {
            if(aux_[3] == choice){
                if(aux_[0] == 1){
                    weeklyType = aux_[1];
                    weekDay = aux_[2];
                    break;
                }
                else if(aux_[0] == 2){
                    monthDay = aux_[1];
                }
            }
        }
        System.out.println("--> Employee List:");
        printEmployeeList(true);
        System.out.println("--> Id of Employee: ");
        int id = Exceptions.inputInteger();
        boolean flag = false;
        int aux_id = 0;
        for (Employee e: employeeList) {
            aux_id = e.getId();
            if(aux_id == id){
                if(choice == 1){ // Weekly
                    e.agenda.setWeekly(weeklyType,weekDay);
                    e.agenda.setPayType(1);
                }
                else if(choice == 2){
                    e.agenda.setMonthly(monthDay);
                    e.agenda.setPayType(2);
                }
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("The employee was not found!");
        }
    }
    public void newAgenda(ArrayList<int[]> agendasList){
        System.out.println("|||| New Agenda");
        System.out.println("--> Select the Payment Type \n (1)Weekly | (2)Monthly");
        int choice = Exceptions.inputIntegerBounds(1,2);
        int frequency;
        int weekDay;
        int dayMonth;
        if(choice == 1){ // Weekly
            System.out.println("--> Type the frequency ( 1 ~3)");
            frequency = Exceptions.inputIntegerBounds(1,3);
            System.out.println("--> type the week day of Payment ( 1 ~ 7)");
            weekDay = Exceptions.inputIntegerBounds(1,7);
            int[] new_agenda = {1,frequency,weekDay,agendasList.size()+1};
            agendasList.add(new_agenda);
        }
        else {
            System.out.println("--> Type the day of month:");
            dayMonth = Exceptions.inputIntegerBounds(1,31);
            int[] new_agenda_ = {2,dayMonth,0,agendasList.size()+1};
            agendasList.add(new_agenda_);
        }
        System.out.println("successful operation");

    }
    public  boolean showMenuOptions(boolean start){
        System.out.println("___________________________________________________________");
        System.out.println(" ||||||||||||||||||    PAYROLL SYSTEM    ||||||||||||||||||");
        if(start){
            start = false;
            this.systemDate = systemDate.SystemDate();
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
        System.out.println("(13) Choose A Different Agenda");
        System.out.println("(14) New Agenda");
        System.out.println("(0)  Exit ");
        System.out.println("---> Choose a Feature:");
//        System.out.println("(9)  Payment Schedule"); Feature Included in "(6) Edit an Employee"  ### PUT IN  program DOCUMENTATION
        return start;
    }
}

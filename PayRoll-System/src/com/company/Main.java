package com.company;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int syndicateID = 50;
    static int boundArray = 50;
    static int boundAttributs = 18;
    static int currentMonth = 0;
    static int firstWeekDay = 0;
    static int currentDay = 0;
    static int[] months = {31,28,31,30,31,30,31,31,30,31,30,31}; // number of days in each month

    public static void main(String[] args) {
//        System.out.println("Choose the number of employees in system [ min(1) ~ max(50) ]:");
//        int boundArray = input.nextInt();
//        input.nextLine();


        String[][] menu = new String[boundArray][boundAttributs];
        String[][] menuCopy = new String[boundArray][boundAttributs];
        String[][][] undo = new String[30][boundArray][boundAttributs];
        String[][][] redo = new String[30][boundArray][boundAttributs];

//        int[] months = {31,28,31,30,31,30,31,31,30,31,30,31}; // number of days in each month

        String[] weekDays = {"(1) Sunday", "(2) Monday", "(3) Tuesday", "(4) Wednesday", "(5) Thursday", "(6) Friday", "(7) Saturday"};
        int i = 0;
        boolean flagMonth = true;
        menu = markArray2D(menu); // Mark Menu Array with "-1"
        undo = markArray3D(undo);
        redo = markArray3D(redo);

        while(true){
            if(flagMonth){
                System.out.println("||||||   PAYROLL   SYSTEM  |||||||");
                System.out.println("Enter the first week day of current month: \n ");
                for (String e: weekDays) {
                    System.out.println(e);
                }
                firstWeekDay = Integer.parseInt(input.nextLine());
                System.out.println("Enter the current day and month\n ");
                System.out.println("Day (1 ~ 31) : ");
                currentDay = Integer.parseInt(input.nextLine());
                System.out.println("Month (1 ~ 12) : ");
                currentMonth = Integer.parseInt(input.nextLine());
                flagMonth = false;
            }
            printMenu(); // Print the options
            int op = Integer.parseInt(input.nextLine());
            if(!(op == 8)){ // Add to Undo List
                put3DArray(menu,undo);
            }
            if(op==0)break;
            switch (op){
                case 1:
                    menu = addEmployee(menu,i); // Add employee
//                    printEmployee(menu,i);
                    i+=1;
                    break;
                case 2:
                    menu = removeEmployee(menu);
                    break;
                case 3:
                    menu = cardPoint(menu);
                    break;
                case 4:
                    menu = saleResult(menu);
                    break;
                case 5:
                    menu = serviceRate(menu);
                    break;
                case 6:
                    menu = changeDataEmployee(menu);
                    break;
                case 7: // Run PayRoll
                    currentDay+=1;
                    menu = runPayRoll(menu);
                    break;
                case 8: // Undo / redo
                    System.out.println("Choose (1)Undo | (2)Redo : ");
                    int aux = Integer.parseInt(input.nextLine());
                    menu = undoRedo(menu,undo, redo, aux);
                    break;
                case 9: // Agenda
                    break;
                case 10: // CreatAgenda
                    break;
            }
        }
    }
    public static String[][] addEmployee(String[][] employee, int i){
        System.out.println("|||| ADD EMPLOYEE ||||");
        System.out.print("Type the attributes of Employee: \n");
        System.out.print("Name: ");
        employee[i][0] = input.nextLine(); // Name
        int aux = i+1;
        employee[i][1]= Integer.toString(aux); // ID
        System.out.print("Address- Street: ");
        employee[i][2]= input.nextLine(); // Street Address
        System.out.print("Address - number:");
        employee[i][3]= input.nextLine(); // Number Address
        System.out.print("Type: \n (1) hourly; \n (2) salaried; \n ");
        employee[i][4] = input.nextLine(); // Type
        //COMMISSIONED -----------------------------------------------------
        if(employee[i][4].equals("2")){ // is Salaried?
            System.out.print("Is the employee commissioned? [ (1)YES, (2)NO ]\n");
            employee[i][5]= input.nextLine(); // Commissioned (0 or 1)
            if(employee[i][5].equals("1")){ // is commissioned?
                System.out.print("Commission Rate ( 0.1 ~ 0.9: ");
                employee[i][6] = input.nextLine(); // Commission Rate
            }
            else{
                employee[i][6] = "0"; // Mark with 0
            }
            employee[i][7] = "0"; // Commissioned - (SALES RESULTS) DATE
            employee[i][8] = "0"; // Commissioned - (SALES RESULTS) VALUE

        }
        // -------------------------------------------------------------------
        employee[i][9] = "0"; // hourly - total time (CARD POINT)
        System.out.print("Salary: ");
        employee[i][10] = input.nextLine(); // salary
        System.out.print("Is the employee part of Syndicate? [ (1)YES, (2)NO ]\n");
        employee[i][11] = input.nextLine(); // syndicate? (0 or 1)
        employee[1][12] = "0";
        employee[i][13] = "0";
        //SYNDICATE ----------------------------------------------------------
        if(employee[i][11].equals("1")){
            System.out.print("Syndicate Fee (0.1 ~ 0.90): ");
            employee[i][12] = input.nextLine(); // Syndicate fee
//            System.out.print("Extra Syndicate Fee: ");
//            employee[i][13] = input.nextLine(); // Extra Syndicate fee
            syndicateID-=1;
            employee[i][14]= Integer.toString(syndicateID); // ID of employee in syndicated
        }
        //---------------------------------------------------------------------
        System.out.print("Payment type (1[Bank Deposit], 2[check in hands], 3[mail check])\n");
        employee[i][15] = input.nextLine(); // Payment type
        employee[i][17] = "1";
        ///// Calculus of the payment Day.
//        int payDay = 0;
        if(employee[i][17].equals("1")){ // Default Schedule
            if(employee[i][4].equals("1")){ // hourly
                employee[i][16] = Integer.toString(weeklyPayDay(6)); // All friday
            }
            else if (employee[i][4].equals("2")) {
                if(employee[i][5].equals("1")){ //Commissioned

                    int payDay = weeklyPayDay(6)*2;
                    if(payDay > months[currentMonth]){
                        payDay = payDay - months[currentMonth-1];
                    }
                    employee[i][16] = Integer.toString(payDay);
                }
                else{
                    employee[i][16] = Integer.toString(lastWorkDayPayment());
                }
            }
        }
        System.out.println("PayDay: "+employee[i][16]);
        System.out.println("success operation!");
        return employee;
    }

    public static String[][] removeEmployee(String[][] employee){
        System.out.println("|||| REMOVE EMPLOYEE ||||");
        System.out.println("ID of employee to be removed \n (To see the Employees List (0) ): ");
        int id = Integer.parseInt(input.nextLine());
        if(id == 0){
            printNameId(employee);
            System.out.println("ID: ");
            id = Integer.parseInt(input.nextLine());
        }
        int j;
        for(j = 0; j <  boundAttributs; j++){ // Mark with -1
            employee[id-1][j] = "-1";
        }
        System.out.println("Employee Removed with success!");
        printEmployee(employee,id-1); // DEBUG ------ REMOVE LATER
        return employee;
    }
    public static String[][] cardPoint(String[][] employee){
        System.out.println("|||| CARD POINT ||||");
        System.out.print("\nInput the Hourly Employee ID: \n(To see the Employees List (0) )\n");
        int id = Integer.parseInt(input.nextLine());
        if(id == 0){
            printNameId(employee);
            System.out.print("ID: ");
            id = Integer.parseInt(input.nextLine());
        }
        if(!(employee[id-1][4].equals("1"))){
            if(employee[id-1][4].equals("-1")){
                System.out.println("The Employee isn't in the system");
            }
            else {
                System.out.println("The Employee with ID = "+id+"isn't hourly.");
            }
            return employee;
        }
        // Input Hour
        System.out.print("\nTime of Entry: ");
        System.out.print("\n a. Type hour: ");
        int hourEntry = validateTime(0,23); // hour
        System.out.print("\n b. Type minutes: ");
        int minuteEntry = validateTime(0,59); // minutes
        int entry = timeInMinutes(hourEntry, minuteEntry); //sum hour and minutes
        // ------------------------------------------------------
        System.out.print("\nTime of Exit: ");
        System.out.print("\n a. Type hour: ");
        int hourExit = validateTime(0,23); // hour
        System.out.print("\n b. Type minutes: ");
        int minuteExit = validateTime(0,59); // minutes
        int exit = timeInMinutes(hourExit, minuteExit); //sum hour and minutes
        int total = (exit - entry)/60;
        System.out.printf("\ntotal = %d\n", total); // DEBUG #####
        int i = id - 1;
        employee[i][9] = Integer.toString(total);
        input.nextLine();
        System.out.println("success operation!");
//        printEmployee(employee,i); DEBUG ####
        return employee;
    }
    public static String[][]saleResult(String[][] employee){
        System.out.println("|||| SALE RESULT ||||");
        System.out.println("ID of employee\n (To see the Employees List (0) ): ");
        int id = Integer.parseInt(input.nextLine());
        if(id == 0){
            printNameId(employee);
            System.out.println("ID: ");
            id = Integer.parseInt(input.nextLine());
        }
        if(!(employee[id-1][5].equals("1"))){
            System.out.println("This Employee isn't Commissioned");
            return employee;
        }
        System.out.print("DATE of sale: ");
        System.out.println("day:"); // whereas it is in the current month
        employee[id-1][7]= input.nextLine();
        System.out.print("\nVALUE of sale");
        employee[id-1][8]= input.nextLine();
        System.out.println("success operation!");
        return employee;
    }

    public static String[][] serviceRate(String[][] employee){
        System.out.println("|||| Service Rate ||||");
        System.out.println("ID of employee\n (To see the Employees List (0) ): ");
        int id = Integer.parseInt(input.nextLine());
        if(id == 0){
            printNameId(employee);
            System.out.println("ID: ");
            id = Integer.parseInt(input.nextLine());
        }
        if(!(employee[id-1][11].equals("1"))){
            System.out.println("Not found in syndicate list");
            return employee;
        }
        System.out.print("\nService Rate (0 ~ 100): ");
        employee[id-1][13] = input.nextLine();
        System.out.println("success operation!");
        return employee;
    }
    public static String[][] changeDataEmployee(String[][] employee){
        System.out.println("|||| CHANGE DATA EMPLOYEE ||||");
        String[] arrayHeader =  {"Name", "ID", "Street Address", "Number Address","Type (Hourly (1), salaried (2) )","Commissioned (YES(1), NO(0))","Commission Rate","Commissioned - Sales Results - DATE","Commissioned - Sales Results - VALUE","Hourly - Total Time","Salary","Syndicated ( YES(1), NO(0))","Syndicate Fee","Extra Syndicate Fee","ID of Employee in syndicated (Number above "+boundArray+" )","Payment type ( (1)Bank Deposit, (2)check in hands, (3)mail check)"};
        System.out.println("ID of Employee:");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Choose an attribute to change:");
        int i;
        int[] index = {0,2,3,4,5,11,12,14,15};
        for(i = 0; i < 9; i++){
            System.out.println("("+i+")"+arrayHeader[index[i]]);
        }
        int pick = Integer.parseInt(input.nextLine());
        System.out.println(arrayHeader[index[pick]]+":");
        employee[id-1][index[pick]] = input.nextLine();
        return employee;
    }
    public static String[][] undoRedo(String[][] menu,String[][][] undo, String[][][] redo, int flag ){
        // array3D == Undo or Redo
        String [][] aux = new String[boundArray][boundAttributs];
        if(flag == 1){ // Undo
            put3DArray(menu,redo); // put menu in Redo Array
            aux = push3DArray(undo, aux); // Push the last matrix added in Undo
            menu = copyMenu(aux,menu);
        }
        if(flag == 2){ // Redo
            put3DArray(menu,undo);
            aux = push3DArray(redo,aux); // Push the last matrix added in Redo
            menu = copyMenu(aux,menu);
        }
//        put3DArray(menu,array3D); // put menu in Undo/Redo Array
//        aux = push3DArray(array3D, aux); // Push the last matrix added in Undo/Redo
//        menu = copyMenu(aux,menu);
        return menu;
    }
    public static int weeklyPayDay(int payWeekDay){
//        int[] months = {31,28,31,30,31,30,31,31,30,31,30,31}; // number of days in each month
        int weekDayVar = weekDay(currentDay);
        int difference, payDay;
        if(weekDayVar > payWeekDay){
            difference = weekDayVar - payWeekDay;
            payDay = currentDay + (7 - difference);
        }
        else {

            difference = weekDayVar - payWeekDay;
            if(weekDayVar <payWeekDay){
                difference = difference*(-1);
            }
            payDay = currentDay + difference;
        }
        System.out.println("Current Month: "+currentMonth);
        if(payDay > months[currentMonth]){
            payDay = payDay - months[currentMonth-1];
        }
        return payDay;
    }
    public static int lastWorkDayPayment(){
        int lastMonthDay = months[currentMonth-1];
        while(true){
            if(weekDay(lastMonthDay) != 1 && weekDay(lastMonthDay) != 7){
                break;
            }
            lastMonthDay-=1;
        }
        return lastMonthDay;
    }
    public static String[][] runPayRoll(String[][] employee) {
        int i;
        Boolean flag = false;
        Double payment = 0.0;
        for (i = 0; i < boundArray; i++) {
            if(employee[i][0].equals("0") || employee[i][0].equals("#")){
                continue;
            }
            if (employee[i][16].equals(Integer.toString(currentDay))) {
                flag = true;
                if (employee[i][17].equals("1")) {
                    if (employee[i][4].equals("1")) { // Pay Hourly
                        payment = payHourly(employee, i);
                    } else if (employee[i][4].equals("2")) { // Pay Salaried
                        if (employee[i][5].equals("1")) { // Pay commissioned
                            payment = payCommissioned(employee, i);
                        } else {
                            payment = paySalaried(employee, i);
                        }
                    } else {
                        // check to new schedules created by user.
                    }
                }
            }
            if (flag){
                printPayment(payment, i, employee);
                flag = false;
            }
        }
        return  employee;
    }
        //////////////////////////////////////   UTILS   FUNCTIONS   ///////////////////////////////////////

    public static void printPayment(Double payment, int i, String[][] employee){
        String[] paymentType = {"Bank Deposit", "check in hands", "mail check"};
        System.out.print("\nprocessing payment...");
        System.out.println("\nEmployee: "+employee[i][0]);
        System.out.println("\nPaycheck: "+payment);
        System.out.println("\nPayment Type: "+paymentType[Integer.parseInt(employee[i][15])-1]);
    }

    public static Double payHourly(String[][] employee, int i){
        Double totalSalary;
        if(Integer.parseInt(employee[i][9]) > 8){
            int difference = Integer.parseInt(employee[i][9]) - 8;
            int salary = 8 * Integer.parseInt(employee[i][10]);
            Double extraSalaray = difference * (Double.parseDouble(employee[i][10])* 1.5);
            totalSalary = salary + extraSalaray;
        }
        else {
            totalSalary = Double.parseDouble(employee[i][10])*Integer.parseInt(employee[i][9]);
        }
        return syndicateCalculus(totalSalary,i,employee);
    }
    public static Double payCommissioned(String[][] employee, int i){
        Double totalSalary = Double.parseDouble(employee[i][10]);
        Double rate = Double.parseDouble(employee[i][6]);
        totalSalary = (totalSalary /2) + ( Double.parseDouble(employee[i][8]) * Double.parseDouble(employee[i][6]));
        employee[i][8] = "0";
        return syndicateCalculus(totalSalary, i, employee);
    }
    public static Double paySalaried(String[][] employee, int i){
        Double totalSalary = Double.parseDouble(employee[i][10]);
        return syndicateCalculus(totalSalary, i, employee);
    }
    public static Double syndicateCalculus(Double totalSalary, int i, String[][] employee){
        if(employee[i][11].equals("1")){ // Part of syndicate
            totalSalary = ((1 - Double.parseDouble(employee[i][12])) * totalSalary) - Double.parseDouble(employee[i][13]);
        }
        return totalSalary;
    }
    public static int weekDay(int currentDay){
        int mod = currentDay % 7;
        int dayweek;
        dayweek = mod + (firstWeekDay - 1);
        if(dayweek>7){
            dayweek = dayweek % 7;
        }
        return dayweek;
    }
    public static void printEmployee(String[][] employee, int i){
        String[] arrayHeader =  {"Name", "ID", "Street Address", "Number Address","Type","Commissioned","Commission Rate","Commissioned - Sales Results - DATE","Commissioned - Sales Results - VALUE","Hourly - Total Time","Salary","Syndicated","Syndicate Fee","Extra Syndicate Fee","ID of Employee in syndicated","Payment type"};
        System.out.print("Would you like to print the employee data? ( Yes(1) or No(0 )");
        String flag = input.nextLine();
        if(flag.equals("1")){
            int j;
            for(j = 0; j <boundAttributs; j++){
                System.out.println(arrayHeader[j] + " = " + employee[i][j]);
            }
        }
    }
    public static void printNameId(String[][] employee){ // Print name and ID of each employee
        int i;
        System.out.println("EMPLOYEES LIST");
        for(i = 0; i < boundArray; i++){
            if(!(employee[i][0].equals("-1"))){
                System.out.println(" Name: "+ employee[i][0]+" | ID:"+employee[i][1]);
            }
        }
    }
    public static void print2DArray(String[][] menu){ // Copy menu to undo
        int i=0, j=0, k = 0;
            for(i = 0; i < boundArray;i++) {
                System.out.print("["+i+"] ");
                for (j=0; j< boundAttributs;j++){
                    System.out.print(menu[i][j]+" ");
                }
                System.out.println();
            }
    }
    public static void print3DArray(String[][][] undo){ // Copy menu to undo
        int i=0, j=0, k = 0;
        for(k = 0; k < 3/*30*/; k++) {
            System.out.print("[" + k + "] " + "\n");
            for (i = 0; i < 3/*boundArray*/; i++) {
                for (j = 0; j < boundAttributs; j++) {
                    System.out.print(undo[k][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    public static String[][][] markArray3D(String[][][] menu){
        int i, j, k;
        for (k = 0; k < 30; k++){
            for(i = 0; i < boundArray;i++) {
                for (j=0; j< boundAttributs;j++){
                    menu[k][i][j] = "-1";
                }
            }
        }
        return menu;
    }
    public static String[][] markArray2D(String[][] menu){
        int i, j;
        for(i = 0; i < boundArray;i++)
        {
            for (j=0; j< boundAttributs;j++){
                menu[i][j] = "-1";
            }
        }
        return menu;
    }
    public static String[][] copyMenu(String[][] mat1, String[][] mat2){
        int i, j;
        for(i = 0; i < boundArray;i++)
        {
            for (j=0; j< boundAttributs;j++){
                mat2[i][j] = mat1[i][j];
            }
        }
        return mat2;
    }
    public static void put3DArray(String[][] menu, String[][][] undo){ // Copy menu to undo
        int i=0, j=0, k = 0;
        while(true){ // pick first free position in K column
            if(undo[k][i][j].equals("-1")){
                break;
            }
            k+=1;
        }
        for(i = 0; i < boundArray;i++) { // Copy menu to undo[k]
            for (j=0; j< boundAttributs;j++){
                undo[k][i][j] = menu[i][j];
            }
        }
    }
    public static String[][] push3DArray(String[][][] array3D, String[][] aux){
        int i=0,j=0,k=0;
        while(true){ // pick first free position in K column
            if(array3D[k][i][j].equals("-1")){
                break;
            }
            k+=1;
        }
        k-=1;
        System.out.println("K"+k);
        for(i = 0; i < boundArray;i++) { // Copy menu to undo[k]
            for (j=0; j< boundAttributs;j++){
                aux[i][j] = array3D[k][i][j];
                array3D[k][i][j] = "-1";
            }
        }
        return aux;
    }
    public static int validateTime(int leftBound, int rightBound){
        int value = -1;
//        boolean flag = true;
        value = input.nextInt(); // reading hour or minutes
//        input.nextInt();// cleaning buffer
        while(true){
            if(value >= leftBound && value <= rightBound) { // validating
//                System.out.printf("value : %d", value); // DEBUG
                if(rightBound == 23){
                    return value * 60; // returning hour in minutes
                }
                return value;
            }
            else{
                System.out.printf("type a valid value -> (%d - %d):", leftBound, rightBound);
                value = input.nextInt(); // reading hour or minutes again
//                input.nextInt();// cleaning buffer
            }
        }
    }
    public static int timeInMinutes(int hour, int minute){
        return hour + minute;
    }

    ////////////////////////////////////////// Menu ///////////////////////////////////////////////
    public static void printMenu(){
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
        System.out.println("(10) Create New Payment Schedule");
        System.out.println("(0) End");
    }
}

package utils;

import Employee.Employee;

public class Day {
    private   int weekDay;
    private   int day;
    private   int month;
    private   int year;
    public    int[] monthsDays = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public    String[] weekdaysDictionary = {"0","Sunday","Monday","Tuesday","Wednesday", "Thursday", "Friday", "Saturday"};
    public    String[] monthsDictionary = {"0","January","February","March","April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Day(int weekDay, int day, int month, int year) {
        this.weekDay = weekDay;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Day(Day original){
        this.weekDay = original.getWeekDay(true);
        this.day = original.getDay();
        this.month = original.getMonth(true);
        this.year = original.getYear();
    }

    public Day() {
        this.weekDay = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public String getWeekDay() {
        return weekdaysDictionary[weekDay];
    }
//    public int getWeekDay(boolean false) {
//        return weekDay;
//    }
    public int getWeekDay(boolean a) {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return monthsDictionary[month];
    }
    public int getMonth(boolean a) {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public  Day SystemDate(){
        int i;
        System.out.println("Welcome to PayRoll System, please, type the current date:");
        System.out.println("--> WEEK DAY (Choose one Option): ");
        for(i = 1; i < 8; i++){
            System.out.println("   ("+i+") "+ weekdaysDictionary[i]);
        }
        int weekDay = Exceptions.inputIntegerBounds(1,7);
//        aux.setWeekDay(weekDay);
        System.out.println("--> DAY (1 - 31):");
        int day = Exceptions.inputIntegerBounds(1,31);
//        aux.setDay(day);
        System.out.println("--> MONTH: (choose one option):");
        for(i = 1; i < 13; i++){
            System.out.println("   ("+i+") "+ monthsDictionary[i]);
        }
        int month = Exceptions.inputIntegerBounds(1,12);
//        aux.setMonth(month);
        System.out.println("--> YEAR (format yyyy ~ equal or greater than 2019):");
        int year = Exceptions.inputIntegerBounds(2019);
//        aux.setYear(year);
        return new Day(weekDay,day,month,year);
    }
    public int getLastMonthDay(int month){
        return monthsDays[month];
    }
}

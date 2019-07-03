package Employee;

import utils.*;
import utils.Exceptions;
import java.util.Scanner;
import java.util.Calendar;

public class Hourly extends Employee implements HourlyInterface{
    // PAYMENT : ALL FRIDAY
    static Scanner input = new Scanner(System.in);
    protected int cardPoint;

    public int getCardPoint() {
        return cardPoint;
    }

    public void setCardPoint(int cardPoint) {
        this.cardPoint = cardPoint;
    }

    public Hourly(){
//        this.agenda = new Agenda();
    }
    public Hourly(Hourly original){
        this.name = original.getName();
        this.id = original.getId();
        this.adress = original.getAdress();
        this.grossSalary = original.getGrossSalary();
        this.syndicate = original.getSyndicate();
        this.unionFee = original.getUnionFee();
        this.extraSyndicateRate = original.getExtraSyndicateRate();
        this.payDay = original.getPayDay();
        this.finalSalary = original.getFinalSalary();
        this.syndicateID = original.getSyndicateID();
        this.alreadyPaid = original.getAlreadyPaid();
        this.agenda = new Agenda();
        this.cardPoint = original.cardPoint;
//        Agenda a = new Agenda();
//        a.setWeekly(1,6); // All Friday
//        this.agenda = a;
    }

    public Hourly(Day systemDate, int size){
//        input.nextLine();
        System.out.println("--> Type the Employee's data:");
        System.out.println("_____________________________");
        setId(systemDate.getYear()+size*7);
        System.out.println("--> Name:");
        setName(Exceptions.inputString());
        System.out.println("--> Street Address:");
        setAdress(Exceptions.inputString());
        System.out.println("--> Salary:");
        setGrossSalary(Exceptions.inputDouble());
        System.out.println("--> Is employee part of Syndicate?");
        System.out.println("type:   (0) NO  |  (1) Yes");
        setSyndicate(Exceptions.inputIntegerBounds(0,1));
        switch (syndicate){
            case 0:
                setUnionFee(0);
                setExtraSyndicateRate(0);
                break;
            case 1:
                System.out.println("--> Union Fee (0.0 ~ 1.0):");
                setUnionFee(Exceptions.inputDoubleBounds(0.0,1.0));
                setSyndicateID(getId()*6);
                break;

        }
        setCardPoint(0);
        setEmployeeType(1);
        setAlreadyPaid(false);
        Agenda a = new Agenda();
        a.setWeekly(1,6); // All Friday
        setAgenda(a);
    }
    public  void payHourly(Employee e){
        double salary = e.getGrossSalary();
        if(e.getSyndicate() == 1){ // Syndicate
            double rate = e.getUnionFee();
            salary = (salary * (1 - e.getExtraSyndicateRate() ) * (1 - e.getUnionFee()));
        }
        Hourly a = (Hourly) e;
        int time = a.getCardPoint();
        if(time > 8){
            salary = salary * (time - (time - 8)) + salary * ((time-8)*1.5);
        }
        else {
            salary = salary * time;
        }
        System.out.println("_________________________");
        System.out.println("--> Name: "+e.getName());
        System.out.println("--> ID: "+e.getId());
        System.out.println("--> Payment: $"+salary);
        System.out.println("_________________________");
        a.setCardPoint(0);

    }

    public int makeCardPoint(){
//        System.out.println("|||| CARDPOINT ");
        System.out.println("--> Entry Time:");
        System.out.println("    | Hour ( 0 ~ 23 ):  ");
        int hourEntry = (Exceptions.inputIntegerBounds(0,23))*60;
        System.out.println("    | Minutes ( 0 ~ 59 ): ");
        int minutesEntry = Exceptions.inputIntegerBounds(0,59);
        System.out.println("--> Exit Time:");
        System.out.println("    | Hour ( 0 ~ 23 )");
        int hourExit = (Exceptions.inputIntegerBounds(0,23))*60;
        System.out.println("    | Minutes ( 0 ~ 59 ): ");
        int minutesExit = Exceptions.inputIntegerBounds(0,59);
        int entry = hourEntry + minutesEntry;
        int exit = hourExit + minutesExit;
        int totaltime;
        if(entry > exit){
            totaltime = (((24*60) - entry) + exit)/60;

        }
        else{
            totaltime = (exit - entry)/60;
        }
        return totaltime;
    }
}

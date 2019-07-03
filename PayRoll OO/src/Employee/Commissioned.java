package Employee;

import utils.Agenda;
import utils.Day;
import utils.Exceptions;

public class Commissioned extends Employee implements CommissionedInterface {

    public double comissionRate;
    public double valueSale;
    public int dateSale;

    public Commissioned(Day systemDate, int size){
//        input.nextLine();
        setId(systemDate.getYear()+size*7);
        System.out.println("--> Type the Employee's data:");
        System.out.println("_____________________________");
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
        setEmployeeType(2);
        setAlreadyPaid(false);
        Agenda a = new Agenda();
        a.setWeekly(2,6); // Each 2 fridays
        setAgenda(a);
        System.out.println("--> Commission Rate (0.0 ~ 1.0):");
        setComissionRate(Exceptions.inputDoubleBounds(0.0,1.0));
    }

    public double getValueSale() {
        return valueSale;
    }

    public void setValueSale(double valueSale) {
        this.valueSale = valueSale;
    }

    public int getDateSale() {
        return dateSale;
    }

    public void setDateSale(int dateSale) {
        this.dateSale = dateSale;
    }

    public  void salesResult(Day systemDate, Commissioned e){
        System.out.println("|||| SALES RESULT");
        System.out.println("--> Result:");
        double result = Exceptions.inputDouble();
        System.out.println("--> Day of Sale:");
        int date = Exceptions.inputIntegerBounds(1,systemDate.getLastMonthDay(systemDate.getMonth(true)));
        e.setValueSale(result+e.getValueSale());
        e.setDateSale(date);
    }
    public void payCommissioned(Employee e){
        double salary = e.getGrossSalary();
        if(e.getSyndicate() == 1){
            double rate = e.getUnionFee();
            salary = (salary * (1 - e.getExtraSyndicateRate() ) * (1 - e.getUnionFee()));
        }
        Commissioned a = (Commissioned) e;
        double value = a.getValueSale();
        double commission = a.getComissionRate();
        salary = salary + (commission*value);
        System.out.println("_________________________");
        System.out.println("--> Name: "+e.getName());
        System.out.println("--> ID: "+e.getId());
        System.out.println("--> Payment: $"+salary);
        System.out.println("_________________________");
        a.setValueSale(0);
        a.setDateSale(0);
    }

    public Commissioned(Commissioned original){
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
//        Commissioned oo = (Commissioned) original;
        this.comissionRate = original.getComissionRate();
        this.valueSale = original.getValueSale();
        this.dateSale = original.getDateSale();
        this.agenda = new Agenda();
//        Agenda a = new Agenda();
//        a.setWeekly(2,6); // Each 2 fridays
//        this.agenda = a;
    }
    public Commissioned(){
//        this.agenda = new Agenda();
    }
    public double getComissionRate() {
        return comissionRate;
    }

    public void setComissionRate(double comissionRate) {
        this.comissionRate = comissionRate;
    }


}

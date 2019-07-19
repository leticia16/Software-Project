package Employee;

import utils.Agenda;
import utils.Day;
import utils.Exceptions;

public class Salaried extends Employee implements SalariedInterface {

    public Salaried(Day systemDate, int size){
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
                setSyndicateID((int)(getGrossSalary()*5+100));
                System.out.println("test syndiID : "+getSyndicateID());
                break;

        }
        setId(systemDate.getYear()+size*7);
        setEmployeeType(3);
        setAlreadyPaid(false);
        Agenda a = new Agenda();
        a.setMonthly(0); // Last Work Day of Month
        setAgenda(a);
    }
    public Salaried(boolean aux){
        Agenda a = new Agenda();
        a.setMonthly(0); // Last Work Day of Month
        this.agenda = a;
//        this.agenda = new Agenda();
    }
    public Salaried(Salaried original){
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
    }
    public  void paySalaried(Employee e){
        double salary = e.getGrossSalary();
        if(e.getSyndicate() == 1){
            double rate = e.getUnionFee();
            salary = (salary * (1 - e.getExtraSyndicateRate() ) * (1 - e.getUnionFee()));
        }
            System.out.println("_________________________");
            System.out.println("--> Name: "+e.getName());
            System.out.println("--> ID: "+e.getId());
            System.out.println("--> Payment: $"+salary);
            System.out.println("_________________________");
    }
    public Salaried(){

    }
}

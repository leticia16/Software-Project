package Employee;
import utils.*;
public class Employee {
    String name;
    int id;
    String adress;
    double grossSalary;
    int syndicate;
    double unionFee;
    double extraSyndicateRate;
    int payDay;
    double finalSalary;
    int syndicateID;
    public Agenda agenda;
    boolean alreadyPaid;

    public boolean getAlreadyPaid() {
        return alreadyPaid;
    }

    public void setAlreadyPaid(boolean alreadyPaid) {
        this.alreadyPaid = alreadyPaid;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public int getSyndicateID() {
        return syndicateID;
    }

    public void setSyndicateID(int syndicateID) {
        this.syndicateID = syndicateID;
    }

    public int getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(int employeeType) {
        this.employeeType = employeeType;
    }

    int employeeType;

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public int getSyndicate() {
        return syndicate;
    }

    public void setSyndicate(int syndicate) {
        this.syndicate = syndicate;
    }

    public double getUnionFee() {
        return unionFee;
    }

    public void setUnionFee(double unionFee) {
        this.unionFee = unionFee;
    }

    public double getExtraSyndicateRate() {
        return extraSyndicateRate;
    }

    public void setExtraSyndicateRate(double extraSyndicateRate) {
        this.extraSyndicateRate = extraSyndicateRate;
    }
}


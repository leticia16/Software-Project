package Employee;
import utils.Day;

public interface CommissionedInterface{
    void salesResult(Day systemDate, Commissioned e);

    void payCommissioned(Employee e);
}
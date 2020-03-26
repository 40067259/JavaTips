package lambda;

/**
 * @author Fred Zhang
 * @create 2020-03-25 10:40 PM
 */
public class EmployeesFilterBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {

        return employee.getSalary() < 10000;
    }
}

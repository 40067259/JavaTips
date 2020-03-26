package lambda;

/**
 * @author Fred Zhang
 * @create 2020-03-25 10:38 PM
 */
public class EmployeesFilterByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}

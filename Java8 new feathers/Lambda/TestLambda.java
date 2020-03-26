package lambda;

import org.junit.Test;

import java.util.*;

/**
 * @author Fred Zhang
 * @create 2020-03-25 9:42 PM
 */

/**
 * When we want to use a interface like Comparator, we can't
 * instantiate a interface object.
 * we must create a solidi class and implements this interface
 *
 * Before java8 is released, the easiest way is to create a
 * anonymous inner class.  See the following test1
 */
public class TestLambda {

    List<Employee> employees = Arrays.asList(new Employee("Jason",50,10030),

            new Employee("Jordan",35,12000),

            new Employee("Katty",28,7000),

            new Employee("Jordan",32,6060),

            new Employee("Jordan",36,9000));

    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };

        TreeSet<Employee> tt = new TreeSet();// if no parameter is passed, the default one is from object
        TreeSet<Integer> ts = new TreeSet(com);
    }
    @Test
    public void test2(){

        Comparator<Integer> com =(x,y) -> Integer.compare(x,y);// We want to use the object of comparator
                                                            //we achieve the interface using lambda
        TreeSet ts = new TreeSet(com);
    }
    @Test
    public void test3(){
        List<Employee> list1 = filterEmployees(employees);
        for(Employee e: list1){
            System.out.println(e);
        }

    }

    // we write a method to filter the employee list and return a new list
    public List<Employee> filterEmployees (List<Employee> list){

        List<Employee> filterList = new ArrayList<>();

        for(Employee e :list){
            if(e.getAge() >= 35){
                filterList.add(e);
            }
        }
        return filterList;
    }
    // if we change a requirement, we need to write another method, but the core codes
    //just one line if(e.getAge() >= 35)  How to do that




    @Test
    public void test4(){

        List<Employee> filteredList = employeesFilter(employees,new EmployeesFilterBySalary());

        for(Employee e : filteredList){
            System.out.println(e);
        }

    }

    /**
     * ------------------Strategy Pattern-------------------
     * The optimization scheme 1: add a interface and create multiple classes to implements the interface
     * (1) create a interface called MyPredicate return a boolean
     * (2) Create a related classes to implement the interface.
     * like FilterEmployeesByAge,FilterEmployeesBySalary..
     *
     * the advantages are: <1> use 1 method to achieve all requirements</1>
     *                     <2> it is easy to extend the app's functions</2>
     * The pros are: <1> we must add a interface</1>
     *               <2> create multiple classes to implements the interfaces</2>
     *               <3> we can't change our logic of interfaces which we implemented inside a class</3>
     *
     * Solutions:
     *           <2> we use anonymous inner class or lambda expression to implement interface</2>
     *           <3> we use factory pattern to create the desired service</3>
     *
     */
    public List<Employee> employeesFilter (List<Employee> list, MyPredicate mp){

        List<Employee> filteredList = new ArrayList<>();

        for(Employee e : list){
            if(mp.test(e)){
                filteredList.add(e);
            }
        }

        return filteredList;
    }

    /**
     *Optimization schema 2:
     * anonymous inner class
     *
     * pros: we write extra codes to create an inner class
     */
    @Test
    public void test5(){
         // here we new a interface directly and create an anonymous inner class
        List<Employee> list = employeesFilter(employees,new MyPredicate<Employee>(){

            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 9000;
            }
        });
          list.forEach(System.out::println);

    }

    /**
     * Optimization schema 3:
     * We use lambda expression to simplify our codes
     */
     @Test
    public void test6(){

         /*Assume the following codes are not comments
           I don't know why the e can't be associated with a Employee object,
           other pc are working well

         List<Employee> list = employeesFilter(employees,(e) -> e.getSalary() > 8000);

         list.forEach(System.out::println);

         */

     }

    /**
     * Every time we must to design a interface,but sometime the standards are not necessary.
     * We can use our flexible way to design a method (interface's signature is fixed,
     * this one is more convenient and flexible)
     * Optimization schema 4:
     *
     * In this version, we don't need a interface and a method
     * we can filter our array or list directly!!!!!!
     *
     * Java8 Giant never die
     */
      @Test
    public void test7(){
          employees.stream()
                   .filter((e) -> e.getSalary() > 8000)
                   .forEach(System.out::println);
      }

}

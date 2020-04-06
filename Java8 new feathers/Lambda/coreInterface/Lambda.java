package coreInterface;

/**
 * @author Fred Zhang
 * @create 2020-04-05 11:43 PM
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Consumer<T>   void accept(T t)
 *
 * Supplier<T>  T get()
 *
 * Function<T,R>  R apply(T t)
 *
 * Predicate<T>  boolean test<T t>
 *
 * no parameter -> Supplier
 * 1 parameter -> Consumer (generic)  Predicate (boolean)
 * 2 parameter -> Function (generic)
 */
public class Lambda {

    @Test
    public void test1(){
        happy(10000d,(e)-> System.out.println("You like pretty girl in strip club, cost is "+ e*2));

    }

    public void happy(Double money, Consumer<Double> con){

        con.accept(money);
    }

    // produce some integers and put them into a list
    public List<Integer> getNumList(int num, Supplier<Integer> sup){

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            int a = sup.get();
            list.add(a);
        }
        return list;
    }
    @Test
    public void test2(){

     List<Integer> list =  getNumList(20,() -> (int)(Math.random() * 100));

     for(int e: list){
         System.out.println(e);
     }

    }

    public String strHandler(String str, Function<String,String>fn){

        return fn.apply(str);
    }
    @Test
    public void test3(){

        String x = strHandler("thsSunday87",(e) -> e.substring(3,9));
        System.out.println(x);
    }

    public List<String> strFilter(List<String>list, Predicate<String>pre){
        List<String> listArr = new ArrayList<>();

        for (String t: list ) {
            if(pre.test(t)){
                listArr.add(t);
            }
        }
        return listArr;
    }

    @Test
    public void test4(){


        List<String> list = Arrays.asList("sfi","mary","apple","elephant","carrot","cherry","Kirkland");

        List<String> out = strFilter(list,(t) -> t.length() <= 5);


        for (String str: out) {
            System.out.println(str);
        }
    }



}

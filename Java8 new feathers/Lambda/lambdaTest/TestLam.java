package lambdaTest;

import org.junit.Test;

import java.sql.SQLOutput;

/**
 * @author Fred Zhang
 * @create 2020-04-05 11:21 PM
 */
public class TestLam {

    public String strHandler(String sentence,StringHandler sh){

        return sh.getValue(sentence);
    }

    @Test
    public void Test1(){
        String str = strHandler("abDidsf",(e)->e.toUpperCase());
        System.out.println(str);

        String ss = strHandler(" /thammer /n",(e)->e.substring(3,9));
        System.out.println(ss);
    }

    public long getValue(long a, long b,Inter2<Long,Long> fn){

        return (long)fn.operation(a,b);
    }
    @Test
    public void Test2(){
        long x = getValue(100,987,(e1,e2)->e1 +e2);
        System.out.println(x);

        long xx = getValue(100,987,(e1,e2)->e1 * e2);
        System.out.println(xx);
    }
}

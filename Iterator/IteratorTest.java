package cvr;

import java.util.*;

/**
 * @author Fred Zhang
 * @create 2020-04-02 10:09 PM
 */
public class IteratorTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new LinkedList<>();

        list.add("s");
        list.add("t");
        list.add("u");
        list.add("v");
        list.add("n");
        list.add("p");
        list.add("k");


        /**
         * iterator, for each loop , listIterator
         */

        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String temp = it.next();
            if(temp.equals("v")) it.remove();// here can remove an element
            if(temp.equals("k")) temp = "xxxxx";// no effect to change an element
        }


        /**
         * Can't operate list inside for each loop, can't remove neither
         */
        for(String s: list){
            if(s.equals("n"))// here we can't change the element of the list
                s = "mm";
           // if(s.equals("t")) if we want to operate a list inside for each loop will throw
            //                 java.util.ConcurrentModificationException
            //list.remove(s);
           System.out.println(s);
        }
        System.out.println("---------------");
        /**
         *  How can we do if we want to operate a list during the traversal?????
         *  listIterator
         *
          */

        Iterator<String> iterator = list.listIterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            if(element.equals("t")) list.set(2,"Hello");// we can operate list within listInterator
            if(element.equals("p")){
                iterator.remove();
                list.set(4,"Add an element");// inside iterator use set to reset an element
            }

            System.out.println(element);
        }


        List<String> list1 = new ArrayList<>();

        list1.add("cc");
        list1.add("tu");
        list1.add("fu");
        list1.add("ttv");
        list1.add("nc");
        list1.add("ip");
        list1.add("Uk");

        /**
         * iterator
         */
        Iterator<String> itt = list1.iterator();
        while(itt.hasNext()){
            String current = itt.next();
            System.out.println(current);
            if(current.endsWith("c")) itt.remove();
        }
        System.out.println("*******************");
        /**
         * for each
         */
        for(String e: list1){
            if(e.equals("ip")) e ="IP";// not change the element of the list
            System.out.println(e);
        }

        System.out.println("*********************");
        /**
         * listIterator
         */
        Iterator<String> ite = list1.listIterator();
        while(ite.hasNext()){
            String current = ite.next();
            if(current.equals("Uk")){
                int index = list1.indexOf(current);
                System.out.println(current+" "+index);
                list1.set(index,"USA");
            }
            System.out.println(current);
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&");
        for(String s : list1){
            System.out.println(s);
        }

        /**
         * Iterate a Map
         */
        HashMap<Integer,String> map = new HashMap<>();
        map.put(4,"catty");
        map.put(7,"Jason");
        map.put(2,"Lily");
        map.put(9,"HuaHua");
        map.put(14,"Loise");
        map.put(1,"Richard");

        /**
         * for each loop
         */
        // method 1
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println("Entry is: "+entry.getKey()+" Value is: "+entry.getValue());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        // method 2
        for(int key:map.keySet()){
            System.out.println("The Key is :"+key+" Value is "+map.get(key));
        }
        // method 3   only produce values no keys
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(String value:map.values()){
            System.out.println("The value is "+value);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        /**
         * Iterator
         */
        Iterator<Map.Entry<Integer,String>> mapIt = map.entrySet().iterator();//entrySet() has iterator
        while(mapIt.hasNext()){
            Map.Entry<Integer,String> entry = mapIt.next();
            if(entry.getKey()==7) mapIt.remove();
            System.out.println("The Key is "+entry.getKey()+" Value is "+entry.getValue());
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for(int k: map.keySet()){
            System.out.println("Key: "+k+"Value: "+map.get(k));
        }

    }
}

package cvr;

import java.util.*;

/**
 * @author Fred Zhang
 * @create 2020-03-31 10:42 PM
 */
public class Cvr {
    // a key for a archive
    private String vin;
    // record a vin's history  ''search'' and "add' are important
    // no requirement for this assignment, but we need to keep the records
    private HashMap<String, Stack<Archive>> historicalRecords;

    // all archives in registration time order, it is usually used to search
    //when the length is less than threadhold use a list, otherwise use a tree
    private Collection<Archive> activeVins;
    //it is an auxiliary set to get a archive from a key efficiently
    private HashSet<String> vinSet;
   // the threshold to resize the collection
    private int threshold;
    //the fixed length of vin
    private int keyLength;

    public Cvr(){}

    public Cvr(String vin) {
        this.vin = vin;
        vinSet.add(vin);
    }

    public Cvr(String vin, HashMap<String, Stack<Archive>> historicalRecords,Collection<Archive> activeVins,
               HashSet<String> vinSet, int threshold, int keyLength) {
        this.vin = vin;
        this.historicalRecords = historicalRecords;
        this.activeVins =activeVins;
        this.vinSet = vinSet;
        this.threshold = threshold;
        this.keyLength = keyLength;
        vinSet.add(vin);
    }

    public Collection<Archive> getActiveVins() {

        return activeVins;
    }

    public void setActiveVins(Collection<Archive> activeVins){

        this.activeVins = activeVins;
    }

    public int getThreshold() {
        return threshold;
    }



    public int getKeyLength() {
        return keyLength;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        vinSet.remove(this.vin);
        this.vin = vin;
        vinSet.add(this.vin);
    }

    public HashMap<String, Stack<Archive>> getHistoricalRecords() {
        return historicalRecords;
    }

    public void setHistoricalRecords(HashMap<String, Stack<Archive>> historicalRecords) {
        this.historicalRecords = historicalRecords;
    }


    public HashSet<String> getVinSet() {
        return vinSet;
    }

    public void setVinSet(HashSet<String> vinSet) {
        this.vinSet = vinSet;
    }

    // problem 5 6
    public void setThreshold(int threshold) throws Exception{
        if(threshold < 100 || threshold > 900000)
            throw new Exception("Invalid threshold, input must from 100 to 900000");
        this.threshold = threshold;
    }
    //a makeup for problem1
    public void resize(){
        if(threshold == activeVins.size() && this.activeVins.getClass().getName().equals("java.util.PriorityQueue"))
           this.setActiveVins(new TreeSet<Archive>(this.activeVins));
        else if (threshold == activeVins.size() && this.activeVins.getClass().getName().equals("java.util.TreeSet"))
            this.setActiveVins(new PriorityQueue<>(this.activeVins));
    }
    //problem 2   set key length
    public void setKeyLength(int keyLength) throws Exception {
        if(keyLength < 10 || keyLength > 17)
            throw new Exception("Invalid keyLength, input must from 10 to 17");

        this.keyLength = keyLength;
    }
    // need a helper to test the vin is valid or not
    //problem 3 generate a random vin

    public  String generate(int keySize) throws Exception{
        if( keyLength != keySize)
            throw new Exception("Your input can't match the keyLength");

        do {
            String vin = "";
            for (int i = 1; i <= keyLength; i++) {
                int sign = new Random().nextInt(36);
                if (sign < 10) vin += sign;
                else vin = vin + (char) (sign + 55);
            }
        }
        while(vinSet.contains(vin));// check if this vin is used

        return vin;
    }
    //problem 4
    //return all sorted keys (lexicographic order)
    // treeSet and priorityQueue all provide ordered elements
    public LinkedList<Archive> allKeys(){
        LinkedList<Archive> allKeysList = null;
        if(activeVins.getClass().getName().equals("java.util.TreeSet")){
            for(Iterator iter = activeVins.iterator(); iter.hasNext(); ) {

                allKeysList.add((Archive) iter.next());
            }
        }
        else{
            PriorityQueue<Archive> temp = (PriorityQueue)activeVins;
            while(!temp.isEmpty()){
                allKeysList.add(temp.remove());
            }
        }
        return allKeysList;
    }
     // problem 5
    //add an entry for the given key and value
    //Because we use a set to store the archive, when want to
    //add a key value entry, we just check the key is the same key
    //belongs to value; we can't use a map, because the problem1's constrain
    public void add(String key,Archive value) throws Exception{
        if(!key.equals(value.getVin())) throw new Exception("key and value do not match");
        activeVins.add(value);
        this.resize();

    }
    //problem 6  remove the value through
    // need 4 steps: (1) find the archive using a vin (2)then delete the archive
    // (3)put it historical record (4) delete it from vinSet
    //
    public Archive getArchiveByVin(String vin){
        Archive solution = null;
        for(Iterator iter = activeVins.iterator(); iter.hasNext(); ) {
           Archive tem = (Archive) iter.next();
           if(tem.getVin().equals(vin)) {
               solution = tem;
               break;
           }
        }
        return solution;
    }
    public void remove (String key){
        Archive archive = getArchiveByVin(key);
        if(archive != null){
            activeVins.remove(archive);
            this.resize();
            vinSet.remove(key);
            historicalRecords.get(key).add(archive);
        }

    }
    //problem 7  getvalues(Key)  "values"  I suppose return the historical records' values
    // the order is most recent to least recent
    public Stack<Archive> getValues(String key){
        return historicalRecords.get(key);
    }
    //problem 8  Not sure it is ordered by the registration time or key's lexicographic order
    // I assume it is the latter one
    public String nextKey(String key) {
        if(activeVins.getClass().getName().equals("java.util.TreeSet"))
           return nextKeyForTreeSet(key);
        else
            return nextKeyForPQ(key);

    }

        //  a helper function for treeSet

        public String nextKeyForTreeSet(String key){
            String solution = null;
            boolean findCurrent = false;
            for (Iterator iter = activeVins.iterator(); iter.hasNext(); ) {
                Archive temp = (Archive) iter.next();
                if (findCurrent == true) {
                    solution = temp.getVin();
                    break;
                }

                if (temp.getVin().equals(vin)) {
                    findCurrent = true;
                }

            }
            return solution;
        }

       public String nextKeyForPQ(String key){
        Queue<Archive> temp =(PriorityQueue<Archive>)activeVins;
        boolean findCurrentKey = false;
        String solution = null;
        Archive next = null;
        while(!temp.isEmpty()){
            Archive current = temp.poll();
            if(findCurrentKey == true){
                solution = current.getVin();
                break;
            }
            if(current.getVin().equals(key))
                findCurrentKey = true;
        }
        return solution;
       }

    //problem 9  Not sure it is ordered by the registration time or key's lexicographic order
    //    I assume it is the latter one
    public String prevKey(String key){
        if(activeVins.getClass().getName().equals("java.util.TreeSet"))
            return prevKeyForTreeSet(key);
        else
            return prevKeyForPQ(key);

    }

    public String prevKeyForTreeSet(String key){
        String solution = null;
        boolean findCurrent = false;
        Archive previous = null;
        for(Iterator iter = activeVins.iterator(); iter.hasNext(); ) {

            Archive tem = (Archive) iter.next();

            if(findCurrent == true){
                solution = previous.getVin();
                break;
            }


            if(tem.getVin().equals(vin)) {
                findCurrent = true;
            }
            if(findCurrent == false)
                previous = (Archive) iter;
        }
        return solution;

    }

    public String prevKeyForPQ(String key){

        Queue<Archive> temp =(PriorityQueue<Archive>)activeVins;
        boolean findCurrentKey = false;
        String solution = null;
        Archive prev = null;
        while(!temp.isEmpty()){
            Archive current = temp.poll();
            if(findCurrentKey == true){
                solution = prev.getVin();
                break;
            }

            if(current.getVin().equals(key))
                findCurrentKey = true;
            if(findCurrentKey == false)
                prev = current;
        }
        return solution;

    }
    //problem 10  prevAccids(key)  need to access historical records and get all accidents
     public ArrayList<Accident> prevAccids(String key){
        Stack<Archive> records = historicalRecords.get(key);
        ArrayList<Accident> solution = new ArrayList<>();
        while(!records.isEmpty()){
            Accident[] temp = records.pop().getAccidents();
            for(int i = temp.length-1; i >= 0; i++)
                solution.add(temp[i]);
        }
        return solution;
     }



    public static void main(String[] args) throws Exception {


    }



}

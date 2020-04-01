package cvr;

import java.util.*;

/**
 * @author Fred Zhang
 * @create 2020-03-31 10:07 PM
 */
public class Accident {

    private Date date;
    private String hostVin;
    private String guestVin;
    private String description;

    public Accident(String hostVin) {
        this.hostVin = hostVin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHostVin() {
        return hostVin;
    }

    public void setHostVin(String hostVin) {
        this.hostVin = hostVin;
    }

    public String getGuestVin() {
        return guestVin;
    }

    public void setGuestVin(String guestVin) {
        this.guestVin = guestVin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "date=" + date +
                ", hostVin='" + hostVin + '\'' +
                ", guestVin='" + guestVin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        HashMap<String,Integer> hp =new LinkedHashMap<>();
        hp.put("new",5);
        hp.put("bcd",2);
        hp.put("ac",99);
        hp.put("yz",22);

        for(String key: hp.keySet()){

            System.out.println("key: "+ key+" value: " + hp.get(key));

        }
        Iterator map1it=hp.entrySet().iterator();
        while(map1it.hasNext())
        {
            Map.Entry<String, Integer> entry=(Map.Entry<String, Integer>) map1it.next();
            System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
        }
    }
}

package cvr;


import java.util.Arrays;

/**
 * @author Fred Zhang
 * @create 2020-03-31 10:35 PM
 */
public class Archive implements Comparable<Archive> {
    private String vin;
    private Owner owner;
    private Accident[] accidents;

    public Archive(String vin) {
        this.vin = vin;
    }

    public Archive(String vin, Owner owner, Accident[] accidents) {
        this.vin = vin;
        this.owner = owner;
        this.accidents = accidents;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Accident[] getAccidents() {
        return accidents;
    }

    public void setAccidents(Accident[] accidents) {
        this.accidents = accidents;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "vin='" + vin + '\'' +
                ", owner=" + owner +
                ", accidents=" + Arrays.toString(accidents) +
                '}';
    }


    @Override
    public int compareTo(Archive o) {
        for(int i = 0; i < this.vin.length(); i++){
            if(this.vin.charAt(i) > o.vin.charAt(i)) return 1;
            else if (this.vin.charAt(i) < o.vin.charAt(i)) return -1;
        }
        return 0;
    }
}

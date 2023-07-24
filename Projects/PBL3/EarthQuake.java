package PBL3;

public class EarthQuake {
    private String country,city,type,date;
    private double magnitude;

    public EarthQuake(String country,String city,double magnitude,String date){
        this.country=country;
        this.city=city;
        this.magnitude=magnitude;
        this.date=date;
    }
    public EarthQuake(double magnitude,String date,String country){
        this.country=country;
        this.date=date;
        this.magnitude=magnitude;

    }
    public EarthQuake(String type, double magnitude,String date){
        this.date=date;
        this.type=type;
        this.magnitude=magnitude;
    }
    public EarthQuake(){}

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }



    @Override
    public String toString(){
//        return "Type: "+type+"\tMagnitude: "+magnitude+"\tDate: "+date;
        return "Date: "+date+" , Magnitude: "+magnitude+" , Country: "+country;
    }
}

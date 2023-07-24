package PBL;

public class PakUni {
    private String name,location,province;
    private int worldRanking,asiaRanking,pakRanking,noOfPublications;

    public PakUni(String name,int worldRanking,int asiaRanking,int pakRanking,String location,String province,int noOfPublications){
        this.name=name;
        this.worldRanking=worldRanking;
        this.asiaRanking=asiaRanking;
        this.pakRanking=pakRanking;
        this.location=location;
        this.province=province;
        this.noOfPublications=noOfPublications;
    }

    public PakUni(){

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getProvince() {
        return province;
    }

    public int getWorldRanking() {
        return worldRanking;
    }

    public int getAsiaRanking() {
        return asiaRanking;
    }

    public int getPakRanking() {
        return pakRanking;
    }

    public int getNoOfPublications() {
        return noOfPublications;
    }

    @Override
    public String toString() {
        return String.format("%5s%10s%10s%10s%30s%30s%80s",worldRanking,asiaRanking,pakRanking,noOfPublications,location,province,name);
    }
}

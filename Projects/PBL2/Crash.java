package PBL2;

public class Crash {
    private final String year,month,date,time,day,city,intersection,hit_run,light_condition,weather_condition,speed,injury_type,partyType,age,gender;

    public Crash(String year, String month, String date, String time, String day, String city, String intersection, String hit_run, String light_condition, String weather_condition, String speed, String injury_type, String partyType, String age, String gender) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.time = time;
        this.day = day;
        this.city = city;
        this.intersection = intersection;
        this.hit_run = hit_run;
        this.light_condition = light_condition;
        this.weather_condition = weather_condition;
        this.speed = speed;
        this.injury_type = injury_type;
        this.partyType = partyType;
        this.age = age;
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getCity() {
        return city;
    }

    public String getIntersection() {
        return intersection;
    }

    public String getHit_run() {
        return hit_run;
    }

    public String getLight_condition() {
        return light_condition;
    }

    public String getWeather_condition() {
        return weather_condition;
    }

    public String getSpeed() {
        return speed;
    }

    public String getInjury_type() {
        return injury_type;
    }

    public String getPartyType() {
        return partyType;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Date: "+date+"\tMonth: "+month+"\tYear: "+year+"\tDay: "+day+"\tTime: "+time+"\tCity: "+city+"\tCrash Intersection: "+
                intersection+"\tCrash Hit and Run: "+hit_run+"\tSpeed: "+speed+"\tWorst Injury in crash: "+injury_type+
                "\tParty type: "+partyType+"\tPerson Age: "+age+"\tGender: "+gender;

//        return date+"/"+month+"/"+year+" on "+day;
    }
}

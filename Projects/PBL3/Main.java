package PBL3;

import java.io.*;
import java.util.Scanner;


public class Main {
    private final double[] magnitude=new double[1104];
    //country array contains total list of countries on the basis of earth quack in each year
    private final String[] country=new String[1104];
    //city array contains name of cities in which earth quack occurred each year
    private final String[] city=new String[1104];
    //year array stores list of years for every single occasion
    private final int[] year=new int[1104];
    //date of every occasion
    private final String[] date=new String[1104];
    //type of every earth quack occurred
    private final String[] earthQuackType=new String[1104];
    //totalDifferentCountries array is for total different countries from 1965 to 2016
    /*
    First I write a code to print total different countries and added a counter variable
    which counted total number of different countries to be 52
    Algo: using for each loop and comparing each country whether it has been stored in the new array or
    not if already present then continue that iteration elso store that country in the new array
     */
    private final String[] totalDifferentCountries=new String[52];
    //array of linked list to store the linked list of each year
    private final MyLinkedList[] yearWiseLL=new MyLinkedList[52];
    //a queue  for storing the biggest earth quack of each year
    private final MyLinkedQueue earthQuackQueue=new MyLinkedQueue();
    // linked list for storing the recent earth quack of each country with most recent on top
    private final MyLinkedList recentCountryWiseQuacksList=new MyLinkedList();
    //array of stacks for storing stack of every country
    private final MyLinkedStack[] countryStack=new MyLinkedStack[52];
    //making constructor to call all the important methods to store data in local variables
    public void fillData(){
        fetchingDataFromCSVFile();
        insertCountry_City();
        insertIntoCollections();
        insertIntoQueue();
        setTotalDifferentCountries();
        //initializing stack of each country
        for(int i=0;i<countryStack.length;i++)countryStack[i]=new MyLinkedStack(totalDifferentCountries[i]);
        //Storing countries information in their corresponding stacks
        for (int i=0;i< country.length;i++)
            for (MyLinkedStack myLinkedStack : countryStack)
                if (country[i].equals(myLinkedStack.getStackName()))
                    myLinkedStack.push(new EarthQuake(earthQuackType[i], magnitude[i], date[i]));
        setRecentCountryWiseQuacksList();
    }
    public void fetchingDataFromCSVFile(){
        //using file class to read from file
        File file=new File("PBL3/Valid countries and cities.csv");
        try {
            int i=0;
            //reading from file line by line
            Scanner scan=new Scanner(file);
            //reading the first line as it is not necessary to store them in data structures
            scan.nextLine();
            while (scan.hasNextLine()){
                //converting each line of Excel into columns
                String[] s=scan.nextLine().split(",");
                /*
                After getting each line from Excel sheet we know that at index 2
                date is coming, but we have to take only year from the complete date
                therefore I am converting the String at index 2 into charArray so
                that I can get the last four characters also making String of last
                four characters I have used StringBuilder class
                 */
                char[] s1=s[2].toCharArray();
                StringBuilder sdb=new StringBuilder();
                for(int j=s1.length-4;j<s1.length;j++)sdb.append(s1[j]);
                //putting each year at corresponding index into year array
                year[i]= Integer.parseInt(sdb+"");
                date[i]=s[2];
                magnitude[i]=Double.parseDouble(s[10]);
                earthQuackType[i]=s[6];
                i++;
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertCountry_City(){
        /*

        First I read csv file and stored latitude and longitude in the arrays
         private double[] latitude=new double[1104];
        double[] longitude = new double[1104];

            As there is large data greater than 1000 so to get one by one country and city
            it took a lot of time in executing the program, so I did this for one time i.e. get
            city and country by providing longitude and latitude and stored the data in
            text file now for all other steps I am using that txt file.
            Below is the code that I used to get country and city from GeoCoding library:
            NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI();
        try {
            BufferedWriter w=new BufferedWriter(new FileWriter("PBL3/Country_and_city.txt",true));

            for(int i=1;i<longitude.length;i++) {
                country[i]=nominatim1.getAdress(latitude[i],(longitude[i])).getCountry();
                city[i]=nominatim1.getAdress(latitude[i],longitude[i]).getCity();
                w.write(country[i]+" : "+city[i]+"\n");
                System.out.println(country[i]+" : "+city[i]);
            }
            w.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        One more thing I want to share with you is that when you wil use above code it may give you error
        then what you've to do? You've to simply use String arrays of latitude and longitude and when using them
        in above code convert them into double by using Double.parseDouble() method of java
        After doing this task you will get some data in different languages then simply search any
        translator tool that can convert your data into English and here you go!
         */
        File f=new File("PBL3/County.txt");
        File f1=new File("PBL3/Countries_Cities.txt");
        try {

            Scanner scan = new Scanner(f);
            Scanner scanner=new Scanner(f1);
            int i=0;
            while (scan.hasNextLine()){
                String s=scan.nextLine();
                String[] s1=scanner.nextLine().split(":");
                country[i]=s;
                city[i++]=s1[1];
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertIntoCollections() {
        int i=0;
        int j=1;
        for(int k=0;k<52;k++) {
            yearWiseLL[k]=new MyLinkedList();
            while (year[i] == year[j]) {
                yearWiseLL[k].insert(new EarthQuake(country[i],city[i],magnitude[i],date[i]));
                i++;
                j++;
                if(j==1104)break;
            }
            yearWiseLL[k].insert(new EarthQuake(country[i],city[i],magnitude[i],date[i]));
            if(i==1104)break;
            i=j;
            j++;
        }
    }
    public void insertIntoQueue(){
        for (MyLinkedList myLinkedList : yearWiseLL) earthQuackQueue.add(myLinkedList.getMax());
    }
    public void setTotalDifferentCountries(){
        int count=0;
        label:      for(int i=0;i<country.length;i++){
            String cou=country[i];
            for(int j=i-1;j>=0;j--)if(cou.equals(country[j]))continue label;
            totalDifferentCountries[count++]=cou;
        }
    }
    public void setRecentCountryWiseQuacksList( ) {
        MyLinkedStack[] arr=countryStack;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                String[] s=arr[j].peek().getDate().split("/");
                int day1=Integer.parseInt(s[1]);
                int month1=Integer.parseInt(s[0]);
                int year1=Integer.parseInt(s[2]);
                s=arr[j+1].peek().getDate().split("/");
                int day2=Integer.parseInt(s[1]);
                int month2=Integer.parseInt(s[0]);
                int year2=Integer.parseInt(s[2]);
                if(year2!=year1){
                    if(year1>year2){
                        MyLinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
                else if(month2!=month1){
                    if(month1>month2){
                        MyLinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
                else {
                    if(day1>day2){
                        MyLinkedStack temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }

            }

        }
        for(MyLinkedStack s:arr)
            recentCountryWiseQuacksList.insert(new EarthQuake(s.peek().getMagnitude(),s.peek().getDate(),s.getStackName()));
    }

    public double[] getMagnitude() {
        return magnitude;
    }

    public String[] getCountry() {
        return country;
    }

    public String[] getCity() {
        return city;
    }

    public int[] getYear() {
        return year;
    }

    public String[] getDate() {
        return date;
    }

    public String[] getEarthQuackType() {
        return earthQuackType;
    }

    public String[] getTotalDifferentCountries() {
        return totalDifferentCountries;
    }

    public MyLinkedList[] getYearWiseLL() {
        return yearWiseLL;
    }

    public MyLinkedQueue getEarthQuackQueue() {
        return earthQuackQueue;
    }

    public MyLinkedList getRecentCountryWiseQuacksList() {
        return recentCountryWiseQuacksList;
    }

    public MyLinkedStack[] getCountryStack() {
        return countryStack;
    }


}

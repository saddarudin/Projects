package PBL2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    ArrayList<Crash>[] year=new ArrayList[9];
    ArrayList<Crash>[] month=new ArrayList[12];
    ArrayList<Crash>[] day=new ArrayList[31];
    MyQueue monthWiseQueue=new MyQueue();
    MyQueue clearWeatherCrashesQueue=new MyQueue();
    MyQueue snowWeatherCrashesQueue=new MyQueue();
    MyQueue rainWeatherCrashesQueue=new MyQueue();
    MyQueue monday=new MyQueue();
    MyQueue tuesday=new MyQueue();
    MyQueue wednesday=new MyQueue();
    MyQueue thursday=new MyQueue();
    MyQueue friday=new MyQueue();
    MyQueue saturday=new MyQueue();
    MyQueue sunday=new MyQueue();
    BinaryTree hit_and_run_tree;
    BinaryTree not_hit_and_run_tree;
    public void setYear(){
        File file=new File("PBL2/ped_crashes.csv");
        try {
            Scanner scan=new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()){
                String[] parts=scan.nextLine().split(",");
                Crash c=new Crash(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5], parts[6],parts[7],parts[8],parts[9],parts[10],parts[11],parts[12],parts[13],parts[14]);
                switch (parts[0]){
                    case "2018"->{
                        if(year[0]==null)year[0]=new ArrayList();
                        year[0].add(c);
                    }
                    case "2017"->{
                        if(year[1]==null)year[1]=new ArrayList();
                        year[1].add(c);
                    }
                    case "2016"->{
                        if(year[2]==null)year[2]=new ArrayList();
                        year[2].add(c);
                    }
                    case "2015"->{
                        if(year[3]==null)year[3]=new ArrayList();
                        year[3].add(c);
                    }
                    case "2014"->{
                        if(year[4]==null)year[4]=new ArrayList();
                        year[4].add(c);
                    }
                    case "2013"->{
                        if(year[5]==null)year[5]=new ArrayList();
                        year[5].add(c);
                    }
                    case "2012"->{
                        if(year[6]==null)year[6]=new ArrayList();
                        year[6].add(c);
                    }
                    case "2011"->{
                        if(year[7]==null)year[7]=new ArrayList();
                        year[7].add(c);
                    }
                    case "2010"->{
                        if(year[8]==null)year[8]=new ArrayList();
                        year[8].add(c);
                    }

                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void setDay(){
        File file=new File("PBL2/ped_crashes.csv");
        try{
            Scanner scan=new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()){
                String[] parts=scan.nextLine().split(",");
                Crash c=new Crash(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5], parts[6],parts[7],parts[8],parts[9],parts[10],parts[11],parts[12],parts[13],parts[14]);
                switch (parts[2]){
                    case "1"->{
                        if(day[0]==null)day[0]=new ArrayList();
                        day[0].add(c);
                    }
                    case "2"->{
                        if(day[1]==null)day[1]=new ArrayList();
                        day[1].add(c);
                    }
                    case "3"->{
                        if(day[2]==null)day[2]=new ArrayList();
                        day[2].add(c);
                    }
                    case "4"->{
                        if(day[3]==null)day[3]=new ArrayList();
                        day[3].add(c);
                    }
                    case "5"->{
                        if(day[4]==null)day[4]=new ArrayList();
                        day[4].add(c);
                    }
                    case "6"->{
                        if(day[5]==null)day[5]=new ArrayList();
                        day[5].add(c);
                    }
                    case "7"->{
                        if(day[6]==null)day[6]=new ArrayList();
                        day[6].add(c);
                    }
                    case "8"->{
                        if(day[7]==null)day[7]=new ArrayList();
                        day[7].add(c);
                    }
                    case "9"->{ if(day[8]==null)day[8]=new ArrayList();
                        day[8].add(c);}
                    case "10"->{
                        if(day[9]==null)day[9]=new ArrayList();
                        day[9].add(c);
                    }
                    case "11"->{
                        if(day[10]==null)day[10]=new ArrayList();
                        day[10].add(c);
                    }
                    case "12"->{ if(day[11]==null)day[11]=new ArrayList();
                        day[11].add(c);}
                    case "13"->{
                        if(day[12]==null)day[12]=new ArrayList();
                        day[12].add(c);
                    }
                    case "14"->{
                        if(day[13]==null)day[13]=new ArrayList();
                        day[13].add(c);
                    }
                    case "15"->{
                        if(day[14]==null)day[14]=new ArrayList();
                        day[14].add(c);
                    }
                    case "16"->{ if(day[15]==null)day[15]=new ArrayList();
                        day[15].add(c);
                    }
                    case "17"->{ if(day[16]==null)day[16]=new ArrayList();
                        day[16].add(c);}
                    case "18"->{ if(day[17]==null)day[17]=new ArrayList();
                        day[17].add(c);}
                    case "19"->{ if(day[18]==null)day[18]=new ArrayList();
                        day[18].add(c);}
                    case "20"->{ if(day[19]==null)day[19]=new ArrayList();
                        day[19].add(c);}
                    case "21"->{ if(day[20]==null)day[20]=new ArrayList();
                        day[20].add(c);}
                    case "22"->{ if(day[21]==null)day[21]=new ArrayList();
                        day[21].add(c);}
                    case "23"->{
                        if(day[22]==null)day[22]=new ArrayList();
                        day[22].add(c);
                    }
                    case "24"->{ if(day[23]==null)day[23]=new ArrayList();
                        day[23].add(c);}
                    case "25"->{ if(day[24]==null)day[24]=new ArrayList();
                        day[24].add(c);}
                    case "26"->{ if(day[25]==null)day[25]=new ArrayList();
                        day[25].add(c);}
                    case "27"->{ if(day[26]==null)day[26]=new ArrayList();
                        day[26].add(c);}
                    case "28"->{ if(day[27]==null)day[27]=new ArrayList();
                        day[27].add(c);}
                    case "29"->{ if(day[28]==null)day[28]=new ArrayList();
                        day[28].add(c);}
                    case "30"->{ if(day[29]==null)day[29]=new ArrayList();
                        day[29].add(c);}
                    case "31"->{ if(day[30]==null)day[30]=new ArrayList();
                        day[30].add(c);}
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void setMonth(){
        File file=new File("PBL2/ped_crashes.csv");
        try {
            Scanner scan=new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()){
                String[] parts=scan.nextLine().split(",");
                Crash c=new Crash(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5], parts[6],parts[7],parts[8],parts[9],parts[10],parts[11],parts[12],parts[13],parts[14]);
                switch (parts[1]){
                    case "January"->{
                        if(month[0]==null)month[0]=new ArrayList();
                        month[0].add(c);
                    }
                    case "February"->{
                        if(month[1]==null)month[1]=new ArrayList();
                        month[1].add(c);
                    }
                    case "March"->{
                        if(month[2]==null)month[2]=new ArrayList();
                        month[2].add(c);
                    }
                    case "April"->{
                        if(month[3]==null)month[3]=new ArrayList();
                        month[3].add(c);
                    }
                    case "May"->{
                        if(month[4]==null)month[4]=new ArrayList();
                        month[4].add(c);
                    }
                    case "June"->{
                        if(month[5]==null)month[5]=new ArrayList();
                        month[5].add(c);
                    }
                    case "July"->{
                        if(month[6]==null)month[6]=new ArrayList();
                        month[6].add(c);
                    }
                    case "August"->{
                        if(month[7]==null)month[7]=new ArrayList();
                        month[7].add(c);
                    }
                    case "September"->{
                        if(month[8]==null)month[8]=new ArrayList();
                        month[8].add(c);
                    }
                    case "October"->{
                        if(month[9]==null)month[9]=new ArrayList();
                        month[9].add(c);
                    }
                    case "November"->{
                        if(month[10]==null)month[10]=new ArrayList();
                        month[10].add(c);
                    }
                    case "December"->{
                        if(month[11]==null)month[11]=new ArrayList();
                        month[11].add(c);
                    }

                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void setMonthWiseQueue(){
        for(ArrayList<Crash> list:month)
            for (Crash crash : list) monthWiseQueue.add(crash);
    }
    public void setSubQueues(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        for(int i=0;i<size;i++){
            Crash c=queue.remove();
            switch (c.getWeather_condition()) {
                case "Clear" -> clearWeatherCrashesQueue.add(c);
                case "Rain" -> rainWeatherCrashesQueue.add(c);
                case "Snow" -> snowWeatherCrashesQueue.add(c);
            }
        }
    }
    public void setWeekQueues(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        for(int i=0;i<size;i++){
            Crash c=queue.remove();
            switch (c.getDay().toLowerCase()){
                case "monday"->monday.add(c);
                case "tuesday"->tuesday.add(c);
                case "wednesday"->wednesday.add(c);
                case "thursday"->thursday.add(c);
                case "friday"->friday.add(c);
                case "saturday"->saturday.add(c);
                case "sunday"->sunday.add(c);
            }
        }
    }
    public void fillTree(){
        MyQueue queue=monthWiseQueue.copy();
        int size= queue.size();
        String month="January";
        int hit_run=0,not_hit_run=0;
        for(int i=0;i<size;i++){
            Crash c=queue.remove();
            if(i==size-1){
                if(c.getHit_run().equals("Hit-and-run"))hit_run++;
                else if (c.getHit_run().equals("Not hit-and-run"))not_hit_run++;
                hit_and_run_tree.add("left",hit_run+","+month);
                not_hit_and_run_tree.add("left",not_hit_run+", "+month);
                return;
            }
            if (!month.equals(c.getMonth())) {
                boolean b = month.equals("February") || month.equals("April") || month.equals("June") || month.equals("August") || month.equals("October") ;
                if (hit_and_run_tree == null) hit_and_run_tree = new BinaryTree(hit_run+", "+month);
                else {
                    if (b) hit_and_run_tree.add("left", hit_run+", "+month);
                    else hit_and_run_tree.add("right", hit_run+", "+month);
                }
                if (not_hit_and_run_tree == null) not_hit_and_run_tree = new BinaryTree(not_hit_run+", "+month);
                else {
                    if (b) not_hit_and_run_tree.add("left", not_hit_run+", "+month);
                    else not_hit_and_run_tree.add("right", not_hit_run+", "+month);
                }
                month = c.getMonth();
                hit_run = 0;
                not_hit_run = 0;
            }
            if(c.getHit_run().equals("Hit-and-run"))hit_run++;
            else if (c.getHit_run().equals("Not hit-and-run"))not_hit_run++;

        }
    }
    public String deadliestDay(MyQueue day){
        //max1: maximum cases for current date and max2 for maximum no of  cases on any date
        int max1=1,max2=1;
        //duplicate queue so that remove method can be called
        MyQueue queue=day.copy();
        //size of queue
        int size= queue.size();
        //crash object for getting starting date
        Crash c=queue.remove();
        //current date
        String date=c.getDate()+"/"+c.getMonth()+"/"+c.getYear();
        //initializing tempDate
        String tempDate=date;
        String date1="";
        for(int i=1;i<size;i++){
            //getting one by one object
            Crash c1=queue.remove();
            //getting date of every object
            date1=c1.getDate()+"/"+c1.getMonth()+"/"+c1.getYear();
            //checking weather date is same
            if(date1.equals(date))max1++;
            //if date has been changed
            else{
                //if cases on previous date are greater than the previous maximum cases
                if(max1>max2)tempDate=date;
                //getting the max no of cases
                max2=Math.max(max1,max2);
                max1=1;
                //updating the current date
                date=date1;
            }
        }
        if(max1>max2)tempDate=date;
        return Math.max(max1,max2)+", were the maximum number of cases happened on "+tempDate+" on "+c.getDay();
    }
    public void question1( ){
        int max1,max2,maxFriday;
        String fridayDate;
        String[] s=deadliestDay(monday).split(",");
        String str=s[1];
        max1=Integer.parseInt(s[0]);
        String[] s1=deadliestDay(tuesday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(wednesday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(thursday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max2,max1);
        s1=deadliestDay(friday).split(",");
        max2=Integer.parseInt(s1[0]);
        maxFriday=max2;
        fridayDate=s1[1];
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(saturday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        s1=deadliestDay(sunday).split(",");
        max2=Integer.parseInt(s1[0]);
        if(max2>max1)str=s1[1];
        max1=Math.max(max1,max2);
        System.out.println(max1+": "+str);
        System.out.println(maxFriday+","+fridayDate+". It was the deadliest Friday among all the Fridays");

    }
    public void question2(){
        System.out.println("Rainy day crashes of every month");
        MyQueue queue=rainWeatherCrashesQueue.copy();
        int size=queue.size();
        int count=0;
        String month=queue.remove().getMonth();
        for(int i=1;i<size;i++){
            String month1=queue.remove().getMonth();
            if(month1.equals(month))count++;
            else {
                System.out.println(month+" crashes "+count);
                count=0;
                month=month1;
            }
        }
        System.out.println(month+" crashes "+count);
    }
    public void question3(){
        System.out.println("1. Which month had the lowest \"hit and run\" crashes?\t"+hit_and_run_tree.getSmallest());
        System.out.println("2. Which month had the highest \"hit and run\" crashes?\t"+hit_and_run_tree.getLargest());
        System.out.println("3. Which month had the lowest \"not hit and run\" crashes?\t"+not_hit_and_run_tree.getSmallest());
        System.out.println("4. Which month had the highest \"not hit and run\" crashes?\t"+not_hit_and_run_tree.getLargest());
        System.out.println("Enter your choice: ");
    }
    public void postulate1(){
        int mondaysCrashes=0,tuesdaysCrashes=0,saturdaysCrashes=0,sundayCrashes=0;
        MyQueue[] queues={monday,tuesday,saturday,sunday};
        for(MyQueue q:queues){
            int size=q.size();
            for(int i=0;i<size;i++){
                String lightCondition=q.remove().getLight_condition();
                if(lightCondition.equals("Dark lighted")){
                    if (monday.equals(q)) mondaysCrashes++;
                    else if (tuesday.equals(q)) tuesdaysCrashes++;
                    else if (saturday.equals(q)) saturdaysCrashes++;
                    else if (sunday.equals(q)) sundayCrashes++;
                }
            }
        }
        System.out.println("Crashes when light condition was dark lighted");
        System.out.println("Monday Crashes "+mondaysCrashes);
        System.out.println("Tuesday Crashes "+tuesdaysCrashes);
        System.out.println("Saturday Crashes "+saturdaysCrashes);
        System.out.println("Sunday Crashes "+sundayCrashes);
    }

    public static void main(String[] args) {
        Solution s=new Solution();
        s.setMonth();
        s.setMonthWiseQueue();
        s.setSubQueues();
        s.setWeekQueues();
        s.fillTree();
        s.postulate1();
    }
}

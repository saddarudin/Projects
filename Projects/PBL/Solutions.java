package PBL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Solutions {

    //PakUni array has been created to store universities' objects
    PakUni[] universities=new PakUni[100];//values
    //String array to store keys of every object of PakUni
    String[] keys=new String[100];

    //Constructor to fetch data from csv file to PakUni array
    //and txt file to keys array

    //Hashmap: to store data in key value pair
    private static final MyHashTable map=new MyHashTable(100);
    //stack1 for storing data order by noOfPublication
    private static final MyLinkedStack stack1=new MyLinkedStack();
    //stack2 for storing data order by PakRanking
    private static final MyLinkedStack stack2=new MyLinkedStack();
    private static final MyLinkedStack SindhUETs=new MyLinkedStack();
    private static final MyLinkedStack PunjabUETs=new MyLinkedStack();
    private static final MyLinkedStack BalochistanUETs=new MyLinkedStack();
    private static final MyLinkedStack KPK_UETs=new MyLinkedStack();
    private static final MyLinkedStack AJK_UETs=new MyLinkedStack();
    private static final MyLinkedStack GilgitBiltistanUETs=new MyLinkedStack();
    public void readFile(){
        //Reading from csv file and txt file
        File file=new File("PBL/Top 100 Unis of Pakistan.csv");
        File file1=new File("PBL/Keys.txt");
        try {
            int i=0;
            Scanner scan = new Scanner(file);
            Scanner scanner=new Scanner(file1);
            while (scan.hasNextLine()){
                String[] parts= scan.nextLine().split(",");
                universities[i]=new PakUni(parts[0],Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),
                        parts[4],parts[5],Integer.parseInt(parts[6]));
                keys[i]=scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //to insert data into hashmap and also return map
    public void insertIntoMap(){
        for(int i=0;i< universities.length;i++)map.put(keys[i],universities[i]);
    }
    public void insertIntoStack(String orderBy){
        for(int i=0;i< keys.length;i++){
            if(orderBy.equals("noOfPublications")) stack1.push(keys[i],universities[i],orderBy);
            else if(orderBy.equals("PakRanking")){
                stack2.push(keys[i],universities[i],orderBy);
                switch (universities[i].getProvince()){
                    case "Sindh"->SindhUETs.push(keys[i],universities[i],orderBy);
                    case "Punjab"->PunjabUETs.push(keys[i],universities[i],orderBy);
                    case "Balochistan"->BalochistanUETs.push(keys[i],universities[i],orderBy);
                    case "KPK"->KPK_UETs.push(keys[i],universities[i],orderBy);
                    case "AJK"->AJK_UETs.push(keys[i],universities[i],orderBy);
                    case "Gilgit Biltistan"->GilgitBiltistanUETs.push(keys[i],universities[i],orderBy);
                }
            }
        }

    }
    public static MyHashTable getMap(){return map;}

    public static MyLinkedStack getStack1(){return stack1;}
    public static MyLinkedStack getStack2(){return stack2;}

    public static MyLinkedStack getSindhUETs() {
        return SindhUETs;
    }

    public static MyLinkedStack getPunjabUETs() {
        return PunjabUETs;
    }

    public static MyLinkedStack getGilgitBiltistanUETs() {
        return GilgitBiltistanUETs;
    }

    public static MyLinkedStack getKPK_UETs() {
        return KPK_UETs;
    }

    public static MyLinkedStack getBalochistanUETs() {
        return BalochistanUETs;
    }

    public static MyLinkedStack getAJK_UETs() {
        return AJK_UETs;
    }

    //Problem1
    public PakUni search(MyHashTable map,String key){
        return map.get(key);
    }//searching from hashtable
    public PakUni[] topX(MyLinkedStack stack,int X){
        PakUni[] uet=new PakUni[X];
        String[] keys=new String[X];
        MyHashTable map=getMap();
        for(int i=0;i<X;i++){
            keys[i]=stack.pop();
            uet[i]=map.get(keys[i]);
        }
        for(int i=X-1;i>=0;i--)stack.push(keys[i]);
        return uet;
    }
    public PakUni[] bottomX(MyLinkedStack stack,int X){
        PakUni[] uet=new PakUni[X];
        MyHashTable map=getMap();
        String[] keys=new String[map.size()];
        int count=0;
        for(int i=0;i< map.size();i++) keys[i]=stack.pop();
        for(int i=map.size()-X;i< map.size();i++)uet[count++]=map.get(keys[i]);
        for(int i=99;i>=0;i--)stack.push(keys[i]);
        return uet;
    }
    public PakUni percentile(int percentile,MyLinkedStack stack){
        /*
        The percentile formula determines the performance of anything
        over other things of same class.
        For example: The percentile formula is used in finding where a
        student stands in the test compared to other candidates.
        A percentile is a number where a certain percentage of scores
        fall below the given number.
         */
        /*
        formula : n=P/100*N where
        n=ordinal rank
        N=No of values in data;
        P=percentile
         */
        int position=(percentile*stack.size())/100;
        return getMap().get(stack.get(position));
    }
    public String performance(){
        String x="",y= "";
        int punjabPub=0,sindhPub=0,balPub=0,kpkPub=0,ajkPub=0,gilgitPub=0;
        MyLinkedStack stack1=getSindhUETs();
        MyLinkedStack stack2=getPunjabUETs();
        MyLinkedStack stack3=getBalochistanUETs();
        MyLinkedStack stack4=getKPK_UETs();
        MyLinkedStack stack5=getAJK_UETs();
        MyLinkedStack stack6=getGilgitBiltistanUETs();
        for(int i=1;i<=stack1.size();i++)
            sindhPub+=getMap().get(stack1.get(i)).getNoOfPublications();
        for(int i=1;i<=stack2.size();i++)
            punjabPub+=getMap().get(stack2.get(i)).getNoOfPublications();
        for(int i=1;i<=stack3.size();i++)
            balPub+=getMap().get(stack3.get(i)).getNoOfPublications();
        for(int i=1;i<=stack4.size();i++)
            kpkPub+=getMap().get(stack4.get(i)).getNoOfPublications();
        for(int i=1;i<=stack5.size();i++)
            ajkPub+=getMap().get(stack5.get(i)).getNoOfPublications();
        for(int i=1;i<=stack6.size();i++)
            gilgitPub+=getMap().get(stack6.get(i)).getNoOfPublications();
        double avg1=(double) sindhPub/stack1.size();
        double avg2=(double) punjabPub/stack1.size();
        double avg3=(double) balPub/stack1.size();
        double avg4=(double) kpkPub/stack1.size();
        double avg5=(double) ajkPub/stack1.size();
        double avg6=(double) gilgitPub/stack1.size();
        double best,worst;
        best= Math.max(avg1, avg2);
        best= Math.max(best, avg3);
        best= Math.max(best, avg4);
        best= Math.max(best, avg5);
        best= Math.max(best, avg6);
        worst= Math.min(avg1, avg2);
        worst= Math.min(worst, avg3);
        worst= Math.min(worst, avg4);
        worst= Math.min(worst, avg5);
        worst= Math.min(worst, avg6);
        if (best == avg1) x = "Sindh";
        else if (best == avg2) x = "Punjab";
        else if (best == avg3) x = "Balochistan";
        else if (best == avg4) x = "KPK";
        else if (best == avg5) x = "AJK";
        else if (best == avg6) x = "Gilgit";
        if (worst == avg1) y = "Sindh";
        else if (worst == avg2) y = "Punjab";
        else if (worst == avg3) y = "Balochistan";
        else if (worst == avg4) y = "KPK";
        else if (worst == avg5) y = "AJK";
        else if (worst == avg6) y = "Gilgit";



        return "Best Province: "+x+"\n Worst Province: "+y;
    }

    public static void main(String[] args) {
        Solutions s=new Solutions();
        s.readFile();
        s.insertIntoMap();
        s.insertIntoStack("noOfPublications");
        s.insertIntoStack("PakRanking");
        while (true) {
            System.out.println("\n\n\n");
            System.out.println("1.Search any PakUni by providing key");
            System.out.println("2. Top x universities of Pakistan");
            System.out.println("3. n universities from bottom");
            System.out.println("4. nth Percentile PakUni");
            System.out.println("5. See provincial ranking of the universities");
            System.out.println("6. Best and worst province");
            System.out.println("7.Exit");
            Scanner scan = new Scanner(System.in);
            switch (scan.next()) {
                case "1" -> {
                    System.out.println("Enter four word key: ");
                    String str=scan.next();
                    System.out.println("wRank\t   aRank\t   pRank\tPub\t\t\t\t\t\t\tLoc\t\t\t\t\t\t\tProvince\t\t\t\t\t\t\t\t\t\tName");
                    System.out.println(getMap().get(str));
                }
                case "2" -> {
                    System.out.println("Enter number of universities: ");
                    int b = Integer.parseInt(scan.next());
                    System.out.println("On the basis of: ");
                    System.out.println("1> Number of publication ");
                    System.out.println("2> Pakistani Ranking ");
                    System.out.println("Your choice: ");
                    String a = scan.next();
                    System.out.println("wRank\t   aRank\t   pRank\tPub\t\t\t\t\t\t\tLoc\t\t\t\t\t\t\tProvince\t\t\t\t\t\t\t\t\t\tName");
                    if (a.equals("1"))
                        for (PakUni u : s.topX(getStack1(), b))
                            System.out.println(u);
                    else if (a.equals("2"))
                        for (PakUni u : s.topX(getStack2(), b))
                            System.out.println(u);
                }
                case "3" -> {
                    System.out.println("Enter number of universities: ");
                    int b = Integer.parseInt(scan.next());
                    System.out.println("On the basis of: ");
                    System.out.println("1> Number of publication ");
                    System.out.println("2> Pakistani Ranking ");
                    System.out.println("Your choice: ");
                    String a = scan.next();
                    System.out.println("wRank\t   aRank\t   pRank\tPub\t\t\t\t\t\t\tLoc\t\t\t\t\t\t\tProvince\t\t\t\t\t\t\t\t\t\tName");
                    if (a.equals("1"))
                        for (PakUni u : s.bottomX(getStack1(), b))
                            System.out.println(u);
                    else if (a.equals("2"))
                        for (PakUni u : s.bottomX(getStack2(), b))
                            System.out.println(u);
                }
                case "4" -> {
                    System.out.println("Enter percentile: ");
                    int b = Integer.parseInt(scan.next());
                    System.out.println("On the basis of: ");
                    System.out.println("1> Number of publication ");
                    System.out.println("2> Pakistani Ranking ");
                    System.out.println("Your choice: ");
                    String a = scan.next();
                    System.out.println("wRank\t   aRank\t   pRank\tPub\t\t\t\t\t\t\tLoc\t\t\t\t\t\t\tProvince\t\t\t\t\t\t\t\t\t\t\tName");
                    if (a.equals("1"))
                        System.out.println(s.percentile(b, getStack1()));
                    else if (a.equals("2"))
                        System.out.println(s.percentile(b, getStack2()));
                }
                case "5" -> {
                    System.out.println("1.Top Ranked Universities of Sindh");
                    System.out.println("2.Top Ranked Universities of Punjab");
                    System.out.println("3.Top Ranked Universities of Balochistan");
                    System.out.println("4.Top Ranked Universities of KPK");
                    System.out.println("5.Top Ranked Universities of AJK");
                    System.out.println("6.Top Ranked Universities of Gilgit Biltistan");
                    String c = scan.next();
                    System.out.println("wRank\t   aRank\t   pRank\tPub\t\t\t\t\t\t\tLoc\t\t\t\t\t\t\tProvince\t\t\t\t\t\t\t\t\t\tName");
                    switch (c) {
                        case "1" -> getSindhUETs().traverse();
                        case "2" -> getPunjabUETs().traverse();
                        case "3" -> getBalochistanUETs().traverse();
                        case "4" -> getKPK_UETs().traverse();
                        case "5" -> getAJK_UETs().traverse();
                        case "6" -> getGilgitBiltistanUETs().traverse();
                    }
                }
                case "6" -> System.out.println(s.performance());
                case "7"->System.exit(0);
            }
        }
    }

}

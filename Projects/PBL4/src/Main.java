import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private EarthQuake[] quakes=new EarthQuake[30];
    private MyQueue queue=new MyQueue();
    private String[] countries={"United States","United Kingdom","France","Japan","Russia","Greece","Australia","Pakistan"};
    private MyStack[] countryStacks=new MyStack[countries.length];
    private MyLinkedList list=new MyLinkedList();

    public void readData(){
        try{
            File f=new File("dataset - PBL.csv");
            Scanner scan=new Scanner(f);
            scan.nextLine();
            int i=0;
            while (scan.hasNextLine()){
                String line[]=scan.nextLine().split(",");
                quakes[i++]=new EarthQuake(Integer.parseInt(line[0]),line[1],Double.parseDouble(line[2]));
            }

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void sortQuakesArray(){
        //applying selection sorting
            for(int i=0;i<quakes.length-1;i++){
                int smallest=i;
                for(int j=i+1;j<quakes.length;j++){
                    if(quakes[smallest].getYear()>quakes[j].getYear())
                        smallest=j;
                }
                EarthQuake temp=quakes[smallest];
                quakes[smallest]=quakes[i];
                quakes[i]=temp;
            }

    }
    public void fillQueue(){
        EarthQuake temp=quakes[0];
        int year=temp.getYear();
        for(int i=1;i<quakes.length;i++){
            if(year == quakes[i].getYear()){
                if(temp.getMagnitude()<quakes[i].getMagnitude())temp=quakes[i];
            }
            else {
                queue.enqueue(temp);
                temp=quakes[i];
                year=temp.getYear();
            }
        }
        queue.enqueue(temp);
    }
    public void fillStacks(){
        for(int i=0;i<countries.length;i++){
            countryStacks[i] = new MyStack(countries[i]);
            for(int j=quakes.length-1;j>=0;j--){
                if(countries[i].equals(quakes[j].getCountry()))countryStacks[i].push(quakes[j]);
                if(countryStacks[i].getSize()==5)break;
            }
        }
    }
    public void fillLinkedList(){
        for(MyStack stack:countryStacks){
            MyStack.Node temp=stack.getTop();
            while (temp!=null){
                if(temp.getData().getMagnitude()>6)list.add(temp.getData());
                temp=temp.getNext();
            }
        }
    }




    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Main m=new Main();
        m.readData();
        m.sortQuakesArray();
        m.fillQueue();
        m.fillStacks();
        m.fillLinkedList();
        int choice;
        while (true){
            System.out.println("\n1. Problem 1: Biggest earthquakes from 2009 to 2013");
            System.out.println("2. Problem 2: Recent 5 earth quakes from each country");
            System.out.println("3. Problem 3: Most recent earthquakes above 6 magnitude");
            System.out.println("0. Exit");
            System.out.print("\n\nEnter your choice: ");
            choice= scan.nextInt();
            switch (choice){
                case 1->m.queue.traverse();
                case 2->{
                    for(MyStack stack: m.countryStacks)stack.traverse();
                }
                case 3->m.list.traverse();
                case 0->System.exit(0);
            }
        }
    }
}
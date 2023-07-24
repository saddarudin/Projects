package OnlineComputerShop;

import java.util.Scanner;

public class OnlineComputerShop{
    private static class Node{
        String category;
        String itemCode;
        String description;
        float price;
        Node next;

        public Node(String category, String itemCode, String description, float price) {
            this.category = category;
            this.itemCode = itemCode;
            this.description = description;
            this.price = price;
        }

    }
    public static void main(String[] args) {
        Node head=null;
        Node temp;
        Scanner scan=new Scanner(System.in);
        double totalPrice=0;
        double basePrice = 200;
        double mainComponentsPrice = 0;
        double extraComponentsPrice = 0;
        double discount = 0;
        String[] itemCode = {"A1","A2","B1","B2","B3","C1","C2","C3","D1","D2","E1","E2","E3","F1","F2","G1","G2"};
        String[] description = {"Compact", "Tower", "8GB", "16GB", "32GB", "1TB HDD", "2TB HDD", "4TB HDD",
                "240GB SSD", "480GB SSD", "1TB HDD", "2TB HDD", "4TB HDD", "DVD/Blu-Ray Player",
                "DVD/Blu-Ray Re-writer", "Standard Version", "Professional Version"};
        float[] price = {75,150,79.99f,149.99f,299.99f,49.99f,89.99f,129.99f,59.99f,119.99f,49.99f,89.99f,
                129.99f,50,100,100,175};
        String input="";
        System.out.println("Item code\tprice\t\tdescription");
        for(int i=0;i<price.length;i++)System.out.print(itemCode[i]+"\t\t\t"+price[i]+"\t\t"+description[i]+"\n");
        System.out.println("Enter A1 or A2 to select the case: ");
        input=scan.next();
        if(input.equals("A1")||input.equals("a1"))head = new Node("Case",itemCode[0],description[0],price[0]);
        else if(input.equals("A2")||input.equals("a2"))head = new Node("Case",itemCode[1],description[1],price[1]);
        else {
            System.out.println("Incorrect Input");
            main(null);
        }
        temp=head;
        System.out.println("Enter B1 or B2 or B3 to select the RAM: ");
        input=scan.next();
        if(input.equals("B1")||input.equals("b1")) temp.next = new Node("RAM",itemCode[2],description[2],price[2]);

        else if(input.equals("B2")||input.equals("b2"))temp.next = new Node("RAM",itemCode[3],description[3],price[3]);
        else if(input.equals("B3")||input.equals("b3"))temp.next = new Node("RAM",itemCode[4],description[4],price[4]);
        else {
            System.out.println("Incorrect Input");
            main(null);
        }
        temp=temp.next;
        System.out.println("Enter C1 or C2 or C3 to select the Main HDD: ");
        input=scan.next();
        if(input.equals("C1")||input.equals("c1")) temp.next= new Node("Main HDD",itemCode[5],description[5],price[5]);
        else if(input.equals("C2")||input.equals("c2")) temp.next = new Node("Main HDD",itemCode[6],description[6],price[6]);

        else if(input.equals("C3")||input.equals("c3")) temp.next= new Node("Main HDD",itemCode[7],description[7],price[7]);

        else {
            System.out.println("Incorrect Input");
            main(null);
        }
        while (true){
            temp=temp.next;
            System.out.println("Want more? (y/n)");
            input = scan.next();
            if(input.equals("y")){
                System.out.println("Enter item code(D1 | D2 | E1 | E2 | E3 | F1 | F2 | G1 | G2): ");
                input=scan.next();
                if (input.equals("D1") || input.equals("d1")) temp.next=new Node("SSD",itemCode[8],description[8],price[8]);
                else if (input.equals("D2") || input.equals("d2")) temp.next=new Node("SSD",itemCode[9],description[9],price[9]);
                 else if (input.equals("E1") || input.equals("e1"))temp.next=new Node("Second HDD",itemCode[10],description[10],price[10]);
                 else if (input.equals("E2") || input.equals("e2"))  temp.next=new Node("Second HDD",itemCode[11],description[11],price[11]);
                 else if (input.equals("E3") || input.equals("e3"))   temp.next=new Node("Second HDD",itemCode[12],description[12],price[12]);
                 else if (input.equals("F1") || input.equals("f1"))   temp.next=new Node("Optical Drive",itemCode[13],description[13],price[13]);
                 else if (input.equals("F2") || input.equals("f2"))   temp.next=new Node("Optical Drive",itemCode[14],description[14],price[14]);
                 else if (input.equals("G1") || input.equals("g1"))  temp.next=new Node("Operating System",itemCode[15],description[15],price[15]);
                 else if (input.equals("G2") || input.equals("g2"))  temp.next=new Node("Operating System",itemCode[16],description[16],price[16]);
            }else break;
        }
        int count=1;
        int noOfExtraComponents = 0;
        System.out.println("Item code\tPrice\t\tCategory\tDescription");
        for(Node n=head;n!=null;n=n.next){
            System.out.println(n.itemCode+"\t\t\t"+n.price+"\t\t"+n.category+"\t\t" +n.description);
            if(count<4)mainComponentsPrice+=n.price;
            else if(count>=4){
                extraComponentsPrice+=n.price;
                noOfExtraComponents++;
            }
            count++;
        }
        if(noOfExtraComponents == 1)discount = extraComponentsPrice /20;
        else if(noOfExtraComponents >1)discount = extraComponentsPrice/10;
        totalPrice = basePrice+extraComponentsPrice+mainComponentsPrice-discount;
        System.out.println("Main components price: "+mainComponentsPrice);
        System.out.println("Extra components price: "+extraComponentsPrice);
        System.out.println("Base price: "+basePrice);
        System.out.println("Discount: "+discount);
        System.out.println("Total Price: "+totalPrice);




//        System.out.print(itemCode[0]+"\t\t\t\t"+description[0]+"\t\t\t"+price[0]);
//        System.out.print("\n"+itemCode[1]+"\t\t\t\t"+description[1]+"\t\t\t"+price[1]);
//        System.out.println("\nEnter item code to select case: ");
//        input=scan.next();
//        if(input.equals("A1"))totalPrice+=price[0];
//        else if(input.equals("A2"))totalPrice+=price[1];
//        System.out.println("Item code\t\tdescription\t\tprice");
//        System.out.print(itemCode[2]+"\t\t\t\t"+description[2]+"\t\t\t"+price[2]);
//        System.out.print("\n"+itemCode[3]+"\t\t\t\t"+description[3]+"\t\t\t"+price[3]);
//        System.out.print("\n"+itemCode[4]+"\t\t\t\t"+description[4]+"\t\t\t"+price[4]);
//        System.out.println("\nEnter item code to select RAM: ");
//        input=scan.next();
//        if(input.equals("B1"))totalPrice+=price[2];
//        else if(input.equals("B2"))totalPrice+=price[3];
//        else if(input.equals("B3"))totalPrice+=price[4];
//
//        System.out.println(totalPrice);


    }


}

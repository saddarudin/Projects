import java.util.Scanner;
public class ATM_Machine {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int i=1;
        do {
            ++i;
            System.out.println("Enter User ID: ");
            String id1=sc.nextLine();
            if(id1.equals(Administrator.login)){
                System.out.println("Enter PIN: ");
                String PIN=sc.nextLine();
                if(PIN.equals(Administrator.pin)){
                    System.out.println("Log In Successfully.");
                    System.out.println("Press enter");
                    sc.nextLine();
                    for(int k=0;k<30;k++) System.out.println();
                    Administrator admin=new Administrator();
                    Customers customer=new Customers();
                    System.out.println("\n          1....ADMINISTRATOR\n\n          2....Customers\n\n          3....Exit");
                    String select=sc.next();
                    switch (select) {
                        case "1" -> admin.display();
                        case "2" -> customer.displayData();
                        case "3" -> System.exit(0);
                        default -> {
                            System.out.println("Please select correct option");
                            ATM_Machine.main(null);
                        }
                    }
                }else{
                    System.out.println("Incorrect Pin");
                }
            }else{
                System.out.println("Account does not exist!!");
            }

        }while (i<=3);
        System.out.println("Login failed wait for 5 seconds");
        try{
            Thread.sleep(5000);
            for(int j=0;j<30;j++) System.out.println();
            ATM_Machine.main(null);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

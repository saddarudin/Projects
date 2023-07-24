import java.util.Scanner;
public class Customers{
    /*Customer Class for customers
    Customer can perform one of the following:
1----Withdraw Cash
2----Cash Transfer
3----Deposit Cash
4----Display Balance
5----Exit
*/
//    Administrator admin;
    static int accountNumber;
    static String date;
    static double rupees;
    double maxAmount=20000;
    public Customers(){
//        admin=new Administrator();
        Customers.accountNumber=Administrator.accountNumber;
        Customers.date=Administrator.date;
        //this is total balance provided by admin
        Customers.rupees=Administrator.balance;
    }

    //maxAmount is amount a user can withdraw in one day
    Scanner scan=new Scanner(System.in);
    //Scanner class to get input from user


    public void displayData(){
        //Display method to display options to user

        for (int i = 0; i < 30; i++) System.out.println();
        System.out.println("CUSTOMER MENU\n");
        System.out.println("1----Withdraw Cash");
        System.out.println("2----Cash Transfer");
        System.out.println("3----Deposit Cash");
        System.out.println("4----Display Balance");
        System.out.println("5----Back\n");
        System.out.println("Please select one of the following options: ");

            //We take string because user may encounter any character
            String b=scan.nextLine();
            for(int i=0; i<30;i++) System.out.println();
        switch (b) {
            case "1" -> withdraw();
            case "2" -> transfer();
            case "3" -> deposit();
            case "4" -> displayBalance();
            case "5" -> ATM_Machine.main(null);
            default -> {
                System.out.println("                                *** Please enter correct input!!  ***" +
                        "\n-----------------------------------------------------------------------------------------------------------------------------");
                displayData();
            }
        }
    }
    public void withdraw(){
        System.out.println("                                     1----Withdraw Cash\n");
        System.out.println("NOTE: A single account holder cannot withdraw more then 20,000 in one day");
        System.out.println("        a)Fast Cash\n        b)Normal Cash\n        c)Back\nPlease select a mode of withdrawal");
        String s=scan.nextLine();
        for (int i = 0; i < 30; i++) System.out.println();
        switch (s) {
            case "a" -> {
                System.out.println("            a) Fast Cash");
                System.out.println("1----500\n2----1000 \n3----2000 \n4----5000 \n5----10000 \n6----15000 \n7----20000 \n");
                System.out.println("Select one of the denominations of money:");
                String st = scan.nextLine();
                String cash = null;
                switch (st) {
                    case "1" -> cash = "500";
                    case "2" -> cash = "1000";
                    case "3" -> cash = "2000";
                    case "4" -> cash = "5000";
                    case "5" -> cash = "10000";
                    case "6" -> cash = "15000";
                    case "7" -> cash = "20000";
                    default -> {
                        for (int i = 0; i < 30; i++) System.out.println();
                        System.out.println("Please select appropriate option");
                        withdraw();
                    }
                }
                if (cash != null) {
                    double enteredCash = Double.parseDouble(cash);
                    if (enteredCash > Customers.rupees) {
                        System.out.println("You do not have sufficient balance");
                        withdraw();
                    } else {

//                        maxAmount = maxAmount - enteredCash;
                        if (enteredCash>maxAmount) {
                            System.out.println("Transaction limit exceeded");
                            withdraw();
                        } else {

                            System.out.println("Are you sure you want to withdraw Rs." + cash + " (Y/N)?");
                            String yes_no = scan.nextLine();
                            if (yes_no.equals("y")) {
                                maxAmount = maxAmount - enteredCash;
                                Customers.rupees = Customers.rupees - enteredCash;
                                Administrator.balance-=enteredCash;
                                System.out.println("Do you wish to print a receipt (Y/N)?");
                                String confirm = scan.nextLine();
                                if (confirm.equals("y")) {
                                    System.out.println("Account # "+Customers.accountNumber);
                                    System.out.println("Date: "+Customers.date);
                                    System.out.println("Withdrawn: "+cash);
                                    System.out.println("Remaining Balance: "+Customers.rupees);
                                    System.out.println("\nType 'ok'");
                                    scan.nextLine();
                                    displayData();
                                }
                                else displayData();

                            }
                            else displayData();
                        }
                    }
                }
            }
            case "b" -> {
                System.out.println("            b) Normal Cash");
                System.out.println("Enter the withdrawal amount: ");
                String withdraw = scan.nextLine();
                //Exception handling
                try {
                    double amount = Double.parseDouble(withdraw);
                    if (amount < 0) throw new Exception();
                    if (amount > Customers.rupees) {
                            System.out.println("You do not have sufficient balance to perform this transaction");
                            withdraw();
                        } else {
                            if(amount>maxAmount){
                                System.out.println("Daily Limit exceeded");
                                withdraw();
                            }else{
                                Customers.rupees = Customers.rupees - amount;
                                Administrator.balance-=amount;
                                maxAmount=maxAmount-amount;
                                System.out.println("Cash Successfully Withdrawn!");
                                System.out.println("Do you wish to print receipt(y/n)?");
                                String c = scan.nextLine();
                                if (c.equals("y")) {
                                    System.out.println("Account # "+Customers.accountNumber);
                                    System.out.println("Date: "+Administrator.date);
                                    System.out.println("Withdrawn: "+amount);
                                    System.out.println("Remaining Balance: " + Customers.rupees);
                                    System.out.println("Type 'ok'");
                                    scan.nextLine();
                                    displayData();
                                }
                                else displayData();
                            }
                        }


                } catch (Exception e) {
                    System.out.println("Please enter correct amount");
                    withdraw();
                }
            }
            case "c" -> displayData();
            default -> {
                for (int i = 0; i < 30; i++) System.out.println();
                System.out.println("Please select appropriate option");
                withdraw();
            }
        }
    }
    public void transfer(){
        System.out.println("2----Cash Transfer");
        System.out.println("Enter amount in multiples of 500: ");
        String enteredMoney=scan.nextLine();
        try{
            int enteredAmount=Integer.parseInt(enteredMoney);
            if(enteredAmount>0 && enteredAmount%500==0){
                if(enteredAmount>Customers.rupees){
                    System.out.println("You do not have sufficient balance to perform this transaction");
                    System.out.println("Type 'ok'");
                    scan.nextLine();
                    displayData();
                }else{
                    System.out.println("Enter the account number to which you want to transfer: ");
                    String accountNo=scan.nextLine();
                    System.out.println("You wish to deposit Rs "+enteredAmount+" in account held by Mr. ABC ; \nPlease re-enter the account number: ");
                    String confirmAccountNo=scan.nextLine();
                    if(accountNo.equals(confirmAccountNo)){
                        Customers.rupees=Customers.rupees-enteredAmount;
                        Administrator.balance-=enteredAmount;
                        System.out.println("Transaction successful");
                        System.out.println("Do you wish to print a receipt (Y/N)?");
                        String c=scan.nextLine();
                        if(c.equals("y")){
                            System.out.println("Account number: "+accountNo+"\nDate: "+Customers.date+"\nAmount transferred: "+enteredAmount);
                            System.out.println("Remaining Balance: "+Customers.rupees);
                            System.out.println("\nType 'ok'");
                            scan.nextLine();
                            displayData();
                        }
                        else{
                            displayData();
                        }
                    }else{
                        System.out.println("Account number does not match to previous");
                        System.out.println("Type 'ok'");
                        scan.nextLine();
                        displayData();
                    }
                }

            }else{
                System.out.println("Enter correct amount");
                System.out.println("Type 'ok'");
                scan.nextLine();
                displayData();
            }
        }catch(Exception e){
            System.out.println("Please enter correct amount multiple of 500");
            System.out.println("Type 'ok'");
            scan.nextLine();
            displayData();
        }

    }
    public void deposit(){
        System.out.println("3----Deposit Cash");
        System.out.println("Enter the cash amount to deposit: ");
        String deposit=scan.nextLine();
        try{
            double depositingAmount=Double.parseDouble(deposit);
            if(depositingAmount<=0){
                System.out.println("Please enter correct amount");
                deposit();
            }
            System.out.println("Cash deposited successfully");
            Customers.rupees+=depositingAmount;
            Administrator.balance+=depositingAmount;
            System.out.println("Do you wish to print a receipt (y/n)? ");
            String c=scan.nextLine();
            if(c.equals("y")){
                System.out.println("Account number # "+Customers.accountNumber+"\nDate: "+Customers.date);
                System.out.println("Deposited: "+depositingAmount);
                System.out.println("Total Balance: "+Customers.rupees);
                System.out.println("Type 'ok'");
                scan.nextLine();
                displayData();
            }else displayData();
        }catch(Exception e){
            System.out.println("Please provide valid amount");
            displayData();
        }
    }
    public void displayBalance(){
        System.out.println("Account # "+Customers.accountNumber);
        System.out.println("Date: "+Customers.date);
        System.out.println("Balance: "+Customers.rupees);
        System.out.println("\nType 'ok'");
        scan.nextLine();
        displayData();
    }
}

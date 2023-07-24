#include<iostream>
#include<fstream>
#include<string>
#include<conio.h>
#include<iomanip>
#include<cstring>

using namespace std;

class Easypaisa
{
	private:
		string password;
		string name; 
		float balance,spbalance,showbalance;
    public:
    	 Easypaisa()
    	{
    		spbalance=0.0;
    		showbalance=0.0;    		
		}
		
	    string pin,username;
	    int counter=0;
	    void title();
	    void showdata();
	    void newaccount();
        void signin();
	    void setpass();
	    void login();
	    void callpass();
	    void menu();
	    void moneytransfer();
        void mobileload();
        void billpayment();
        void payments();
        void confirm();

};

/*        ****************** TITLE ********************                    */
                    
void Easypaisa :: title()
{
	cout<<"\n\n";
	cout<<setw(60);
 for(int i=0;i<42;i++)
 {
	cout<<(char)3;
 if(i==20)
	 {
	 	cout<<endl<<setw(60)<<(char)3<<"                   "<<(char)3;
	 	cout<<endl<<setw(60)<<(char)3<<" Easypaisa Service "<<(char)3;
	 	cout<<endl<<setw(60)<<(char)3<<"                   "<<(char)3;	 
		cout<<endl<<setw(60);  
	 }   
 }
    cout<<"\n\n";
}

/*        ************ Confirm Message  ***************         */

void Easypaisa :: confirm()
{
	cout<<"\n\nPress 1 to confirm\n";
	cout<<"\n\nPress 2 to cancel\n";
	int to;
	cin>>to;
	system("cls");
	title();	
  if(to==1)
  {
    cout<<"\n\nYour transaction has been completed succesfully\n\n";
    cout<<"\n\nPress any key to go to main menu\n\n";
    getch();
    menu();
  }
  else if(to==2)
  {
	cout<<"\nYou're transaction has been canceled:\n";
	cout<<"\nPress any key to go to main menu\n";
	getch();
	balance+=spbalance;
	menu();				
  }
  else
  {
	menu();
  }

}

/*    ************************ Show Data **************************           */

void Easypaisa :: showdata()
{	
	system("cls");
	title();
	cout<<"\n\n1.Create a new account";
	cout<<"\n\n2.Login";
	cout<<"\n\nEnter your choice: ";   
	int a;
	cin>>a;
	system("cls");
		
  switch(a)
  {/*    ************** New Account **************          */	
	case 1:
		{
			newaccount();
	        setpass();
	        cout<<"\nWould you like to see your password?(y/n) \n";
	        char character;
	        character=getch();
          if(character=='y'||character=='Y')
		  {
			cout<<"\nYou Entered: "<<password<<endl;
			cout<<"\nPress any key\n";
            getch();
            system("cls");
            title();                
		  }
			system("cls");
			title();
label:      cout<<"\n\nConfirm password: ";
            callpass();
          if(password==pin)
		  {
			system("cls");
	        cout<<"\n\nYou've created your account succesfully ....";
	        cout<<endl<<"\nPress any key to log in: \n";
	        getch();
	        system("cls");
	        login();
		  } 
		  else
		  {
			system("cls");
	        cout<<"\n\nIncorrect confirm password! Try Again\n\n";
			pin.erase(0,counter);
			goto label;				
		  } 
                  
		}
		break;
	case 2:
		{
			login();
			break;
		}

 }
}


         /********************* NEW ACCOUNT *****************/

void Easypaisa :: newaccount()
{	
	title();
	cout<<"\n\t\t\t\t\t\t_________*** Creating New Account ***________\n\n";	
	ofstream sadar1("sadar.txt",ios::app);	
	cout<<"Enter Your Name: \n";
    getline(cin,name);
	getline(cin,name); 
	sadar1<<name<<endl;
	cout<<"\nEnter your CNIC (without dashes): \n";	
    long long int nic;
    cin>>nic;
    cout<<"\nEnter Date Of Birth (DD-MM-YY): \n";
    char ch;
    int date,month,year;
	cin>>date>>ch>>month>>ch>>year;
 if(month<0||month>12)
 {  	
    system("cls");
	cout<<"\n\n\t\tIncorrect Month! \n";
	newaccount();
 }
 if(year>2004)
 { 	
    system("cls");
	cout<<"\n\n\t\tYou're Ineligible!!! You are under 18!\n";
	newaccount();
 }
}

/*         *************   SET PASSWORD **************       */


void Easypaisa :: setpass()
{	int len;
	cout<<"\nEnter new pin: \n";
	password.erase(0,len);
	ofstream out("saddar.txt",ios::app);
 for(int i=0;;i++)
 {
    char ch;
	ch=getch();
	
	if(ch==(char)13)break;
	else if(ch==(char)8)
	{
	    password.erase(0,i+1);
		setpass();
	}
	else
	{		
	    password+=ch;
	    cout<<"*";
	}
 } 
    len=password.length();
    out<<name<<password<<"\n";
 
}

     /*********************  CALLING A PASSWORD ***************/
     
void Easypaisa :: callpass()
{
	int leng;
	pin.erase(0,leng);
    counter=0;
 for(int i=0;;i++)
 {
	counter++;
	char ch;
    ch=getch();
   if(ch==(char)13)break;
   else if(ch==(int)8)
   {
	    pin.erase(0,counter+1);
		callpass();
   }
   else
   {		
	    pin+=ch;
        cout<<"*";
   }
 }
    leng=pin.length();	
}

/*   ******************  LOGIN   *******************       */
	
void Easypaisa :: login()
{
	title();	
	cout<<"\n\t\t\t\t\t\t________******  LOGIN  *****_____________\n\n";
	cout<<"\nEnter User Name:(You may have to enter the same name two times) \n";
	getline(cin,username);
	getline(cin,username);
	string s1;
	ifstream sdb("sadar.txt");
  while(sdb.good())
  {
    getline(sdb,s1);
   if(username==s1)
   {  
  	    cout<<"\nEnter Password: \n";
  	    callpass();
  	    string newpin;
  	    ifstream SDB("saddar.txt");
  	 while(SDB.good())
  	 {
  		getline(SDB,newpin);
  	  if(username+pin==newpin)
  	  {
  			cout<<"\nEnter amount to deposit in your account: ";
  			cin>>balance;  			
  			menu();
  			break;
	  }
   	 }
	if(username+pin!=newpin)
	{
		system("cls");
		cout<<"\n\nIncorrect Password! Try Again!\n\n";		
		login();
	}  	
	break;
    } 
	
  }
   if(username!=s1)
   {
       system("cls");  
	   cout<<"Username does not exist\n\n";	 
	  login();
	}
}			

       /**************Main Menu***********/

void Easypaisa :: menu()
{
	system("cls");
    title();
    cout<<"\n\n\t\t\t____________********  Welcome To Main Menu    *********______________\n\n";
	cout<<"1.Money Transfer\n\n";
	cout<<"2.Mobile Load\n\n";
	cout<<"3.Bill Payment\n\n";
	cout<<"4.Payments\n\n";
	cout<<"5.My Account\n\n";
	cout<<"6.LOG OUT\n\n";
	cout<<"Enter your choice: \n";	
	int choose;
	cin>>choose;
 switch(choose)
 {
	case 1:
		{
			moneytransfer();
			break;
		}
		
	case 2:
		{
			mobileload();
			break;
		}
	case 3:
		{
			billpayment();
			break;
		}
	case 4:
		{
			payments();
			break;
		}
	case 5:
		{
			system("cls");
			title();
			cout<<"\n1.Check Balance\n";
			cout<<"\n2.Recharge Balance\n";
			cout<<"\n3.Back to main menu\n";
			cout<<"\nEnter your choice:\n";
			int sele;
			cin>>sele;
			system("cls");
			title();
			if(sele==1)
			{
				cout<<"You're remaining balance is: "<<balance;
				cout<<"\nPress any key to go to main menu\n";
				getch();
				menu();
			}
			else if(sele==2)
			{
				int amountt;
				cout<<"\nEnter amount to recharge: ";
				cin>>amountt;
				balance+=amountt;
				cout<<"\n\nPress any key to go to main menu\n";
				getch();
				menu();
			}
			else
			{
				menu();
			}
			break;
		}
	case 6:
		{
			showdata();
			break;
		}
	default:
		{
			system("cls");
			menu();
		 	break;
		}
		
		
 } 

}


/*            ********1.Money Transfer******               */

void Easypaisa :: moneytransfer()
{
	system("cls");
	title();		
	cout<<"\n\t\t\t************* Money Transfer *************\n\n";
	cout<<"1.Send To Mobile Account\n\n";
	cout<<"2.Send To CNIC\n\n";
	cout<<"3.Bank Transfer\n\n";
	cout<<"4.Back to main menu\n\n";
	cout<<"Enter your choice: \n";
	int select;
	cin>>select;
	system("cls");
	title();
	cout<<"\n\t\t\t************* Money Transfer *************\n\n";
		
	
 switch(select)
 {
 	
 	case 1:/*************MOBILE ACCOUNT****************/

 		{
		    cout<<"\n\t\t\t___________________Mobile Account______________\n\n";
            cout<<"1.Easypaisa\n\n";
			cout<<"2.JAzzcash\n\n";
			cout<<"3.Back to main menu\n\n";
			cout<<"Enter your choice: \n";
			int b;
			cin>>b;
         if(b==1)
		 {
		    system("cls");
		    title();
		    cout<<"\n\n\t\t\t____**** Sending money to Easypaisa Account  ***_____\n\n";
            cout<<"\n\nEnter receiver mobile number: ";
            long long int rmno;
            cin>>rmno;
            cout<<"\nEnter amount: ";
            long int amount;
            cin>>spbalance;
            balance-=spbalance;
           if(balance<0)
           {
                cout<<"\nYou do not have sufficient balance!\n";
            	balance+=spbalance;
            	getch();
            	menu();
		   }
            system("cls");
            title();            
		    cout<<"\n\n\t\t\t____**** Sending money to Easypaisa Account  ***_____\n\n";
            cout<<"\nYou are sending Rs."<<spbalance<<" to:\n\n";
            cout<<"Mobile number: "<<rmno;
            confirm();
          }
          else if(b==2)
		  {
		    system("cls");
		    title();
		    cout<<"\n\n\t\t\t____**** Sending money to JazzCash Account  ***_____\n\n";
            cout<<"\n\nEnter receiver mobile number: ";
            long long int rmno;
            cin>>rmno;
            cout<<"\nEnter amount: ";
            long int amount;
            cin>>spbalance;
            balance-=spbalance;
            if(balance<0)
			{
            	cout<<"\nYou do not have sufficient amount\n";
            	balance+=spbalance;
            	getch();
            	menu();
			}
            system("cls");
            title();            
		    cout<<"\n\n\t\t\t____**** Sending money to JazzCash Account  ***_____\n\n";
            cout<<"\n\nYou are sending Rs."<<spbalance<<" to:\n\n";
            cout<<"Mobile number: "<<rmno;
            confirm();
          }
		    else if(b==3)
		    {
		    	menu();
		    }
           else
           {
		        system("cls");
			    moneytransfer();
		   }
		 break;
        }
    case 2:/*         *********************CNIC****************      */

    	{    		
    		 
    	    cout<<"\n\t\t\t_________________****  CNIC   *****_______________\n\n";
            cout<<"Enter Receiver Mobile Number: ";
            long long int rmno;
            cin>>rmno;
            cout<<"\nEnter receiver CNIC number: ";
            long long int rnic;
			cin>>rnic;
            cout<<"\nEnter ammount: ";
            long int amount;
            cin>>spbalance;
            balance-=spbalance;
            if(balance<0)
			{
            	cout<<"\n You do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
			}
            system("cls");
            title();
            cout<<"\n\nYou're sending Rs."<<spbalance<<" to:\n\n";
            cout<<"CNIC :"<<rnic<<endl;
            cout<<"\nMobile Number: \n"<<rmno;
            confirm();           
		   	break;    		 
		}
	case 3:
		{
			 /**************Bank Transfer******************/
			 
			 
			    cout<<"\n\t\t\t_______________BANK Transfer_____________\n\n";
                cout<<"\n\n1.HBL\n\n2.UBL\n\n3.Bank Al Falah\n\n4.Bank Al Habib\n\n";
				cout<<"5.Allied Bank Limited\n\n6.Meezan Bank\n\n7.MCB Bank\n\n";
                cout<<"8.NBP\n\n9.Faysal Bank Pakistan\n\n10.JS Bank Limited\n\n";
                cout<<"11.Askari Bank\n\n12.Summit Bank\n\n";
				cout<<"13.Bank Islami Pakistan Limited\n\n14.Soneri Bank";
                cout<<"\n\n15.Bank Of Punjab\n\n16.Sindh Bank\n\n17.Other Bank";
				cout<<"\n\n18.Back\n\n";
			    cout<<"Enter your choice\n";
				int choice;
                cin>>choice;                                
				system("cls");
                title();
                cout<<"\n\n";
                if(choice==1)
				{
				    cout<<"\n\t\t\t________________________****  HBL   ****__________________________________"; 
				}
                else if(choice==2)
				{
				    cout<<"\n\t\t\t________________________****  UBL   *****_________________________________________";
				}
                else if(choice==3)
				{
				    cout<<"\n\t\t\t________________________********  Bank Al Falah   *****_______________________";
				}
                else if(choice==4)
				{
				    cout<<"\n\t\t\t__________________**********    Bank Al Habib     ********__________________";
				}
                else if(choice==5)
				{
				    cout<<"\n\t\t\t___________________***********   Allied Bank Limited     *******_______________";
				}
                else if(choice==6)
				{
				    cout<<"\n\t\t\t________________***********  Meezan Bank  ******____________________";
				}
                else if(choice==7)
				{
				    cout<<"\n\t\t\t__________________**************  MCB Bank     ***********______________________";
				}
                else if(choice==8)
				{
				    cout<<"\n\t\t\t____________________*********   NBP  *********_______________";
				}
                else if(choice==9)
				{
				    cout<<"\n\t\t\t___________**** Faysal Bank Pakistan   **********__________________-__";
				}
                else if(choice==10)
				{
				    cout<<"\n\t\t\t________________****  JS Bank Limited   ****_______________";
				}
                else if(choice==11)
				{
				    cout<<"\n\t\t\t_____________*******  Askari Bank   ********__________________";
				}
                else if(choice==12)
				{
				    cout<<"\n\t\t\t______________*********  Summit Bank    **********__________________";
				}
                else if(choice==13)
				{
				    cout<<"\n\t\t\t__________________*****************  Bank Islami Pakistan   *******___________";
				}
                else if(choice==14)
				{
				    cout<<"\n\t\t\t____________***********  Soneri Bank    ********__________________";
				}
                else if(choice==15)
				{
				    cout<<"\n\t\t\t_____________*********   Bank Of Punjab  ************_________________";
				}
                else if(choice==16)
				{
				    cout<<"\n\t\t\t___________**********  Bank Of Sindh  ********_____________";
				}
                else if(choice==17)
				{
				    cout<<"\nEnter Bank Name: ";				    
	                string bankname;
                    getline(cin,bankname);
	                getline(cin,bankname);
	                system("cls");
	                title();
	                cout<<"\n\n\t\t\t_______________*******   "<<bankname<<"    ******___________________";
				}
                else
				{
				    system("cls");
					moneytransfer();
				}
                    cout<<"\n\nEnter account number: ";
                    long long int acno;
                    cin>>acno;
                    cout<<"\n\nEnter receiver mobile number: ";
                    long long int remono;
                    cin>>remono;
                    cout<<"\n\nEnter receiver CNIC number: ";
                    long long int renic;
                    cin>>renic;
                    cout<<"\nEnter amount: ";
                    long int paisa;
                    cin>>spbalance;
                    balance-=spbalance;
                    if(balance<0)
					{
                    	cout<<"\nYou do not have sufficient balance\n";
                    	getch();
                        balance+=spbalance;
                    	menu();
					}
                    system("cls");
                    title();
                    cout<<"\n\nYou're sending Rs."<<spbalance<<" to:\n\n";
                    cout<<"Account Number: "<<acno<<endl;
                    cout<<"\nCNIC :"<<renic<<endl;
                    cout<<"\nMobile Number: "<<remono<<endl;
                    confirm();
		break;			 
		}
	case 4:
		{
			/*             **********Back********                  */
			menu();
			break;		         	
		}
	default :
		{
			moneytransfer();
			break;
		}
         
      
 			
 }




}


/*          ***********2.Mobile Load*************                             */

void Easypaisa :: mobileload()
{	
	system("cls");
	title();
	cout<<"\n\n1.Telenor";
	cout<<"\n\n2.Mobilink";
	cout<<"\n\n3.Ufone";
	cout<<"\n\n4.Back to main menu\n\n";
	cout<<"Enter your choice: \n";	
	int c;
	cin>>c;
	system("cls");
	title();
    if(c==1)
    {
		cout<<"\n\t\t\t__________******  TELENOR NETWORK   ******________\n";
	}
    else if(c==2)
	{
	    cout<<"\n\t\t\t__________******  MOBILINK NETWORK   ******________\n";
	}
    else if(c==3)
	{
	    cout<<"\n\t\t\t__________******  UFONE NETWORK   ******________\n";
	}
    else if(c==4)
    {
	    menu();
	}
    else 
	{
	    mobileload();
	}	
	cout<<"\nEnter Mobile Number: ";
	long long int mno;
	cin>>mno;
	cout<<"\nEnter amount: ";
	long int rupees;
	cin>>spbalance;
    balance-=spbalance;
    if(balance<0)
	{
		cout<<"\nYou do not have sufficient balance\n";
		getch();
		balance+=spbalance;
		menu();
	}
	system("cls");
	title();
	cout<<"\n\nYou're sending Balance of Rs."<<spbalance<<" to: ";
	cout<<"\n\nMobile number: "<<mno;
    confirm();
}
   
                  /**************3.Bill Payment************/

void Easypaisa :: billpayment()
{
	system("cls");
	title();
	cout<<"\n\n1.Electricity";
	cout<<"\n\n2.Telephone";
	cout<<"\n\n3.Gas";
	cout<<"\n\n4.Internet";
	cout<<"\n\n5.Water";
	cout<<"\n\n6.Back to main menu\n\n";
	cout<<"Enter your choice: \n";
	int e;
	cin>>e;
	system("cls");
	title();
	cout<<"\n\n";
    if(e==1)
	{
		cout<<"\n\t\t\t_______**** PAYING ELCTRICITY BILL USING EASYPAISY APP  ***____";
	}
    else if(e==2)
	{  
		cout<<"\n\t\t\t_______**** PAYING TELEPHONE BILL USING EASYPAISY APP  ***____";
    }
    else if(e==3)
	{
		cout<<"\n\t\t\t_______**** PAYING SUI GASS BILL USING EASYPAISY APP  ***____";
	}
    else if(e==4)
	{
		cout<<"\n\t\t\t_______**** PAYING INTERNET BILL USING EASYPAISY APP  ***____";
	}
    else if(e==5)
	{
		cout<<"\n\t\t\t_______**** PAYING WATER BILL USING EASYPAISY APP  ***____";
	}
    else if(e==6)
	{
	    menu();
	} 
    else
	{
	    system("cls");
		billpayment();
	}
	cout<<"\n\nEnter consumer number: \n";	
	long long int conno;
	cin>>conno;
	cout<<"\n\nEnter amount to pay: ";
	cin>>spbalance;
    balance-=spbalance;
    if(balance<0)
	{
		cout<<"\nYou do not have sufficient balance\n";
		getch();
		balance+=spbalance;
		menu();
	}
	cout<<"\n\nYour bill amount is Rs."<<spbalance<<" only.\n";
    confirm();
}

/*                           ******* 4.Payment ********                                    */

void Easypaisa :: payments()
{
	system("cls");
	title();
	cout<<"\n\n1.Online Ticket Booking";
	cout<<"\n\n2.Traffic Challan";
	cout<<"\n\n3.Pay Fee";
	cout<<"\n\n4.Donation";
	cout<<"\n\n5.Back to main menu\n\n";
	cout<<"Enter your choice:\n";
	int f;
	cin>>f;
	system("cls");
	title();	
	     
 switch(f)
 {
	case 1: 
    	{
			
/*                        ************ A.Online Ticket Booking   ***************                              */
	     
		cout<<"\n\t\t\t\t___________***  Online Ticket Booking    ******_________\n\n";
	    cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	    cout<<"\nBuy tickets with discounts and deals for\n\n";
	    cout<<"\n1.Coach";
		cout<<"\n\n2.Flight";
		cout<<"\n\n3.Train";
		cout<<"\n\n4.Cricket";
		cout<<"\n\n5.Back\n\n";
		cout<<"Enter your choice: \n";
		int g;
		cin>>g;
		system("cls");
		title();
		cout<<"\n";
		
				   
		    /**********  a.Coach Ticket Buying  *********/
		if(g==1)
		{
			spbalance=3300.0;
            balance-=spbalance;
            if(balance<0) 
			{
                cout<<"\nYou do not have sufficient balance\n";
                getch();
                balance+=spbalance;
                menu();
			}
			cout<<"\n\t\t\t\t___________********  Online Ticket Booking   *********_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________****   COACH TICKET    *****________\n";
	        cout<<"\nEnter passanger's name: \n";
	        string customer;
	        getline(cin,customer);
	        getline(cin,customer);
	        cout<<"\nEnter passanger's mobile number: \n";
	        long long int number;
	        cin>>number;
	        cout<<"\nEnter departure date (DD-MM-YY): "<<endl;
	        int days,months,years;
	        char cher;
	        cin>>days>>cher>>months>>cher>>years;
	        cout<<"\nFrom (enter city name):\n";
	        string city1;
	        getline(cin,city1);
	        getline(cin,city1);
	        cout<<"\nTo (enter city name):\n";
	        string city2;
            getline(cin,city2);
            getline(cin,city2);
            system("cls");
            title();
            cout<<"\n";
            cout<<"\n\t\t\t\t___________*********   Online Ticket Booking     ********_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________****   COACH TICKET    *****________\n";
	        cout<<"\n\t\t\t____________________________________________________________________\n";
	        cout<<endl<<"Name of passanger: "<<customer<<endl;
	        cout<<endl<<"From: "<<city1<<endl;
	        cout<<endl<<"To: "<<city2<<endl;
	        cout<<endl<<"Date: "<<days<<"-"<<months<<"-"<<years<<endl;
	        cout<<endl<<"Coach Name: AKC"<<endl;
	        cout<<endl<<"Boarding Time: 10:20 AM"<<endl;
	        cout<<endl<<"Seat: 21"<<endl;
	        cout<<endl<<"Price: Rs.3300"<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(g==2)
		{
/*           **********  b. AIR TICKET BUYING    ********************               */
			spbalance=95600.0;
	        balance-=spbalance;
            if(balance<0) 
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			}            	        
			cout<<"\n\t\t\t\t___________*********    Online Ticket Booking   *******_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________*****      AIR TICKET       ******________\n";
	        cout<<"\nEnter passanger's name: \n";
	        string customer;
			getline(cin,customer);
	        getline(cin,customer);
	        cout<<"\n\nEnter passanger's NIC number: \n";
	        long long int cnic;
	        cin>>cnic;
	        cout<<"\nEnter passanger's mobile number: \n";
	        long long int number;
			cin>>number;
	        cout<<"\nEnter date of departure (DD-MM-YY): "<<endl;
	        int days,months,years;
	        char cher;
			cin>>days>>cher>>months>>cher>>years;
            cout<<"\nFrom (country name): \n";
            string country1;
			getline(cin,country1);
	        getline(cin,country1);
            cout<<"\n\nTo (country name): \n";
            string country2;
			getline(cin,country2);
	        getline(cin,country2);
	        system("cls");
	        title();
	        cout<<"\n\t\t\t\t___________**********    Online Ticket Booking *********_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________*****      AIR TICKET       ******________\n";
	        cout<<"\n\n\t\t\t____________________________________________________________________\n";
	        cout<<endl<<"Name of passanger: "<<customer<<endl;
	        cout.setf(ios::fixed);cout<<endl<<"NIC Number: "<<cnic<<endl;
	        cout.setf(ios::fixed);cout<<endl<<"Mobile Number: "<<number<<endl;
	        cout<<endl<<"From: "<<country1<<endl;
	        cout<<endl<<"To: "<<country2<<endl;
	        cout<<endl<<"Date: "<<days<<"-"<<months<<"-"<<years<<endl;
	        cout<<endl<<"Flight Name: ULON23"<<endl;
	        cout<<endl<<"Boarding Time: 10:20 PM"<<endl;
	        cout<<endl<<"Gate: 22"<<endl;
	        cout<<endl<<"Seat: 21B"<<endl;
	        cout<<endl<<"Price: Rs.95600"<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(g==3)
		{
				/*****************  c.Train Ticket Buying  ***************/

            cout<<"\n\t\t\t\t___________************    Online Ticket Booking   **********_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________TRAIN TICKET________\n";
	        cout<<"\nEnter passanger's name: \n";
	        string customer;
			getline(cin,customer);
	        getline(cin,customer);
	        cout<<"\nEnter passanger's mobile number: \n";
	        long long int number;
			cin>>number;
	        cout<<"\nEnter date of departure (DD-MM-YY): "<<endl;
	        int days,months,years;
	        char cher;
			cin>>days>>cher>>months>>cher>>years;
            cout<<"\nFrom (station name): \n";
            string station1;
			getline(cin,station1);
	        getline(cin,station1);
            cout<<"\n\nTo (station name): \n";
            string station2;
			getline(cin,station2);
	        getline(cin,station2);
saddar:	    system("cls");
            title();      
	        cout<<"\nSELECT CLASS:";
			cout<<"\t\n\n1.Economy";
			cout<<"\t\n\n2.Executive";
			cout<<"\t\n\n3.First Class\n\n";
			int h,k;
			string CLASS;
	        cin>>h;
	        system("cls");
	        title();
	        if(h==1)
			{
				k=2500;
				CLASS="Economy";
			}
	        else if(h==2)
			{
				k=3200;
				CLASS="Executive";
			}
	        else if(h==3)
			{
				k=2800;
				CLASS="First Class";
			}
		    else
            {
			    goto saddar; 
			}
			spbalance=k;
	    	balance-=spbalance;
            if(balance<0)
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			}            
			cout<<"\n\t\t\t\t___________Online Ticket Booking Pakistan_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________TRAIN TICKET________\n";
	        cout<<"\n\n\t\t\t____________________________________________________________________\n";
	        cout<<endl<<"Name of passanger: "<<customer<<endl;
	        cout<<endl<<"From: "<<station1<<endl;
	        cout<<endl<<"To: "<<station2<<endl;
	        cout<<endl<<"Date: "<<days<<"-"<<months<<"-"<<years<<endl;
	        cout<<endl<<"Train Name: Allama Iqbal Express"<<endl;
	        cout<<endl<<"Class: "<<CLASS<<endl;
	        cout<<endl<<"Boarding Time: 11:00 AM"<<endl;
	        cout<<endl<<"Seat: 11A"<<endl;
	        cout<<endl<<"Price: Rs."<<k<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(g==4)
		{
				/*****************  e. CRICKET TICKET BUYING  ******************/
            cout<<"\nTicket Price is Rs.2500\n\n";
			cout<<"Press any key to continue: ";
			getch();           			
		    long long int number;
		    spbalance=2500.0;
		    balance-=spbalance;
            if(balance<0)
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			} 
			cout<<"\nEnter your mobile number: \n";        	
	        cin>>number;
			system("cls");
			title();	        
			cout<<"\n\t\t\t\t_________CRICKET MATCH TICKET________\n";
	        cout<<"\n\n\t\t\t## Book your ticket for ICC Men's T20 World Cup 2022, Australia ##\n\n";	        
		    string s[6];
            s[0]="\n1.Sunday 23 October               Pakistan VS India                             Melbourne\n";
            s[1]="\n2.Thursday 27 October             Pakistan VS B1                                Perth\n";
            s[2]="\n3.Sunday 30 October               Pakistan VS A2                                Perth\n";
            s[3]="\n4.Thursday 3 November             Pakistan VS South Africa                      Sydney\n";
            s[4]="\n5.Sunday 6 November               Pakistan VS Bangladesh                        Adelaide\n";
            s[5]="\n6.Sunday 13 November              FINAL                                         Melbourne";	
            cout<<"  Day & Date                      Match                                         Ground Name:\n\n\n";
         for(int i=0;i<6;i++)cout<<s[i]<<endl;
            cout<<"\nSELECT MATCH: \n\n";
            int l;
            cin>>l;
            string match;
         for(int j=0;j<6;j++)
		 {
		    if(l==j+1)
		    {
		    	match=s[j];
			}			 
		 }
	        string part=match.substr(35,26);
	        string datepart=match.substr(0,25);
	        system("cls");
	        title();
	        cout<<"\n\t\t\t\t___________**********      Online Ticket Booking ***********_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________CRICKET MATCH TICKET________\n";
	        cout<<"\n\t\t\t__________________________________________________________________________________________\n";
	        cout<<endl<<"              **********__________"<<part<<"______________**********"<<endl;
	        cout<<endl<<"Gate: 6"<<endl;
	        cout<<endl<<"Seat: 338F"<<endl;
	        cout<<endl<<"Date: "<<datepart<<endl;
        	cout<<endl<<"Price: Rs.2500"<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
 		}
		else
		{
			payments();
		}
		break;
    	}
	case 2:/********  B. Traffic Challan   *********/
		{
			cout<<"\n\t\t\t________******    Traffic Challan         *********__________\n\n";
            cout<<endl<<"1.Pay fine of Rs.200 for having no helmet.\n";
            cout<<endl<<"2.Pay fine of Rs.300 for exceeding prescribed speed limit.\n";
            cout<<endl<<"3.Pay fine of Rs.500 for carrying passangers exceeding permissible limit.\n";
            cout<<endl<<"4.Pay fine of Rs.200 for violation of traffic signals.\n";
            cout<<endl<<"5.Pay fine of Rs.600 for overloading a goods vehicle.\n";
            cout<<endl<<"6.Pay fine of Rs.1000 for driving a motor vehicle at night without proper lights.\n";
            cout<<endl<<"7.Pay fine of Rs.700 for driving a motor vehicle without a driving licence.\n";
            cout<<endl<<"8.Back\n";
            cout<<endl<<"Enter your choice: \n";
            int m;
			cin>>m;
			system("cls");
			title();
			if(m==1)
			{
				spbalance=200;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.200 for having no helmet.\n";                        
			}
			else if(m==2)
			{
				spbalance=300;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  } 
			  cout<<endl<<"You are paying fine of Rs.300 for exceeding prescribed speed limit.\n";
			}
			else if(m==3)
			{
				spbalance=500;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			   cout<<endl<<"You are paying fine of Rs.500 for carrying passangers exceeding permissible limit.\n"; 
			}
			else if(m==4)
			{ 
				spbalance=200;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.200 for violation of traffic signals.\n"; 
			}
			else if(m==5)
			{
				spbalance=600;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.600 for overloading a goods vehicle.\n"; 
			}
			else if(m==6)
			{
				spbalance=1000;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.1000 for driving a motor vehicle at night without proper lights.\n"; 
			}
			else if(m==7)
			{
				spbalance=700;
				balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.700 for driving a motor vehicle without a driving licence.\n"; 
			}
			else if(m==8)
			{
				payments();
			}			
			else
			{
				cout<<"\nIncorrect choice\n";
				cout<<"\nPress any key:";
				getch();
				menu();
			}
	    	confirm();         
		 	break;
		}
		
	case 3:        /************* C. Pay Fee  ***********/

		{
back:		cout<<"\n\t\t\t_____________**********              PAY FEE          **********____________\n\n";
            cout<<"\n\n1.University";
			cout<<"\n\n2.College";			
		    cout<<"\n\n3.School";
		    cout<<"\n\n4.Back\n\n";
		    cout<<endl<<"Enter your choice: ";
		    int n;
            cin>>n;
            system("cls");
            title();
          if(n==1)  /***********  a. University  *************/
          {
          	cout<<endl<<"Enter University Name: \n";
	        string university;
			getline(cin,university);
	        getline(cin,university);
saddar1:	system("cls");
            title();
	        cout<<"\n\t\t\t_______*****   "<<university<<"    *********____________\n\n";
	        cout<<endl<<"Select type of fees:";
			cout<<"\n\n1.Semester fee";
			cout<<"\n\n2.Admission fee";
			cout<<"\n\n3.Summer semester fee";
			cout<<"\n\n4.Hostel";
			cout<<"\n\n5.Other\n\n";
			cout<<"6.Back\n\n";
	        int o;
			cin>>o;			
			system("cls");
			title();
		    if(o==5)
			{
			    cout<<"\nSpecify type of fees to pay: \n";
                string specie;
				getline(cin,specie);
				getline(cin,specie);
				cout<<endl<<"Enter amount to pay: ";
				long int u;
				cin>>spbalance;
			    balance-=spbalance;
              if(balance<0)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
		      }
		    }
		    else if(o==6)
		    {
		    	system("cls");
		    	goto back;
			}
		    else if(o>0||o<5)
			{
				cout<<"\nEnter amount to pay\n";
			    cin>>spbalance;
			    balance-=spbalance;
                if(balance<0)
				{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			    }            			
			    cout<<"\nPay Rs."<<spbalance<<" against voucher number given to you: \n";
			}
			else
			{
			    cout<<"\nIncorrect Input!\n";
			    cout<<"\nPress any key\n";
			    getch();
			    goto saddar1;
			}			
				system("cls");
				title();
				cout<<"\nPay Rs."<<spbalance<<" against voucher number given to you: \n";			    
				cout<<"\nEnter voucher number: \n";
                long int voucher;
				cin>>voucher;
                confirm();
		  }
		  else if(n==2)             /**********b. COLLEGE  ***********/
		  {		  	
		  	cout<<"\nEnter College Name: \n";
            string college;
			getline(cin,college);
	        getline(cin,college);
saddar2:	system("cls");
            title();
            cout<<"\n\n1.Transportation Fee\n\n2.Tution Fee.\n\n3.Hostel Fee";
			cout<<"\n\n4.Admission Fee\n\n5.Monthly Fee\n\n6.Annual Fee\n\n7.Other\n\n8.Back\n\n";
            cout<<"Enter your choice: ";
            int p;
	        cin>>p;
	        system("cls");
	        title();
	        if(p==7)
			{
	            cout<<"Specify type of fee: \n";
	            string otherfee;
				getline(cin,otherfee);
	            getline(cin,otherfee);	            
			    cout<<"\n\nEnter amount to pay: \n";
	            long int v;
				cin>>spbalance;
			    balance-=spbalance;
               if(balance<0)
			   {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			  }            
	            cout<<"\nYou are paying Rs."<<spbalance<<" to "<<college<<" for "<<otherfee<<" fees\n";	
			}
			else if(p==8)
			{
				goto back;
			}
		

	        else if(p>0||p<7)
			{
			    cout<<"\n\nEnter amount to pay\n";
			    cin>>spbalance;
			    balance-=spbalance;
            if(balance<0)           
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			}            

			    cout<<"You are paying Rs."<<spbalance<<" to "<<college<<" for transportation fees\n";
			}
	       	else
			{
				cout<<"\nIncorrect Input\n";
				cout<<"Press any key\n";
				getch();
				goto saddar2;
			}
				cout<<"\nEnter voucher number: \n";
                long int voucher;
				cin>>voucher;            
               confirm();
          }
          else if(n==3) /*************  c. SCHOOL  ****************/

          {
          	string school;
            cout<<"\nEnter School Name: \n";
	        getline(cin,school);
	        getline(cin,school);
	        system("cls");
	        title();
	        cout<<"\n\nSELECT:\n\n1.Transportation Fee\n\n2.Tution Fee.";
			cout<<"\n\n3.Hostel Fee\n\n4.Admission Fee";
            cout<<"\n\n5.Monthly Fee\n\n6.Annual Fee\n\n7.Other\n\n8.Back\n\n";
	        int q;
			cin>>q;
saddar3:	system("cls");
            title();
            cout<<"\n\n";
            if(q==7)
			{
	            cout<<"Specify type of fee: \n";
                string otherschoolfee;	 
	            getline(cin,otherschoolfee);
	            getline(cin,otherschoolfee);
	            cout<<"\n\nEnter amount to pay: \n";
	            long int w;
				cin>>spbalance;
				balance-=spbalance;
               if(balance<0)
              {
            	cout<<"\nYou do not have sufficient balance\n";
                getch();
				balance+=spbalance;
            	menu();
		      }
            
	            cout<<"\nYou are paying Rs."<<spbalance<<" to "<<school<<" for "<<otherschoolfee<<" fees\n";
			}
			else if(q==8)
			{
				goto back;
			}
	        
		
			else if(q>0||q<7)
			
			{
				cout<<"\nEnter amount to pay\n";
				cin>>spbalance;
			    balance-=spbalance;
               if(balance<0)
	           {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
		       }
            
			    cout<<"You are paying Rs."<<spbalance<<" to "<<school<<endl;
			}
	       	else
			{
				cout<<"\nIncorrect Input!\n";
				cout<<"\nPress any key: ";
				getch();
				menu();
			}
			    cout<<"\nEnter voucher number: \n";
                long int voucher;
				cin>>voucher;            
               confirm();
          }
          else if(n==4)
          {
          	payments();
		  }
	   break;
	   }
	   
	case 4:    /************ D. DONATION  ***************/
        {
        	system("cls");
        	title();
        	cout<<"\n";
        	cout<<"\n\t\t\t________________********    DONATION   *********_____________\n\n";
            cout<<"\n1.PM Corona Fund\n\n2.PM Dam Fund\n\n3.EDHI\n\n4.Back\n\n";
            cout<<"\nEnter your choice: \n";
			int r;
			cin>>r;            
			system("cls");
			title();
			if(r<0||r>4)
			{
				cout<<"\nIncorrect Input\n";
				cout<<"\nEnter any key\n";
				getch();
				menu();
			}
			else if(r==4)
			{
				payments();
			}
            cout<<endl<<"Enter amount you want to donate: \n";  
            long int t;
			cin>>spbalance;
            balance-=spbalance;
            if(balance<0)     
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	balance+=spbalance;
            	menu();
			}            
            confirm();
         break;
		}
	case 5:
	{
		menu();
	} 
 }

}


int main()
{
	system("color B4");
	Easypaisa e;
	e.title();
	e.showdata();
//    e.menu();
}
	




#include<iostream>
#include<fstream>
#include<string>
#include<conio.h>
#include<iomanip>
#include<cstring>
#include<sstream>

using namespace std;

class Easypaisa
{
	private:
		string username,name;
		string roll;
		string pin,pincode,password;
		float spbalance,totalBalance;
		float rechargeBalance,oldBalance;
		long long nic,mno,voucher;
		int a,b,c;  //for date of birth
	    int counter;
        int len,leng,lengt;
		int i, j, x, y, z;    //used in loops
		char ch;
    public:
    	 Easypaisa()
    	{
    		i = 0;
    		j = 0;
    		x = 0;
    		y = 0; 
    		z = 0;
    		mno = 0;
    		voucher = 0;
    		counter = 0;
    		len = 0;
    		leng = 0;
    		lengt = 0;
    		spbalance=0.0;   
			rechargeBalance = 0; 
			oldBalance = 0;	
			totalBalance = 0;	
		}
		
	    
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
 for(i=0; i<42; i++)
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
	cin>>x;
	system("cls");
	title();	
  if(x==1)
  {
  	ifstream balanceout((username+roll)+".txt");
  	string s;
  	getline(balanceout,s);
  	stringstream(s)>>totalBalance;  //Convert string to float	
    totalBalance-=spbalance;
	ofstream balance((username+roll)+".txt",ios::in);
	balance<<totalBalance<<endl;
	cout<<"\n\nYour transaction has been completed succesfully\n\n"; 
    cout<<"\n\nPress any key to go to main menu\n\n";
    getch();
    menu();
  }
  else if(x==2)
  {
	cout<<"\nYou're transaction has been canceled:\n";
	cout<<"\nPress any key to go to main menu\n";
	getch();
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
	cin>>x;
	system("cls");
		
  switch(x)
  {
               /*    ************** New Account **************          */	
	case 1:
		{
			newaccount();
			system("cls");
			title();
	        setpass();
	        cout<<"\nWould you like to see your password?(y/n) \n";
	        ch=getch();
          if(ch=='y'||ch=='Y')
		  {
			cout<<"\nYou Entered: "<<password<<endl;
			cout<<"\nPress any key\n";
            getch();
            system("cls");
            title();                
		  }
			system("cls");
			title();
label:      cout<<"\n\nConfirm password: \n";
            pin.erase(0,leng);
          for(i=0; ; i++)
          {
space:          ch=getch();
                if(ch=='\r')break;
                else if(ch=='\b')
                {
	                pin.erase(pin.end()-1);
		            x=pin.length();
		            system("cls");
		            title();
		            cout<<"\n\nConfirm password: \n";
		            for(j=x; j>0; j--)cout<<"*";
		            goto space;
                }
                else
                {		
	                pin+=ch;
                    cout<<"*";
                }
          }    
            leng=pin.length();	
        if(password==pin)
		{
			system("cls");
	        cout<<"\n\nYou've created your account succesfully ....";
	        cout<<"\n\nYour balance is Rs. 00 After Login Go to My Account And Recharge Balance\n";
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
	cout<<"\nEnter your CNIC (without dashes): \n";	
    cin>>nic;
    cout<<"Enter your roll number (eg. 21SW077): ";
    getline(cin,roll);
	getline(cin,roll);
	sadar1<<name<<roll<<endl;
	ofstream newfile((name+roll)+".txt");
	newfile<<totalBalance;
    cout<<"\nEnter Date Of Birth (DD-MM-YY): \n";
	cin>>a>>ch>>b>>ch>>c;
 if(b<0||b>12)
 {  	
    system("cls");
	cout<<"\n\n\t\tIncorrect Month! \n";
	newaccount();
 }
 if(c<0)
 {
 	system("cls");
 	cout<<"Incorrect Month";
 	newaccount();
 }
 if(c>2004)
 { 	
    system("cls");
	cout<<"\n\n\t\tYou're Ineligible!!! You are under 18!\n";
	newaccount();
 }
}

/*         *************   SET PASSWORD **************       */


void Easypaisa :: setpass()
{	
	cout<<"\nEnter new pin: \n";
	password.erase(0,len);
	ofstream out("saddar.txt",ios::app);
 for(i=0; ; i++)
 {
backspace:	ch=getch();	
	if(ch=='\r')break;
	else if(ch=='\b')
	{
	    password.erase(password.end()-1);
		x=password.length();
		system("cls");
		title();
		cout<<"\nEnter new pin: \n";
		for(j=x; j>0; j--)cout<<"*";
		goto backspace;
	}
	else
	{		
	    password+=ch;
	    cout<<"*";
	}
 } 
    len=password.length();
    out<<name<<roll<<password<<"\n";
 
}

     /*********************  CALLING A PASSWORD ***************/
     
void Easypaisa :: callpass()
{
	 
	pincode.erase(0,lengt);
 for(i=0; ; i++)
 {
space:    ch=getch();
   if(ch=='\r')break;
   else if(ch=='\b')
   {
	    pincode.erase(pincode.end()-1);
		x=pincode.length();
		system("cls");
		title();
		cout<<"Enter password: \n";
		for(j=x; j>0; j--)cout<<"*";
		goto space;
   }
   else
   {		
	    pincode+=ch;
        cout<<"*";
   }
 }
    
    lengt=pincode.length();	
   	
    
}

/*   ******************  LOGIN   *******************       */
	
void Easypaisa :: login()
{
	title();	
	cout<<"\n\t\t\t\t\t\t________******  LOGIN  *****_____________\n\n";
	cout<<"\nEnter User Name:(You may have to enter the same name two times) \n";
	getline(cin,username);
	getline(cin,username);
	cout<<"Enter roll number: ";
	getline(cin,roll);
	string s1;
	ifstream sdb("sadar.txt");
  while(sdb.good())
  {
    getline(sdb,s1);
   if(username+roll==s1)
   {  
        system("cls");
		title(); 
  	    cout<<"\nEnter Password: \n";
  	    callpass();
  	    string newpin;
  	    ifstream SDB("saddar.txt");
  	 while(SDB.good())
  	 {
  		getline(SDB,newpin);
  	  if(username+roll+pincode==newpin)
  	  {
            ifstream balanceout((username+roll)+".txt");
            string s;
            getline(balanceout,s);
            stringstream(s)>>totalBalance;  //Convert string to float	
  		    menu();
  		    break;
	  }
   	 }
	if(username+roll+pincode!=newpin)
	{
		system("cls");
		cout<<"\n\nIncorrect Password! Try Again!\n\n";		
		login();
	}  	
	break;
    } 
	
  }
   if(username+roll!=s1)
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
	cin>>y;
 switch(y)
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
			cin>>y;
			system("cls");
			title();
			if(y==1)
			{
				cout<<"Enter your roll number: ";
				getline(cin,roll);
				getline(cin,roll);
				ifstream open("sadar.txt");
				string s;
				while(open.good())
				{
					getline(open,s);
					if(username+roll == s)
					{
						ifstream out((username+roll)+".txt");
						string str;
					    getline(out,str);
					    cout<<"You're remaining balance is: "<<str;
				        cout<<"\nPress any key to go to main menu\n";
				        getch();
				        menu();
					}
				}
				
			}
			else if(y==2)
			{
				cout<<"\nEnter your roll number ";
				string rollNo;
				getline(cin,rollNo);
				getline(cin,rollNo);
				if(rollNo == roll)
				{
					cout<<"\nEnter amount to recharge: ";
					cin>>rechargeBalance;
					totalBalance = totalBalance + rechargeBalance;
					ofstream input((username+roll)+".txt", ios::in);
					input<<totalBalance<<endl;
				}
				
				
				cout<<"\n\nPress any key to go to main menu\n";
				getch();
				menu();
			}
			else
				menu();
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
	cin>>y;
	system("cls");
	title();
	cout<<"\n\t\t\t************* Money Transfer *************\n\n";
		
	
 switch(y)
 {
 	
 	case 1:/*************MOBILE ACCOUNT****************/

 		{
		    cout<<"\n\t\t\t___________________Mobile Account______________\n\n";
            cout<<"1.Easypaisa\n\n";
			cout<<"2.JAzzcash\n\n";
			cout<<"3.Back to main menu\n\n";
			cout<<"Enter your choice: \n";
			cin>>z;
         if(z==1)
		 {
		    system("cls");
		    title();
		    cout<<"\n\n\t\t\t____**** Sending money to Easypaisa Account  ***_____\n\n";
            cout<<"\n\nEnter receiver mobile number: ";
            cin>>mno;
            cout<<"\nEnter amount: ";
            cin>>spbalance;
           if(spbalance>totalBalance)
           {
                cout<<"\nYou do not have sufficient balance!\n";
            	getch();
            	menu();
		   }
		   else
		   {
		   	system("cls");
            title();            
		    cout<<"\n\n\t\t\t____**** Sending money to Easypaisa Account  ***_____\n\n";
            cout<<"\nYou are sending Rs."<<spbalance<<" to:\n\n";
            cout<<"Mobile number: "<<mno;
            confirm();
		   }
            
          }
          else if(z==2)
		  {
		    system("cls");
		    title();
		    cout<<"\n\n\t\t\t____**** Sending money to JazzCash Account  ***_____\n\n";
            cout<<"\n\nEnter receiver mobile number: ";
            cin>>mno;
            cout<<"\nEnter amount: ";
            cin>>spbalance;
            if(spbalance>totalBalance)
			{
            	cout<<"\nYou do not have sufficient amount\n";
            	getch();
            	menu();
			}
            system("cls");
            title();            
		    cout<<"\n\n\t\t\t____**** Sending money to JazzCash Account  ***_____\n\n";
            cout<<"\n\nYou are sending Rs."<<spbalance<<" to:\n\n";
            cout<<"Mobile number: "<<mno;
            confirm();
          }
		    else if(z==3)
		    	menu();
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
            cin>>mno;
            cout<<"\nEnter receiver CNIC number: ";
			cin>>nic;
            cout<<"\nEnter ammount: ";
            cin>>spbalance;
            if(spbalance>totalBalance)
			{
            	cout<<"\n You do not have sufficient balance\n";
            	getch();
			}
            system("cls");
            title();
            cout<<"\n\nYou're sending Rs."<<spbalance<<" to:\n\n";
            cout<<"CNIC :"<<nic<<endl;
            cout<<"\nMobile Number: \n"<<mno;
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
                cin>>x;                                
				system("cls");
                title();
                cout<<"\n\n";
                if(x==1)cout<<"\n\t\t\t________________________****  HBL   ****__________________________________"; 
                else if(x==2)cout<<"\n\t\t\t________________________****  UBL   *****_________________________________________";
                else if(x==3)cout<<"\n\t\t\t________________________********  Bank Al Falah   *****_______________________";
                else if(x==4)cout<<"\n\t\t\t__________________**********    Bank Al Habib     ********__________________";
                else if(x==5)cout<<"\n\t\t\t___________________***********   Allied Bank Limited     *******_______________";
                else if(x==6)cout<<"\n\t\t\t________________***********  Meezan Bank  ******____________________";
                else if(x==7)cout<<"\n\t\t\t__________________**************  MCB Bank     ***********______________________";
                else if(x==8)cout<<"\n\t\t\t____________________*********   NBP  *********_______________";
                else if(x==9)cout<<"\n\t\t\t___________**** Faysal Bank Pakistan   **********__________________-__";
                else if(x==10)cout<<"\n\t\t\t________________****  JS Bank Limited   ****_______________";
                else if(x==11)cout<<"\n\t\t\t_____________*******  Askari Bank   ********__________________";
                else if(x==12)cout<<"\n\t\t\t______________*********  Summit Bank    **********__________________";
                else if(x==13)cout<<"\n\t\t\t__________________*****************  Bank Islami Pakistan   *******___________";
                else if(x==14)cout<<"\n\t\t\t____________***********  Soneri Bank    ********__________________";
                else if(x==15)cout<<"\n\t\t\t_____________*********   Bank Of Punjab  ************_________________";
                else if(x==16)cout<<"\n\t\t\t___________**********  Bank Of Sindh  ********_____________";
                else if(x==17)
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
                    cin>>voucher;
                    cout<<"\n\nEnter receiver mobile number: ";
                    cin>>mno;
                    cout<<"\n\nEnter receiver CNIC number: ";
                    cin>>nic;
                    cout<<"\nEnter amount: ";
                    cin>>spbalance;
                    if(spbalance>totalBalance)
					{
                    	cout<<"\nYou do not have sufficient balance\n";
                    	getch();
                    	menu();
					}
                    system("cls");
                    title();
                    cout<<"\n\nYou're sending Rs."<<spbalance<<" to:\n\n";
                    cout<<"Account Number: "<<voucher<<endl;
                    cout<<"\nCNIC :"<<nic<<endl;
                    cout<<"\nMobile Number: "<<mno<<endl;
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
	cout<<"\n\n4.Zong";
	cout<<"\n\n5.Back to main menu\n\n";
	cout<<"Enter your choice: \n";	
	cin>>y;
	system("cls");
	title();
    if(y==1)cout<<"\n\t\t\t__________******  TELENOR NETWORK   ******________\n";
    else if(y==2)cout<<"\n\t\t\t__________******  MOBILINK NETWORK   ******________\n";
    else if(y==3)cout<<"\n\t\t\t__________******  UFONE NETWORK   ******________\n";
    else if(y==4)cout<<"\n\t\t\t__________******  ZONG NETWORK   ******________\n";
	else if(y == 5)menu();
    else mobileload();
	cout<<"\nEnter Mobile Number: ";
	cin>>mno;
	cout<<"\nEnter amount: ";
	cin>>spbalance;
    if(spbalance>totalBalance)
	{
		cout<<"\nYou do not have sufficient balance\n";
		getch();
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
	cin>>z;
	system("cls");
	title();
	cout<<"\n\n";
    if(z==1)cout<<"\n\t\t\t_______**** PAYING ELCTRICITY BILL USING EASYPAISY APP  ***____";
    else if(z==2)cout<<"\n\t\t\t_______**** PAYING TELEPHONE BILL USING EASYPAISY APP  ***____";
    else if(z==3)cout<<"\n\t\t\t_______**** PAYING SUI GASS BILL USING EASYPAISY APP  ***____";
    else if(z==4)cout<<"\n\t\t\t_______**** PAYING INTERNET BILL USING EASYPAISY APP  ***____";
    else if(z==5)cout<<"\n\t\t\t_______**** PAYING WATER BILL USING EASYPAISY APP  ***____";
    else if(z==6)menu();
    else
	{
	    system("cls");
		billpayment();
	}
	cout<<"\n\nEnter consumer number: \n";	
	cin>>voucher;
	cout<<"\n\nEnter amount to pay: ";
	cin>>spbalance;
    if(spbalance>totalBalance)
	{
		cout<<"\nYou do not have sufficient balance\n";
		getch();
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
	cin>>x;
	system("cls");
	title();	
	     
 switch(x)
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
		cin>>x;
		system("cls");
		title();
		cout<<"\n";
		
				   
		    /**********  a.Coach Ticket Buying  *********/
		if(x==1)
		{
			spbalance=3300.0;
            if(spbalance>totalBalance) 
			{
                cout<<"\nYou do not have sufficient balance\n";
                getch();
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
	        cin>>mno;
	        cout<<"\nEnter departure date (DD-MM-YY): "<<endl;
	        cin>>a>>ch>>b>>ch>>c;
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
	        cout<<endl<<"Date: "<<a<<"-"<<b<<"-"<<c<<endl;
	        cout<<endl<<"Coach Name: AKC"<<endl;
	        cout<<endl<<"Boarding Time: 10:20 AM"<<endl;
	        cout<<endl<<"Seat: 21"<<endl;
	        cout<<endl<<"Price: Rs.3300"<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(x==2)
		{
/*           **********  b. AIR TICKET BUYING    ********************               */
			spbalance=95600.0;
            if(spbalance>totalBalance) 
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
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
	        cin>>nic;
	        cout<<"\nEnter passanger's mobile number: \n";
			cin>>mno;
	        cout<<"\nEnter date of departure (DD-MM-YY): "<<endl;
			cin>>a>>ch>>b>>ch>>c;
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
	        cout.setf(ios::fixed);cout<<endl<<"NIC Number: "<<nic<<endl;
	        cout.setf(ios::fixed);cout<<endl<<"Mobile Number: "<<mno<<endl;
	        cout<<endl<<"From: "<<country1<<endl;
	        cout<<endl<<"To: "<<country2<<endl;
	        cout<<endl<<"Date: "<<a<<"-"<<b<<"-"<<c<<endl;
	        cout<<endl<<"Flight Name: ULON23"<<endl;
	        cout<<endl<<"Boarding Time: 10:20 PM"<<endl;
	        cout<<endl<<"Gate: 22"<<endl;
	        cout<<endl<<"Seat: 21B"<<endl;
	        cout<<endl<<"Price: Rs.95600"<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(x==3)
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
			cin>>mno;
	        cout<<"\nEnter date of departure (DD-MM-YY): "<<endl;
			cin>>a>>ch>>b>>ch>>c;
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
			string CLASS;
	        cin>>y;
	        system("cls");
	        title();
	        if(y==1)
			{
				z=2500;
				CLASS="Economy";
			}
	        else if(y==2)
			{
				z=3200;
				CLASS="Executive";
			}
	        else if(y==3)
			{
				z=2800;
				CLASS="First Class";
			}
		    else
			    goto saddar; 
			spbalance=z;
            if(spbalance>totalBalance)
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			}            
			cout<<"\n\t\t\t\t___________Online Ticket Booking Pakistan_________\n\n";
	        cout<<"\n\n\t\t\t**Pakistan's leading online ticket booking platform.**\n\n";
	        cout<<"\n\t\t\t\t_________TRAIN TICKET________\n";
	        cout<<"\n\n\t\t\t____________________________________________________________________\n";
	        cout<<endl<<"Name of passanger: "<<customer<<endl;
	        cout<<endl<<"From: "<<station1<<endl;
	        cout<<endl<<"To: "<<station2<<endl;
	        cout<<endl<<"Date: "<<a<<"-"<<b<<"-"<<c<<endl;
	        cout<<endl<<"Train Name: Allama Iqbal Express"<<endl;
	        cout<<endl<<"Class: "<<CLASS<<endl;
	        cout<<endl<<"Boarding Time: 11:00 AM"<<endl;
	        cout<<endl<<"Seat: 11A"<<endl;
	        cout<<endl<<"Price: Rs."<<z<<endl; 
	        cout<<endl<<"**Price will be deducted from your account."<<endl;
            confirm();
		}
		
		else if(x==4)
		{
				/*****************  e. CRICKET TICKET BUYING  ******************/
            cout<<"\nTicket Price is Rs.2500\n\n";
			cout<<"Press any key to continue: ";
			getch();           			
		    spbalance=2500.0;
            if(spbalance>totalBalance)
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			} 
			cout<<"\nEnter your mobile number: \n";        	
	        cin>>mno;
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
         for(i=0;i<6;i++)cout<<s[i]<<endl;
            cout<<"\nSELECT MATCH: \n\n";
            cin>>x;
            string match;
         for(j=0;j<6;j++)
		 {
		    if(x==j+1)match=s[j];	 
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
			payments();
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
			cin>>x;
			system("cls");
			title();
			if(x==1)
			{
				spbalance=200;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.200 for having no helmet.\n";                        
			}
			else if(x==2)
			{
				spbalance=300;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  } 
			  cout<<endl<<"You are paying fine of Rs.300 for exceeding prescribed speed limit.\n";
			}
			else if(x==3)
			{
				spbalance=500;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			   cout<<endl<<"You are paying fine of Rs.500 for carrying passangers exceeding permissible limit.\n"; 
			}
			else if(x==4)
			{ 
				spbalance=200;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.200 for violation of traffic signals.\n"; 
			}
			else if(x==5)
			{
				spbalance=600;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.600 for overloading a goods vehicle.\n"; 
			}
			else if(x==6)
			{
				spbalance=1000;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.1000 for driving a motor vehicle at night without proper lights.\n"; 
			}
			else if(x==7)
			{
				spbalance=700;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }
			  cout<<endl<<"You are paying fine of Rs.700 for driving a motor vehicle without a driving licence.\n"; 
			}
			else if(x==8)
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
            cin>>y;
            system("cls");
            title();
          if(y==1)  /***********  a. University  *************/
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
			cin>>z;			
			system("cls");
			title();
		    if(z==5)
			{
			    cout<<"\nSpecify type of fees to pay: \n";
                string specie;
				getline(cin,specie);
				getline(cin,specie);
				cout<<endl<<"Enter amount to pay: ";
				cin>>spbalance;
              if(spbalance>totalBalance)
			  {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
		      }
		    }
		    else if(z==6)
		    {
		    	system("cls");
		    	goto back;
			}
		    else if(z>0||z<5)
			{
				cout<<"\nEnter amount to pay\n";
			    cin>>spbalance;
                if(spbalance>totalBalance)
				{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
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
		  else if(y==2)             /**********b. COLLEGE  ***********/
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
	        cin>>x;
	        system("cls");
	        title();
	        if(x==7)
			{
	            cout<<"Specify type of fee: \n";
	            string otherfee;
				getline(cin,otherfee);
	            getline(cin,otherfee);	            
			    cout<<"\n\nEnter amount to pay: \n";
				cin>>spbalance;
               if(spbalance>totalBalance)
			   {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
            	menu();
			  }            
	            cout<<"\nYou are paying Rs."<<spbalance<<" to "<<college<<" for "<<otherfee<<" fees\n";	
			}
			else if(x==8)
			{
				goto back;
			}
		

	        else if(x>0||x<7)
			{
			    cout<<"\n\nEnter amount to pay\n";
			    cin>>spbalance;
            if(spbalance>totalBalance)           
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
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
				cin>>voucher;            
               confirm();
          }
          else if(y==3) /*************  c. SCHOOL  ****************/

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
			cin>>x;
saddar3:	system("cls");
            title();
            cout<<"\n\n";
            if(x==7)
			{
	            cout<<"Specify type of fee: \n";
                string otherschoolfee;	 
	            getline(cin,otherschoolfee);
	            getline(cin,otherschoolfee);
	            cout<<"\n\nEnter amount to pay: \n";
				cin>>spbalance;
               if(spbalance>totalBalance)
              {
            	cout<<"\nYou do not have sufficient balance\n";
                getch();
            	menu();
		      }
            
	            cout<<"\nYou are paying Rs."<<spbalance<<" to "<<school<<" for "<<otherschoolfee<<" fees\n";
			}
			else if(x==8)
			{
				goto back;
			}
	        
		
			else if(x>0||x<7)
			
			{
				cout<<"\nEnter amount to pay\n";
				cin>>spbalance;
               if(spbalance>totalBalance)
	           {
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
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
          else if(y==4)
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
			cin>>y;            
			system("cls");
			title();
			if(y<0||y>4)
			{
				cout<<"\nIncorrect Input\n";
				cout<<"\nEnter any key\n";
				getch();
				menu();
			}
			else if(y==4)
			{
				payments();
			}
            cout<<endl<<"Enter amount you want to donate: \n";  
			cin>>spbalance;
            if(spbalance>totalBalance)     
			{
            	cout<<"\nYou do not have sufficient balance\n";
            	getch();
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
	




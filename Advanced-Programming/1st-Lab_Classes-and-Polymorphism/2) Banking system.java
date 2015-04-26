import java.util.Arrays;
import java.util.Scanner;
import java.math.BigDecimal;

class Account {
	private String name, balance;
	private static long idCount=0;
	private long id;
	
	public Account(){}
	public Account(String name, String balance)
	{
		id=idCount++;
		this.name=name;
		this.balance=balance;
	}
	public String getBalance()
	{
		//return String.format("%."+(balance.lastIndexOf('.')+3)+"s", balance);
		return balance;
	}
	public String getUserName()
	{
		return name;
	}
	public long getID()
	{
		return id;
	}
	public void setBalance(String balance)
	{
		this.balance=balance;
	}
	@Override
	public String toString()
	{
		return "Name:"+name+"\nBalance:"+String.format("%."+(balance.lastIndexOf('.')+3)+"s", balance)+"$\n";
	}
	@Override
	public boolean equals(Object o)
	{
		if (o==null) return false;
		if (getClass()!=o.getClass()) return false;
		Account a=(Account)o;
		if (name.equals(a.getUserName())&&balance.equals(a.getBalance())&&id==a.getID())
		{
			return true;
		}
		return false;
	}

}

abstract class Transaction {
	protected long from, to;
	protected String desc, amount;
	
	public Transaction(){}
	public Transaction(long from, long to, String desc, String amount)
	{
		this.from=from;
		this.to=to;
		this.desc=desc;
		this.amount=amount;
	}
	public String getFlatAmount(){return "0";}
	public int getPercent(){return 0;}
	
	public Transaction(long from, long to, String amount)
	{
		this.from=from;
		this.to=to;
		this.amount=amount;
	}
	public String getAmount()
	{
		return amount;
	}
	public String getDescription()
	{
		return desc;
	}
	public long getFromAccountID()
	{
		return from;
	}
	public long getToAccountID()
	{
		return to;
	}
	public String toString()
	{
		return "Amount:"+amount+"\nProvision:10.00$\nDescription:"+desc+"\nFrom:"+getFromAccountID()+"\nTo:"+getToAccountID()+'\n';
	}
}

class FlatAmountProvisionTransaction extends Transaction
{
	private String flat_amount_provision;
	
	FlatAmountProvisionTransaction(){}
	FlatAmountProvisionTransaction(long from, long to, String amount, String flat_amount_provision)
	{
		super(from, to, "FlatAmount", amount);
		this.flat_amount_provision=flat_amount_provision;
	}
	public String getFlatAmount()
	{
		return flat_amount_provision;
	}
	@Override
	public boolean equals(Object o) //DO NOT KNOW THE CRITERIA FOR EQUALITY!
	{
		if (o==null) return false;
		if (getClass()!=o.getClass()) return false;
		FlatAmountProvisionTransaction f=(FlatAmountProvisionTransaction)o;
		if (flat_amount_provision.equals(f.getFlatAmount())) return true;
		return false;
	}
}

class FlatPercentProvisionTransaction extends Transaction
{
	private int cents_per_dolar;
	
	FlatPercentProvisionTransaction(){}
	FlatPercentProvisionTransaction(long from, long to, String amount, int cents_per_dolar)
	{
		super(from, to, "FlatPercent", amount);
		this.cents_per_dolar=cents_per_dolar;
	}
	public int getPercent()
	{
		return cents_per_dolar;
	}
	
	@Override
	public boolean equals(Object o) //DO NOT KNOW THE CRITERIA FOR EQUALITY!
	{
		if (o==null) return false;
		if (getClass()!=o.getClass()) return false;
		FlatPercentProvisionTransaction f=(FlatPercentProvisionTransaction)o;
		if (cents_per_dolar==f.getPercent()) return true;
		return false;
	}
}

class Bank {
	private String name;
	public Account[] accounts;
	public String transfers, provision;

	public Bank() {
	}

	public Bank(String name, Account[] accounts) {
		this.name = name;
		transfers = "0.00$";
		provision = "0.00$";
		this.accounts = new Account[accounts.length];
		for (int i = 0; i < accounts.length; i++)
			this.accounts[i] = accounts[i];
	}

	public boolean inArray(long id) {
		for (Account a : accounts)
			if (a.getID() == id)
				return true;
		return false;
	}

	private boolean isFlatAmount(Transaction t)
	{
		if (t.getClass()==FlatAmountProvisionTransaction.class)
			return true;
		else
			return false;
	}
	
	public boolean makeTransaction(Transaction t) 
	{
		if (inArray(t.getFromAccountID())&&inArray(t.getToAccountID()))//botch users in same bank
		{
			double provisionAmount=0;
			double fromAccount=Double.parseDouble(accounts[(int)t.getFromAccountID()].getBalance().substring(0, accounts[(int)t.getFromAccountID()].getBalance().length()-1));
			double moneyToTransfer=Double.parseDouble(t.getAmount().substring(0,t.getAmount().length()-1));
			if(isFlatAmount(t))
				provisionAmount=Double.parseDouble(t.getFlatAmount().substring(0, t.getFlatAmount().length()-1));
			else
				provisionAmount=new BigDecimal((int)moneyToTransfer*t.getPercent()/(double)100).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
			if (fromAccount-(moneyToTransfer+provisionAmount)>=0) //do provision
			{
				accounts[(int)t.getFromAccountID()].setBalance(new BigDecimal(fromAccount-(moneyToTransfer+provisionAmount)).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+"$");
				accounts[(int)t.getToAccountID()].setBalance(new BigDecimal(Double.parseDouble(accounts[(int)t.getToAccountID()].getBalance().substring(0, accounts[(int)t.getToAccountID()].getBalance().length()-1))+moneyToTransfer).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+"$");
				provision=new BigDecimal(Double.parseDouble(provision.substring(0,provision.length()-1))+provisionAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+'$';
				transfers=new BigDecimal(Double.parseDouble(transfers.substring(0,transfers.length()-1))+moneyToTransfer).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+'$';
				return true;
			}
			return false;
		}
		return false;
	}

	public String totalTransfers() {
		return transfers;
	}

	public String totalProvision() {
		return provision;
	}

	@Override
	public String toString() {
		String str = new String(name + "\n\n");

		for (Account a : accounts) {
			str += a.toString() + "\n";
		}

		return str;
	}
	
	public String getName()
	{
		return name;
	}
	public String getTransfers()
	{
		return transfers;
	}
	public String getProvision()
	{
		return provision;
	}
	@Override
	public boolean equals(Object o)
	{
		if (o==null) return false;
		if (getClass()!=o.getClass()) return false;
		Bank b=(Bank)o;
		if (name.equals(b.getName())&&transfers.equals(b.getTransfers())&&provision.equals(b.getProvision())&&accounts.length==b.accounts.length)
		{
			for (int i=0;i<accounts.length;i++)
				if (!accounts[i].equals(b.accounts[i]))
					return false;
			return true;
		}
		return false;
	} 
}




public class BankTester {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		String test_type = jin.nextLine();
		switch ( test_type ) {
			case "typical_usage" : 
				testTypicalUsage(jin); 
				break;
			case "equals":
				testEquals(); 
				break;
		}
		jin.close();
	}
	
	private static void testEquals() {
		Account a1 = new Account("Andrej","20.00$");
		Account a2 = new Account("Andrej","20.00$");
		Account a3 = new Account("Andrej","30.00$");
		Account a4 = new Account("Gajduk","20.00$");
		if ( ! ( a1.equals(a1)&&! a1.equals(a2)
			&& ! a2.equals(a1)
			&& ! a3.equals(a1)
			&& ! a4.equals(a1)
			&& ! a1.equals(null) ) ) {
			System.out.println("Your account equals method does not work properly."); 
			return;
		}
		if ( a1.getID() == a2.getID() || a1.getID() == a3.getID() || a1.getID() == a4.getID()
			|| a2.getID() == a3.getID() || a2.getID() == a4.getID() || a3.getID() == a4.getID()	) {
			System.out.println("Different accounts have the same IDS. This is not allowed");
			return;
		}
		FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
		FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
		FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
		FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
		FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(20, 10, "20.00$", 10);
		FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
		FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
		FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
		if ( fa1.equals(fa1) &&
		   ! fa2.equals(null) &&
			 fa2.equals(fa1) &&
			 fa1.equals(fa2) &&
			 fa1.equals(fa3) &&
		   ! fa1.equals(fa4) &&
		   ! fa1.equals(fa5) &&
		   ! fa1.equals(fp1) &&
		     fp1.equals(fp1) &&
		   ! fp2.equals(null) &&
			 fp2.equals(fp1) &&
			 fp1.equals(fp2) &&
			 fp1.equals(fp3) &&
		   ! fp1.equals(fp4) &&
		   ! fp1.equals(fp5) &&
		   ! fp1.equals(fp6) ) {
			System.out.println("Your transactions equals methods do not work properly."); 
			return;
		}
		Account accounts[] = new Account[] { a1,a2,a3,a4 };
		Account accounts1[] = new Account[] { a2,a1,a3,a4 };
		Account accounts2[] = new Account[] { a1,a2,a3 };
		Account accounts3[] = new Account[] { a1,a2,a3,a4 };
		
		Bank b1 = new Bank("Test",accounts);
		Bank b2 = new Bank("Test",accounts1);
		Bank b3 = new Bank("Test",accounts2);
		Bank b4 = new Bank("Sample",accounts);
		Bank b5 = new Bank("Test",accounts3);
		if (! ( b1.equals(b1) &&
		    ! b1.equals(null) &&
			! b1.equals(b2) &&
			! b2.equals(b1) &&
			! b1.equals(b3) &&
			! b3.equals(b1) &&
			! b1.equals(b4) &&
			  b1.equals(b5)  )) {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}	
		accounts[2] = a1;
		if ( ! b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
		long from_id = a2.getID();
		long to_id = a3.getID();
		Transaction t = new FlatAmountProvisionTransaction(from_id,to_id,"3.00$","3.00$");
		b1.makeTransaction(t);
		if ( b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
		b5.makeTransaction(t);
		if ( ! b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
        System.out.println("All your equals methods work properly.");
	}

	private static void testTypicalUsage(Scanner jin) {
		String bank_name = jin.nextLine();
		int num_accounts = jin.nextInt();jin.nextLine();
		Account accounts[] = new Account[num_accounts];
		for ( int i = 0 ; i < num_accounts ; ++i ) 
			accounts[i] = new Account(jin.nextLine(),jin.nextLine());
		Bank bank = new Bank(bank_name,accounts);
		while ( true ) {
			String line = jin.nextLine();
			switch ( line ) {
				case "stop":
					return;
				case "transaction":
					String descrption = jin.nextLine();
					String amount = jin.nextLine();
					String parameter = jin.nextLine();
					int from_idx = jin.nextInt();
					int to_idx = jin.nextInt();
					jin.nextLine();
					Transaction t = getTransaction(descrption,from_idx,to_idx,amount,parameter,bank);
					System.out.println("Transaction amount:"+t.getAmount());
					System.out.println("transaction description:"+t.getDescription());
					System.out.println("Transaction succesfull? "+bank.makeTransaction(t));
					break;
				case "print":
					System.out.println(bank.toString());
					System.out.println("Total provisions:"+bank.totalProvision());
					System.out.println("Total transfers:"+bank.totalTransfers());
					System.out.println();
					break;
			}
		}
	}
	
	private static Transaction getTransaction(String description,int from_idx,int to_idx,String amount,String o,Bank bank) {
		switch ( description ) {
			case "FlatAmount":
				return new FlatAmountProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount,o);
			case "FlatPercent":
				return new FlatPercentProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount,Integer.parseInt(o));
		}
		return null;
	}
	
	

}

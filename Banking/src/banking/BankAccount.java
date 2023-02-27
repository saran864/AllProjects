package banking;



import java.util.Date;
import repository.BankingRepository;


public class BankAccount {
	BankAccountController bankAccountController;
	 BankingRepository br=BankingRepository.getInstance();
	public BankAccount(BankAccountController bankAccountController) {
		this.bankAccountController=bankAccountController;
	}

    public void CheckBalance(int accountNo) {
    	
    	long balance=br.checkBalance(accountNo);
    	if(balance==-1)
    	{
    		bankAccountController.returncheckBalance("Wrong Account Number!!");
    		
    	}
    	else
    	{
    		bankAccountController.returncheckBalance(balance);
    		
    	}
    	
	}
    public void checkWithDrawFund(int accountNo,long Amount, Date dateInput) {
		long balance=br.checkWithdraw(accountNo, Amount, dateInput);
		if(balance==0)
		{
			bankAccountController.returncheckWithDrawFund("Insufficient Balance");
		}
		else {
			bankAccountController.returncheckWithDrawFund(balance, Amount);
		}
	}

	public void CheckTransactionHistory(int accountNo) {
		
		bankAccountController.returnTrasactionHistory(br.viewTransection(accountNo)); 
		
	}

	public void checkDeposit(int accountNo,long Amount, Date date ) {
		
		long balance=br.checkDeposit(accountNo, Amount, date);
		System.out.println(balance);
		{
			bankAccountController.returncheckDeposit(balance, Amount);
		}
		
	}

	

		
	}

	
	    
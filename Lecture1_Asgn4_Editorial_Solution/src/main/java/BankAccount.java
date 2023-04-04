public class BankAccount {
    String accountNumber;
    int balance;
    double roi;

    double getSimpleInterest(int time){
        return balance * roi * time / 100;
    }

    double getBalanceWithInterest(int time){
        return balance + getSimpleInterest(time);
    }
}


import java.util.InputMismatchException;
import java.util.List;

public class MainMenu implements MainMenuInterface {

    public double balance;

    Card card;

    @Override
    public void showBalance() {

        for (Card card: App.usersCardList)
        {
            System.out.println("\n Account Balance: " + card.getBalance() + " MDL");
        }

        System.out.println("\n1. Recharge your card balance\n2. Main menu");

        try
        {
            System.out.print(App.YELLOW + "\nEnter your choice: " + App.RESET);
            int choice = App.sc.nextInt();

            switch (choice)
            {
                case 1 -> rechargeCardBalance();
                case 2 -> App.mainMenu();
                default -> {
                    App.ClearTerminal();
                    System.out.println(App.RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + App.RESET);
                    App.Delay();
                    App.ClearTerminal();
                    App.mainMenu();
                }
            }

        } catch (InputMismatchException e) {
            App.ClearTerminal();
            System.out.println(App.RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + App.RESET);
            App.sc.next();
            App.Delay();
            App.ClearTerminal();
            App.mainMenu();
        }
    }

    @Override
    public void rechargeCardBalance()
    {
        App.ClearTerminal();

        System.out.print("Enter an amount: $");
        double newAmount = App.sc.nextDouble();

        balance = newAmount + balance;
        card.setBalance(balance);

        System.out.println(App.GREEN + "Your card balance has successfully been recharged! " + App.RESET);
        App.Delay();

        showBalance();

    }

    @Override
    public void showCardDetails() {
        App.ClearTerminal();
        System.out.println(App.BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + App.RESET);
        System.out.println(App.GREEN + "\n-> Your Card Details" + App.RESET);

        List<Card> usersCardList = DataSystem.readCardData();
        List<User> usersDataList = DataSystem.readUsersData();

        for (Card card: usersCardList)
        {
            if (App.currentUserName.equals(card.getHolderName()))
            {
                for (User user: usersDataList)
                {
                    if (App.currentUserName.equals(user.getUsername()))
                    {
                        System.out.println("\nFull name: " + App.YELLOW + user.getFullName() + App.RESET);
                        break;
                    }
                }
                System.out.println("Card number: " + App.YELLOW  + card.getCardNumber() + App.RESET);
                System.out.println("Card CVV: " + App.YELLOW  + card.getCVV() + App.RESET);
                System.out.println("Card expiry date: " + App.YELLOW  + card.getExpiryDate() + App.RESET);
                System.out.println("Card type: " + App.YELLOW  + card.getCardType() + App.RESET + "\n");
                break;
            }
        }

        System.out.println("\n1. Main Menu");
        try
        {
            System.out.print(App.YELLOW + "\nEnter your choice: " + App.RESET);
            int choice = App.sc.nextInt();

            switch (choice)
            {
                case 1 -> App.mainMenu();
                default -> {
                    App.ClearTerminal();
                    System.out.println(App.RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + App.RESET);
                    App.Delay();
                    App.ClearTerminal();
                    App.mainMenu();
                }
            }

        } catch (InputMismatchException e) {
            App.ClearTerminal();
            System.out.println(App.RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + App.RESET);
            App.sc.next();
            App.Delay();
            App.ClearTerminal();
            App.mainMenu();
        }
    }

    @Override
    public void showLastTransactions() {

    }
}

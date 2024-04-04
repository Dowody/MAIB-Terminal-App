import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    static Scanner sc = new Scanner(System.in);

    static MainMenu menu = new MainMenu();
    static List<User> usersDataList = DataSystem.readUsersData();
    static List<Card> usersCardList = DataSystem.readCardData();

    static String currentUserName;
    static boolean hasDebitCard;

    public static void main (String[] args)
    {
        app();
    }

    public static void app()
    {
        while (true)
        {
            ClearTerminal();
            System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~\n" + RESET);

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Close app");

            try
            {
                System.out.print(YELLOW + "\nEnter your choice: " + RESET);
                int choice = sc.nextInt();

                switch (choice)
                {
                    case 1 -> loginUser();
                    case 2 -> registerUser();
                    case 3 -> closeApp();
                    default -> {
                        ClearTerminal();
                        System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                        Delay();
                        ClearTerminal();
                    }
                }

            } catch (InputMismatchException e) {
                ClearTerminal();
                System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                sc.next();
                Delay();
                ClearTerminal();
            }
        }
    }

    public static void closeApp()
    {
        ClearTerminal();
        System.out.println(GREEN + "\nExiting the program. Have a nice day!\n\n" + RESET);
        Delay();
        ClearTerminal();
        System.exit(0);
    }

    // REGISTER METHODS

    public static void registerUser()
    {
        ClearTerminal();

        System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + RESET);
        System.out.println(GREEN + "\n-> User Registration\n" + RESET);

        sc.nextLine();
        System.out.print(RESET + "Enter your Full Name: " + YELLOW);
        String full_name = sc.nextLine();

        System.out.print(RESET + "Enter your Date Of Birth: " + YELLOW);
        String date_of_birth = sc.next();

        System.out.print(RESET + "Enter your Phone Number: " + YELLOW);
        String phone_number = sc.next();

        System.out.print(RESET + "\nEnter a Username: " + YELLOW);
        String username = sc.next();


        for (User user: usersDataList)
        {
            if (user.getUsername().equals(username))
            {
                ClearTerminal();
                System.out.println(RED + "\nUsername already exists. Please choose a different username." + RESET);
                Delay(); ClearTerminal();
                registerUser();
                return;
            }
        }

        System.out.print(RESET + "Enter a Password: " + YELLOW);
        String password = sc.next();

        User newUser = new User(username, password, full_name, date_of_birth, phone_number);

        if (DataSystem.saveUser(newUser))
        {
            ClearTerminal();
            System.out.println(GREEN + "\nRegistration successful! You can now log in." + RESET);
            Delay(); ClearTerminal();

            usersDataList = DataSystem.readUsersData();
        }
        else
        {
            ClearTerminal();
            System.out.println(RED + "\nError registering user. Please try again later." + RESET);
            Delay(); ClearTerminal();
        }
    }

    public static void loginUser()
    {
        ClearTerminal();

        System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + RESET);
        System.out.println(GREEN + "\n-> User Login\n" + RESET);

        System.out.print("Enter your username: " + YELLOW);
        String username = sc.next();

        System.out.print(RESET + "Enter your password: " + YELLOW);
        String password = sc.next();

        boolean userFound = false;
        for (User user: usersDataList)
        {
            if (password.equals(user.getPassword()) && username.equals(user.getUsername()))
            {
                ClearTerminal();
                System.out.println(GREEN + "\nLogin successful! Welcome " + user.getFullName() + "." + RESET);
                Delay(); ClearTerminal();

                currentUserName = username;

                mainMenu();
                break;
            }
        }
        if (!userFound)
        {
            ClearTerminal();
            System.out.println(RED + "\nIncorrect username or password. Try again" + RESET);
            Delay();
            ClearTerminal();
        }
    }

    // MAIN MENU

    static void mainMenu()
    {
        ClearTerminal();

        for (User user: usersDataList)
        {
            System.out.println(user.getUsername());
        }

        for (Card card : usersCardList)
        {
            System.out.println(card.getHasDebitCard());
            if (card.getHolderName().equals(currentUserName))
            {
                System.out.println("FINE");
                hasDebitCard = true;
                break;
            }
        }




        if (!hasDebitCard)
        {

            System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + RESET);
            System.out.println(GREEN + "\n-> Main Menu\n" + RESET);

            System.out.println(RED + "You do not have any credit cards!" + RESET);

            System.out.println("\n1. Create your debit card");
            System.out.println("2. Log out");

            try
            {
                System.out.print(YELLOW + "\nEnter your choice: " + RESET);
                int choice = sc.nextInt();

                switch (choice)
                {
                    case 1 -> createCard();
                    case 2 -> app();
                    default -> {
                        ClearTerminal();
                        System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                        Delay();
                        ClearTerminal();
                        mainMenu();
                    }
                }

            } catch (InputMismatchException e) {
                ClearTerminal();
                System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                sc.next();
                Delay();
                ClearTerminal();
                mainMenu();
            }
        }
        else
        {
            System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + RESET);
            System.out.println(GREEN + "\n-> Main Menu\n" + RESET);

            System.out.println("1. Show card details");
            System.out.println("\n2. Balance");

            System.out.println("6. Log out");
            try
            {
                System.out.print(YELLOW + "\nEnter your choice: " + RESET);
                int choice = sc.nextInt();

                switch (choice)
                {
                    case 1 -> menu.showCardDetails();
                    case 2 -> menu.showBalance();
                    case 6 -> app();
                    default -> {
                        ClearTerminal();
                        System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                        Delay();
                        ClearTerminal();
                        mainMenu();
                    }
                }

            } catch (InputMismatchException e) {
                ClearTerminal();
                System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                sc.next();
                Delay();
                ClearTerminal();
                mainMenu();
            }
        }
    }

    // MAINMENU FUNCTIONS

    public static void createCard()
    {
        ClearTerminal();
        System.out.println(BLUE + "\n~~~~~~~~~~ MAIB ~~~~~~~~~~" + RESET);

        System.out.println(GREEN + "\n-> Create a Debit Card\n" + RESET);

        System.out.println("Choose your card type:");
        System.out.println("1. MAIB Daily");
        System.out.println("2. MAIB Freelance");
        System.out.println("3. MAIB Junior");
        System.out.println("4. MAIB Gama Universal");
        System.out.println("5. MAIB Gama Premium");
        System.out.println("6. MAIB Platinum");

        try
        {
            System.out.print(YELLOW + "\nEnter your choice: " + RESET);
            int choice = sc.nextInt();

            switch (choice)
            {
                case 1 -> CreateDebitCard(currentUserName, "MAIB Daily");
                case 2 -> CreateDebitCard(currentUserName, "MAIB Freelance");
                case 3 -> CreateDebitCard(currentUserName, "MAIB Junior");
                case 4 -> CreateDebitCard(currentUserName, "MAIB Universal");
                case 5 -> CreateDebitCard(currentUserName, "MAIB Premium");
                case 6 -> CreateDebitCard(currentUserName, "MAIB Platinum");
                default -> {
                    ClearTerminal();
                    System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
                    Delay();
                    ClearTerminal();
                    mainMenu();
                }
            }

        } catch (InputMismatchException e) {
            ClearTerminal();
            System.out.println(RED + "\nInvalid input! Please enter a valid number.\n\n\n\n" + RESET);
            sc.next();
            Delay();
            ClearTerminal();
            mainMenu();
        }

    }

    public static void CreateDebitCard(String currentUserName, String cardType)
    {
        String holderName = currentUserName;
        String cardNumber = generateCardNumber();
        String cvv = generateCVV();
        String expiryDate = "06/29";
        double balance = 0;

        Card card = new Card(holderName, cardNumber, cvv, expiryDate, cardType, true, balance);
        System.out.println();

        if (DataSystem.saveCard(card))
        {
            ClearTerminal();
            System.out.println(GREEN + "\nCard created successfully!" + RESET);
            Delay(); ClearTerminal();

            hasDebitCard = true;
            DataSystem.readCardData();
            mainMenu();
        }
        else
        {
            ClearTerminal();
            System.out.println(RED + "\nError creating your card. Please try again later." + RESET);
            Delay(); ClearTerminal();
        }

    }

    // UI METHODS

    public static void ClearTerminal()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Delay()
    {
        for (int i = 1; i < 4; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static String generateCardNumber()
    {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 16; i++)
        {
            if (i > 0 && i % 4 == 0)
            {
                sb.append(" ");
            }
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String generateCVV()
    {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 3; i++)
        {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}


//   TO DO
//
//   1. Fulfil account and see last fulfills
//   2. Send money function
//   3.
//   4.
//   5.
//
//
//      HAS DEBIT CARD PROBLEM WHEN CREATING ANY CARD
//
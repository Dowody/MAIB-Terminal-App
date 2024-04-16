import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DataSystem {

    private static final String USERS_DATA_FILE = "users_data.txt";
    private static final String USERS_CARD_FILE = "users_cards.txt";

    public static boolean hasDebitCard;

    public static List<User> readUsersData()
    {
        List<User> usersDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_DATA_FILE)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                String username = parts[0].trim();
                String password = parts[1].trim();
                String full_name = parts[2].trim();
                String date_of_birth = parts[3].trim();
                String phone_number = parts[4].trim();

                usersDataList.add(new User(username, password, full_name, date_of_birth, phone_number));
            }

        } catch (IOException e)  {
            System.out.println(App.RED + "\nError reading data :" + e.getMessage() + App.RESET);
        }

        return usersDataList;
    }

    public static boolean saveUser(User user)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_DATA_FILE, true)))
        {
            writer.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getFullName() + ", " + user.getDateOfBirth() + ", " + user.getPhoneNumber());
            writer.newLine();
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(App.RED + "\nError writing the data :" + e.getMessage() + App.RESET);
            return false;
        }
    }

    public static boolean saveCard(Card card)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_CARD_FILE, true)))
        {
            writer.write(
                card.getHolderName() + ", " +
                    card.getCardNumber() + ", " +
                    card.getCVV() + ", " +
                    card.getExpiryDate() + ", " +
                    card.getCardType() + ", " +
                    card.getHasDebitCard() + ", " +
                    card.getBalance());
            writer.newLine();
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(App.RED + "\nError writing the data :" + e.getMessage() + App.RESET);
            return false;
        }
    }

    public static List<Card> readCardData()
    {
        List<Card> usersCardList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_CARD_FILE)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");

                    if (parts.length == 7)
                    {
                        String holderName = parts[0].trim();
                        String cardNumber = parts[1].trim();
                        String cvv = parts[2].trim();
                        String expiryDate = parts[3].trim();
                        String cardType = parts[4].trim();
                        hasDebitCard = Boolean.parseBoolean(parts[5].trim());
                        double balance = Double.parseDouble(parts[6].trim());

                        usersCardList.add(new Card(holderName, cardNumber, cvv, expiryDate, cardType, hasDebitCard, balance));
                    }
            }

        } catch (IOException e)  {
            System.out.println(App.RED + "\nError reading data :" + e.getMessage() + App.RESET);
        }

        return usersCardList;
    }

}
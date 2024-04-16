import java.util.*;

public class PhoneBook {

    public static void main(String[] args) {
        Map<String, Contact> phoneBook = new HashMap<>();

        addContact(phoneBook, "Иванов Иван", "8-900-123-45-67");
        addContact(phoneBook, "Петров Петр", "8-901-765-43-21", "8-902-345-67-89");
        addContact(phoneBook, "Сидоров Сидор", "8-903-987-65-43");
        addContact(phoneBook, "Иванов Иван", "8-904-234-56-78"); // Повторяющееся имя
        addContact(phoneBook, "Петров Петр", "8-905-567-89-01"); // Повторяющееся имя

        Map<String, Contact> sortedPhoneBook = sortByNumberOfPhones(phoneBook);

        printPhoneBook(sortedPhoneBook);
    }

    private static void addContact(Map<String, Contact> phoneBook, String name, String... phoneNumbers) {
        Contact contact = phoneBook.getOrDefault(name, new Contact(name));
        for (String phoneNumber : phoneNumbers) {
            contact.addPhoneNumber(phoneNumber);
            break;
        }
        phoneBook.put(name, contact);
    }

    private static Map<String, Contact> sortByNumberOfPhones(Map<String, Contact> phoneBook) {
        LinkedHashMap<String, Contact> sortedPhoneBook = new LinkedHashMap<>(phoneBook);
        sortedPhoneBook.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getPhoneNumbers().size()).reversed())
                .forEach(entry -> sortedPhoneBook.put(entry.getKey(), entry.getValue()));
        return sortedPhoneBook;
    }

    private static void printPhoneBook(Map<String, Contact> phoneBook) {
        for (Contact contact : phoneBook.values()) {
            System.out.println(contact);
        }
    }

    static class Contact {
        private String name;
        private Set<String> phoneNumbers;

        public Contact(String name) {
            this.name = name;
            this.phoneNumbers = new HashSet<>();
        }

        public void addPhoneNumber(String phoneNumber) {
            phoneNumbers.add(phoneNumber);
        }

        public String getName() {
            return name;
        }

        public Set<String> getPhoneNumbers() {
            return phoneNumbers;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", phoneNumbers=" + phoneNumbers +
                    '}';
        }
    }
}
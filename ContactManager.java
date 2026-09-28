import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactManager {

    private static final Map<String, Contact> contacts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    static class Contact {
        String phone;
        String email;

        Contact(String phone, String email) {
            this.phone = phone;
            this.email = email;
        }
    }

    static void addContact() {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine().trim();

        if (contacts.containsKey(name)) {
            System.out.println("Contact '" + name + "' already exists. Use Update instead.");
            return;
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter email address: ");
        String email = scanner.nextLine().trim();

        contacts.put(name, new Contact(phone, email));
        System.out.println("Contact '" + name + "' added successfully.");
    }

    static void deleteContact() {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine().trim();

        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Contact '" + name + "' deleted successfully.");
        } else {
            System.out.println("Contact '" + name + "' not found.");
        }
    }

    static void searchContact() {
        System.out.print("Enter name (or part of name) to search: ");
        String term = scanner.nextLine().trim().toLowerCase();

        boolean found = false;

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            if (entry.getKey().toLowerCase().contains(term)) {
                Contact c = entry.getValue();

                System.out.println("Found -> Name: " + entry.getKey()
                        + ", Phone: " + c.phone
                        + ", Email: " + c.email);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contact found.");
        }
    }

    static void updateContact() {
        System.out.print("Enter the name of the contact to update: ");
        String name = scanner.nextLine().trim();

        if (!contacts.containsKey(name)) {
            System.out.println("Contact '" + name + "' not found.");
            return;
        }

        Contact c = contacts.get(name);

        System.out.println("Leave a field blank to keep the current value.");
        System.out.println("Current phone: " + c.phone);
        System.out.println("Current email: " + c.email);

        System.out.print("Enter new phone (or press Enter to skip): ");
        String newPhone = scanner.nextLine().trim();

        System.out.print("Enter new email (or press Enter to skip): ");
        String newEmail = scanner.nextLine().trim();

        if (!newPhone.isEmpty()) {
            c.phone = newPhone;
        }

        if (!newEmail.isEmpty()) {
            c.email = newEmail;
        }

        System.out.println("Contact '" + name + "' updated successfully.");
    }

    static void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts saved yet.");
            return;
        }

        System.out.println("\n" + "-".repeat(50));
        System.out.printf("%-20s%-15s%-20s%n", "Name", "Phone", "Email");
        System.out.println("-".repeat(50));

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            Contact c = entry.getValue();

            System.out.printf("%-20s%-15s%-20s%n",
                    entry.getKey(), c.phone, c.email);
        }

        System.out.println("-".repeat(50));
    }

    static void showMenu() {
        System.out.println("\n===== CONTACT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Contact");
        System.out.println("2. Delete Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Update Contact");
        System.out.println("5. Show All Contacts");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        while (true) {
            showMenu();

            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    deleteContact();
                    break;
                case "3":
                    searchContact();
                    break;
                case "4":
                    updateContact();
                    break;
                case "5":
                    showContacts();
                    break;
                case "6":
                    System.out.println("Exiting Contact Management System.");
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please enter a number from 1 to 6.");
            }
        }
    }
}

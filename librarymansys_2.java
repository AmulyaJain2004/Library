import java.util.*;

class User {
    String name;
    String password;
    String role;
    String[] borrowedBooks;
    User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.borrowedBooks = new String[5];
    }

    public void displayBorrowedBooks(){
        System.out.println("You have currently below books now: ");
        if (borrowedBooks[0] == null){
            System.out.println("You have not borrowed any books yet.");
            return;
        }
        else {
            for (int i = 0; borrowedBooks[i]!=null; i++){
                System.out.println(borrowedBooks[i]);
            }
        }
        return;
    }
}

class Library {
    public static String[] availableBooks = new String[50];
    public static String[] requested_books = new String[5];
    public static User[] users = new User[100]; 
    
    Library(){
        availableBooks[0] = "b1";
        availableBooks[1] = "b2";
        availableBooks[2] = "b3";
        users[0] = new User("admin", "admin", "librarian");
        users[1] = new User("user1", "user1", "user");
        users[2] = new User("user2", "user2", "user");
    }

    public void displayAvailableBooks(){
        System.out.println("Here are the available books: ");
        for (int i = 0; i < availableBooks.length; i++){
            System.out.println(availableBooks[i]);
        }
    }

    public void addBook(String bookName){
        for (int i = 0; i < availableBooks.length; i++){
            if (availableBooks[i] == null || availableBooks[i].equals("")){
                availableBooks[i] = bookName;
                System.out.println("Book added successfully: " + bookName);
                return;
            } 
            else if (availableBooks[i] != null && availableBooks[i].equals(bookName)){
                System.out.println("Book already exists: " + bookName);
                return;
            }
        }
        System.out.println("Library is full. Cannot add more books.");
    }

    public void removeBook(String bookName){
        for (int i = 0; i < availableBooks.length; i++){
            if (availableBooks[i] == null || availableBooks[i].equals("")){
                System.out.println("Book does not exist and cannot be removed: " + bookName);
                return;
            } 
            else if (availableBooks[i] != null && availableBooks[i].equals(bookName)){
                availableBooks[i] = "";
                System.out.println("Book removed successfully: " + bookName);
                return;
            }
        }
        System.out.println("Book not found: " + bookName);
    }

    public int requestBook(String bookName){
        int i  = 0;
        while (requested_books[i] != null){
            i++;
        }
        requested_books[i] = bookName;
        for (int j = 0; j < availableBooks.length; j++){
            if (availableBooks[j].equals(requested_books[i])){
                availableBooks[j] = "";
                System.out.println("Book requested successfully: " + bookName);
                return 1;
            }
        }
        System.out.println("Book not available: " + bookName);
        return 0;
    }

    public int assignBook(User user, String bookName){
        int i = 0;
        int assigned = 1;
        while (user.borrowedBooks[i] != null){
            i++;
        }
        user.borrowedBooks[i] = bookName;
        for (int j = 0; j < requested_books.length; j++){
            if (requested_books[j].equals(user.borrowedBooks[i])){
                requested_books[j] = "";
                System.out.println("Book assigned successfully: " + bookName);
                return assigned;
            }
            else {
                System.out.println("Book cannot be assigned due to some reasons");
            }
        }
        assigned = 0;   
        return assigned;
    }

    public static void librarianInterface(User librarian, Scanner sc){
        Library lib = new Library();
        System.out.println("Do you want to add a book or remove a book ?");
        System.out.println("Enter 1 for adding book and 2 for removing a book");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter the name of the book you want to add other than available books: ");
                String bookAdd = sc.next();
                lib.addBook(bookAdd);
                break;
            case 2:
                System.out.println("Enter the name of the book you want to remove from available books: ");
                String bookRemove = sc.next();
                lib.removeBook(bookRemove);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        sc.close();
    }

    public static void userInterface(User user, Scanner sc){
        Library lib = new Library();
        System.out.println("Do you want to read book or request a book ?");
        System.out.println("Enter 1 for reading book and 2 for requesting a book");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                lib.displayAvailableBooks();
                System.out.println("You can read the books from above listed books");
                break;
            case 2:
                System.out.println("Enter the name of the book you want to request: ");
                String bookName = sc.next();
                int isRequested = lib.requestBook(bookName);
                if (isRequested == 1){
                    int isAssigned = lib.assignBook(user, bookName);
                    if (isAssigned == 1){
                        user.displayBorrowedBooks();
                        System.out.println("You can read the books from above listed books");
                    }
                    else {
                        System.out.println("Book cannot be assigned due to some reasons");
                    }
                }
                else {
                    System.out.println("Book cannot be requested due to some reasons");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        sc.close();
    }

    public static void library(User authenticatedUser, Scanner sc){
        if (authenticatedUser.role.equals("librarian")){
            System.out.println("Welcome Librarian " + authenticatedUser.name);
            librarianInterface(authenticatedUser, sc);
        }
        else {
            System.out.println("Welcome " + authenticatedUser.name);
            userInterface(authenticatedUser, sc);
        }
    }
}

class librarymansys_2 {

    public static User signUp(Scanner sc){
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();
        System.out.println("Please enter your role:");
        String role = sc.nextLine();
        for(int i = 0; i < Library.users.length; i++){
            if (Library.users[i]==null){
                Library.users[i] = new User(username, password, role);
                System.out.println("User registered successfully!");
                return Library.users[i];
            }
            else if (Library.users[i].name.equals(username)){
                System.out.println("Username already exists. Try Logging in or Choose a different username.");
            }
        }
        return null;
    }

    public static User login_default(Scanner sc){
        Library lib = new Library(); // This is for Library class constructor to initialize the users array but I think I need to think of a better way to do this
        System.out.println("Welcome back to the Library Management System!");
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();
        for(int i = 0; i < Library.users.length; i++){
            if (Library.users[i] != null && Library.users[i].name.equals(username) && Library.users[i].password.equals(password)){
                System.out.println("Login successful!");
                return Library.users[i];
            }
        }
        System.out.println("User not found. Please sign up first.");
        sc.close();
        return null;
    }

    // public static User login(User user){
    //     for(int i = 0; i < users.length; i++){
    //         if (Library.users[i].name.equals(user.name) && Library.users[i].password.equals(user.password)){
    //             System.out.println("Login successful!");
    //             return Library.users[i];
    //         }
    //     }
    //     System.out.println("User not found. Please sign up first.");
    //     return null;
    // }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Please choose an option:");
        System.out.println("1. Sign Up\n2. Login\n3. Exit");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch(choice){
            case 1:
                User signInUser = signUp(sc);
                if (signInUser != null) {
                    // library entry
                    Library.library(signInUser, sc);
                } else {
                    System.out.println("Login failed. Please try again.");
                }
                break;
            case 2:
                User loginUser = login_default(sc);
                if (loginUser != null) {
                    // library entry
                    Library.library(loginUser, sc);
                } else {
                    System.out.println("Login failed. Please try again.");
                }
                break;
            case 3:
                System.out.println("Exiting the system. Goodbye!");
                sc.close();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        } 
        
        
        System.out.println("Registered Users:");
        for (User user : Library.users) {
            if (user != null) {
                System.out.println("Username: " + user.name + ", Password: " + user.password);
            }
        }
        System.out.println("Available Books:");
        for (String book : Library.availableBooks) {
            if (book == "") {
                System.out.println("Book not available");
            }
            else if (book != null) {
                System.out.println(book);
            }
        }
    }
}
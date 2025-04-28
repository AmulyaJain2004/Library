import java.util.Scanner;

class libmansys {
    String books[] = {"b1", "b2", "b3", "b4", "b5"};
    String requested_books[] = new String[5];
    String user_books[] = new String[5];

    public int requestBook(String bookName){
        int i  = 0;
        while (requested_books[i] != null){
            i++;
        }
        requested_books[i] = bookName;
        for (int j = 0; j < books.length; j++){
            if (books[j].equals(requested_books[i])){
                books[j] = "";
                System.out.println("Book requested successfully: " + bookName);
                return 1;
            }
        }
        System.out.println("Book not available: " + bookName);
        return 0;
    }

    public int assignBook(String bookName){
        int i = 0;
        int assigned = 1;
        while (user_books[i] != null){
            i++;
        }
        user_books[i] = bookName;
        for (int j = 0; j < requested_books.length; j++){
            if (requested_books[j].equals(user_books[i])){
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

    public static void main(String[] args){
        libmansys obj = new libmansys();
        System.out.println("Welcome to Library Management System");
        Scanner sc = new Scanner(System.in);
        System.out.println("Here are the available books: ");
        for (int i = 0; i < obj.books.length; i++){
            System.out.println(obj.books[i]);
        }
        System.out.println("Do you want to read book or request a book ?");
        System.out.println("Enter 1 for reading book and 2 for requesting a book");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("You can read the books from above listed books");
                break;
            case 2:
                System.out.println("Enter the name of the book you want to request: ");
                String bookName = sc.next();
                int isRequested = obj.requestBook(bookName);
                int isAssigned = 0;
                if (isRequested == 1){
                    isAssigned = obj.assignBook(bookName);
                    if (isAssigned == 1){
                        System.out.println("You have currently below books now: ");
                        for (int i = 0; obj.user_books[i]!=null; i++){
                            System.out.println(obj.user_books[i]);
                        }
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
        }
        sc.close();
        return;      
    }
}
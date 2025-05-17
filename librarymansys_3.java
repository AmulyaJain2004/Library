import java.util.*;

class Library {
    
}

/*----------------------------------------------------------------------------------------------- */
class Book {

    enum BookStatus {
        available,
        checked_out,
        reserved
    }

    public int id;
    public String title;
    public String author;
    public BookStatus status;
    
    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public void setStatus (BookStatus status) {
        this.status = status;
    }

    public String getStatus() {
        return status.toString();
    }
}

class Books_DB {
    public static ArrayList<Book> books = new ArrayList<Book>();

    // methods for books db operation
}

/*----------------------------------------------------------------------------------------------- */
class User {

    enum UserType {
        admin,
        student,
        librarian
    }

    public int id;
    public String name;
    public String email;
    public String password;
    public UserType role = null;

    public User(int id, String name, String email, String password, UserType role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role.toString();
    }

    // update user information 
    // update email, password, name

}

class Users_DB {
    public static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static User getUserById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return users.get(i);
            }
        }
        return null;
    }
   
    public static void updateUser(int id, String name, String email, String password) {
        User user = getUserById(id);
        if (user != null) {
            user.name = name;
            user.email = email;
            user.password = password;
        }
    }
    
    public static void deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            users.remove(user);
        }
    }
}

class Admin extends User {
    Admin(int id, String name, String email, String password) {
        super(id, name, email, password, UserType.admin);
    }
}

class Admins_DB extends Users_DB {

}

class Student extends User {
    Student(int id, String name, String email, String password) {
        super(id, name, email, password, UserType.student);
    }
}

class Students_DB extends Users_DB {

}

class Librarian extends User {
    Librarian(int id, String name, String email, String password) {
        super(id, name, email, password, UserType.librarian);
    }
}

class Librarians_DB extends Users_DB {

}

/*------------------------------------------------------------------------------------------------- */

public class librarymansys_3 {
    
}

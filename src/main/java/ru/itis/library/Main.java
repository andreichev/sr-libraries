package ru.itis.library;

import ru.itis.library.exceptions.DbException;
import ru.itis.library.model.Book;
import ru.itis.library.model.GivenBook;
import ru.itis.library.model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            for(User user: readUsers()) {
                System.out.println(user);
            }
        } catch (DbException e) {
            System.out.println(e.getMessage());
        }
    }

    static List<Book> readBooks() throws DbException {
        List<Book> result = new ArrayList<>();
        try {
            InputStream stream = new FileInputStream("data/books.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                Book book = new Book(Integer.parseInt(fields[0]), fields[1], fields[2]);
                result.add(book);
            }
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DbException("Data corrupted");
        }
        return result;
    }

    static List<User> readUsers() throws DbException {
        List<User> result = new ArrayList<>();
        try {
            InputStream stream = new FileInputStream("data/users.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                User user = new User(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]));
                result.add(user);
            }
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DbException("Data corrupted");
        }
        return result;
    }

    static void saveUser(User user) throws DbException {
        try {
            OutputStream stream = new FileOutputStream("data/users.csv", true);
            PrintStream printStream = new PrintStream(stream);
            printStream.print(user.getId());
            printStream.print(";");
            printStream.print(user.getName());
            printStream.print(";");
            printStream.print(user.getAge());
            printStream.println();
            printStream.close();
        } catch (FileNotFoundException e) {
            throw new DbException("File not found");
        }
    }

    static void giveBook(Book book, User user) {
        
    }

    static void saveGivenBook(GivenBook givenBook) throws DbException {
        try {
            OutputStream stream = new FileOutputStream("data/users_books.csv", true);
            PrintStream printStream = new PrintStream(stream);
            printStream.print(givenBook.getUserId());
            printStream.print(";");
            printStream.print(givenBook.getBookId());
            printStream.print(";");
            printStream.print(Instant.now().toString());
            printStream.println();
            printStream.close();
        } catch (FileNotFoundException e) {
            throw new DbException("File not found");
        }
    }
}

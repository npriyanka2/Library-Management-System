import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Book {
    private String bookID;
    private String bookTitle;
    private String studentName;
    private String registrationNumber;
    private String issuingDate;
    private String returnDate;

    public Book(String bookID, String bookTitle, String studentName, String registrationNumber, String issuingDate, String returnDate) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.studentName = studentName;
        this.registrationNumber = registrationNumber;
        this.issuingDate = issuingDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String[] toArray() {
        return new String[]{bookID, bookTitle, studentName, registrationNumber, issuingDate, returnDate};
    }
}

public class LibraryManagement extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, label4, label5, label6;
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private JPanel panel;
    private ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "books.txt"; // File for persistence

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Font font = new Font("Arial", Font.BOLD, 20);

        label1 = new JLabel("Book ID");
        label2 = new JLabel("Book Title");
        label3 = new JLabel("Student Name");
        label4 = new JLabel("Registration Number");
        label5 = new JLabel("Issuing Date");
        label6 = new JLabel("Return Date");

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);

        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);

        textField1.setFont(font);
        textField2.setFont(font);
        textField3.setFont(font);
        textField4.setFont(font);
        textField5.setFont(font);
        textField6.setFont(font);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.setFont(font);
        viewButton.setFont(font);
        editButton.setFont(font);
        deleteButton.setFont(font);
        clearButton.setFont(font);
        exitButton.setFont(font);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);
        gbc.gridx++;
        panel.add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label2, gbc);
        gbc.gridx++;
        panel.add(textField2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label3, gbc);
        gbc.gridx++;
        panel.add(textField3, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label4, gbc);
        gbc.gridx++;
        panel.add(textField4, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label5, gbc);
        gbc.gridx++;
        panel.add(textField5, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label6, gbc);
        gbc.gridx++;
        panel.add(textField6, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, gbc);
        add(panel);
        loadBooksFromFile(); // Load books from file on startup
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Book book = new Book(
                    textField1.getText(), textField2.getText(), textField3.getText(),
                    textField4.getText(), textField5.getText(), textField6.getText());
            books.add(book);
            saveBooksToFile(); // Save to file
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        } else if (e.getSource() == viewButton) {
            showBookTable();
        } else if (e.getSource() == editButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to edit:");
            for (Book book : books) {
                if (book.getBookID().equals(bookID)) {
                    book.setBookTitle(textField2.getText());
                    book.setStudentName(textField3.getText());
                    book.setRegistrationNumber(textField4.getText());
                    book.setIssuingDate(textField5.getText());
                    book.setReturnDate(textField6.getText());
                    saveBooksToFile(); // Save to file
                    JOptionPane.showMessageDialog(this, "Book updated successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        } else if (e.getSource() == deleteButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getBookID().equals(bookID)) {
                    books.remove(i);
                    saveBooksToFile(); // Save to file
                    JOptionPane.showMessageDialog(this, "Book deleted successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    // Method to display books in a JTable
    private void showBookTable() {
        String[] columns = {"Book ID", "Book Title", "Student Name", "Registration Number", "Issuing Date", "Return Date"};
        String[][] data = new String[books.size()][6];

        for (int i = 0; i < books.size(); i++) {
            data[i] = books.get(i).toArray();
        }

        JTable table = new JTable(data, columns);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setRowHeight(30);
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font(tableHeader.getFont().getName(), Font.BOLD, 20));

        JFrame frame = new JFrame("View Books");
        frame.add(scrollPane);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    // File Handling Methods
    private void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (Book book : books) {
                writer.println(String.join(",", book.toArray()));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving books: " + e.getMessage());
        }
    }

    private void loadBooksFromFile() {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                books.add(new Book(bookData[0], bookData[1], bookData[2], bookData[3], bookData[4], bookData[5]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing book file found, starting fresh.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage());
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagement());
    }
}

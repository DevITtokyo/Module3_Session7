package ra.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float price;
    private String author;
    private boolean status;

    public Book() {
    }
    public Book(String bookId, String bookName, float price, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status = status;
    }
    // Getter and Setter
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void inputData (Scanner scanner){
        // Mã sách gồm 4 ký tự ắt đầu là B
        System.out.print("Nhập vào mã sách : ");
        boolean checkBookId = true;
        do {
            this.bookId = scanner.nextLine();
            if (this.bookId.length() == 4) {
                if (this.bookId.startsWith("B")) {
                        break;
                } else {
                        System.err.println(" Mã sách bắt đầu là B, vui lòng nhập lại");
                    }
                } else {
                    System.err.println(" Mã sách gồm 4 ký tự, vui lòng nhập lại");
                }
        } while (checkBookId);

        // Tên sách có từ 10-50 ký tự
        System.out.print("Nhập vào tên sách : ");
        boolean checkBookName = true;
        do {
            this.bookName = scanner.nextLine();
            if (bookName.length() >= 10 && bookName.length() <= 50) {
                break;
            } else {
                System.err.println("Tên sách có độ dài từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (checkBookName);

        // Giá sách có giá trị lớn hơn 0
        System.out.print("Nhập vào giá sách : ");
        boolean checkPrice = true;
        do {
            this.price = Float.parseFloat(scanner.nextLine());
            if (price>0){
                break;
            }else {
                System.err.println("Giá sách phải lớn hơn 0, vui lòng nhập lại");
            }
        }while (checkPrice);

        // Nhập tên tác giả
        System.out.print("Nhập vào tên tác giả : ");
        this.author = scanner.nextLine();

        // Nhập vào trạng thái sách
        System.out.print("Nhập vào trạng thái sách : ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }
    public void displayData(){
        String bookStatus = this.status ? "Hoạt động" : "Không hoạt động";
        System.out.printf("Mã sách : %s - Tên sách: %s - Giá sách : %f\n",this.bookId,this.bookName,this.price);
        System.out.printf("Tác giả: %s - Trạng thái: %b\n",this.author,bookStatus);
        System.out.println("-----------------------------------------------------------------------------");
    }
}

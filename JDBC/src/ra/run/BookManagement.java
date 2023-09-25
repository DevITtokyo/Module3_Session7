package ra.run;

import ra.bussiness.BookBussiness;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************MENU************");
            System.out.println("1. Hiển thị  sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thống kê sách theo theo tác giả");
            System.out.println("7. Sắp xếp sách theo giá tăng dần");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    BookManagement.displayListBook();
                    break;
                case 2:
                    BookManagement.createBook(scanner);
                    break;
                case 3:
                    BookManagement.updateBook(scanner);
                    break;
                case 4:
                    BookManagement.deleteBook(scanner);
                    break;
                case 5:
                    BookManagement.searchBookByName(scanner);
                    break;
                case 6:
                    BookManagement.statisticalBook();
                    break;
                case 7:
                    BookManagement.sortBookPrice();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-8");
            }
        }while (true);
    }

    //1. Hiển thị sách
    public static void displayListBook(){
        List<Book> listProduct = BookBussiness.getAllBook();
        System.out.println("THÔNG TIN CÁC SÁCH:");
        listProduct.stream().forEach(book->book.displayData());
    }

    // 2. Thêm mới sách
    public static void createBook(Scanner scanner){
        //Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Book bookNew = new Book();
        bookNew.inputData(scanner);
        //Gọi createBook của BookBussiness để thực hiện thêm dữ liệu vào db
        boolean result = BookBussiness.createBook(bookNew);
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
        }
    }

    // 3.Cập nhật sách
    public static void updateBook(Scanner scanner){
        System.out.println("Nhập mã sách cần cập nhật:");
        String bookIdUpdate = scanner.nextLine();
        //Kiểm tra mã sách này có tồn tại hay không
        Book bookUpdate = BookBussiness.getBookById(bookIdUpdate);
        if (bookUpdate!=null){
            //Mã sách  có tồn tại trong CSDL
            System.out.print("Nhập vào tên sách để cập nhật: ");
            bookUpdate.setBookName(scanner.nextLine());
            System.out.print("Nhập vào giá sách để cập nhật: ");
            bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.print("Nhập vào tác giả để cập nhật: ");
            bookUpdate.setAuthor(scanner.nextLine());
            System.out.print("Nhập vào trạng thái để cập nhật: ");
            bookUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
            //Thực hiện cập nhật
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result) {
                System.out.println("Cập nhật thành công.");
            } else {
                System.err.println("Cập nhật thất bại, vui lòng thực hiện lại!");
            }
        } else {
            // bookId không tồn tại trong CSDL
            System.err.println("Mã sách không tồn tại.");
        }
    }

    //4. Xoá sách
    public static void deleteBook(Scanner scanner) {
        System.out.print("Nhập vào mã sách cần xóa: ");
        String bookIdDelete = scanner.nextLine();
        //Kiểm tra mã sách này có tồn tại hay không
        Book bookDelete = BookBussiness.getBookById(bookIdDelete);
        if (bookDelete != null) {
            boolean result = BookBussiness.deleteBook(bookIdDelete);
            if (result) {
                System.out.println("Xóa thành công.");
            } else {
                System.err.println("Có lỗi xảy ra, vui lòng thực hiện lại!");
            }
        } else {
            System.err.println("Mã sách không tồn tại!");
        }
    }

    // 5. Tìm kiếm sách theo tên sách
    public static void searchBookByName(Scanner scanner) {
        System.out.print("Nhập vào tên sách cần tìm: ");
        String bookNameSearch = scanner.nextLine().toLowerCase().trim();
        // //Kiểm tra tên sách có tồn tại hay không
        List<Book> bookList = BookBussiness.getBookByName(bookNameSearch);
        if (!bookList.isEmpty()) {
            System.out.println("Sách tìm theo tên "+ bookNameSearch);
            bookList.stream().forEach(Book::displayData);
            System.out.println();
        } else {
            System.err.println("Không tìm thấy sách " + bookNameSearch);
        }
    }

    // 6. Thống kê sách theo theo tác giả
    public static void statisticalBook() {
        System.out.println("Số lượng sách thống kê theo tác giả : ");
        BookBussiness.statisticalBookByAuthor();
    }
    //7. Sắp xếp sách theo giá tăng dần
    public static void sortBookPrice() {
        List<Book> bookList = BookBussiness.sortPriceASC();
        System.out.println("Sách được sắp xếp theo giá tăng dần : ");
        bookList.stream().forEach(Book::displayData);
        System.out.println();
    }
}

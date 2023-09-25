Create database BookManagement;
use BookManagement;

create table Book (
	bookId varchar(4) primary key unique,
    bookName varchar(50) not null unique,
    price float,
    author varchar(50),
    status bit default 1
);

-- 1. Lấy tất cả sách
DELIMITER //
create procedure get_all_book()
BEGIN
	select * from Book;
END //
DELIMITER ;

-- 2. Thêm mới sách
DELIMITER //
create procedure create_book(
	book_id varchar(4),
    book_name varchar(50),
    book_price float,
    book_author varchar(100),
    book_status bit
)
BEGIN 
	insert into Book(bookId, bookName, price, author, status)
    values(book_id, book_name, book_price, book_author, book_status);    
END //
DELIMITER ;

-- 3. Cập nhật sách
DELIMITER //
create procedure update_book(
	book_id varchar(4),
    book_name varchar(50),
    book_price float,
    book_author varchar(100),
    book_status bit
)
BEGIN
	update Book
    set bookName = book_name,
    price = book_price,
    author = book_author,
    status = book_status
    where bookId = book_id;
END //
DELIMITER ;

-- 4. Xoá sách
DELIMITER //
create procedure delete_book(book_id varchar(4))
BEGIN
	delete from Book
    where bookId = book_id;
END //
DELIMITER ;

-- 5. Tìm kiếm sách theo tên
DELIMITER //
create procedure get_book_by_name(book_name varchar(50))
BEGIN
	select bookId, bookName, price, author, status from Book
    where bookName like concat('%', book_name,'%');
END //
DELIMITER ;

-- 6. Thống kê sách theo tác giả
DELIMITER //
create procedure statistical_book_by_author()
BEGIN
	select author, count(bookId) as bookCnt from Book
    group by author;
END //
DELIMITER ;

-- 7. Sắp xếp sách theo giá tăng dần
DELIMITER //
create procedure sort_price_asc()
BEGIN
	select bookId, bookName, price, author, status from Book
    order by Book.price ASC;
END //
DELIMITER ;

--  Lấy sách theo bookId
DELIMITER //
create procedure get_book_by_id(book_id varchar(4))
BEGIN 
	select bookId, bookName, price, author, status from Book
    where bookId = book_id;
END //
DELIMITER ;
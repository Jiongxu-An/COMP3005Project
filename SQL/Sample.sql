--delete all data to initialize
delete from sale;
delete from book;
delete from publisher;
delete from customer;
delete from basket;
delete from order_info;
delete from tracking;

--insert sample data
insert into book values (9780008471293, '10000', 'The Lord Of The Rings', 'J.R.R. Tolkien', 'Novel', 1248, 199.00);
insert into book values (9780261102354, '10000', 'The Fellowship Of The Ring (the Lord Of The Rings, Book 1)', 'J.R.R. Tolkien', 'Novel', 448, 10.99);
insert into book values (9780261102378, '10000', 'The Return Of The King (the Lord Of The Rings, Book 3)', 'J.R.R. Tolkien', 'Novel', 464, 10.99);
insert into book values (9780571084838, '10001', 'LORD OF THE FILES', 'William Golding', 'Novel', 240, 14.50);
insert into book values (9781408855652, '10002', 'Harry Potter And The Philosopher''s Stone', 'J.K. Rowling', 'Novel', 352, 14.99);

insert into publisher values ('10000', 'HARPERCOLLINS PUBLISHERS', '195 Broadway', 'orders@harpercollins.com', '1-800-242-7737', '00001');
insert into publisher values ('10001', 'Faber & Faber', '74-77 Great Russell Street', 'bookshop@faber.co.uk', '+44 (0) 20 7927 3800');
insert into publisher values ('10002', 'Bloomsbury USA', '', 'contact-usa@bloomsbury.com', '(888) 330-8477');

create table publisher
	(publisher_id		varchar(5),
	name		     varchar(30),
	address    		varchar(30),
      email              varchar(30),
      phone_number       varchar(20),
      bank_account       varchar(5),
	primary key (publisher_id)
	);

create table customer
	(customer_id		varchar(5),
      email              varchar(30),
	name		     varchar(30),
      password           varchar(20),
	shipping    		varchar(30),
      billing            varchar(30),
	primary key (customer_id)
	);

create table book
     (isbn               numeric(13,0),
      publisher_id       varchar(5),
      title              varchar(30),
      author             varchar(20),
      genre              varchar(10),
      pages              int,
      price              numeric(5,2),
      primary key (isbn),
      foreign key (publisher_id) references publisher,
      check (pages > 0 and isbn > 0)
     );

create table sale
     (sale_number		varchar(5),
      publisher_id       varchar(5),
      isbn               numeric(13,0),
 	sale_percentage	numeric(2,0),
  	primary key (sale_number),
      foreign key (publisher_id) references publisher,
      foreign key (isbn) references book
	);

create table basket
	(basket_id		varchar(5),
      isbn               numeric(13,0),
	customer_id		varchar(10),
	primary key (basket_id),
      foreign key (isbn) references book,
      foreign key (customer_id) references customer
	);

create table order_info
	(order_number		varchar(5),
      customer_id        varchar(5),
	name		     varchar(30),
	shipping    		varchar(30),
      billing            varchar(30),
	primary key (order_number),
      foreign key (customer_id) references customer
	);

create table tracking
	(tracking_id        varchar(5),
      order_number		varchar(10),
	primary key (tracking_id),
      foreign key (order_number) references order_info
	);
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
	shipping    		varchar(30),
      billing            varchar(30),
	primary key (customer_id)
	);

create table book
     (isbn               varchar(13),
      publisher_id       varchar(5),
      title              varchar(100),
      author             varchar(20),
      genre              varchar(30),
      pages              int,
      price              numeric(5,2),
      num_available      int,
      num_sold           int,
      sale_percentage    numeric(2,2),
      primary key (isbn),
      foreign key (publisher_id) references publisher,
      check (pages > 0)
     );

create table basket
	(basket_id		varchar(5),
      isbn               varchar(13),
	primary key (basket_id),
      foreign key (isbn) references book
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
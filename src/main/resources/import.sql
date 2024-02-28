insert into "user" values (101, 'kollabhargavreddy@gmail.com','Bhargav','Reddy','admin','ssn101','user123');
insert into "user" values (102, 'kbhargavreddy09@gmail.com','Bhargav','Reddy','admin','ssn102','user124');

insert into orders (order_id, "user_id", order_description) values (2001,101, 'Order1');
insert into orders (order_id, "user_id", order_description) values (2003,101, 'Order2');
insert into orders (order_id, "user_id", order_description) values (2004,102, 'Order3');
insert into orders (order_id, "user_id", order_description) values (2005,102, 'Order4');
insert into options (name_option, price_option)
VALUES ('Social package: food',200);
insert into options (name_option, price_option)
VALUES ('Superior social package: food',500);
insert into options (name_option, price_option)
VALUES ('Payment for gas',120);
insert into options (name_option, price_option)
VALUES ('Payment for light',150);
insert into options (name_option, price_option)
VALUES ('Payment for water',100);
insert into options (name_option, price_option)
VALUES ('Set of clothes',2900);
insert into options (name_option, price_option)
VALUES ('Linens', 3500);

insert into needy (needy_name, needy_status, photo_path, option_id)
VALUES ('Donald Duck', 'status 1', '/storage/donald.gif', select option_id from options where name_option = 'Social package: food'),
       ('Guffi', 'status 2', '/storage/guffi.jpg',select option_id from options where name_option = 'Payment for gas'),
       ('Beast of Beauty', 'status 3', '/storage/beast.gif',select option_id from options where name_option = 'Set of clothes'),
       ('Dopey', 'status 4', '/storage/prostak.jpg',select option_id from options where name_option = 'Linens'),
       ('Grumpy', 'status 4', '/storage/vorchun.png',select option_id from options where name_option = 'Payment for light'),
       ('Yellow Moon', 'status 5', '/storage/moon.png',select option_id from options where name_option = 'Superior social package: food');


insert into user (username)
VALUES ('testuser');

insert into donation_list (user_id, created_date)
VALUES (sysdate, select user_id from user where username = 'testuser'),
       (sysdate, select user_id from user where username = 'testuser');

insert into donation_item (option_id, donation_list_id)
VALUES (select option_id from needy where needy_name = 'Donald Duck',
        select min(donation_list_id) from donation_list where user_id in (select user_id from user where username = 'testuser')),
        (select option_id from needy where needy_name = 'Dopey',
        select min(donation_list_id) from donation_list where user_id in (select user_id from user where username = 'testuser')),
        (select option_id from needy where needy_name = 'Beast of Beauty',
        select max(donation_list_id) from donation_list where user_id in (select user_id from user where username = 'testuser'));


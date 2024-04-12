insert into user_details(id, birth_date, name)
values (10001, current_date(), 'Leo');

insert into user_details(id, birth_date, name)
values (10002, current_date(), 'Leo 2');

insert into user_details(id, birth_date, name)
values (10003, current_date(), 'Leo 3');

insert into post(id, description, user_id)
values(20001, 'I want to learn Spring Boot', 10001);

insert into post(id, description, user_id)
values(20002, 'I want to know what love is', 10002);

insert into post(id, description, user_id)
values(20003, 'I want you to show me', 10002);
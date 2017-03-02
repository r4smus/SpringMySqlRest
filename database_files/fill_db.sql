use springrest;
insert into person (first_name,last_name) values ('Christian','Kienle');
insert into person (first_name,last_name) values ('Matthias','Winzig');


SELECT DATE_FORMAT(birthday,'%d.%m.%Y'), first_name, last_name
FROM springrest.person;
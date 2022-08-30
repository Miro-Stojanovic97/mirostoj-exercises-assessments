
drop database if exists solar_farm_test;
create database solar_farm_test;
use solar_farm_test;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled bit not null default(0),
    phone_number varchar(30) not null
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table solar_panel (
	id int primary key auto_increment,
	section varchar(100) not null,
	`row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(10) not null,
    is_tracking bit not null,
    app_user_id int not null,
    constraint fk_solar_panel_app_user_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id)
);

delimiter //
create procedure set_known_good_state()
begin
	delete from solar_panel;
    alter table solar_panel auto_increment = 1;
	delete from app_user_role;
	delete from app_user;
    alter table app_user auto_increment = 1;
	delete from app_role;
    alter table app_role auto_increment = 1;

	insert into app_role (`name`) values
		('USER'),
		('ADMIN');

	-- passwords are set to "P@ssw0rd!"
	insert into app_user (username, password_hash, disabled, phone_number)
		values
		('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, '555-444-3333'),
		('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, '777-888-4444');

	insert into app_user_role
		values
		(1, 2),
		(2, 1);

	insert into solar_panel (section, `row`, `column`, year_installed, material, is_tracking, app_user_id)
		values
		('The Ridge', 1, 1, 2020, 'POLY_SI', true, 1),
		('The Ridge', 1, 2, 2019, 'MONO_SI', true, 1),
		('Flats', 1, 1, 2017, 'A_SI', true, 1),
		('Flats', 2, 6, 2017, 'CD_TE', true, 2),
		('Flats', 3, 7, 2000, 'CIGS', false, 2);
    
end //
delimiter ;

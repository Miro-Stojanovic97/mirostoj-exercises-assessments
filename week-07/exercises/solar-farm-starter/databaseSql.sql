drop database if exists solar_farm;

create database solar_farm;

use solar_farm;

create table solar_panel (
    panel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(50) null,
    is_tracking boolean null
);

insert into solar_panel 
    (section, `row`, `column`, year_installed, material, is_tracking) 
values
    ('The Ridge', 1, 1, 2020, 'POLY', true),
    ('Flats', 2, 6, 2017, 'CD_TE', true);
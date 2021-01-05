### Item Service

1. Get items - return all the items details in the table
   http://localhost:6072/itemservice/items
2. Get item detail if item name is sent as a parameter
   http://localhost:6072/itemservice/items/{itemname}

```SQL

create database omsitemdb;
use omsitemdb;
create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
create table item (id bigint not null, creation_date varchar(20) not null, description varchar(100) not null, item_name varchar(50) not null, price double precision, primary key (id)) engine=MyISAM;

```

### [Buy me a Book](https://www.buymeacoffee.com/praveenoruganti)


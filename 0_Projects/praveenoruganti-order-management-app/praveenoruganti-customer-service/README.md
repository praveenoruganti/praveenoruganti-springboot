### Customer Service

1. Get customers - return all the customer details in the table
   http://localhost:6070/customerservice/customers
2. Create a customer by sending the customer details
   http://localhost:6070/customerservice/customers
   - when a create customer method is invoked. Insert the details in customer table and publish "CustomerCreated" event along with customer details.
   - sales order service has to subscribe to "CustomerCreated" event 

```SQL
create database omscustomerdb;
use omscustomerdb;
create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
create table customer (id bigint not null, creation_date varchar(20) not null, email varchar(50) not null, first_name varchar(50) not null, last_name varchar(50) not null, primary key (id)) engine=MyISAM;

```

### [Contribution for a Cause](http://bit.ly/2WryDT8)
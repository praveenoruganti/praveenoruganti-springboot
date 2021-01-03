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

### [Buy me a Book](https://www.buymeacoffee.com/praveenoruganti)

### Connect with me:

[<img align="left" alt="praveenorugantitech.blogspot.com" width="22px" src="https://raw.githubusercontent.com/iconic/open-iconic/master/svg/globe.svg" />][website]
[<img align="left" alt="praveenoruganti | Facebook Group" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/facebook.svg" />][facebookgroup]
[<img align="left" alt="praveenoruganti | Twitter" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/twitter.svg" />][twitter]
[<img align="left" alt="praveenoruganti | Instagram" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/instagram.svg" />][instagram]
[<img align="left" alt="praveenoruganti | Email" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/gmail.svg" />][email]

<br/>

[website]: https://praveenorugantitech.blogspot.com
[twitter]: https://mobile.twitter.com/praveenoruganti
[facebookgroup]: https://www.facebook.com/groups/praveenorugantitech
[instagram]: https://instagram.com/praveenorugantitech
[email]: mailto:praveenorugantitech@gmail.com


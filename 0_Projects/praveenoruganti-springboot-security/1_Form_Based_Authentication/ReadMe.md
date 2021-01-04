### SpringBoot Security Form Based Authentication

- Now open http://localhost:8080/api/v1/students

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/login.PNG "Login")

- Now enter user id as user and fetch the password from console like below and enter the same in login page.

Using generated security password: 4196312f-c7e5-45c3-a20b-780a0612089d


![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/login1.PNG "Login1")

- Login successful and it will show the service response.


![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/response1.PNG "Response1")


- Now open http://localhost:8080/logout

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/logout.PNG "Logout")

- Now click on Log Out Button

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/logout1.PNG "Logout1")

- Now we will customized the userid and password in application end i.e.. we need to set the below properties in application.yml

spring:
  security:
    user:
      name: praveenoruganti
      password: springsecurity
      
- Now open http://localhost:8080/api/v1/students and enter userid as praveenoruganti and password as springsecurity.


![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-security/1_Form_Based_Authentication/src/main/resources/images/customizedlogin.PNG "Logout1")


You can refer my [article](https://praveenorugantitech.blogspot.com/2019/05/spring-security-form-based.html) for more details. 

### [Buy me a Book](https://bit.ly/388sUbE)

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





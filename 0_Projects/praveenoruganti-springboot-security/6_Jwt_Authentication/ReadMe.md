### Spring Security JWT Authentication

JWT stands for JSON Web Token. JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact 
and self-contained way for securely transmitting information between parties as a JSON object. 
This information can be verified and trusted because it is digitally signed. The client will need to 
authenticate with the server using the credentials only once. During this time the server validates 
the credentials and returns the client a JSON Web Token(JWT). For all future requests the client 
can authenticate itself to the server using this JSON Web Token(JWT) and so does not need to send 
the credentials like username and password.


- Generate a JSON Web Token 
Create a POST request with url localhost:8080/authenticate. Body should have valid username and password. 
In our case username is praveen and password is password123.

{
  "username": "praveen",
  "password" : "password123"

}

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenoruganti-springboot-security/6_Jwt_Authentication/src/main/resources/images/jwt1.PNG "Authenticate")

- Validate the JSON Web Token
Try accessing the url localhost:8080/api/v1/students using the above generated token in the header as follows

Authorization : Bearer Token

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenoruganti-springboot-security/6_Jwt_Authentication/src/main/resources/images/jwt2.PNG "Authenticate")

- You can refer my [article](https://praveenorugantitech.blogspot.com/2019/05/spring-security-jwt-authentication.html) for more details. 






# interfaceadministration
First of all in Mysql add this :
  INSERT INTO roles(name) VALUES('ROLE_USER');
	INSERT INTO roles(name) VALUES('ROLE_SERVER');
	INSERT INTO roles(name) VALUES('ROLE_ADMIN');
  to add roles in DataBase
then download an extension chrome called "Advanced Rest Client" https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
to add an admin account write { "email" : "admin@gmail.com" ,
                                  "username" : "admin" ,
                                  "password" : "admin1234" ,
                                  "role" : "ROLE_ADMIN"}
to add a server account write { "email" : "server@gmail.com" ,
                                  "username" : "server" ,
                                  "password" : "server1234" ,
                                  "role" : "ROLE_SERVER"}  
to add a user account write { "email" : "user@gmail.com" ,
                                  "username" : "user" ,
                                  "password" : "user1234" ,
                                  "role" : "ROLE_USER"}                                  

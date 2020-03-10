# spring-boot-with-outh2

# build an application
	mvn clean install
# start an application
	mvn spring-boot:run

# use this url to get access and refresh token
	{your-server-url}/oauth/token	
	
	using these attribute
	first select basic auth and add client_id and client-secret
	add content-type:application/x-www-form-urlencoded in header
	after this add following attributes in the body
		username:Shahid
		password:password
		grant_type:password
 
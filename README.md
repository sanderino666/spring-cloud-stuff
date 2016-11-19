# spring-cloud-stuff

## Using the user-service

Users are stored in a in memory H2 database. To add a new user just append a new insert statement in user-service\src\main\resources\data.sql and restart the user-service.
The default user is John Doe. Username = user and password = password. 

1. Authenticate user: curl "acme:acmesecret@localhost:8002/uaa/oauth/token?grant_type=password&username=user&password=password" -X POST -H "Content-Type:application/x-www-form-urlencoded"
2. Get access_token from JSON response
3. Retrieve profile: curl "http://localhost:9999/user/uaa/v1/me" -X GET -H "Authorization: Bearer <access_token>"
4: Retrieve userInfo: curl "http://localhost:9999/user/uaa/user" -X GET -H "Authorization: Bearer <access_token>"
A weather alerts system, utilizing Springboot, MVC Design, and Java. Recommended usage with an API platform, such as Postman.

Run program, then utilize the providied URL's to get information, grouped by specific requirements.

Example data:
Get the email of everyone within a specific city.

// localhost:8080/apiPerson/communityEmail/city/{city}

Return person information based on given first and last names.

// http://localhost:8080/apiPerson/personInfo?firstName=John&lastName=Doe

Return person information grouped by address, based on station number.

// http://localhost:8080/apiPerson/firestation?stationNumber=1

Return station number and person information, grouped by address.

// http://localhost:8080/apiPerson/fire?address=123 Main St

Get all phone numbers belonging to a station.

// http://localhost:8080/apiPerson/phoneAlert?stationNumber=1

Return persons under the age of 18 at a specific address.

// http://localhost:8080/apiPerson/childAlert?address=123 Main St

List all people, grouped by fire station.

// http://localhost:8080/apiPerson/flood

    

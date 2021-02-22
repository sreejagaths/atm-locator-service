# atm-locator-service
1.Requirement Doc
  Backend Technical Test Overview 
    ● Create a spring boot application to expose the REST APIs with the following definition. 
    ● The application should invoke an external service to gather a list of atms: https://www.ing.nl/api/locator/atms/ 
    ● The application should expose following APIs and return a well formed JSON response 
    ○ An API to get a list of all the atms 
    ○ An API to filter the atms by city 

    ● Apply clean code standards and/or principals as much as possible during the implementation. 
  
  Bonus features 
    ● Unit tests using jUnit 
  
  Deliverables 
    ● The code can be shared as a zip or through github repository 
    ● A short document explaining how to run and test the application 
    ● Postman collection or curl commands to test the APIs


2. Design & Development
  Approach
    *Consume the external rest service on 1st request/bootstrap/asynchronously 
    *Cache it for further ref/use; use redis or any other simpler caching tech
	
  Technologies used
    Java8, Spring Boot, Maven, Eclipse
    Gson API for JSON to Java conversion    
    Redis for Caching
    RestTemplate for accessing external REST API to fetch ATMs
    EventListener to eager fetch of the ATMs details on Application Ready
    Swagger for documentation
    SLF4J for Logging

  Assumptions made
    Installing a new ATM is very seldom and hence loaded the ATM details on Application Ready and kept them available in the application/cache for further references
    *Redis cache has been installed already, otherwise install it first and run the application. Please ref https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-redis-on-ubuntu-16-04 for the steps to install and secure Redis on Ubuntu/Linux; Ref. Steps to run the application for more details in the same lines.
    *You can remove the caching related annotations from ATMLocatorController.java and AtmLocatorApplication.java if you dont want external caching


  Enhancements/ToDos
    We can sync the new ATMs details periodically by a scheduled job
    Junit Test cases


3. Steps To Run the Application
	* Ensure Redis server installed & configured already on the machine where we gonna run this application or Remove the caching related annotations, like @EnableCaching & @Cacheable, from the application, incase external caching not needed; However local/application level caching still be there which doesn’t need any external installation/configuration
	* Ensure changing the redis server password in application.properties file as per your need. Or you can have the default password 123123 added in your redis.conf file.
  Step 1. Locate the AtmLocatorApplication.java and run it as a standalone java program; You may use the shortcut Shft+Alt+X J to run it on eclipse
        *You can check the Eclipse console for the application status whether started
  Step 2. Open the browser and fire any of the APIs mentioned in the API Details/Documentation section of this doc

4. API Details/Documentation

  API 1. For the detailed API documentation 
  Swagger AP -http://localhost:8050/api/swagger.json

  API 2. Fetch All ATMs
  API -/atm-locator/
  Method: GET
  Sample Request: http://localhost:8050/atm-locator/
  Sample Response: 
    {"size":2681,"atms":[{"address":{"street":"Jonagoldstraat","housenumber":"57","postalcode":"6515 EN","city":"Nijmegen","geoLocation":{"lat":"51.879227","lng":"5.837548"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"12:00","hourTo":"21:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Pastoor Ossestraat","housenumber":"19","postalcode":"7627 PJ","city":"Bornerbroek","geoLocation":{"lat":"52.310178","lng":"6.655367"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},...

  Sample Error Response: 
    {
      "size": 0,
      "code": "500",
      "message": "Unable to load ATMs at this moment. Please try again!"
    }

  API 3. Fetch ATMs by City  
    API: /atm-locator/city/Geleen
    Method: GET
    Sample URL: http://localhost:8050/atm-locator/city/Geleen
    Sample Success Response:
      {"size":6,"atms":[{"address":{"street":"Markt","housenumber":"5","postalcode":"6161 GE","city":"Geleen","geoLocation":{"lat":"50.969017","lng":"5.827086"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Dr. H. van der Hoffplein","housenumber":"1","postalcode":"6162 BG","city":"Geleen","geoLocation":{"lat":"50.98319","lng":"5.84459"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Zuidhof","housenumber":"1","postalcode":"6164 BA","city":"Geleen","geoLocation":{"lat":"50.95744","lng":"5.82649"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Pastoor Vonckenstraat","housenumber":"19","postalcode":"6166 CV","city":"Geleen","geoLocation":{"lat":"50.97154","lng":"5.84031"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"07:00","hourTo":"23:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Rijksweg Zuid","housenumber":"2","postalcode":"6161 BP","city":"Geleen","geoLocation":{"lat":"50.967012","lng":"5.826757"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"08:00","hourTo":"21:00"}]},{"dayOfWeek":1,"hours":[{"hourFrom":"10:00","hourTo":"19:00"}]}],"functionality":"Geldautomaat","type":"GELDMAAT"},{"address":{"street":"Markt","housenumber":"108","postalcode":"6161 GN","city":"Geleen","geoLocation":{"lat":"50.970441","lng":"5.828722"}},"distance":"0","openingHours":[{"dayOfWeek":2,"hours":[{"hourFrom":"13:00","hourTo":"18:00"}]},{"dayOfWeek":3,"hours":[{"hourFrom":"09:00","hourTo":"18:00"}]},{"dayOfWeek":4,"hours":[{"hourFrom":"09:00","hourTo":"18:00"}]},{"dayOfWeek":5,"hours":[{"hourFrom":"09:00","hourTo":"18:00"}]},{"dayOfWeek":6,"hours":[{"hourFrom":"09:00","hourTo":"18:00"}]},{"dayOfWeek":7,"hours":[{"hourFrom":"09:00","hourTo":"17:00"}]},{"dayOfWeek":1,"hours":[]}],"functionality":"Geld storten en opnemen","type":"GELDMAAT"}]}

    Sample Error Response:
      {
        "size": 0,
        "code": "500",
        "message": "Unable to load ATMs of the requested city at this moment. Please try again!"
      }


# GDS Mini Project 

This Spring boot application is developed using Java 11. The repository contains 3 files, `seed.csv`, `test_proper.csv` and 
`test_improper.csv`.

`seed.csv` corresponds to the csv file that will be seeded upon application startup.


`test_proper.csv` corresponds to a csv file that will be accepted upon calling `/upload` post request


`test_improper_1.csv` and `test_improper_2.csv`  corresponds to a csv file that will be rejected upon calling `/upload` post request.
  - `test_improper_1.csv` contains salary that is not parsable.
  - `test_improper_2.csv` contains more than 2 rows.


### Additional questions

- What kind of testing would you perform on the application in addition to unit testing?
    - Integration testing where we test the interactions between the software classes and also 
    a stress test on the system, to ensure that the currently deployed system is able to meet performance
      requirement for the expected concurrent number of HTTP requests.
      

- How would you scale up the system to handle potentially millions of files ?
     - One way would be to increase the number of servers serving requests. A reverse proxy / load
            balancer can be used to distribute the requests in a round robin to reduce load on each server.
       

- CSV files can be very large and take a long time to process. This can be a problem using HTTP POST calls.
  How would you update the design to handle HTTP POST requests that can potentially take a long time to execute?
    - One potential solution would be to spin up a separate thread to execute the long running HTTP Post request.
    Once the server receives the request from the client, it could run the computation asynchronously in a different thread 
      while the main thread handling the request replies immediately. Once the computation is done, a callback can be executed
      to notify the client of the computed result. Another solution would be to use a pub/sub architecture to execute the computation
      asynchronously. The server similarly replies the client immediately while it sends the computation work to another backend service
      that will work on it. The second option involves more developmental effort as the transport mechanism has to be set up
      to send the computation input over to another service. A much better separation of concerns is achieved this way however, and additional compute 
      resources can be taken advantage of to potentially distribute the computation work. 



**CS 545 - Web Application Architecture**

**Project Requirements - (React + Spring)**


This is an engineering proof of concept. The goal is to get some hands-on experience building a full-stack project with Spring and React technologies.

 

**Project Topic – Mini Property Management System**

| **Feature**                                                  | **Done** |
| ------------------------------------------------------------ | -------- |
| **Admin**                                                    | Done     |
| a.    The admin should have a dashboard page                 | Done     |
| b.   If the owner registers to the web site, he/she need to get approval  from Admin in order to post properties. | Done     |
| **Owner**                                                    | Done     |
| a.    Register as Owner                                      | Done     |
| b.   Property (CRUD). If a property is under ‘**pending’** it cannot be deleted. | Done     |
| c.    Owner cannot submit offers on property from the website | Done     |
| d.   Maintain offers:  a)   Reject offer if the owner does not accept. The property status should remain  ‘**available**’   b)   If ‘**pending**’ phase get accepted from both sides. The property status becomes ‘**contingent**’.  c)   Receive messages from a customer (General inquires NOT an offer)  d)   Cancel contingency  e)   Maintain offers placed (list of placed offers) | Done     |
| **Customer**                                                 | Done     |
| a.    Register as Customer                                   | Done     |
| b.   Cannot offer properties on this website                 | Done     |
| a)   Customer actions:  a)   Check offer History  b)   Maintain current offers placed  c)   Cannot cancel offer after ‘contingency’  d)   Download/Print receipt as PDF or Excel  e)   Place offer, the property status will be changed to ‘**pending**’ if the offer  gets accepted  f)    Send message to the property owner  g)   Maintain Saved List | Done     |
| **General**                                                  | Done     |
| Login/Logout                                                 | Done     |
| Security with JWT (Users should not be able to  access other pages links) | Done     |
| Process verifications etc. (user get email of  purchase, gets a message)  Adding refresh tokens (Optional) | Done     |
| Validation is optional.                                      | Done     |
| **Technical aspects**                                        | Done     |
| Neat code and organization                                   | Done     |
| Managed packages, folders, and files                         | Done     |
|                                                              |          |
|                                                              |          |



## Running the project

- Take a checkout
  - To run the backend (API)
    - `cd ./src/api`
    - `./mvnw spring-boot:run`
  - To  run the front end Single Page Application (SPA)
    - `cd ./src/spa/`
    - `npm install`
    - `npm start`
- Navigate to `http://localhost:3000` from your browser
- *Note*: 
  - The images are served from a Azure blob storage, if the storage is not running configure it in `application.properties` file
  - The CORS policy is configured to `http://localhost:3000` 



Pre-defined users:

|      | Username              | Password |
| ---- | --------------------- | -------- |
| 1    | admin@admin.com       | admin    |
| 2    | customer@customer.com | customer |
| 3    | owner@owner.com       | owner    |


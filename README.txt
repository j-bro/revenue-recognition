# Revenue Recognition
Submission for SOEN 387 - Fall 2016, Assignment #2
Jeremy Brown ID 27515421

Revenue Recognition is a web application that allows creating sale contracts for given products & calculating
revenue on any given date for a given contract.


## Usage

### Products
From the homepage, click on 'Products'. Here you will see a list of the existing products in the database.
To learn more about a product, click on its name in the list to be taken to a page containing more information about it.

### Contracts
From the home page, click on 'Contracts'. Here you will see a list of the existing contracts in the database, and a
form to create a new contract. Click on a contract's name to see more information about it. The info page shows
the contract's ID, its related product, its revenue, and the recognized revenue at different dates. By entering a date
in the text field, the user can see how much revenue is expected to be made by the given date.

You can also create a contract by specifying a product, total revenue, and the date when the contract was signed.
With this informatino, the system computes the different revenue recognition dates for the new contract and stores
them in the databse, along with all other information related to that contract.


## Design

The application implements a design that lazy-loads objects from the database, and keeps them in memory in case they
are needed again soon. When fetching an object, the in-memory cache will be checked before going to the database.

See the DESIGN.txt file for more information about the application design.


## How to run

### Database setup
To initialize the database tables, run the db-init.sql script in your MySQL database.

### Populate initial data
Once the server is running, you can populate the database with some dummy data by making an empty POST request to
'http://<your-server-ip>/revenue-recognition/populate'.

### War
The project can be easily deployed on any container web server using the provided 'revenue-recognition.war' archive.
For example, using Tomcat simply upload the WAR file from the admin server manager page.
To enable connection to the database, set the following environment variables accordingly (examples given):
 - MYSQL_HOST=localhost
 - MYSQL_PORT=3306
 - MYSQL_ROOT_PASSWORD=rootroot

The service will be available at 'http://<your-server-ip>/revenue-recognition/'.

### From source
The source code of the project can be found under the 'src/' directory, however this does not contain the project build files.
The full project build files can be found at 'https://github.com/j-bro/revenue-recognition/archive/submission.zip'.
From the project root directory, run './gradlew build' to generate the WAR file.


Bonus: if you have Docker installed on your local machine, you can generate a Dockerfile by running ./gradlew createDockerfile.
This Dockerfile can be used to start a container running a Tomcat server with the application already installed.
Be sure to pass the environment variables used to connect the database to the container by using the -e flag.

(c) 2016 Jeremy Brown

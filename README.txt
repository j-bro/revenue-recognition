# Revenue Recognition
Submission for SOEN 387 - Fall 2016, Assignment #2
Jeremy Brown ID 27515421

[short description]


## Usage

### App must
 - Query the content of a contract from the database
 - Calculate the revenue recognition of a contract & store the result in the database
 - "Calculate the revenue for a contract and before a date"

### Need to
 - Delay load of object to memory until it is actually needed
 - Check if an object is in memory before querying the database
 - Maintain the modification status of an object before writing to the database
 - Maintain data consistency when multiple users use the system concurrently


## Data


## How to run

### Database setup
To initialize the database tables, run the db-init.sql script in your MySQL database.

### Populate initial data
Once the server is running, you can populate the database with some dummy data by making a POST request to 'http://<your-server-ip>/revenue-recognition/populate'.

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
Be sure to pass the environment variables to the container using the -e flag.

(c) 2016 Jeremy Brown

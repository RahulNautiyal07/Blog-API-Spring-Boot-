# Blog-API in Spring-Boot


## Development
   #### MySQL

      
   #### PostgresSQL
   
   To  login to Postgres, run the following command:

        psql postgres # for MacOS 
        sudo -u postgres psql postgres # for Linux


   1. Create Database, User and Psssword.
    
      Login to Postgres and run the following commands:

        ```postgresql
        CREATE DATABASE blog;
        CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blog_password';
        GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;
        ``
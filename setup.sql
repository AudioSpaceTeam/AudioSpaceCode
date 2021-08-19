-- # Below create the user we will be using to access the data for the application
-- # TODO: change user password to what you are going to use, don't use default
CREATE USER audiospace_user@localhost IDENTIFIED BY 'password';
-- # Below grants the user we just created all CRUD functionality on our new database.
GRANT ALL ON audiospace_db.* TO audiospace_user@localhost;
-- #  Below drops the database if it is already create (You will lose all previous data)
DROP DATABASE IF EXISTS audiospace_db;
-- # Creates database if it is not already created.
CREATE DATABASE IF NOT EXISTS audiospace_db;
-- # Make sure you are using said database to run anything.
USE audiospace_db;

# Incase you need to drop the tables uncomment out below, and run these.
# SET FOREIGN_KEY_CHECKS = 0;
# DROP TABLE events,events_genres,events_performers,events_requesters, genres,reviews,users,users_genres;
# SET FOREIGN_KEY_CHECKS = 1;




# Below create the user we will be using to access the data for the application
CREATE USER audiospace_user@localhost IDENTIFIED BY '1q2w3e4r!Q@W#E$R';
# Below grants the user we just created all CRUD functionality on our new database.
GRANT ALL ON audiospace_db.* TO audiospace_user@localhost;
#  Below drops the database if it is already create (You will lose all previous data)
DROP DATABASE IF EXISTS audiospace_db;
# Creates database if it is not already created.
CREATE DATABASE IF NOT EXISTS audiospace_db;
# Make sure you are using said database to run anything.
USE audiospace_db;

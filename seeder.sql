# DO NOT RUN THIS UNTIL SPRING MAKES YOUR DATABASE;
# Only things that need to be insert are the genres because we're hardcoding these, since users don't need to beable to create news ones.
INSERT INTO audiospace_db.genres(genre_name)
VALUES ('Rock'),
       ('Latin Music'),
       ('Country'),
       ('Techno'),
       ('Rap/Hiphop'),
       ('Reggae');

# INSERT INTO audiospace_db.events(description, location, price, slots, start_date_time, title, promoter_id)
# VALUES ('This is a description', 'Codeup',59.99,3,)

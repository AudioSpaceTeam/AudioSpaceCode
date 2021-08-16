# DO NOT RUN THIS UNTIL SPRING MAKES YOUR DATABASE;
# Only things that need to be insert are the genres because we're hardcoding these, since users don't need to beable to create news ones.
INSERT INTO audiospace_db.genres(genre_name)
VALUES ('Rock'),
       ('Latin Music'),
       ('Country'),
       ('Techno'),
       ('Rap/Hiphop'),
       ('Reggae');

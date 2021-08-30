# DO NOT RUN THIS UNTIL SPRING MAKES YOUR DATABASE;
# Only things that need to be insert are the genres because we're hardcoding these, since users don't need to beable to create news ones.

# We are not dropping the tables because we are using spring, so we do not create manually the tables, we just want to fill them in/clear them out after spring makes them.
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE audiospace_db.genres;
INSERT INTO audiospace_db.genres(genre_name)
VALUES ('Rock'),
       ('Latin Music'),
       ('Country'),
       ('Techno'),
       ('Rap/Hiphop'),
       ('Reggae');

TRUNCATE TABLE audiospace_db.genres2;
INSERT INTO audiospace_db.genres2(genre_name)
VALUES ('Jazz'),
       ('Blues'),
       ('Grunge'),
       ('Punk'),
       ('Classical'),
       ('Ambient');


# new seeder. Run seeder below.
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE audiospace_db.genres;
INSERT INTO audiospace_db.genres(genre_name)
VALUES ('Rock'),
       ('Latin Music'),
       ('Country'),
       ('Techno'),
       ('Rap/Hiphop'),
       ('Reggae'),
       ('Jazz'),
       ('Blues'),
       ('Grunge'),
       ('Punk'),
       ('Classical'),
       ('Ambient');

TRUNCATE TABLE audiospace_db.users;

INSERT INTO audiospace_db.users(bio, display_name, email, is_promoter, password, username, image_url)
VALUES ('Professional Promoter born and raised in San antonio.', 'Elliot', 'Elliot@gmail.com', true,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'elliboi',
        'https://cdn.filestackcontent.com/zT21FEt7S0SNbeo1gzUP'),
       ('I used to perform, but now I manage talent hit me up @ notAustin@gmail.com.', 'Dripstin Whipley',
        'notAustin@gmail.com', true, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'austino',
        'https://cdn.filestackcontent.com/NNQgJD2LRAeaSGtFjSdX'),
       ('Howdy I am Doug Dimmadome! Owner of the DimsDale DimmaDome! I am looking to forward to working with small artist, and help them out to get them some exposure.',
        'Doug DimmaDome', 'Doug@dimmadome.com', true, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC',
        'dougdimma', 'https://cdn.filestackcontent.com/wu1lKHYSRm6ipdCISFAY'),
       ('I have always wanted to be a Rock Star since I was young. Now I am chasing my dreams.', 'Bill Dipperly',
        'bill@dipperly.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'billdill',
        'https://cdn.filestackcontent.com/WAv5sPUSRCagHxOfkH70'),
       ('I am a Country/Latin music artist from Colorado.', 'Stick the Kid', 'stick@hotmail.com', false,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'stickyboi',
        'https://cdn.filestackcontent.com/vOncEMsTOqS1V8jANrmC'),
       ('I am a Reggae Artist but I also make Techno/Hiphop Music.', 'Snoop Lion', 'snoop@hotmail.com', false,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'snooplion',
        'https://cdn.filestackcontent.com/A5cDBw0NTYatYAao4Epw'),
       ('Hip hop artist, I also dabble in techno. I am a full time coder in real life tho. I just do this for fun.',
        'D3BUG', 'notJay@ymail.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'debuggin',
        'https://cdn.filestackcontent.com/GwvG3uhAQRuLes4mNVTI'),
       ('I make Latin Music, but recently I have been making a few Rock songs.', 'Lonely Road',
        'notJaun@gmail.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', '2juanS',
        'https://cdn.filestackcontent.com/Ab5DAktIQnCxYl8KNbDi');

TRUNCATE TABLE audiospace_db.users_genres;
INSERT INTO audiospace_db.users_genres(user_id, genre_id)
VALUES (4, 1),
       (5, 2),
       (5, 3),
       (6, 4),
       (6, 5),
       (6, 6),
       (7, 4),
       (7, 5),
       (8, 1),
       (8, 2);

# Andrea? or the manager to schedule time off.
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE audiospace_db.events;
INSERT INTO audiospace_db.events(description,location,address,city,state,zipcode, price, slots, start_date_time, title, promoter_id)
VALUES ('PAYS WELL!!! Experience the beauty of the Central Park fireflies along with Bach music for cello solo performed by acclaimed Cellist Elad Kabilio. This is the Graduation for Neptune! Any bands are welcome, we need a variety.', 'Codeup','600 Navarro St','San Antonio','Texas',78205,59.99, 3,
        '2021-09-3 12:30:00', 'Graduation', 2),
       ('Captivating views of the Manhattan skyline including the Statue of Liberty, Brooklyn Bridge, Ellis Island, the Freedom Tower and more.', 'The Guenther House','205 E Guenther','San Antonio','Texas',78204, 109.99, 2, '2021-08-30 20:00:00',
        'Super Cool Event', 2),
       ('The program will include selections from Bach''s masterful Suites for cello solo paired with night music such as Moon River and Debussy''s Clair De Lune.',
        'The St. Anthony, a Luxury Collection Hotel','300 East Travis Street','San Antonio','Texas',78205, 59.99, 3, '2021-08-18 14:27:00', 'Upside Down', 1),
       ('*INVITE THE WHOLE FAMILY! The right place to celebrate your birthday, surprise birthday, bachelor/bachelorette, family reunion, graduation, holidays & many more', 'Paramour Roof Top Bar, 4th Floor','102 9th St','San Antonio','Texas',78215, 50.99, 6, '2021-09-18 22:30:00',
        'Grand Opening', 1),
       (' We''re excited to make our return to this room - formerly Rose Gold - which has been reshaped and rejuvenated with a Funktion One sound system. We''ll be welcoming our guests Conor and A. Alvarez to the decks with us for this night of deep grooves. This is a free party',
        'TriPoint, San Antonio','3233 N St Marys St','San Antonio','Texas',78212, 108.99, 5, '2021-10-20 20:30:00', 'TriPoint Comedy', 1),
       ('A summer evening on the Hudson is a visit to the world’s best city in the 2019 Time Out Index. Warm breezes, city views and brilliant sunsets melting into moonlit waves. What a better way to enjoy them with three floors of music, friends & family, food & the Statue of Liberty!',
        'The Argyle, San Antonio','934 Patterson Ave','San Antonio','Texas',78209, 35.99, 4, '2021-09-10 11:30:00', 'Chill Event', 2),
       ('Looking to show off local talent, All are welcome to apply. While attending the event, you may be exposed to the risk of contracting a communicable disease, including but not limited to COVID-19. While we have put in place preventative measures to reduce the spread of COVID-19, we cannot guarantee that you will not become infected with COVID-19.',
        'Los Patios','934 Patterson Ave','San Antonio','Texas',78209, 70.99, 10,
        '2021-09-10 15:00:00', 'Exposure Event', 1),
       ('Looking for live Latin ambient music for background music. Also need to hype up award presenting moments. Unwind from the week and relax on the water while you indulge on breathtaking views of Manhattan and the Statue of Liberty. While on board, be sure to check out our fully stocked bar! Order drinks straight to your table or at the bar!',
        'Casa Rio','430 E Commerce St,','San Antonio','Texas',78205, 180.99, 3, '2021-09-12 14:00:00', 'Awards Ceremony', 1),
       ('Dress to Impress, this is an upscale venue in heart of New York City. Sneakers and jeans are ok for guys. Ladies heels, sandels, wedges ok... no sneakers. Admission at doorman''s discretion.',
        'The Rosenberg Sky Room','430 E Commerce St','San Antonio','Texas',78212, 70.99, 10, '2021-10-02 16:00:00', 'Get-together', 3),
       ('Swing by for a relaxing and lovely sunset concert - audience members are welcome to bring blankets or lawn chairs for their comfort, something to drink or snack on, and some questions! Family or date night friendly.',
        'The Veranda','1746 Lockhill Selma Rd','San Antonio','Texas',78213, 97.99, 2, '2021-10-24 11:00:00', 'Company Fun', 3);

TRUNCATE TABLE audiospace_db.events_genres;
INSERT INTO audiospace_db.events_genres(event_id, genre_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 1),
       (2, 4),
       (3, 2),
       (3, 3),
       (4, 1),
       (5, 5),
       (5, 6),
       (6, 2),
       (6, 3),
       (6, 4),
       (6, 6),
       (7, 1),
       (7, 2),
       (7, 3),
       (7, 4),
       (7, 5),
       (7, 6),
       (8, 2),
       (9, 4),
       (9, 5),
       (9, 6),
       (10, 2),
       (10, 3);

TRUNCATE TABLE audiospace_db.events_performers;
INSERT INTO audiospace_db.events_performers(event_id, user_id)
VALUES (1, 4),
       (1, 5),
       (1, 6),
       (2, 7),
       (3, 8),
       (4, 4),
       (5, 6),
       (5, 7),
       (6, 5),
       (7, 4),
       (9, 6),
       (9, 7),
       (10, 5);

TRUNCATE TABLE audiospace_db.events_requesters;
INSERT INTO audiospace_db.events_requesters(event_id, user_id)
VALUES (1, 7),
       (2, 4),
       (3, 5),
       (4, 5),
       (4, 8),
       (5, 5),
       (6, 6),
       (6, 7),
       (7, 5),
       (7, 6),
       (7, 7),
       (7, 8),
       (8, 5),
       (8, 8),
       (10, 8);


TRUNCATE TABLE audiospace_db.reviews;
INSERT INTO audiospace_db.reviews(body, rating, reviewee_id, reviewer_id)
VALUES ('Yea where is my money at bro. You weren\'t even there!', 1, 1, 4),
       ('Yea I was underpayed because of "taxes".', 2, 2, 5),
       ('Yoo he did dimma dab, but it was kinda weak...', 3, 3, 6),
       ('Super cool performance', 4, 4, 1),
       ('Awesome performance!', 5, 5, 2),
       ('This guy is TERRIBLE with kids.', 2, 6, 3),
       ('Perfect except for the outfits.', 4, 7, 1),
       ('They showed up drunk.', 1, 8, 2),
       ('As expected nothing too special.', 3, 1, 5),
       ('Epic show! Thanks for coming out.', 5, 2, 6),
       ('This show was life changing man.', 5, 3, 7),
       ('Showed up on time and played well.', 4, 4, 2),
       ('Had to leave early but while they were there it was awesome.', 3, 5, 3),
       ('Ugh never again.', 2, 6, 1),
       ('Do not quit your day job bucko.', 1, 7, 2),
       ('PHENOMENAL PERFORMANCE!', 5, 8, 3);

SET FOREIGN_KEY_CHECKS = 1;

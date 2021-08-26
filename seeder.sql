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

TRUNCATE TABLE audiospace_db.users;

INSERT INTO audiospace_db.users(bio, display_name, email, is_promoter, password, username, image_url)
VALUES ('Professional Promoter born and raised in San antonio.', 'Elliot', 'Elliot@gmail.com', true,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'elliboi',
        'https://cdn.filestackcontent.com/nffA9ioLQOKUoTmpFq50'),
       ('I used to perform, but now I manage talent hit me up @ notAustin@gmail.com.', 'Dripstin Whipley',
        'notAustin@gmail.com', true, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'austino',
        'https://cdn.filestackcontent.com/JP0iJwllTDuDNs11QhJL'),
       ('Howdy I am Doug Dimmadome! Owner of the DimsDale DimmaDome! I am looking to forward to working with small artist, and help them out to get them some exposure.',
        'Doug DimmaDome', 'Doug@dimmadome.com', true, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC',
        'dougdimma', 'https://cdn.filestackcontent.com/nffA9ioLQOKUoTmpFq50'),
       ('I have always wanted to be a Rock Star since I was young. Now I am chasing my dreams.', 'Bill Dipperly',
        'bill@dipperly.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'billdill',
        'https://cdn.filestackcontent.com/nffA9ioLQOKUoTmpFq50'),
       ('I am a Country/Latin music artist from Colorado.', 'Stick the Kid', 'stick@hotmail.com', false,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'stickyboi',
        'https://cdn.filestackcontent.com/Se3dPxQQqW2ILwMm6rr1'),
       ('I am a Reggae Artist but I also make Techno/Hiphop Music.', 'Snoop Lion', 'snoop@hotmail.com', false,
        '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'snooplion',
        'https://cdn.filestackcontent.com/5o9gKUvQ5eXgXstqomvF'),
       ('Hip hop artist, I also dabble in techno. I am a full time coder in real life tho. I just do this for fun.',
        'D3BUG', 'notJay@ymail.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', 'debuggin',
        'https://cdn.filestackcontent.com/PjNv7DRSRLKccjMTEbOh'),
       ('I make Latin Music, but recently I have been making a few Rock songs.', 'Twenti Juan Savage',
        'notJaun@gmail.com', false, '$2a$10$6B0X5JkY7PbzIdwWjk5fyuymdlb8OFrWXxFI7VYQn9lAaY0PRxHOC', '2juanS',
        'https://cdn.filestackcontent.com/4470CtaRwOK1STMtK0AL');

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

TRUNCATE TABLE audiospace_db.events;
INSERT INTO audiospace_db.events(description, location, price, slots, start_date_time, title, promoter_id)
VALUES ('This is the Graduation for Neptune! Any bands are welcome, we need a variety.', 'Codeup', 59.99, 3,
        '2021-09-3 12:30:00', 'Graduation', 2),
       ('This is a Rock and Techno event.', 'The Guenther House, San Antonio', 109.99, 2, '2021-08-30 20:00:00',
        'Super Cool Event', 2),
       ('This is a Country and Latin event. This is for a birthday party.',
        'The St. Anthony, a Luxury Collection Hotel, San Antonio', 59.99, 3, '2021-08-18 14:27:00', 'Upside Down', 1),
       ('Bar Opening Need Rock Bands only!', 'Paramour Roof Top Bar, San Antonio', 50.99, 6, '2021-09-18 22:30:00',
        'Grand Opening', 1),
       ('This is for a comedy event, live music will be played during breaks, We are looking for Rap/hiphop or Techno performers.',
        'TriPoint, San Antonio', 108.99, 5, '2021-10-20 20:30:00', 'TriPoint Comedy', 1),
       ('Exclusive event for investors. Only need instrumentals playing, no lyrics and nothing too distracting.',
        'The Argyle, San Antonio', 35.99, 4, '2021-09-10 11:30:00', 'Chill Event', 2),
       ('Looking to show off local talent, All are welcome to apply.', 'Los Patios, San Antonio', 70.99, 10,
        '2021-09-10 15:00:00', 'Exposure Event', 1),
       ('Looking for live Latin ambient music for background music. Also need to hype up award presenting moments.',
        'Casa Rio, San Antonio', 180.99, 3, '2021-09-12 14:00:00', 'Awards Ceremony', 1),
       ('Looking for some chill background music for a get-together. Preferably Rap/hiphop or Reggae. I\'m open to techno tho.',
        'The Rosenberg Sky Room, San Antonio', 70.99, 10, '2021-10-02 16:00:00', 'Get-together', 3),
       ('Looking for Country or Latin style performers. This event is for a company outting.',
        'The Veranda, San Antonio', 97.99, 2, '2021-10-24 11:00:00', 'Company Fun', 3);

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


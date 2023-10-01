insert into files(name, path) values('bring_him_home.jpg', 'file\945efbba-585f-4e55-b1b9-0d7c10c37974bring_him_home.jpg');
insert into files(name, path) values('little_woman.jpg', 'file\9322b203-cbc9-4dcb-8116-548d546a56d6little_woman.jpg');
insert into files(name, path) values('moon.jpg', 'file\3d02e134-7518-42d4-82e5-6240bc431903moon.jpg');
insert into files(name, path) values('thor.jpg', 'file\cb3541f3-5940-483b-bd3d-3ff41974ef88thor.jpg');
insert into genres(name) values('Action');
insert into genres(name) values('Horror');
insert into genres(name) values('Comedy');
insert into genres(name) values('Documentary');
insert into genres(name) values('Thriller');
insert into genres(name) values('Romance');
insert into genres(name) values('Drama');
insert into genres(name) values('Music film');
insert into films
    (name,
    description,
    "year",
    genre_id,
    minimal_age,
    duration_in_minutes,
    file_id)
values
    ('Thor',
    'As the son of Odin (Anthony Hopkins), king of the Norse gods, Thor (Chris Hemsworth) will soon inherit the throne of Asgard from his aging father. However, on the day that he is to be crowned, Thor reacts with brutality when the gods enemies, the Frost Giants, enter the palace in violation of their treaty.',
    2011,
    1,
    18,
    114,
    4);
insert into films
    (name,
    description,
    "year",
    genre_id,
    minimal_age,
    duration_in_minutes,
    file_id)
values
    ('Moon',
    'Moon ends with the clones learning a harsh truth: if a team comes to save them, they will be murdered. The second Sam, who is older, realizes that clones are supposed to die when the three-year plan to stay in space is over. The younger Sam wants GERTY to find a third clone so the older Sam can go back home.',
    2009,
    7,
    16,
    97,
    3);
insert into films
    (name,
    description,
    "year",
    genre_id,
    minimal_age,
    duration_in_minutes,
    file_id)
values
    ('Little woman',
    'Little Women is a 2019 American coming-of-age period drama film written and directed by Greta Gerwig. It is the seventh film adaptation of the 1868 novel of the same name by Louisa May Alcott. It chronicles the lives of the March sisters—Meg, Jo, Beth, and Amy—in Concord, Massachusetts, during the 19th century.',
    2019,
    7,
    18,
    135,
    2);
insert into films
    (name,
    description,
    "year",
    genre_id,
    minimal_age,
    duration_in_minutes,
    file_id)
values
    ('Bring him home',
    'When tough street kid Ricky Horvath loses his beloved dog Buddy to a syndicate of vicious dognappers, he must turn to the one man he cannot forgive: his estranged grandfather, police detective Geza Horvath.',
    2000,
    8,
    14,
    92,
    1);
insert into halls
    (name,
    row_count,
    place_count,
    description)
values
    ('Green hall',
    10,
    30,
    'Smallest hall in cinema'
    );
insert into halls
    (name,
    row_count,
    place_count,
    description)
values
    ('Yellow hall',
    11,
    35,
    'Middle hall in cinema'
    );
insert into halls
    (name,
    row_count,
    place_count,
    description)
values
    ('Yellow hall',
    12,
    40,
    'Biggest hall in cinema'
    );
insert into film_sessions
    (film_id,
    halls_id,
    start_time,
    end_time,
    price)
values
    (1,
    1,
    '2023-07-10 11:30:00',
    '2023-07-10 14:30:00',
    200);
insert into film_sessions
    (film_id,
    halls_id,
    start_time,
    end_time,
    price)
values
    (1,
    2,
    '2023-07-10 12:30:00',
    '2023-07-10 15:30:00',
    250);
insert into film_sessions
    (film_id,
    halls_id,
    start_time,
    end_time,
    price)
values
    (2,
    3,
    '2023-07-11 12:00:00',
    '2023-07-11 15:00:00',
    250);
insert into film_sessions
    (film_id,
    halls_id,
    start_time,
    end_time,
    price)
values
    (3,
    1,
    '2023-07-11 12:00:00',
    '2023-07-11 15:00:00',
    300);
insert into film_sessions
    (film_id,
    halls_id,
    start_time,
    end_time,
    price)
values
    (4,
    3,
    '2023-07-12 17:00:00',
    '2023-07-12 20:00:00',
    600);
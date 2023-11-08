insert into users (id, email, password) values
(100, 'tejonic399@qianhost.com', 'password'),
(101, 'john399@qianhost.com', 'password'),
(102, 'john499@qianhost.com', 'password'),
(103, 'john599@qianhost.com', 'password'),
(104, 'john699@qianhost.com', 'password'),
(105, 'john799@qianhost.com', 'password'),
(106, 'james007@qianhost.com', 'password');

insert into media (id, title, description, url, uploader_id, created_at) values
(100, 'my image', 'my first image upload', 'https://www.cloudinary.com/xyz',
 100, '2023-09-10 00:00:01'),
(102, 'my image', 'my first image upload', 'https://www.cloudinary.com/abc',
101, '2023-09-10 00:00:00'),
(103, 'my image', 'my first image upload', 'https://www.cloudinary.com/def',
101, '2023-09-10 00:00:00');

insert into comment (id, text, media_id, commenter_id, created_at) values
(200, 'I hate your picture', 102, 100, '2023-09-10 00:00:01'),
(201, 'I like your video', 102, 100, '2023-09-10 00:00:01');
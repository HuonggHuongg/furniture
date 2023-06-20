create database furniture;

use furniture;

create table role
(
    role_id   int auto_increment
        primary key,
    role_name varchar(50) null
);

INSERT INTO furniture.role (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO furniture.role (role_id, role_name) VALUES (2, 'ROLE_USER');

create table users
(
    user_name        varchar(255)  not null
        primary key,
    avatar           varchar(1000) null,
    created_at       date          null,
    deleted          bit           not null,
    email            varchar(255)  null,
    first_name       varchar(255)  null,
    last_name        varchar(255)  null,
    password         varchar(555)  null,
    phone_number     varchar(255)  null,
    updated_at       datetime(6)   null,
    reset_pass_token varchar(255)  null,
    address          varchar(255)  null
);

INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('admin', 'https://theeastwing.net/wp-content/uploads/sites/3/2022/02/hinh-anh-con-vat-9.jpg', '2023-05-09', false, 'admin12345@gmail.com', null, null, '$2a$10$.SYWXSxP9Ie/UnktpqiAA.4to9WHNVxnlFDd3WG0Dtdzp70OcHRnS', '0961734520', null, null, null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('anhhong', null, '2023-06-04', false, 'honganh@mailinator.com', null, null, '$2a$10$OVEHyEp84TqoK0AhVnVmaOu48tASmfsI2sTl0vFIVs8.ZLRf7.Dty', '0961734520', null, null, null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('behuong', 'https://theeastwing.net/wp-content/uploads/sites/3/2022/02/hinh-anh-con-vat-9.jpg', '2023-05-05', false, 'huong1234@gmail.com', 'Huong', 'Huong', '$2a$10$/NbjT/J0WBvm8ZrqAXbOx.nfJT1zr0UBPhftegFT5COkBUaJrg15C', '0905984497', '2023-06-04 20:05:00.717505', 'I7AjjpUSjU0dWZueNlAa', null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('honganh', null, '2023-06-04', false, 'anh12345@gmail.com', null, null, '$2a$10$r/bNFnSO7dng.lzwQVzUgeP3tVmmmUELfoCeTfGyMrbiY7OMkODHO', '0961734520', null, null, null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('honganh1', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686036896777_2022-03-31-21-23-02-401.jpg?alt=media&token=ef742f3d-868f-4ca7-beb0-8c66e045c048', '2023-06-04', false, 'honganh1@mailinator.com', 'Anhh', 'Hog', '$2a$10$qBfz6c0FH2drPpc7cGr4Oe0TNwIg6IYZgu6k4ZodhwMMO37ehD6Yu', '0961734520', '2023-06-06 14:35:01.389580', null, 'Nguyen Huy Tuong');
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('honganh123', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686794600653_IMG_3512.JPG?alt=media&token=5c8d4504-8f6c-492f-81d4-33939f1d1998', '2023-06-04', false, 'honganh123@mailinator.com', 'Hong', 'Anh', '$2a$10$P.fMxPMiKUIvIeXlKa0bpOqQCtT5MP6rEZGMa1AZhPVWafnehKxqy', '0961734520', '2023-06-15 09:03:25.541631', null, 'Nguyen Huy Tuong');
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('huong', 'https://theeastwing.net/wp-content/uploads/sites/3/2022/02/hinh-anh-con-vat-9.jpg', '2023-06-04', false, 'huong12345@gmail.com', 'Huong', 'huong', null, '0961734520', '2023-06-04 23:05:01.383908', null, 'Nguyen Huy Tuong');
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('huyenthoai', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686322536338_logo2.jpg?alt=media&token=da14d58d-2056-47d8-99d8-15a00a0b1dd9', '2023-06-04', false, 'huonghuong@mailinator.com', 'Thoai', 'Thoai', '$2a$10$N78QNX03X0uy7vbGWPiTYumWRNQND7sSHvBOH6Ir8nQpiBgxuu2hK', '0961734520', null, null, null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('karayuu', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686322536338_logo2.jpg?alt=media&token=da14d58d-2056-47d8-99d8-15a00a0b1dd9', '2023-06-04', false, 'karayuu@gmail.com', 'Huong', 'Huong', '$2a$10$zd2vj9DNWoxrHZWoRaGi8ejtvcHOB9ktuvjoRGWd1McLtr/bhY04C', '0905984497', null, null, null);
INSERT INTO furniture.users (user_name, avatar, created_at, deleted, email, first_name, last_name, password, phone_number, updated_at, reset_pass_token, address) VALUES ('phanan', '', '2023-05-14', false, 'phanan12345@gmail.com', 'An', 'Phan', '$2a$10$P.fMxPMiKUIvIeXlKa0bpOqQCtT5MP6rEZGMa1AZhPVWafnehKxqy', '0905984497', null, null, null);

create table user_role
(
    id        int auto_increment
        primary key,
    role_id   int          null,
    user_name varchar(255) null,
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (role_id),
    constraint FKpooi9jdvihvvs9242fnrhs147
        foreign key (user_name) references users (user_name)
);

INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (1, 2, 'behuong');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (2, 1, 'admin');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (3, 2, 'huong');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (4, 1, 'behuong');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (5, 2, 'phanan');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (6, 2, 'karayuu');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (7, 2, 'honganh');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (8, 2, 'huyenthoai');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (9, 2, 'anhhong');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (10, 2, 'honganh123');
INSERT INTO furniture.user_role (id, role_id, user_name) VALUES (11, 2, 'honganh1');

create table cart
(
    cart_id    int auto_increment
        primary key,
    created_at date         null,
    deleted    bit          not null,
    total      int          null,
    updated_at datetime(6)  null,
    user_name  varchar(255) null,
    constraint FKinqm4i288dclrkdvailj3sjd1
        foreign key (user_name) references users (user_name)
);

INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (1, '2023-05-05', false, null, null, 'behuong');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (2, '2023-05-09', false, null, null, 'admin');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (3, '2023-05-09', false, 1490000, '2023-05-30 10:29:40.316115', 'huong');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (4, '2023-05-14', false, 1758000, '2023-05-17 18:42:48.940436', 'phanan');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (5, '2023-06-04', false, null, null, 'karayuu');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (6, '2023-06-04', false, null, null, 'honganh');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (7, '2023-06-04', false, null, null, 'huyenthoai');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (8, '2023-06-04', false, null, null, 'anhhong');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (9, '2023-06-04', false, null, '2023-06-15 14:23:41.505399', 'honganh123');
INSERT INTO furniture.cart (cart_id, created_at, deleted, total, updated_at, user_name) VALUES (10, '2023-06-04', false, 888, '2023-06-15 22:36:13.832051', 'honganh1');


create table category
(
    category_id   int auto_increment
        primary key,
    category_name varchar(255) null,
    created_at    date         null,
    deleted       bit          not null,
    updated_at    datetime(6)  null
);

INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (1, 'Sofa', '2023-06-14', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (2, 'Chair', '2023-06-14', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (3, 'Desktop', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (4, 'Kitchen cabinet', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (5, 'Wall Cabinet', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (6, 'Lamb', '2023-06-16', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (7, 'Clock', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (8, 'Statue', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (9, 'Mirror', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (10, 'Carpet', '2023-05-05', false, null);
INSERT INTO furniture.category (category_id, category_name, created_at, deleted, updated_at) VALUES (19, 'Beds', '2023-06-16', false, null);

create table product
(
    product_id         int auto_increment
        primary key,
    created_at         date          null,
    deleted            bit           not null,
    description        varchar(3000) null,
    image              varchar(2000) null,
    inventory_quantity int           null,
    price              int           null,
    product_name       varchar(255)  null,
    updated_at         datetime(6)   null,
    category_id        int           null,
    constraint FK1mtsbur82frn64de7balymq9s
        foreign key (category_id) references category (category_id)
);

INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (25, '2023-06-07', false, '3-seater sofa from Osaka collection brings modern and poetic features of Japan, creating a unique living space full of luxury. The product has solid gold-colored stainless steel legs, the mattress is covered with fabric and can be removed. Osaka 3-seater sofa model 1 fabric 46 not only brings a sophisticated and luxurious design but also gives people a comfortable and pleasant feeling.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686120738382_SOFA-3-CHO-OSAKA-MAU-1-VAI-29.jpg?alt=media&token=3c73a670-dd59-4c0b-9d36-dde8617dfbb5', 2, 1300, 'Sofa 3 chỗ Osaka mẫu 1 vải 46', '2023-06-14 16:22:48.365057', 1);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (26, '2023-06-07', false, '3-seater sofa from Osaka collection brings modern and poetic features of Japan, creating a unique living space full of luxury. The product has solid gold-colored stainless steel legs, the mattress is covered with fabric and can be removed. Osaka 3-seater sofa model 1 fabric 46 not only brings a sophisticated and luxurious design but also gives people a comfortable and pleasant feeling.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686121261542_SOFA-CHICAGO-3-CHO-GOI.jpg?alt=media&token=80137950-ffaa-4bb5-9e2e-0fb3769f60f8', 994, 1500, 'Chicago 3 seater sofa + pillow', '2023-06-07 14:01:09.913952', 1);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (27, '2023-06-07', false, '3-seater sofa from Osaka collection brings modern and poetic features of Japan, creating a unique living space full of luxury. The product has solid gold-colored stainless steel legs, the mattress is covered with fabric and can be removed. Osaka 3-seater sofa model 1 fabric 46 not only brings a sophisticated and luxurious design but also gives people a comfortable and pleasant feeling.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686121335723_Sofa-3-Cho-Ona-Him-da-den.jpg?alt=media&token=388c4125-c4a8-477a-92f3-1e661c9922ac', 990, 1400, 'Ona Him 3-seater sofa black leather', null, 1);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (28, '2023-06-07', false, 'Elegance chair is made from American white ash. The transparent and slightly flexible seat is woven with polyester rope from Germany whose quality, high elasticity, and weaving method are intricately matched in terms of strength, weight, expression, and comfort. The gently curved armrests meet the frame of chair with feminine grace, inviting you to sit down. It is available with arm and without armrests. Elegance chair exudes the beauty of a professional dancer with elegance and fascinating.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686121449383_ban-ghe-an-phong-an-elegance-1.jpg?alt=media&token=2ebf41d2-411d-4ed2-80ad-6e9d0044255b', 992, 1300, 'Elegance armrest dining chair – black 1', null, 2);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (29, '2023-06-07', false, 'Elegance chair is made from American white ash. The transparent and slightly flexible seat is woven with polyester rope from Germany whose quality, high elasticity, and weaving method are intricately matched in terms of strength, weight, expression, and comfort. The gently curved armrests meet the frame of chair with feminine grace, inviting you to sit down. It is available with arm and without armrests. Elegance chair exudes the beauty of a professional dancer with elegance and fascinating.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686121547915_ban-an-peak-hien-dai-van-may-ceramic-22-768x461.jpg?alt=media&token=eeba629c-a4b6-4ea0-a587-0be9d7b9d0bc', 994, 1600, 'Peak dining table with cloud-patterned ceramic surface', null, 2);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (30, '2023-06-07', false, 'Elegance chair is made from American white ash. The transparent and slightly flexible seat is woven with polyester rope from Germany whose quality, high elasticity, and weaving method are intricately matched in terms of strength, weight, expression, and comfort. The gently curved armrests meet the frame of chair with feminine grace, inviting you to sit down. It is available with arm and without armrests. Elegance chair exudes the beauty of a professional dancer with elegance and fascinating.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686121758711_ban-an-pio.jpg?alt=media&token=29582fae-4055-4a0a-a242-a8687febd289', 989, 1100, 'Pio Dining table 6 seat – 1M8', '2023-06-07 14:09:20.939661', 2);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (31, '2023-06-07', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686122130966_103105-den-ban-savona-ma-trang-2.jpg?alt=media&token=63512daa-9c72-4771-a34b-366bbb3a9f8c', 998, 888, 'Table Lamp Savona', null, 6);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (32, '2023-06-07', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686122336108_DEN-DUNG-PALMERA-53063K-1.jpg?alt=media&token=624bccea-f8b7-4f3e-b49d-8ed9ec515dd9', 983, 989, 'Palmera Lamp 53063K', null, 6);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (33, '2023-06-12', false, 'Senzatempo Matt Oxide Watch Senzatempo Matt Oxide WatchSenzatempo Matt Oxide Watch', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686815840226_double-sofa-01.png?alt=media&token=42390d9f-7642-4f93-9311-deeb5adf2f92', 996, 888, 'Table Lamp Savona', '2023-06-15 14:57:23.812251', 1);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (34, '2023-06-14', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686815935751_102733-sofa-penny-3-cho-vai-xanh.jpg?alt=media&token=2fa2e607-1e48-45c3-9a8c-fbed24d49f4f', 20, 1200, 'Bridge sofa Brown Wood/Beige leather', '2023-06-15 14:58:58.021923', 1);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (35, '2023-06-14', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686734215192_double-sofa-02.png?alt=media&token=4df1e18b-bd29-497e-ab1e-51c234a13803', 20, 1000, 'Bridge sofa Brown Wood/Beige leather', '2023-06-14 16:16:58.533496', 3);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (36, '2023-06-14', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686734305234_double-sofa-03.png?alt=media&token=51a26065-b220-4704-b826-bdadc773edee', 989, 888, 'Bridge sofa Brown Wood/Beige leather', null, 3);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (37, '2023-06-14', false, 'Add a touch of texture to your table tops with the sophisticated Savona Table Lamp, featuring a white ceramic lamp base with spirally relief. Its cosmopolitan look is played up by an antique brass finish lamp socket and base and a simple cream lampshade.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686815717817_101490-sofa-rumba-goc-trai-phai-hien-dai-boc-vai-2.jpg?alt=media&token=d6297c18-5688-4bf3-bf19-5de04c9999ae', 989, 989, 'Table Lamp Savona', '2023-06-15 14:55:22.166186', 2);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (38, '2023-06-15', false, 'beautiful', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686815328704_6._fancy.jpg?alt=media&token=41b8366c-79ff-4eaf-9a14-8b5a046df174', 20, 1000, 'Palmera Kitchen', null, 4);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (39, '2023-06-15', false, 'beautiful', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686815778002_double-sofa-02.png?alt=media&token=d33cd6f4-86e8-4149-9e52-019e9c9475f5', 99, 1300, 'Bridge sofa Brown Wood/Beige leather', null, 2);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (40, '2023-06-16', false, 'Penny drawer bed with the highlight is four large storage compartments that conveniently store bedroom items such as pillows, blankets, and sheets very neatly. Definitely will be the optimal choice for modern bedroom space. Penny drawer bed has 2 sizes 1m6 and 1m8, diverse colors.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686889520114_3_90508_1-4.jpg?alt=media&token=284a58bf-4a58-4953-91c8-f4c2706144fc', 999, 999, 'Penny drawer bed 1m8', null, 19);
INSERT INTO furniture.product (product_id, created_at, deleted, description, image, inventory_quantity, price, product_name, updated_at, category_id) VALUES (41, '2023-06-16', false, 'Penny drawer bed with the highlight is four large storage compartments that conveniently store bedroom items such as pillows, blankets, and sheets very neatly. Definitely will be the optimal choice for modern bedroom space. Penny drawer bed has 2 sizes 1m6 and 1m8, diverse colors.', 'https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686889565785_giuong-cayon-3-101869-1.jpg?alt=media&token=edf003d6-8ee0-487f-b731-06809f3a9a3e', 999, 1111, 'Canyon Bed 1m6', null, 19);


create table cart_item
(
    cart_item_id      int auto_increment
        primary key,
    created_at        date        null,
    deleted           bit         not null,
    payment_cart_item int         null,
    quantity          int         null,
    updated_at        datetime(6) null,
    cart_id           int         null,
    product_id        int         null,
    constraint FK1uobyhgl1wvgt1jpccia8xxs3
        foreign key (cart_id) references cart (cart_id),
    constraint FKjcyd5wv4igqnw413rgxbfu4nv
        foreign key (product_id) references product (product_id)
);

INSERT INTO furniture.cart_item (cart_item_id, created_at, deleted, payment_cart_item, quantity, updated_at, cart_id, product_id) VALUES (215, '2023-06-15', false, 888, 1, '2023-06-16 10:17:09.121774', 10, 33);

create table status_order
(
    status_id   int auto_increment
        primary key,
    status_name varchar(255) null
);

INSERT INTO furniture.status_order (status_id, status_name) VALUES (1, 'Pending');
INSERT INTO furniture.status_order (status_id, status_name) VALUES (2, 'Deliveried');


create table order_user
(
    order_id          int auto_increment
        primary key,
    created_at        datetime(6)  null,
    deleted           bit          not null,
    full_name         varchar(255) null,
    phone_number      varchar(255) null,
    receiving_address varchar(255) null,
    total_order       int          null,
    updated_at        datetime(6)  null,
    status_id         int          null,
    user_name         varchar(255) null,
    payment_status    bit          not null,
    constraint FKfe8hjh1oux8r9j1ucil9woce
        foreign key (status_id) references status_order (status_id),
    constraint FKoy196p7yw1jpib1qelbfm692w
        foreign key (user_name) references users (user_name)
);

INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (83, '2023-06-07 13:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 989, '2023-06-07 15:32:01.930000', 2, 'huyenthoai', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (84, '2023-06-07 13:00:00.000000', false, 'Hong Anh', '0961734520', 'Nguyen Luong Bang', 1100, '2023-06-09 14:51:56.604411', 2, 'karayuu', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (85, '2023-06-08 14:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 3488, '2023-06-08 14:52:00.454000', 2, 'karayuu', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (86, '2023-06-08 15:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 2800, '2023-06-08 14:52:01.850000', 2, 'honganh123', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (87, '2023-06-08 15:20:00.000000', false, 'huong', '0961734520', 'aaa', 2389, '2023-06-14 11:01:53.212162', 2, 'honganh123', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (92, '2023-06-08 15:40:00.000000', false, 'huong', '0961734520', 'aaa', 1500, '2023-06-10 14:52:03.268622', 2, 'honganh123', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (93, '2023-06-08 15:00:00.000000', false, 'huong', '0961734520', 'aaa', 2700, '2023-06-10 14:52:04.762868', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (94, '2023-06-09 09:09:09.000000', false, 'huong', '0961734520', 'aaa', 6589, '2023-06-10 14:52:05.673027', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (95, '2023-06-09 10:10:00.000000', false, 'huong', '0961734520', 'aaa', 1100, '2023-06-10 14:52:06.291636', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (96, '2023-06-09 11:11:11.000000', false, 'huong', '0961734520', 'aaa', 1500, '2023-06-10 14:52:07.010836', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (97, '2023-06-09 12:12:12.000000', false, 'huong', '0961734520', 'aaa', 1100, '2023-06-10 14:52:07.205545', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (98, '2023-06-09 13:13:00.000000', false, 'huong', '0961734520', 'aaa', 1100, '2023-06-10 14:52:07.518640', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (99, '2023-06-09 08:00:00.000000', false, 'Hong Anh', '0961734520', 'Nguyen Luong Bang', 1300, '2023-06-10 14:52:07.960346', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (100, '2023-06-09 08:30:00.000000', false, 'huong', '0961734520', 'aaa', 989, '2023-06-10 14:52:08.504515', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (101, '2023-06-09 09:00:00.000000', false, 'huong', '0961734520', 'aaa', 989, '2023-06-10 14:52:09.259889', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (102, '2023-06-09 10:30:00.000000', false, 'huong', '0961734520', 'aaa', 1100, '2023-06-10 14:52:09.475734', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (103, '2023-06-09 13:14:00.000000', false, 'huong', '0961734520', 'aaa', 1300, '2023-06-10 14:52:09.907155', 2, 'honganh1', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (104, '2023-06-09 16:08:00.000000', false, 'huong', '0961734520', 'aaa', 1300, '2023-06-10 14:52:10.251708', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (105, '2023-06-09 19:10:00.000000', false, 'huong', '0961734520', 'aaa', 1600, '2023-06-10 14:52:10.572849', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (106, '2023-06-09 22:00:00.000000', false, 'huong', '0961734520', 'aaa', 1100, '2023-06-10 14:52:10.822273', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (107, '2023-06-09 20:00:00.000000', false, 'huong', '0961734520', 'aaa', 4100, '2023-06-10 14:52:11.035461', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (108, '2023-06-09 13:00:00.000000', false, 'huong', '0961734520', 'aaa', 2900, '2023-06-10 14:52:11.251746', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (109, '2023-06-09 09:00:00.000000', false, 'huong', '0961734520', 'aaa', 2389, '2023-06-10 14:52:11.596893', 1, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (110, '2023-06-09 18:00:00.000000', false, 'huong', '0961734520', 'aaa', 5600, '2023-06-11 14:52:11.839000', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (111, '2023-06-10 07:00:00.000000', false, 'huong', '0961734520', 'aaa', 4000, '2023-06-10 14:52:12.085340', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (112, '2023-06-10 08:00:00.000000', false, 'huong', '0961734520', 'aaa', 2700, '2023-06-10 14:52:12.271959', 2, 'honganh123', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (113, '2023-06-12 17:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 989, '2023-06-12 09:52:39.649074', 2, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (114, '2023-06-15 19:00:00.000000', false, 'honganh123', '0909090909', 'huong', 5000, null, 1, 'honganh123', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (115, '2023-06-15 07:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 1500, null, 1, 'honganh1', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (116, '2023-06-15 18:00:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 1776, '2023-06-15 22:32:18.640705', 1, 'honganh1', true);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (117, '2023-06-15 18:30:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 2600, null, 1, 'honganh1', false);
INSERT INTO furniture.order_user (order_id, created_at, deleted, full_name, phone_number, receiving_address, total_order, updated_at, status_id, user_name, payment_status) VALUES (118, '2023-06-15 20:20:00.000000', false, 'huong', '0961734520', 'Nguyen Huy Tuong', 888, '2023-06-15 22:34:59.237991', 1, 'honganh1', true);


create table order_item
(
    order_item_id      int auto_increment
        primary key,
    created_at         date        null,
    deleted            bit         not null,
    payment_order_item int         null,
    quantity           int         null,
    updated_at         datetime(6) null,
    order_id           int         null,
    product_id         int         null,
    feedback_status    bit         not null,
    constraint FK551losx9j75ss5d6bfsqvijna
        foreign key (product_id) references product (product_id),
    constraint FKo1mdo0r4tafqf4fbojuye2l4a
        foreign key (order_id) references order_user (order_id)
);

INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (114, '2023-06-07', false, 989, 1, '2023-06-10 09:12:12.897153', 83, 32, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (115, '2023-06-07', false, 1100, 1, null, 84, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (116, '2023-06-08', false, 2600, 2, '2023-06-09 16:48:41.297365', 85, 28, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (117, '2023-06-08', false, 888, 1, '2023-06-09 16:46:55.494225', 85, 31, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (118, '2023-06-08', false, 1500, 1, '2023-06-09 16:50:15.255904', 86, 26, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (119, '2023-06-08', false, 1300, 1, null, 86, 25, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (120, '2023-06-08', false, 1500, 1, null, 92, 26, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (121, '2023-06-08', false, 1300, 1, null, 93, 25, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (122, '2023-06-08', false, 1400, 1, null, 93, 27, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (123, '2023-06-09', false, 989, 1, null, 94, 32, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (124, '2023-06-09', false, 5600, 4, null, 94, 27, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (125, '2023-06-09', false, 1100, 1, null, 95, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (126, '2023-06-09', false, 1500, 1, null, 96, 26, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (127, '2023-06-09', false, 1100, 1, null, 97, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (128, '2023-06-09', false, 1100, 1, null, 98, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (129, '2023-06-09', false, 1300, 1, null, 99, 28, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (130, '2023-06-09', false, 989, 1, null, 100, 32, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (131, '2023-06-09', false, 989, 1, null, 101, 32, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (132, '2023-06-09', false, 1100, 1, null, 102, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (133, '2023-06-09', false, 1300, 1, '2023-06-09 16:42:18.420067', 103, 25, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (134, '2023-06-09', false, 1300, 1, null, 104, 25, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (135, '2023-06-09', false, 1600, 1, null, 105, 29, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (136, '2023-06-09', false, 1100, 1, '2023-06-15 22:20:04.331444', 106, 30, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (137, '2023-06-09', false, 1500, 1, '2023-06-09 16:39:39.087814', 107, 26, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (138, '2023-06-09', false, 2600, 2, '2023-06-09 16:40:59.808969', 107, 25, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (139, '2023-06-09', false, 1600, 1, '2023-06-09 16:34:11.467443', 108, 29, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (140, '2023-06-09', false, 1300, 1, '2023-06-09 16:34:53.769449', 108, 28, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (141, '2023-06-09', false, 1400, 1, '2023-06-09 16:38:36.009089', 109, 27, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (142, '2023-06-09', false, 989, 1, '2023-06-09 15:57:16.431891', 109, 32, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (143, '2023-06-09', false, 2600, 2, '2023-06-09 23:34:43.853456', 110, 25, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (144, '2023-06-09', false, 1400, 1, '2023-06-09 23:34:43.858473', 110, 27, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (145, '2023-06-09', false, 1600, 1, '2023-06-09 23:34:43.861462', 110, 29, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (146, '2023-06-10', false, 1100, 1, '2023-06-10 09:00:45.547314', 111, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (147, '2023-06-10', false, 1600, 1, '2023-06-10 09:00:45.529088', 111, 29, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (148, '2023-06-10', false, 1300, 1, '2023-06-10 09:00:45.536074', 111, 28, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (149, '2023-06-10', false, 1100, 1, '2023-06-10 09:06:55.188204', 112, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (150, '2023-06-10', false, 1600, 1, '2023-06-10 09:06:55.185204', 112, 29, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (151, '2023-06-12', false, 989, 1, '2023-06-12 09:58:50.697167', 113, 32, true);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (152, '2023-06-15', false, 2800, 2, '2023-06-15 14:23:26.645867', 114, 27, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (153, '2023-06-15', false, 2200, 2, '2023-06-15 14:23:26.650741', 114, 30, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (154, '2023-06-15', false, 1500, 1, '2023-06-15 22:27:00.578173', 115, 26, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (155, '2023-06-15', false, 1776, 2, '2023-06-15 22:30:21.919238', 116, 33, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (156, '2023-06-15', false, 2600, 2, '2023-06-15 22:32:55.126529', 117, 28, false);
INSERT INTO furniture.order_item (order_item_id, created_at, deleted, payment_order_item, quantity, updated_at, order_id, product_id, feedback_status) VALUES (157, '2023-06-15', false, 888, 1, '2023-06-15 22:34:05.595729', 118, 33, false);


create table feedback
(
    feedback_id  int auto_increment
        primary key,
    comment_text varchar(255) null,
    created_at   date         null,
    deleted      bit          not null,
    rating       int          null,
    updated_at   datetime(6)  null,
    product_id   int          null,
    user_name    varchar(255) null,
    constraint FK5rp548xa4cf1txqho4c90ufls
        foreign key (user_name) references users (user_name),
    constraint FKlsfunb44jdljfmbx4un8s4waa
        foreign key (product_id) references product (product_id)
);

INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (2, 'Clock is soo beautiful!!!', '2023-06-08', false, 4, null, 26, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (10, 'Clock is soo beautiful!!!', '2023-06-08', false, 4, null, 26, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (11, 'Lamb is good', '2023-06-08', false, 4, null, 32, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (12, 'so beautiful!!!', '2023-06-08', false, 5, null, 30, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (13, 'Verry good! Must try', '2023-06-09', false, 5, null, 32, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (14, 'a', '2023-06-09', false, 5, null, 32, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (15, 'ok', '2023-06-09', false, 5, null, 29, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (16, 'So beautiful!!!', '2023-06-09', false, 5, null, 29, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (17, 'qwe', '2023-06-09', false, 5, null, 29, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (18, 'BEAUTIFUL!!!', '2023-06-09', false, 5, null, 29, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (19, 'BEAUTY!!!', '2023-06-09', false, 5, null, 27, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (20, 'OKK!!', '2023-06-09', false, 5, null, 26, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (21, 'OK', '2023-06-09', false, 5, null, 25, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (22, 'a', '2023-06-09', false, 5, null, 25, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (23, 'q', '2023-06-09', false, 5, null, 31, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (24, '123', '2023-06-09', false, 5, null, 28, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (25, 'So good!', '2023-06-09', false, 5, null, 26, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (26, 'Good', '2023-06-10', false, 3, null, 32, 'honganh123');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (27, 'good', '2023-06-12', false, 4, null, 32, 'honganh1');
INSERT INTO furniture.feedback (feedback_id, comment_text, created_at, deleted, rating, updated_at, product_id, user_name) VALUES (28, 'Expensive ', '2023-06-15', false, 1, null, 30, 'honganh1');

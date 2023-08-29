INSERT INTO shop (shop_name, shop_address, shop_detail_address, shop_tier, shop_tier_code)
VALUES ('Shop A', '서울시 송파구 동남로 191', '101동 1101호', 'Bronze', 'B'),
       ('Shop B', '서울시 송파구 동남로 192', '202동 2202호', 'Silver', 'S'),
       ('Shop C', '서울시 송파구 동남로 193', '303동 3303호', 'Gold', 'G');

INSERT INTO review (review_contents, shop_id)
VALUES ('좋아요', 1),
       ('싫어요', 1),
       ('나가주세요', 1);

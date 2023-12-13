BEGIN TRANSACTION;
 

DROP TABLE IF EXISTS ingredient, ingredient_type, nutrition, 
					recipe, recipe_tag, recipe_type, 
					measurement, recipe_ing, 
					meal, meal_type, recipe_meal, 
					mealplan, mealplan_type, meal_mealplan, 
					schedule, mealplan_schedule;


CREATE TABLE ingredient_type(
	ing_type_id serial NOT NULL, 
	ing_type varchar (20),
	CONSTRAINT PK_ingredient_type PRIMARY KEY (ing_type_id)
);

CREATE TABLE nutrition(
	nutrition_id serial NOT NULL,
	calories numeric,
	protein numeric,
	carb numeric,
	fat numeric,
	CONSTRAINT PK_nutrition PRIMARY KEY (nutrition_id)
);

CREATE TABLE ingredient(
	ing_id serial NOT NULL,
	ing_name varchar (50) NOT NULL,
	ing_type_id int,
	nutrition_id int,
	CONSTRAINT PK_ingredient PRIMARY KEY (ing_id),
	CONSTRAINT FK_ingredient_ingredient_type FOREIGN KEY (ing_type_id) REFERENCES ingredient_type (ing_type_id),
	CONSTRAINT FK_ingredient_nutrition FOREIGN KEY (nutrition_id) REFERENCES nutrition (nutrition_id)
);

CREATE TABLE recipe_tag(
	recipe_tag_id serial NOT NULL, 
	recipe_tag_desc varchar (50),
	CONSTRAINT PK_recipe_tag PRIMARY KEY (recipe_tag_id)
);

CREATE TABLE recipe_type(
	recipe_type_id serial NOT NULL, 
	recipe_type_desc varchar (50),
	CONSTRAINT PK_recipe_type PRIMARY KEY (recipe_type_id)
);

CREATE TABLE measurement(
	msm_id serial NOT NULL, 
	msm_unit varchar (50),
	CONSTRAINT PK_measurement PRIMARY KEY (msm_id)
);

CREATE TABLE recipe(
	recipe_id serial NOT NULL,
	recipe_type_id int NOT NULL,
	recipe_tag_id int NOT NULL,
	recipe_name varchar (100) NOT NULL,
	picture_path varchar (100), 
	prep_time int,
	instruction text,
	favorited boolean,
	CONSTRAINT PK_recipe PRIMARY KEY (recipe_id),
	CONSTRAINT FK_recipe_recipe_type FOREIGN KEY (recipe_type_id) REFERENCES recipe_type (recipe_type_id),
	CONSTRAINT FK_recipe_recipe_tag FOREIGN KEY (recipe_tag_id) REFERENCES recipe_tag (recipe_tag_id)
);

CREATE TABLE recipe_ing(
	recipe_id int NOT NULL,
	ing_id int NOT NULL,
	msm_id int NOT NULL,
	quantity numeric NOT NULL,
	CONSTRAINT PK_recipe_ing PRIMARY KEY (recipe_id, ing_id, msm_id, quantity),
	CONSTRAINT FK_recipe_ing_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
	CONSTRAINT FK_recipe_ing_ingredient FOREIGN KEY (ing_id) REFERENCES ingredient (ing_id),
	CONSTRAINT FK_recipe_ing_measurement FOREIGN KEY (msm_id) REFERENCES measurement (msm_id)
);

CREATE TABLE meal_type(
	meal_type_id serial NOT NULL, 
	meal_type_name varchar (100),
	CONSTRAINT PK_meal_type PRIMARY KEY (meal_type_id)
);

CREATE TABLE meal(
	meal_id serial NOT NULL, 
	meal_name varchar (100),
	meal_type_id int NOT NULL,
	CONSTRAINT PK_meal PRIMARY KEY (meal_id),
	CONSTRAINT FK_meal_meal_type FOREIGN KEY (meal_type_id) REFERENCES meal_type(meal_type_id)
);

CREATE TABLE recipe_meal (
    recipe_id int NOT NULL,
    meal_id int NOT NULL,
	CONSTRAINT PK_recipe_meal PRIMARY KEY (recipe_id, meal_id),
    CONSTRAINT FK_recipe_meal_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_recipe_meal_meal FOREIGN KEY (meal_id) REFERENCES meal(meal_id)
);

CREATE TABLE mealplan_type(
	mealplan_type_id serial NOT NULL, 
	mealplan_type_name varchar (100),
	CONSTRAINT PK_mealplan_type PRIMARY KEY (mealplan_type_id)
);

CREATE TABLE mealplan(
	mealplan_id serial NOT NULL, 
	mealplan_name varchar (100),
	mealplan_type_id int NOT NULL,
	CONSTRAINT PK_mealplan PRIMARY KEY (mealplan_id),
	CONSTRAINT FK_mealplan_mealplan_type FOREIGN KEY (mealplan_type_id) REFERENCES mealplan_type(mealplan_type_id)
);

CREATE TABLE meal_mealplan (
    meal_id int NOT NULL,
    mealplan_id int NOT NULL,
	CONSTRAINT PK_meal_mealplan PRIMARY KEY (meal_id, mealplan_id),
    CONSTRAINT FK_meal_mealplan_meal FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
    CONSTRAINT FK_meal_mealplan_mealplan FOREIGN KEY (mealplan_id) REFERENCES mealplan(mealplan_id)
);

CREATE TABLE schedule(
	sched_id serial NOT NULL, 
	user_id int NOT NULL,
	mealplan_date date,
	CONSTRAINT PK_schedule PRIMARY KEY (sched_id),
	CONSTRAINT FK_schedule_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE mealplan_schedule(
    mealplan_id int NOT NULL,
    sched_id int NOT NULL,
	CONSTRAINT PK_mealplan_schedule PRIMARY KEY(mealplan_id, sched_id),
    CONSTRAINT FK_mealplan_schedule_mealplan FOREIGN KEY (mealplan_id) REFERENCES mealplan(mealplan_id),
    CONSTRAINT FK_mealplan_schedule_schedule FOREIGN KEY (sched_id) REFERENCES schedule(sched_id)
);

-- populate the ingredient_type table (source: http://teamnutrition.usda.gov )
INSERT INTO ingredient_type (ing_type) VALUES ('fruits');
INSERT INTO ingredient_type (ing_type) VALUES ('vegetables');
INSERT INTO ingredient_type (ing_type) VALUES ('grains');
INSERT INTO ingredient_type (ing_type) VALUES ('dairy');
INSERT INTO ingredient_type (ing_type) VALUES ('protein foods');
INSERT INTO ingredient_type (ing_type) VALUES ('seasoning');
INSERT INTO ingredient_type (ing_type) VALUES ('condiment');
INSERT INTO ingredient_type (ing_type) VALUES ('oil and fat');

-- populate the recipe_type table
INSERT INTO recipe_type (recipe_type_desc) VALUES ('appetizer');
INSERT INTO recipe_type (recipe_type_desc) VALUES ('entree');
INSERT INTO recipe_type (recipe_type_desc) VALUES ('dessert');
INSERT INTO recipe_type (recipe_type_desc) VALUES ('salad');
INSERT INTO recipe_type (recipe_type_desc) VALUES ('soup');
INSERT INTO recipe_type (recipe_type_desc) VALUES ('finger food');

-- populate the recipe_tag table 
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('generic');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('vegan');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('vegetarian');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('keto');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('paleo');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('gluten-free');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('diabetic friendly');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('nut-free');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('soy-free');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('allergy warning');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('cholesterol control');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('weight loss');
INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('guilty pleasure');

-- populate the measurement table
INSERT INTO measurement (msm_unit) VALUES ('oz');
INSERT INTO measurement (msm_unit) VALUES ('lbs');
INSERT INTO measurement (msm_unit) VALUES ('g');
INSERT INTO measurement (msm_unit) VALUES ('fluid oz');
INSERT INTO measurement (msm_unit) VALUES ('tsp');
INSERT INTO measurement (msm_unit) VALUES ('Tbsp');
INSERT INTO measurement (msm_unit) VALUES ('cup');
INSERT INTO measurement (msm_unit) VALUES ('piece(s)');
INSERT INTO measurement (msm_unit) VALUES ('slice(s)');
INSERT INTO measurement (msm_unit) VALUES ('fruit');
INSERT INTO measurement (msm_unit) VALUES ('serving');

-- populate the meal_type table 
INSERT INTO meal_type (meal_type_name) VALUES ('breakfast');
INSERT INTO meal_type (meal_type_name) VALUES ('lunch');
INSERT INTO meal_type (meal_type_name) VALUES ('dinner');
INSERT INTO meal_type (meal_type_name) VALUES ('snack');

-- populate the mealplan_type table 
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('vacation mealplan');
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('sickday mealplan');
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('busyweek mealplan');
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('special_occasion mealplan');
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('holiday mealplan');
INSERT INTO mealplan_type (mealplan_type_name) VALUES ('generic mealplan');

-- grant access as per instructions
GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;


COMMIT;

select * from users; 
select * from recipe_type; 
select * from ingredient_type;
select * from nutrition; 

INSERT INTO nutrition (nutrition_id, calories, protein, carb, fat)
VALUES (1, 5, 5, 5, 5), (2, 5, 5, 5, 5), (3, 5, 5, 5, 5);
INSERT INTO ingredient (ing_id, ing_name, ing_type_id, nutrition_id)
VALUES (1, 'grape', 1, 1), (2, 'banana', 2, 2), (3, 'celery', 3, 3);
select * from nutrition; 
select * from ingredient; 

INSERT INTO recipe_type (recipe_type_id, recipe_type_desc)
VALUES (0, '');

INSERT INTO recipe_tag (recipe_tag_id, recipe_tag_desc)
VALUES (0, '');

INSERT INTO meal_type (meal_type_id, meal_type_name)
VALUES (0, '');

INSERT INTO ingredient_type (ing_type_id, ing_type)
VALUES (0, '');

INSERT INTO mealplan_type(mealplan_type_id, mealplan_type_name)
VALUES (0, '');

INSERT INTO measurement(msm_id, msm_unit)
VALUES (0, '');




INSERT INTO recipe (recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, prep_time, instruction, favorited)
VALUES (0, 0, 0, 'Spaghetti and Meatballs', 'pasta.jpg', 20, 'Cook it!', false),
(1, 0, 0, 'Scrambled Eggs', 'eggs.jpg', 15, 'Scramble!', false),
(2, 0, 0, 'Roasted Vegetables', 'veggies.jpg', 30, 'Roast!', false),
(3, 0, 0, 'Ramen Noodles', 'ramen.jpg', 5, 'Boil!', false),
(4, 0, 0, 'Stovetop Bacon', 'bacon.jpg', 15, 'Cook the bacon!', false);

INSERT INTO meal(meal_id, meal_name, meal_type_id)
VALUES (0, '', 0), (1, 'Pasta Dinner', 0), (2, 'Ramen Lunch', 0), (3, 'Bacon and Eggs', 0);



INSERT INTO mealplan (mealplan_id, mealplan_name, mealplan_type_id)
VALUES (0, '', 0), (1, 'Pasta and Eggs', 0);

select * from ingredient order by ing_id; 
select * from nutrition; 
select * from recipe; 
select * from meal; 
select * from mealplan;
select * from users; 
select * from recipe_ing;
select * from measurement; 
select * from ingredient_type; 
select * from recipe_type;
select * from recipe_tag;

INSERT INTO nutrition (nutrition_id, calories, protein, carb, fat)
VALUES (4, 12, 5, 2, 1), (5, 39, 14, 1, 6), (6, 10, 8, 3, 1), (7, 20, 15, 3, 2), (8, 9, 1, 6, 0), (9, 11, 3, 7, 2) ;

INSERT INTO ingredient (ing_id, ing_name, ing_type_id, nutrition_id)
VALUES (9, 'beef', 5, 5), (10, 'pork', 5, 7), (11, 'chicken', 5, 6), (7, 'tofu', 5, 4), (12, 'shrimp', 5, 8);

INSERT INTO recipe_tag (recipe_tag_desc) VALUES ('comfort food');
select * from recipe_ing;




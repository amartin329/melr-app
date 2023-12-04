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
	description varchar (50) NOT NULL,
	calories int,
	protein int,
	carb int,
	fat int,
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
	recipe_tag varchar (50),
	CONSTRAINT PK_recipe_tag PRIMARY KEY (recipe_tag_id)
);

CREATE TABLE recipe_type(
	recipe_type_id serial NOT NULL, 
	recipe_type varchar (50),
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
	CONSTRAINT PK_recipe PRIMARY KEY (recipe_id),
	CONSTRAINT FK_recipe_recipe_type FOREIGN KEY (recipe_type_id) REFERENCES recipe_type (recipe_type_id),
	CONSTRAINT FK_recipe_recipe_tag FOREIGN KEY (recipe_tag_id) REFERENCES recipe_tag (recipe_tag_id)
);

CREATE TABLE recipe_ing(
	recipe_id int NOT NULL,
	ing_id int NOT NULL,
	msm_id int NOT NULL,
	quantity int NOT NULL,
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

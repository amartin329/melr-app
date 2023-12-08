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

INSERT INTO nutrition(nutrition_id, calories, protein, carb, fat)
VALUES (0, 0, 0, 0, 0);


INSERT INTO recipe (recipe_id, recipe_type_id, recipe_tag_id, recipe_name, picture_path, prep_time, instruction, favorited)
VALUES (0, 0, 0, 'Spaghetti and Meatballs', 'pasta.jpg', 20, 'Cook it!', false),
(1, 0, 0, 'Scrambled Eggs', 'eggs.jpg', 15, 'Scramble!', false),
(2, 0, 0, 'Roasted Vegetables', 'veggies.jpg', 30, 'Roast!', false),
(3, 0, 0, 'Ramen Noodles', 'ramen.jpg', 5, 'Boil!', false),
(4, 0, 0, 'Stovetop Bacon', 'bacon.jpg', 15, 'Cook the bacon!', false);

INSERT INTO meal(meal_id, meal_name, meal_type_id)
VALUES (0, '', 0), (1, 'Pasta Dinner', 0), (2, 'Ramen Lunch', 0), (3, 'Bacon and Eggs', 0);

INSERT INTO ingredient (ing_id, ing_name, ing_type_id, nutrition_id)
VALUES (1, 'grape', 1, 1), (2, 'banana', 2, 2), (3, 'celery', 3, 3);

INSERT INTO mealplan (mealplan_id, mealplan_name, mealplan_type_id)
VALUES (0, '', 0), (1, 'Pasta and Eggs', 0);
-- Data base for final project "Nutritional supplements"
-- Creating database and tables

CREATE DATABASE nutritional_supplements;
USE nutritional_supplements;


-- table supplement
CREATE TABLE supplement	(id INT(10) AUTO_INCREMENT,
						e_cod VARCHAR(10),
						name VARCHAR(50),
						other_names VARCHAR(2000),
						category ENUM('antioxidant', 'flavor', 'anti_caking_agent', 'moisture_retaining_agent', 'wax',
							'thickener', 'complexing_agent', 'preservative', 'colorant', 'frother', 'sweetener', 'baking_powder',
                            'acidity_regulator', 'stabilizer', 'texturator', 'amplifiers', 'color_retainer', 'emulsifier'),
						danger ENUM('very_low','low','middle','high'),
						origin ENUM('animal','vegetable','artificial','synthetic'),
						using_info BLOB(5000),
						harm VARCHAR(3000),
						benefit VARCHAR(3000),
						general_info VARCHAR(5000),
						legislation VARCHAR(3000),
						PRIMARY KEY (id));


-- table user
CREATE TABLE user 	(id INT(10) AUTO_INCREMENT,
					email VARCHAR(50) NOT NULL,
					password VARCHAR(50) NOT NULL,
					name VARCHAR(50) NOT NULL,
					active INT(10) NOT NULL,
					PRIMARY KEY (id));


-- table role
CREATE TABLE role	(id INT(10) AUTO_INCREMENT,
					role VARCHAR(50) NOT NULL,
					description VARCHAR(50) NOT NULL,
                    PRIMARY KEY (id));


-- table user and role
CREATE TABLE user_role	(id INT(10) AUTO_INCREMENT,
                        user_id INT(10) NOT NULL,
                        role_id INT(10) NOT NULL,
						PRIMARY KEY (id),
                        FOREIGN KEY (user_id) REFERENCES user (id),
                        FOREIGN KEY (role_id) REFERENCES role (id));


-- table product
CREATE TABLE product 	(id INT(10) AUTO_INCREMENT,
						name VARCHAR(50) NOT NULL,
						long_name VARCHAR(200),
						trade_mark VARCHAR(50),
						producing_factory VARCHAR(200),
						producing_country VARCHAR(50),
						composition VARCHAR(3000),
						e_supplements VARCHAR(200),
						other_supplements VARCHAR(500),
						term_and_conditions_storage VARCHAR(3000),
                        product_weight FLOAT(10),
						protein FLOAT(10),
						fat FLOAT(10),
						carbohydrate FLOAT(10),
						calorie_content FLOAT(10),
						standards VARCHAR(3000),
						barcode VARCHAR(50),
						PRIMARY KEY (id));


-- table supplement and product
CREATE TABLE supplement_product	(id INT(10) AUTO_INCREMENT,
								supplement_id INT(10) NOT NULL,
								product_id INT(10) NOT NULL,
								PRIMARY KEY (id),
								FOREIGN KEY (supplement_id) REFERENCES supplement (id),
								FOREIGN KEY (product_id) REFERENCES product (id));
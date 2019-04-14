-- Data base for final project "Nutritional additives"
-- Creating database and tables

CREATE DATABASE additives;
USE additives;

-- table additive
CREATE TABLE additive	(id INT(10) AUTO_INCREMENT,
						e_cod VARCHAR(10),
						name VARCHAR(200),
						other_names VARCHAR(2000),
						category ENUM('not_assigned','antioxidant', 'flavor', 'anti_caking_agent', 'moisture_retaining_agent', 'wax',
							'thickener', 'complexing_agent', 'preservative', 'colorant', 'frother', 'sweetener', 'baking_powder',
                            'acidity_regulator', 'stabilizer', 'texturator', 'amplifiers', 'color_retainer', 'emulsifier'),
						danger ENUM('not_assigned','zero','very_low','low','middle','high','very_high'),
						origin ENUM('not_assigned','animal','vegetable','natural','artificial','synthetic','microbiological'),
						using_info BLOB(5000),
						harm BLOB(5000),
						benefit BLOB(5000),
						general_info BLOB(5000),
						legislation BLOB(5000),
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
						e_additives VARCHAR(200),
						other_additives VARCHAR(500),
						term_and_conditions_storage VARCHAR(3000),
                        product_weight FLOAT(10),
						protein FLOAT(10),
						fat FLOAT(10),
						carbohydrate FLOAT(10),
						calorie_content FLOAT(10),
						standards VARCHAR(3000),
						barcode VARCHAR(50),
						PRIMARY KEY (id));


-- table additive and product
CREATE TABLE additive_product	(id INT(10) AUTO_INCREMENT,
								additive_id INT(10) NOT NULL,
								product_id INT(10) NOT NULL,
								PRIMARY KEY (id),
								FOREIGN KEY (additive_id) REFERENCES additive (id),
								FOREIGN KEY (product_id) REFERENCES product (id));
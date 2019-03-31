-- Data base for final project "Nutritional supplements"
-- Creating database and tables

CREATE DATABASE nutritional_supplements;
USE nutritional_supplements;


-- table supplement
CREATE TABLE supplement	(id INT(10) AUTO_INCREMENT,
                        e_cod VARCHAR(10) NOT NULL,
						name VARCHAR(50) NOT NULL,
						other_names VARCHAR(500) NOT NULL,
						category VARCHAR(50) NOT NULL,
						danger VARCHAR(50) NOT NULL,
						origin VARCHAR(50) NOT NULL,
						using_info VARCHAR(3000) NOT NULL,
						harm VARCHAR(3000) NOT NULL,
						benefit VARCHAR(3000) NOT NULL,
						general_info VARCHAR(3000) NOT NULL,
						legislation VARCHAR(3000) NOT NULL,
						PRIMARY KEY (id));


-- table user
CREATE TABLE user 	(id INT(10) AUTO_INCREMENT,
					email VARCHAR(50) NOT NULL,
					password VARCHAR(50) NOT NULL,
					name VARCHAR(50) NOT NULL,
					last_name VARCHAR(50) NOT NULL,
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
						long_name VARCHAR(200) NOT NULL,
						trade_mark VARCHAR(50) NOT NULL,
						producing_factory VARCHAR(200) NOT NULL,
						producing_country VARCHAR(50) NOT NULL,
						composition VARCHAR(3000) NOT NULL,
						e_supplements VARCHAR(200),
						other_supplements VARCHAR(500),
						term_and_conditions_storage VARCHAR(3000) NOT NULL,
                        product_weight FLOAT(10) NOT NULL,
						protein FLOAT(10) NOT NULL,
						fat FLOAT(10) NOT NULL,
						carbohydrate FLOAT(10) NOT NULL,
						calorie_content FLOAT(10) NOT NULL,
						standards VARCHAR(3000) NOT NULL,
						barcode VARCHAR(50) NOT NULL,
						PRIMARY KEY (id));


-- table supplement and product
CREATE TABLE supplement_product	(id INT(10) AUTO_INCREMENT,
								supplement_id INT(10) NOT NULL,
								product_id INT(10) NOT NULL,
								PRIMARY KEY (id),
								FOREIGN KEY (supplement_id) REFERENCES supplement (id),
								FOREIGN KEY (product_id) REFERENCES product (id));
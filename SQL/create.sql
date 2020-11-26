-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-11-11 23:32:33.123

-- tables
-- Table: allergy
CREATE TABLE allergy (
                         allergies_id int NOT NULL AUTO_INCREMENT,
                         patient_id int NOT NULL,
                         description varchar(300) NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT allergy_pk PRIMARY KEY (allergies_id)
);

-- Table: background
CREATE TABLE background (
                            background_id int NOT NULL AUTO_INCREMENT,
                            patient_id int NOT NULL,
                            description varchar(300) NOT NULL,
                            status int NOT NULL,
                            tx_id int NOT NULL,
                            tx_username varchar(100) NOT NULL,
                            tx_host varchar(100) NOT NULL,
                            tx_date timestamp NOT NULL,
                            CONSTRAINT background_pk PRIMARY KEY (background_id)
);

-- Table: consult
CREATE TABLE consult (
                         consult_id int NOT NULL AUTO_INCREMENT,
                         medical_history_id int NOT NULL,
                         diagnosis varchar(200) NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT consult_pk PRIMARY KEY (consult_id)
);

-- Table: conversation
CREATE TABLE conversation (
                              conversation_id int NOT NULL AUTO_INCREMENT,
                              consult_id int NOT NULL,
                              message varchar(200) NOT NULL,
                              status int NOT NULL,
                              tx_id int NOT NULL,
                              tx_username varchar(100) NOT NULL,
                              tx_host varchar(100) NOT NULL,
                              tx_date timestamp NOT NULL,
                              CONSTRAINT conversation_pk PRIMARY KEY (conversation_id)
);

-- Table: doctor
CREATE TABLE doctor (
                        doctor_id int NOT NULL AUTO_INCREMENT,
                        user_id int NOT NULL,
                        person_id int NOT NULL,
                        status int NOT NULL,
                        tx_id int NOT NULL,
                        tx_username varchar(100) NOT NULL,
                        tx_host varchar(100) NOT NULL,
                        tx_date timestamp NOT NULL,
                        CONSTRAINT doctor_pk PRIMARY KEY (doctor_id)
);

-- Table: doctor_specialty
CREATE TABLE doctor_specialty (
                                  doctor_specialty_id int NOT NULL AUTO_INCREMENT,
                                  specialty_id int NOT NULL,
                                  doctor_id int NOT NULL,
                                  price int NOT NULL,
                                  from_time time NOT NULL,
                                  to_time time NOT NULL,
                                  status int NOT NULL,
                                  tx_id int NOT NULL,
                                  tx_username varchar(100) NOT NULL,
                                  tx_host varchar(100) NOT NULL,
                                  tx_date timestamp NOT NULL,
                                  CONSTRAINT doctor_specialty_pk PRIMARY KEY (doctor_specialty_id)
);

-- Table: laboratory
CREATE TABLE laboratory (
                            laboratory_id int NOT NULL AUTO_INCREMENT,
                            consult_id int NOT NULL,
                            name varchar (50) NOT NULL,
                            `order` varchar(200) NOT NULL,
                            status int NOT NULL,
                            tx_id int NOT NULL,
                            tx_username varchar(100) NOT NULL,
                            tx_host varchar(100) NOT NULL,
                            tx_date timestamp NOT NULL,
                            CONSTRAINT laboratory_pk PRIMARY KEY (laboratory_id)
) COMMENT 'La his';

-- Table: medical_history
CREATE TABLE medical_history (
                                 medical_history_id int NOT NULL AUTO_INCREMENT,
                                 patient_id int NOT NULL,
                                 doctor_specialty_id int NOT NULL,
                                 status int NOT NULL,
                                 tx_id int NOT NULL,
                                 tx_username varchar(100) NOT NULL,
                                 tx_host varchar(100) NOT NULL,
                                 tx_date timestamp NOT NULL,
                                 CONSTRAINT medical_history_pk PRIMARY KEY (medical_history_id)
);

-- Table: patient
CREATE TABLE patient (
                         patient_id int NOT NULL AUTO_INCREMENT,
                         person_id int NOT NULL,
                         user_id int NOT NULL,
                         gender varchar(10) NOT NULL,
                         height double(4,2) NOT NULL,
                         weight double(4,2) NOT NULL,
                         blood_group varchar(10) NOT NULL,
                         temperature double(4,2) NOT NULL,
                         pressure varchar(10) NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT patient_pk PRIMARY KEY (patient_id)
);

-- Table: person
CREATE TABLE person (
                        person_id int NOT NULL AUTO_INCREMENT,
                        first_name varchar(60) NOT NULL,
                        first_surname varchar(60) NOT NULL,
                        second_surname varchar(60) NOT NULL,
                        birthdate date NOT NULL,
                        status int NOT NULL,
                        tx_id int NOT NULL,
                        tx_username varchar(100) NOT NULL,
                        tx_host varchar(100) NOT NULL,
                        tx_date timestamp NOT NULL,
                        CONSTRAINT person_pk PRIMARY KEY (person_id)
);

-- Table: prescription
CREATE TABLE prescription (
                              prescription_id int NOT NULL AUTO_INCREMENT,
                              consult_id int NOT NULL,
                              description varchar(100) NOT NULL,
                              status int NOT NULL,
                              tx_id int NOT NULL,
                              tx_username varchar(100) NOT NULL,
                              tx_host varchar(100) NOT NULL,
                              tx_date timestamp NOT NULL,
                              CONSTRAINT prescription_pk PRIMARY KEY (prescription_id)
);

-- Table: product
CREATE TABLE product (
                         product_id int NOT NULL AUTO_INCREMENT,
                         prescription_id int NOT NULL,
                         product_name varchar(100) NOT NULL,
                         product_detail varchar(100) NOT NULL,
                         product_quantity varchar(100) NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (product_id)
);

-- Table: qualification
CREATE TABLE qualification (
                               qualification_id int NOT NULL AUTO_INCREMENT,
                               doctor_specialty_id int NOT NULL,
                               patient_id int NOT NULL,
                               qualification int NOT NULL,
                               status int NOT NULL,
                               tx_id int NOT NULL,
                               tx_username varchar(100) NOT NULL,
                               tx_host varchar(100) NOT NULL,
                               tx_date timestamp NOT NULL,
                               CONSTRAINT qualification_pk PRIMARY KEY (qualification_id)
);

-- Table: resource
CREATE TABLE resource (
                          resource_id int NOT NULL AUTO_INCREMENT,
                          consult_id int NOT NULL,
                          image varchar(200) NOT NULL,
                          status int NOT NULL,
                          tx_id int NOT NULL,
                          tx_username varchar(100) NOT NULL,
                          tx_host varchar(100) NOT NULL,
                          tx_date timestamp NOT NULL,
                          CONSTRAINT resource_pk PRIMARY KEY (resource_id)
);

-- Table: role
CREATE TABLE role (
                      role_id int NOT NULL AUTO_INCREMENT,
                      role_name varchar(100) NOT NULL,
                      status int NOT NULL,
                      tx_id int NOT NULL,
                      tx_username varchar(100) NOT NULL,
                      tx_host varchar(100) NOT NULL,
                      tx_date timestamp NOT NULL,
                      CONSTRAINT role_pk PRIMARY KEY (role_id)
);

-- Table: specialty
CREATE TABLE specialty (
                           specialty_id int NOT NULL AUTO_INCREMENT,
                           name varchar(60) NOT NULL,
                           image varchar(200) NOT NULL,
                           status int NOT NULL,
                           tx_id int NOT NULL,
                           tx_username varchar(100) NOT NULL,
                           tx_host varchar(100) NOT NULL,
                           tx_date timestamp NOT NULL,
                           CONSTRAINT specialty_pk PRIMARY KEY (specialty_id)
);

-- Table: user
CREATE TABLE user (
                      user_id int NOT NULL AUTO_INCREMENT,
                      person_id int NOT NULL,
                      email varchar(100) NOT NULL,
                      password varchar(100) NOT NULL,
                      phone_number varchar(50) NOT NULL,
                      profile_picture varchar(200) NOT NULL,
                      status int NOT NULL,
                      tx_id int NOT NULL,
                      tx_username varchar(100) NOT NULL,
                      tx_host varchar(100) NOT NULL,
                      tx_date timestamp NOT NULL,
                      CONSTRAINT user_pk PRIMARY KEY (user_id)
);

-- Table: user_role
CREATE TABLE user_role (
                           user_role_id int NOT NULL AUTO_INCREMENT,
                           user_id int NOT NULL,
                           role_id int NOT NULL,
                           status int NOT NULL,
                           tx_id int NOT NULL,
                           tx_username varchar(100) NOT NULL,
                           tx_host varchar(100) NOT NULL,
                           tx_date timestamp NOT NULL,
                           CONSTRAINT user_role_pk PRIMARY KEY (user_role_id)
);

-- foreign keys
-- Reference: Product_prescription (table: product)
ALTER TABLE product ADD CONSTRAINT Product_prescription FOREIGN KEY Product_prescription (prescription_id)
    REFERENCES prescription (prescription_id);

-- Reference: allergy_patient (table: allergy)
ALTER TABLE allergy ADD CONSTRAINT allergy_patient FOREIGN KEY allergy_patient (patient_id)
    REFERENCES patient (patient_id);

-- Reference: background_patient (table: background)
ALTER TABLE background ADD CONSTRAINT background_patient FOREIGN KEY background_patient (patient_id)
    REFERENCES patient (patient_id);

-- Reference: consult_medical_history (table: consult)
ALTER TABLE consult ADD CONSTRAINT consult_medical_history FOREIGN KEY consult_medical_history (medical_history_id)
    REFERENCES medical_history (medical_history_id);

-- Reference: conversation_consult (table: conversation)
ALTER TABLE conversation ADD CONSTRAINT conversation_consult FOREIGN KEY conversation_consult (consult_id)
    REFERENCES consult (consult_id);

-- Reference: doctor_person (table: doctor)
ALTER TABLE doctor ADD CONSTRAINT doctor_person FOREIGN KEY doctor_person (person_id)
    REFERENCES person (person_id);

-- Reference: doctor_specialty_doctor (table: doctor_specialty)
ALTER TABLE doctor_specialty ADD CONSTRAINT doctor_specialty_doctor FOREIGN KEY doctor_specialty_doctor (doctor_id)
    REFERENCES doctor (doctor_id);

-- Reference: doctor_specialty_specialty (table: doctor_specialty)
ALTER TABLE doctor_specialty ADD CONSTRAINT doctor_specialty_specialty FOREIGN KEY doctor_specialty_specialty (specialty_id)
    REFERENCES specialty (specialty_id);

-- Reference: doctor_user (table: doctor)
ALTER TABLE doctor ADD CONSTRAINT doctor_user FOREIGN KEY doctor_user (user_id)
    REFERENCES user (user_id);

-- Reference: laboratory_consult (table: laboratory)
ALTER TABLE laboratory ADD CONSTRAINT laboratory_consult FOREIGN KEY laboratory_consult (consult_id)
    REFERENCES consult (consult_id);

-- Reference: medical_history_doctor_specialty (table: medical_history)
ALTER TABLE medical_history ADD CONSTRAINT medical_history_doctor_specialty FOREIGN KEY medical_history_doctor_specialty (doctor_specialty_id)
    REFERENCES doctor_specialty (doctor_specialty_id);

-- Reference: medical_history_patient (table: medical_history)
ALTER TABLE medical_history ADD CONSTRAINT medical_history_patient FOREIGN KEY medical_history_patient (patient_id)
    REFERENCES patient (patient_id);

-- Reference: patient_person (table: patient)
ALTER TABLE patient ADD CONSTRAINT patient_person FOREIGN KEY patient_person (person_id)
    REFERENCES person (person_id);

-- Reference: patient_user (table: patient)
ALTER TABLE patient ADD CONSTRAINT patient_user FOREIGN KEY patient_user (user_id)
    REFERENCES user (user_id);

-- Reference: prescription_consult (table: prescription)
ALTER TABLE prescription ADD CONSTRAINT prescription_consult FOREIGN KEY prescription_consult (consult_id)
    REFERENCES consult (consult_id);

-- Reference: qualification_doctor_specialty (table: qualification)
ALTER TABLE qualification ADD CONSTRAINT qualification_doctor_specialty FOREIGN KEY qualification_doctor_specialty (doctor_specialty_id)
    REFERENCES doctor_specialty (doctor_specialty_id);

-- Reference: qualification_patient (table: qualification)
ALTER TABLE qualification ADD CONSTRAINT qualification_patient FOREIGN KEY qualification_patient (patient_id)
    REFERENCES patient (patient_id);

-- Reference: resource_consult (table: resource)
ALTER TABLE resource ADD CONSTRAINT resource_consult FOREIGN KEY resource_consult (consult_id)
    REFERENCES consult (consult_id);

-- Reference: user_person (table: user)
ALTER TABLE user ADD CONSTRAINT user_person FOREIGN KEY user_person (person_id)
    REFERENCES person (person_id);

-- Reference: user_role_role (table: user_role)
ALTER TABLE user_role ADD CONSTRAINT user_role_role FOREIGN KEY user_role_role (role_id)
    REFERENCES role (role_id);

-- Reference: user_role_user (table: user_role)
ALTER TABLE user_role ADD CONSTRAINT user_role_user FOREIGN KEY user_role_user (user_id)
    REFERENCES user (user_id);

-- End of file.

-- ALTER TABLE laboratory ADD COLUMN name varchar(50);

-- DROP TABLE image_table;

ALTER TABLE resource CHANGE `image` `name` varchar(100);
ALTER TABLE resource ADD COLUMN type varchar(100);
ALTER TABLE resource ADD COLUMN pic_byte longblob;

create table image_table (
                             id int AUTO_INCREMENT PRIMARY KEY,
                             name varchar(100),
                             type varchar(100),
                             pic_byte longblob
);
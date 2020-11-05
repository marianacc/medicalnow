-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-10-28 16:06:47.875

-- tables
-- Table: consult
CREATE TABLE consult (
                         consult_id int NOT NULL AUTO_INCREMENT,
                         medical_history_id int NOT NULL,
                         patient_id int NOT NULL,
                         doctor_specialty_id int NOT NULL,
                         message varchar(200) NOT NULL,
                         consult_date date NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT consult_pk PRIMARY KEY (consult_id)
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
                                  status int NOT NULL,
                                  tx_id int NOT NULL,
                                  tx_username varchar(100) NOT NULL,
                                  tx_host varchar(100) NOT NULL,
                                  tx_date timestamp NOT NULL,
                                  CONSTRAINT doctor_specialty_pk PRIMARY KEY (doctor_specialty_id)
);

-- Table: feature
CREATE TABLE feature (
                         feature_id int NOT NULL AUTO_INCREMENT,
                         feature_name varchar(100) NOT NULL,
                         feature_code varchar(50) NOT NULL,
                         status int NOT NULL,
                         tx_id int NOT NULL,
                         tx_username varchar(100) NOT NULL,
                         tx_host varchar(100) NOT NULL,
                         tx_date timestamp NOT NULL,
                         CONSTRAINT feature_pk PRIMARY KEY (feature_id)
);

-- Table: laboratory
CREATE TABLE laboratory (
                            laboratory_exams_id int NOT NULL AUTO_INCREMENT,
                            medical_history_id int NOT NULL,
                            lab_exam_order varchar(200) NOT NULL,
                            status int NOT NULL,
                            tx_id int NOT NULL,
                            tx_username varchar(100) NOT NULL,
                            tx_host varchar(100) NOT NULL,
                            tx_date timestamp NOT NULL,
                            CONSTRAINT laboratory_pk PRIMARY KEY (laboratory_exams_id)
) COMMENT 'La his';

-- Table: medical_history
CREATE TABLE medical_history (
                                 medical_history_id int NOT NULL AUTO_INCREMENT,
                                 diagnosis varchar(200) NOT NULL,
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
                         height double(2,2) NOT NULL,
    weight double(2,2) NOT NULL,
    blood_group varchar(10) NOT NULL,
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
                        id_number varchar(20) NOT NULL,
                        first_name varchar(60) NOT NULL,
                        first_surname varchar(60) NOT NULL,
                        second_surname varchar(60) NOT NULL,
                        birthdate date NOT NULL,
                        city varchar(50) NOT NULL,
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
                              medical_history_id int NOT NULL,
                              prescription_date date NOT NULL,
                              treatment_prescription varchar(100) NOT NULL,
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
                          patient_resource_id int NOT NULL AUTO_INCREMENT,
                          consult_id int NOT NULL,
                          image varchar(200) NOT NULL,
                          status int NOT NULL,
                          tx_id int NOT NULL,
                          tx_username varchar(100) NOT NULL,
                          tx_host varchar(100) NOT NULL,
                          tx_date timestamp NOT NULL,
                          CONSTRAINT resource_pk PRIMARY KEY (patient_resource_id)
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

-- Table: role_feature
CREATE TABLE role_feature (
                              role_feature_id int NOT NULL AUTO_INCREMENT,
                              role_id int NOT NULL,
                              feature_id int NOT NULL,
                              status int NOT NULL,
                              tx_id int NOT NULL,
                              tx_username varchar(100) NOT NULL,
                              tx_host varchar(100) NOT NULL,
                              tx_date timestamp NOT NULL,
                              CONSTRAINT role_feature_pk PRIMARY KEY (role_feature_id)
);

-- Table: specialty
CREATE TABLE specialty (
                           specialty_id int NOT NULL AUTO_INCREMENT,
                           specialty_name varchar(60) NOT NULL,
                           specialty_image varchar(200) NOT NULL,
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
                      user_image varchar(200) NOT NULL,
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

-- Reference: consult_doctor_specialty (table: consult)
ALTER TABLE consult ADD CONSTRAINT consult_doctor_specialty FOREIGN KEY consult_doctor_specialty (doctor_specialty_id)
    REFERENCES doctor_specialty (doctor_specialty_id);

-- Reference: consult_medical_history (table: consult)
ALTER TABLE consult ADD CONSTRAINT consult_medical_history FOREIGN KEY consult_medical_history (medical_history_id)
    REFERENCES medical_history (medical_history_id);

-- Reference: consult_patient (table: consult)
ALTER TABLE consult ADD CONSTRAINT consult_patient FOREIGN KEY consult_patient (patient_id)
    REFERENCES patient (patient_id);

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

-- Reference: laboratory_results_medical_history (table: laboratory)
ALTER TABLE laboratory ADD CONSTRAINT laboratory_results_medical_history FOREIGN KEY laboratory_results_medical_history (medical_history_id)
    REFERENCES medical_history (medical_history_id);

-- Reference: patient_person (table: patient)
ALTER TABLE patient ADD CONSTRAINT patient_person FOREIGN KEY patient_person (person_id)
    REFERENCES person (person_id);

-- Reference: patient_user (table: patient)
ALTER TABLE patient ADD CONSTRAINT patient_user FOREIGN KEY patient_user (user_id)
    REFERENCES user (user_id);

-- Reference: qualification_doctor_specialty (table: qualification)
ALTER TABLE qualification ADD CONSTRAINT qualification_doctor_specialty FOREIGN KEY qualification_doctor_specialty (doctor_specialty_id)
    REFERENCES doctor_specialty (doctor_specialty_id);

-- Reference: qualification_patient (table: qualification)
ALTER TABLE qualification ADD CONSTRAINT qualification_patient FOREIGN KEY qualification_patient (patient_id)
    REFERENCES patient (patient_id);

-- Reference: resource_consult (table: resource)
ALTER TABLE resource ADD CONSTRAINT resource_consult FOREIGN KEY resource_consult (consult_id)
    REFERENCES consult (consult_id);

-- Reference: role_feature_feature (table: role_feature)
ALTER TABLE role_feature ADD CONSTRAINT role_feature_feature FOREIGN KEY role_feature_feature (feature_id)
    REFERENCES feature (feature_id);

-- Reference: role_feature_role (table: role_feature)
ALTER TABLE role_feature ADD CONSTRAINT role_feature_role FOREIGN KEY role_feature_role (role_id)
    REFERENCES role (role_id);

-- Reference: treatment_medical_history (table: prescription)
ALTER TABLE prescription ADD CONSTRAINT treatment_medical_history FOREIGN KEY treatment_medical_history (medical_history_id)
    REFERENCES medical_history (medical_history_id);

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

-- ALTER TABLE MARIANA

ALTER TABLE laboratory ADD laboratory_name varchar (100);
ALTER TABLE laboratory ADD lab_order_date date;

ALTER TABLE resource ADD COLUMN resource_name varchar(100);
ALTER TABLE resource RENAME COLUMN patient_resource_id to resource_id;
ALTER TABLE resource RENAME COLUMN file to image;
ALTER TABLE resource MODIFY COLUMN file mediumblob;


ALTER TABLE medical_history ADD COLUMN patient_id int;
ALTER TABLE medical_history ADD CONSTRAINT patient FOREIGN KEY patient (patient_id)
    REFERENCES patient (patient_id);
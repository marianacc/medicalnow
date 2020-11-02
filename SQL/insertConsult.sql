-- Medicina General
INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (1, 16, 9, 'Buenos dias doctor, hoy he estado con temperatura y con un dolor de estomago muy fuerte.', '2020/10/09', 1, 0, 'root', '127.0.0.1', now());

INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (2, 1, 10, 'Hola doctor. Estoy con dolor de cabeza y tambien veo puntos negros. Que puede ser?', '2020/10/10', 1, 0, 'root', '127.0.0.1', now());

-- Dermatologia
INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (3, 2, 1, 'Buenas tardes doctor, ayer estuve con el gato de mi hermana y hoy mis brazos estan rojos y me pican.', '2020/10/12', 1, 0, 'root', '127.0.0.1', now());

INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (4, 3, 12, 'Hola doctor. He estado comiendo bastantes lacteos ultimamente, y me esta doliendo mucho el estomago.', '2020/10/12', 1, 0, 'root', '127.0.0.1', now());

-- Odontologia
INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (5, 4, 5, 'Doctor buenos dias. Me esta doliendo mi boca, puede ser posible que tengan que sacarme la muela del juicio?', '2020/10/14', 1, 0, 'root', '127.0.0.1', now());

INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)
VALUES (6, 5, 6, 'Hola doctor. Estoy con mucho dolor en la mandibula todas las mañanas.', '2020/10/15', 1, 0, 'root', '127.0.0.1', now());
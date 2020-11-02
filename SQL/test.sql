-- Seleccionar todas las especialidades

SELECT sp.specialty_id, sp.specialty_name, count(doc_sp.specialty_id), sp.specialty_image
FROM specialty sp
    JOIN doctor_specialty doc_sp on sp.specialty_id = doc_sp.specialty_id
WHERE sp.status = 1
and doc_sp.status = 1
GROUP BY sp.specialty_name, doc_sp.specialty_id, sp.specialty_image;

-- Seleccionar los doctores por especialidad

SELECT doc.doctor_id, per.first_name, per.first_surname, per.second_surname, avg(qua.qualification)
FROM person per
    JOIN doctor doc on per.person_id = doc.person_id
        JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id
            JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id
                JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id
WHERE doc.status = 1
AND per.status = 1
AND doc_spec.status = 1
AND qua.status = 1
AND spe.specialty_id = 5
GROUP BY doc.doctor_id, per.first_name, per.first_surname, per.second_surname;

-- Devolver el Id segun un email y contraseña
SELECT user_id FROM user WHERE email = 'ahentzer0@wisc.edu' and password = '12345';

-- Seleccionar el último paciente registrado
SELECT MAX(person_id) FROM patient WHERE status = 1;

-- Seleccionar los features de un usuario
SELECT DISTINCT fea.feature_code
FROM user usr
    JOIN user_role uro ON usr.user_id = uro.user_id
        JOIN role rle ON rle.role_id = uro.role_id
            JOIN role_feature rfe ON rfe.role_id = rle.role_id
                JOIN feature fea ON fea.feature_id = rfe.feature_id
WHERE usr.user_id = 1
AND usr.status = 1
AND uro.status = 1
AND rle.status = 1
AND rfe.status = 1
AND fea.status = 1;

-- Seleccionar todos los laboratorios de un paciente
SELECT lab.laboratory_exams_id, lab.laboratory_name, per.first_name, per.first_surname, spec.specialty_name, lab.lab_order_date
FROM laboratory lab
    JOIN medical_history med_his on lab.medical_history_id = med_his.medical_history_id
        JOIN consult con on med_his.medical_history_id = con.medical_history_id
            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id
                JOIN specialty spec on doc_spec.specialty_id = spec.specialty_id
                    JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id
                        JOIN person per on doc.person_id = per.person_id
                            JOIN patient pat on con.patient_id = pat.patient_id
WHERE pat.patient_id = 4
AND lab.status = 1
AND med_his.status = 1
AND doc_spec.status = 1
AND spec.status = 1
AND doc.status = 1
AND per.status = 1
AND pat.status = 1;

-- Seleccionar todas las prescripciones de un paciente
SELECT pre.prescription_id, med_his.diagnosis, per.first_name, per.first_surname, pre.prescription_date
FROM prescription pre
    JOIN medical_history med_his on pre.medical_history_id = med_his.medical_history_id
        JOIN consult con on med_his.medical_history_id = con.medical_history_id
            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id
                JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id
                    JOIN person per on doc.person_id = per.person_id
                        JOIN patient pat on con.patient_id = pat.patient_id
WHERE pat.patient_id = 16
AND pre.status = 1
AND med_his.status = 1
AND doc_spec.status = 1
AND doc.status = 1
AND per.status = 1
AND pat.status = 1;

-- Seleccionar el nombre de un usuario según su id de paciente

SELECT per.first_name, per.first_surname, per.second_surname
FROM person per
    JOIN user u on per.person_id = u.person_id
        JOIN patient pat on u.user_id = pat.user_id
WHERE pat.patient_id = 1
AND per.status = 1
AND u.status = 1
AND pat.status = 1;





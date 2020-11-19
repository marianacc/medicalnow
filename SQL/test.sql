-- Seleccionar todas las prescripciones de la consulta
SELECT pre.prescription_id
FROM prescription pre
    JOIN consult c on pre.consult_id = c.consult_id
WHERE c.consult_id = ?
AND pre.status = 1;



-- Los datos para la historia medica del paciente
SELECT mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, per.birthdate, usr.phone_number, usr.email
FROM person per
    JOIN user usr on per.person_id = usr.person_id
        JOIN patient pat on usr.user_id = pat.user_id
            JOIN medical_history mh on pat.patient_id = mh.patient_id
                JOIN consult con on mh.medical_history_id = con.medical_history_id
WHERE con.consult_id = ?
AND per.status = 1
AND usr.status = 1
AND pat.status = 1
AND mh.status = 1;

-- Seleccionar las fechas de las consultas por historial medico
SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(con.tx_date), con.status
FROM consult con
    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
        JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
            JOIN specialty spe on ds.specialty_id = spe.specialty_id
                JOIN doctor d on ds.doctor_id = d.doctor_id
                    JOIN person per on d.person_id = per.person_id
WHERE mh.medical_history_id = ?
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND d.status = 1
AND per.status = 1
GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name;


-- Obtener datos medicos del paciente
SELECT pat.weight, pat.height, pat.blood_group, pat.temperature, pat.pressure
FROM patient pat
    JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = ?
AND pat.status = 1
AND usr.status = 1;


-- Verificar si la historia medica tiene id o no
SELECT med.medical_history_id
FROM medical_history med
    JOIN patient pat on med.patient_id = pat.patient_id
        JOIN user usr on pat.user_id = usr.user_id
           JOIN doctor_specialty ds on med.doctor_specialty_id = ds.doctor_specialty_id
WHERE usr.user_id = ?
AND ds.doctor_specialty_id = ?
AND med.status = 1
AND pat.status = 1
AND ds.status = 1;

-- Seleccionar los datos del usuario
SELECT per.first_name, per.first_surname, per.second_surname, per.birthdate, usr.email, usr.password, usr.phone_number
FROM person per
    JOIN user usr on per.person_id = usr.person_id
WHERE usr.user_id = ?
AND per.status = 1
AND usr.status = 1;

-- Selecionar el rol del usuario
SELECT rol.role_name
FROM role rol
    JOIN user_role urol on rol.role_id = urol.role_id
        JOIN user usr on urol.user_id = usr.user_id
AND usr.user_id = 1
AND rol.status = 1
AND urol.status = 1;

-- Todas las historias médicas de un paciente
SELECT mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(c.tx_date), c.status
FROM medical_history mh
    JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
        JOIN specialty spe on ds.specialty_id = spe.specialty_id
            JOIN doctor doc on ds.doctor_id = doc.doctor_id
                JOIN person per on doc.person_id = per.person_id
                    JOIN consult c on mh.medical_history_id = c.medical_history_id
                        JOIN patient p on mh.patient_id = p.patient_id
                            JOIN user usr on p.user_id = usr.user_id
WHERE usr.user_id = 1
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND doc.status = 1
AND per.status = 1
AND p.status = 1
AND usr.status = 1
AND c.status = 1
OR c.status = 0
GROUP BY mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, spe.name, c.status;

-- Mostrar el contenido dentro de una historia médica

SELECT per.first_name, per.first_surname, per.second_surname, per.birthdate, usr.phone_number, usr.email, con.diagnosis
FROM person per
    JOIN user usr on per.person_id = usr.person_id
        JOIN patient pat on usr.user_id = pat.user_id
            JOIN medical_history mh on pat.patient_id = mh.patient_id
                JOIN consult con on mh.medical_history_id = con.medical_history_id
WHERE mh.medical_history_id = 1
AND per.status = 1
AND usr.status = 1
AND per.status = 1
AND pat.status = 1
AND mh.status = 1
AND con.status = 1;

-- Actualizar el diagnostico en una consulta
UPDATE consult
SET diagnosis = ?
WHERE consult_id = ?;

-- Seleccionar el diagnostico segun un id de consulta
SELECT diagnosis
FROM consult
WHERE consult_id = ?;

-- Seleccionar todas las consultas del paciente que tienen prescripciones
SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date
FROM consult con
    JOIN prescription pre on con.consult_id = pre.consult_id
        JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
            JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
                JOIN specialty spe on ds.specialty_id = spe.specialty_id
                    JOIN doctor doc on ds.doctor_id = doc.doctor_id
                        JOIN person per on doc.person_id = per.person_id
                            JOIN patient pat on mh.patient_id = pat.patient_id
                                JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = ?
AND con.status = 1
AND pre.status = 1
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND doc.status = 1
AND per.status = 1
GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date;

SELECT pre.prescription_id, con.tx_date
FROM prescription pre
    JOIN consult con on pre.consult_id = con.consult_id
WHERE con.consult_id = ?
AND pre.status = 1
AND con.status = 1;


-- Seleccionar el nombre de la especialidad dependiendo de la consulta
SELECT spe.name
FROM specialty spe
    JOIN doctor_specialty ds on spe.specialty_id = ds.specialty_id
        JOIN medical_history mh on ds.doctor_specialty_id = mh.doctor_specialty_id
            JOIN consult c on mh.medical_history_id = c.medical_history_id
WHERE c.consult_id = ?
AND spe.status = 1
AND ds.status = 1
AND mh.status = 1
AND c.status = 1;

-- Seleccionar el diagnositco segun el medicalHistoryId
SELECT con.diagnosis
FROM consult con
    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
WHERE con.consult_id = 1;

-- Verificar si el paciente tiene un historial medico con el doctor de la especialidad
SELECT COUNT(med.medical_history_id)
FROM medical_history med
    JOIN patient pat on med.patient_id = pat.patient_id
         JOIN doctor_specialty ds on med.doctor_specialty_id = ds.doctor_specialty_id
WHERE pat.patient_id = 1
  AND ds.doctor_specialty_id = 10
  AND med.status = 1
  AND pat.status = 1
  AND ds.status = 1
GROUP BY medical_history_id;

SELECT name
FROM specialty
WHERE specialty_id = 1
AND status = 1;


SELECT mh.medical_history_id
FROM medical_history mh
    JOIN patient pat on mh.patient_id = pat.patient_id
        JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
WHERE mh.patient_id = 1
AND ds.doctor_specialty_id = 1
AND mh.status = 1
AND pat.status = 1
AND ds.status = 1;

-- Seleccionar si existe una consulta activa entre el paciente y el doctor de la especialidad
SELECT con.consult_id
FROM consult con
    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
WHERE mh.medical_history_id = 1
AND con.status = 1
AND mh.status = 1;

SELECT consult_id
FROM consult
WHERE medical_history_id = 1
AND status = 1;

-- Seleccionar todas las consultas por paciente
SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(con.tx_date)
FROM consult con
    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
        JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
            JOIN specialty spe on ds.specialty_id = spe.specialty_id
                JOIN doctor doc on ds.doctor_id = doc.doctor_id
                    JOIN person per on doc.person_id = per.person_id
                        JOIN patient pat on mh.patient_id = pat.patient_id
                            JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = ?
AND con.status = 1
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND doc.status = 1
AND per.status = 1
AND pat.status = 1
AND usr.status = 1
GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date;


-- Seleccionar todas las historias clinicas del paciente
SELECT mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, MIN(mh.tx_date), con.status
FROM medical_history mh
    JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
        JOIN specialty s on ds.specialty_id = s.specialty_id
            JOIN doctor d on ds.doctor_id = d.doctor_id
                JOIN person per on d.person_id = per.person_id
                    JOIN consult con on mh.medical_history_id = con.medical_history_id
                        JOIN patient pat on mh.patient_id = pat.patient_id
                            JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = 1
AND mh.status = 1
AND ds.status = 1
AND s.status = 1
AND per.status = 1
AND pat.status = 1
AND usr.status = 1
GROUP BY mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, con.status;



-- Seleccionar todas las consultas por doctor
SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, MIN(con.tx_date)
FROM consult con
    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
        JOIN patient pat on mh.patient_id = pat.patient_id
            JOIN person per on pat.person_id = per.person_id
                JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
                    JOIN doctor doc on ds.doctor_id = doc.doctor_id
                        JOIN user usr on usr.user_id = doc.user_id
WHERE usr.user_id = ?
  AND con.status = 1
  AND mh.status = 1
  AND per.status = 1
  AND pat.status = 1
  AND ds.status = 1
  AND doc.status = 1
  AND usr.status = 1
GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, con.tx_date;

SELECT conv.tx_id, conv.message
FROM conversation conv
    JOIN consult cons on conv.consult_id = cons.consult_id
WHERE conv.consult_id = 7
AND conv.status = 1
AND cons.status = 1;

SELECT rol.role_id
FROM role rol
    JOIN user_role usr on rol.role_id = usr.role_id
        JOIN user u on usr.user_id = u.user_id
WHERE u.user_id = 25
AND rol.status = 1
AND usr.status = 1
AND u.status = 1;

SELECT lab.laboratory_id, lab.name, per.first_name, per.first_surname, per.second_surname, spe.name, DATE(lab.tx_date)
FROM laboratory lab
    JOIN consult con on lab.consult_id = con.consult_id
        JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
            JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
                JOIN specialty spe on ds.specialty_id = spe.specialty_id
                    JOIN doctor doc on ds.doctor_id = doc.doctor_id
                        JOIN person per on doc.person_id = per.person_id
                            JOIN patient p on mh.patient_id = p.patient_id
                                JOIN user usr on p.user_id = usr.user_id
WHERE usr.user_id = ?
AND lab.status = 1
AND con.status = 1
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND doc.status = 1
AND per.status = 1
AND usr.status = 1;

SELECT pre.prescription_id, mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, spe.name, DATE(pre.tx_date)
FROM prescription pre
    JOIN consult con on pre.consult_id = con.consult_id
        JOIN medical_history mh on con.medical_history_id = mh.medical_history_id
            JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id
                JOIN specialty spe on ds.specialty_id = spe.specialty_id
                    JOIN doctor doc on ds.doctor_id = doc.doctor_id
                        JOIN person per on doc.person_id = per.person_id
                            JOIN patient p on mh.patient_id = p.patient_id
                                JOIN user usr on usr.user_id = p.user_id
AND usr.user_id = ?
AND pre.status = 1
AND con.status = 1
AND mh.status = 1
AND ds.status = 1
AND spe.status = 1
AND doc.status = 1
AND per.status = 1
AND p.status = 1
AND usr.status = 1;

UPDATE consult
SET status = 0
WHERE consult_id = 3;


SELECT pre.prescription_id, med_his.diagnosis, per.first_name, per.first_surname, pre.prescription_date\n" +
                        "FROM prescription pre\n" +
                        "    JOIN medical_history med_his on pre.medical_history_id = med_his.medical_history_id\n" +
                        "        JOIN consult con on med_his.medical_history_id = con.medical_history_id\n" +
                        "            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id\n" +
                        "                JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id\n" +
                        "                    JOIN person per on doc.person_id = per.person_id\n" +
                        "                        JOIN patient pat on con.patient_id = pat.patient_id\n" +
                        "                            JOIN user usr on pat.user_id = usr.user_id\n" +
                        "WHERE usr.user_id = ?\n" +
                        "AND pre.status = 1\n" +
                        "AND med_his.status = 1\n" +
                        "AND doc_spec.status = 1\n" +
                        "AND doc.status = 1\n" +
                        "AND per.status = 1\n" +
                        "AND pat.status = 1\n" +
                        "AND usr.status = 1;


FROM role rol
    JOIN user_role ur on rol.role_id = ur.role_id
        JOIN user usr on ur.user_id = usr.user_id
            JOIN patient p on usr.user_id = p.user_id
                JOIN medical_history mh on p.patient_id = mh.patient_id
                    JOIN consult con on mh.medical_history_id = con.medical_history_id
                        JOIN conversation conv on con.consult_id = conv.consult_id
WHERE con.consult_id = 5
AND rol.status = 1
AND ur.status = 1
AND usr.status = 1
AND mh.status = 1
AND con.status = 1
AND conv.status = 1;

SELECT per.first_name, per.first_surname, per.second_surname, s.name
FROM person per
    JOIN doctor doc on per.person_id = doc.person_id
        JOIN doctor_specialty ds on doc.doctor_id = ds.doctor_id
            JOIN specialty s on ds.specialty_id = s.specialty_id
                JOIN medical_history mh on ds.doctor_specialty_id = mh.doctor_specialty_id
                    JOIN consult c on mh.medical_history_id = c.medical_history_id
WHERE c.consult_id = 1
AND per.status = 1
AND doc.status = 1
AND ds.status = 1
AND s.status = 1
AND mh.status = 1
AND c.status = 1;

-- Seleccionar el id del doctor segun el id del usuario

SELECT doc.doctor_id
FROM doctor doc
        JOIN user usr on doc.user_id = usr.user_id
WHERE usr.user_id = 2
AND doc.status = 1
AND usr.status = 1;


SELECT per.first_name, per.first_surname, per.second_surname
FROM person per
    JOIN user usr on per.person_id = usr.person_id
        JOIN patient pat on per.person_id = pat.person_id
            JOIN medical_history mh on pat.patient_id = mh.patient_id
                JOIN consult con on mh.medical_history_id = con.medical_history_id
WHERE con.consult_id = 5
AND per.status = 1
AND usr.status = 1
AND pat.status = 1
AND mh.status = 1
AND con.status = 1;

-- Seleccionar todas las especialidades

SELECT sp.specialty_id, sp.specialty_name, count(doc_sp.specialty_id), sp.specialty_image
FROM specialty sp
    JOIN doctor_specialty doc_sp on sp.specialty_id = doc_sp.specialty_id
WHERE sp.status = 1
and doc_sp.status = 1
GROUP BY sp.specialty_name, doc_sp.specialty_id, sp.specialty_image;

-- Seleccionar los doctores por especialidad

SELECT  doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname, doc_spec.price, doc_spec.from_time, doc_spec.to_time, avg(qua.qualification)\n" +
                        "FROM person per\n" +
                        "         JOIN doctor doc on per.person_id = doc.person_id\n" +
                        "         JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id\n" +
                        "         JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id\n" +
                        "         JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id\n" +
                        "WHERE doc.status = 1\n" +
                        "  AND per.status = 1\n" +
                        "  AND doc_spec.status = 1\n" +
                        "  AND qua.status = 1\n" +
                        "  AND spe.specialty_id = ?\n" +
                        "GROUP BY doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname" +
                        "ORDER BY avg(qua.qualification);

SELECT doc_spec.doctor_specialty_id, spe.name, per.first_name, per.first_surname, per.second_surname, doc_spec.price, doc_spec.from_time, doc_spec.to_time, avg(qua.qualification), doc.doctor_id
FROM person per
    JOIN doctor doc on per.person_id = doc.person_id
        JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id
            JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id
                JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id
WHERE doc.status = 1
AND per.status = 1
AND doc_spec.status = 1
AND qua.status = 1
AND spe.specialty_id = 2
GROUP BY doc_spec.doctor_specialty_id, spe.name, per.first_name, per.first_surname, per.second_surname, doc_spec.price, doc_spec.from_time, doc_spec.to_time, qua.qualification, doc.doctor_id
ORDER BY qua.qualification desc;

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
                                JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = 20
AND lab.status = 1
AND med_his.status = 1
AND doc_spec.status = 1
AND spec.status = 1
AND doc.status = 1
AND per.status = 1
AND pat.status = 1
AND usr.status = 1;

-- Seleccionar todas las prescripciones de un paciente
SELECT pre.prescription_id, med_his.diagnosis, per.first_name, per.first_surname, pre.prescription_date
FROM prescription pre
    JOIN medical_history med_his on pre.medical_history_id = med_his.medical_history_id
        JOIN consult con on med_his.medical_history_id = con.medical_history_id
            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id
                JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id
                    JOIN person per on doc.person_id = per.person_id
                        JOIN patient pat on con.patient_id = pat.patient_id
                            JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = 16
AND pre.status = 1
AND med_his.status = 1
AND doc_spec.status = 1
AND doc.status = 1
AND per.status = 1
AND pat.status = 1
AND usr.status = 1;

-- Seleccionar el nombre de un usuario según su id de paciente

SELECT per.first_name, per.first_surname, per.second_surname
FROM person per
    JOIN user usr on per.person_id = usr.person_id
WHERE usr.user_id = 16
AND per.status = 1
AND usr.status = 1;

-- Seleccionar los datos del usuario para que los visualice

SELECT per.first_name, per.first_surname, per.second_surname, usr.phone_number, per.birthdate, pat.weight, pat.height, per.city, usr.email, usr.password
FROM person per
    JOIN user usr on per.person_id = usr.person_id
        JOIN patient pat on usr.user_id = pat.user_id
WHERE usr.user_id = 16
AND per.status = 1
AND usr.status = 1
AND pat.status = 1;

-- Obtener el id de la persona segun su user id
SELECT per.person_id
FROM person per
    JOIN user usr on per.person_id = usr.person_id
AND user_id = 16
AND per.status = 1
AND usr.status = 1;

-- Actualizar los datos de una persona segun su id de persona
UPDATE person
SET first_name = 'Brennen', first_surname = 'Romaines', second_surname = 'Connew', birthdate = '2000/06/19', city = 'La Paz'
WHERE person_id = 1
AND person.status = 1;

-- Actualizar los datos de usuario de una persona segun su id de usuario
UPDATE user
SET email = '', password = '', phone_number = ''
WHERE user_id = 1
AND user.status = 1;

-- Obtener el id del paciente segun el id del usuario
SELECT pat.patient_id
FROM patient pat
         JOIN user usr on pat.user_id = usr.user_id
WHERE pat.patient_id = 16
AND pat.status = 1
AND usr.status = 1;

-- Actualizar datos de un paciente
UPDATE patient
SET weight = ?, height = ?, blood_group = ?, temperature = ?, pressure = ?
WHERE patient_id = ?
AND status = 1;

SELECT mh.medical_history_id
FROM medical_history mh
    JOIN patient p on mh.patient_id = p.patient_id
        JOIN user u on p.user_id = u.user_id
WHERE u.user_id = ?
AND mh.status = 1
AND p.status = 1
AND u.status = 1;

-- Obtener el detalle de la prescripción
SELECT pro.product_name, pro.product_detail, pro.product_quantity
FROM product pro
    JOIN prescription pre on pro.prescription_id = pre.prescription_id
WHERE pre.prescription_id = 1
AND pre.status = 1
AND pro.status = 1;


-- Obtener la descripción de la prescripción
SELECT description
FROM prescription
WHERE prescription_id = 1
AND status = 1;


-- Verificar si el userId tiene un historial medico o no
SELECT med.medical_history_id
FROM medical_history med
    JOIN patient pat on med.patient_id = pat.patient_id
        JOIN user usr on pat.user_id = usr.user_id
WHERE usr.user_id = 39
AND med.status = 1
AND pat.status = 1
AND usr.status = 1;

-- Retornar el maximo id de las prescripciones
SELECT MAX(prescription_id)
FROM prescription
WHERE consult_id = ?;

-- Seleccionar los datos para la pantalla del pago
SELECT per.first_name, per.first_surname, per.second_surname, spe.name, avg(qua.qualification), ds.price
FROM person per
    JOIN doctor doc on per.person_id = doc.person_id
        JOIN doctor_specialty ds on doc.doctor_id = ds.doctor_id
            JOIN specialty spe on ds.specialty_id = spe.specialty_id
                JOIN qualification qua on ds.doctor_specialty_id = qua.doctor_specialty_id
WHERE ds.doctor_specialty_id = ?
AND per.status = 1
and doc.status = 1
AND ds.status = 1
AND spe.status = 1
AND qua.status = 1;

SELECT MAX(medical_history_id) from medical_history where medical_history.status = 1;

-- Obtener el id de la especialidad del doctor
SELECT doc_spec.doctor_specialty_id
FROM doctor_specialty doc_spec
    JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id
WHERE doc.doctor_id = 1
AND doc_spec.status = 1
AND doc.status = 1;

-- Devolver a los doctores de medicina general
SELECT spe.specialty_name, per.first_name, per.first_surname, per.second_surname, avg(qua.qualification), doc.doctor_id
FROM person per
         JOIN doctor doc on per.person_id = doc.person_id
         JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id
         JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id
         JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id
WHERE doc.status = 1
  AND per.status = 1
  AND doc_spec.status = 1
  AND qua.status = 1
  AND spe.specialty_id = 4
GROUP BY doc.doctor_id, per.first_name, per.first_surname, per.second_surname;
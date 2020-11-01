-- Seleccionar todas las especialidades

SELECT sp.specialty_id, sp.specialty_name, count(doc_sp.specialty_id), sp.specialty_image
FROM specialty sp JOIN doctor_specialty doc_sp
    on sp.specialty_id = doc_sp.specialty_id
WHERE sp.status = 1
and doc_sp.status = 1
GROUP BY sp.specialty_name, doc_sp.specialty_id, sp.specialty_image

-- Seleccionar los doctores por especialidad

SELECT doc.doctor_id, per.first_name, per.first_surname, per.second_surname, avg(qua.qualification)
FROM person per JOIN doctor doc
    on per.person_id = doc.person_id
        JOIN doctor_specialty doc_spec
        on doc.doctor_id = doc_spec.doctor_id
            JOIN qualification qua
            on doc_spec.doctor_specialty_id = qua.doctor_specialty_id
                JOIN specialty spe
                on spe.specialty_id = doc_spec.specialty_id
WHERE doc.status = 1
AND per.status = 1
AND doc_spec.status = 1
AND qua.status = 1
AND spe.specialty_id = 5
GROUP BY doc.doctor_id, per.first_name, per.first_surname, per.second_surname

-- Devolver el Id segun un email y contraseña
SELECT user_id FROM user WHERE email = 'ahentzer0@wisc.edu' and password = 'aG6xyB4Ss';

-- Seleccionar el último paciente registrado
SELECT MAX(person_id) FROM patient WHERE status = 1;


CREATE TABLE Doctor (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    salary NUMBER,
    address VARCHAR2(200)
);

INSERT INTO Doctor (id, name, salary, address) VALUES (1, 'Dr. Ahmed', 5000, 'Cairo');
INSERT INTO Doctor (id, name, salary, address) VALUES (2, 'Dr. Sarah', 6000, 'Giza');
INSERT INTO Doctor (id, name, salary, address) VALUES (3, 'Dr. John', 7000, 'Alexandria');
INSERT INTO Doctor (id, name, salary, address) VALUES (4, 'Dr. Mona', 8000, 'Mansoura');
INSERT INTO Doctor (id, name, salary, address) VALUES (5, 'Dr. Ali', 1000, 'Luxor');
INSERT INTO Doctor (id, name, salary, address) VALUES (6, 'Dr. Hoda', 2000, 'Aswan');
INSERT INTO Doctor (id, name, salary, address) VALUES (7, 'Dr. Omar', 3000, 'Tanta');
INSERT INTO Doctor (id, name, salary, address) VALUES (8, 'Dr. Laila', 4500, 'Ismailia');
INSERT INTO Doctor (id, name, salary, address) VALUES (9, 'Dr. Youssef', 5500, 'Suez');
INSERT INTO Doctor (id, name, salary, address) VALUES (10, 'Dr. Noha', 6500, 'Port Said');
COMMIT;

UPDATE Doctor 
SET salary = 20000 
WHERE id = 3;
COMMIT;

DELETE FROM Doctor 
WHERE id = 9;
COMMIT;

SELECT name || ' earns ' || TO_CHAR(salary) AS doctor_compensation 
FROM Doctor;

SELECT id, name, salary * 2 AS doubled_salary, address 
FROM Doctor;

SELECT * FROM Doctor 
WHERE salary IN (1000, 2000, 3000);

ALTER TABLE Doctor RENAME TO PRD_DOCTOR;
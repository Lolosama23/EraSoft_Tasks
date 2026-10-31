
-- 1. Find employees where EMPLOYEE_ID is between 100 and 105
SELECT *
FROM EMPLOYEES
WHERE EMPLOYEE_ID BETWEEN 100 AND 105;

-- 2. Find employees with specific EMPLOYEE_IDs
SELECT *
FROM EMPLOYEES
WHERE EMPLOYEE_ID IN (151, 152, 153, 154, 155);

-- 3. Find employees whose FIRST_NAME starts with P or p
SELECT *
FROM EMPLOYEES
WHERE UPPER(FIRST_NAME) LIKE 'P%';

-- 4. Find employees whose FIRST_NAME ends with A or a
SELECT *
FROM EMPLOYEES
WHERE UPPER(FIRST_NAME) LIKE '%A';

-- 5. Find employees whose FIRST_NAME contains A or a
SELECT *
FROM EMPLOYEES
WHERE UPPER(FIRST_NAME) LIKE '%A%';

-- 6. Find employees whose third character is E or e
SELECT *
FROM EMPLOYEES
WHERE UPPER(FIRST_NAME) LIKE '__E%';

-- 7. Find employees who don't have a manager
SELECT *
FROM EMPLOYEES
WHERE MANAGER_ID IS NULL;

-- 8. Find employees who have a manager
SELECT *
FROM EMPLOYEES
WHERE MANAGER_ID IS NOT NULL;

-- 9. Insert a new employee without a manager
INSERT INTO EMPLOYEES
(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID, MANAGER_ID)
VALUES
(300, 'Loai', 'Osama', 'LOAI300', SYSDATE, 'IT_PROG', NULL);

-- 10. Find employees who work as AD_VP or IT_PROG
SELECT *
FROM EMPLOYEES
WHERE JOB_ID IN ('AD_VP', 'IT_PROG');

-- 11. Sort employees by LAST_NAME ascending
SELECT *
FROM EMPLOYEES
ORDER BY LAST_NAME ASC;

-- 12. Sort employees by HIRE_DATE descending
SELECT *
FROM EMPLOYEES
ORDER BY HIRE_DATE DESC;

-- 13. Sort by DEPARTMENT_ID ascending and SALARY descending
SELECT *
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID ASC, SALARY DESC;

-- 14. Display LAST_NAME in lowercase
SELECT LOWER(LAST_NAME)
FROM EMPLOYEES;

-- 15. Display FIRST_NAME in uppercase
SELECT UPPER(FIRST_NAME)
FROM EMPLOYEES;

-- 16. Display FIRST_NAME and LAST_NAME in title case
SELECT INITCAP(FIRST_NAME), INITCAP(LAST_NAME)
FROM EMPLOYEES;

-- 17. Find employees whose LAST_NAME is Smith regardless of case
SELECT *
FROM EMPLOYEES
WHERE LOWER(LAST_NAME) = 'smith';


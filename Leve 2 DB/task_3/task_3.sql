-- 1. Create the Employees Table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    FirstName VARCHAR2(50),
    LastName VARCHAR2(50),
    Department VARCHAR2(50),
    Salary NUMBER
);

-- 2. Insert the 5 records
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES (101, 'John1', 'Doe1', 'HR', 20000);
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES (102, 'John2', 'Doe2', 'IT', 50000);
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES (103, 'John3', 'Doe3', 'CS', 40000);
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES (104, 'John4', 'Doe4', 'IT', 10000);
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary) VALUES (105, 'John5', 'Doe5', 'ZX', 30000);
COMMIT;

-- 3. Update the salary for EmployeeID 101
UPDATE Employees 
SET Salary = 600000 
WHERE EmployeeID = 101;
COMMIT;

-- 4. Delete a record where Department = 101
-- Note: I put '101' in quotes because the Department column is text (VARCHAR2). 
-- (If you actually meant EmployeeID = 101, you would change 'Department' to 'EmployeeID')
DELETE FROM Employees 
WHERE Department = '101';
COMMIT;

-- 5. Retrieve all employees in the IT department
SELECT * FROM Employees 
WHERE Department = 'IT';

-- 6. Select all data with FirstName and LastName concatenated as one column
SELECT 
    EmployeeID, 
    FirstName || ' ' || LastName AS FullName, 
    Department, 
    Salary 
FROM Employees;
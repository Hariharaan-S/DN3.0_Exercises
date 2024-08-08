-- Initial Setup

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
); 

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (3, 'Maria', TO_DATE('1994-08-27', 'YYYY-MM-DD'), 1300, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (3, 3, 'Savings', 1300, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Exercise 1
-- a) Write a PL/SQL block that loops through all customers, checks their age, and if they are above 60, apply a 1% discount to their current loan interest rates.
-- b) Write a PL/SQL block that iterates through all customers and sets a flag IsVIP to TRUE for those with a balance over $10,000.
-- c) Write a PL/SQL block that fetches all loans due in the next 30 days and prints a reminder message for each customer. 
SET SERVEROUTPUT ON;

-- a)
BEGIN
    FOR customer_record IN (SELECT * FROM Customers) LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, customer_record.DOB) / 12) > 60 THEN
            UPDATE Loans 
            SET InterestRate = InterestRate - (InterestRate * 0.01) 
            WHERE Loans.CustomerID = customer_record.CustomerID;
            
            COMMIT; -- Commit should be outside the loop ideally or after all changes in this case.
            
            DBMS_OUTPUT.PUT_LINE('Updated: ' || customer_record.CustomerID);
        END IF;
    END LOOP;
END;
/

ALTER TABLE Customers ADD (IsVIP NUMBER(1));
-- b)
BEGIN
    FOR customer_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF customer_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 1
            WHERE CustomerID = customer_rec.CustomerID;
        ELSE
            UPDATE Customers
            SET IsVIP = 0
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;

clear SCREEN;
-- c)
DECLARE
    v_due_date DATE;
    v_customer_name VARCHAR2(100);
    v_loan_amount NUMBER;
BEGIN
    FOR due_loan in (SELECT l.EndDate, c.Name, l.LoanAmount FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID
                     WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP
        v_due_date := due_loan.EndDate;
        v_customer_name := due_loan.Name;
        v_loan_amount := due_loan.LoanAMount;
        dbms_output.put_line('Reminder: Dear ' || v_customer_name || 
                             ', your loan of amount $' || v_loan_amount || 
                             ' is due on ' || TO_CHAR(v_due_date, 'DD-MON-YYYY') || 
                             '. Please ensure timely payment.');
    ENd LOOP;
END;

-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Exercise 2

-- a) Write a stored procedure SafeTransferFunds that transfers funds between two accounts. Ensure that if any error occurs (e.g., insufficient funds), an appropriate error message is logged and the transaction is rolled back.
-- b) Write a stored procedure UpdateSalary that increases the salary of an employee by a given percentage. If the employee ID does not exist, handle the exception and log an error message.
-- c) Write a stored procedure AddNewCustomer that inserts a new customer into the Customers table. If a customer with the same ID already exists, handle the exception by logging an error and preventing the insertion.

-- a)
CREATE OR REPLACE PROCEDURE SafeTransferFunds (customerID1 IN Number, customerID2 IN Number, transferAmount IN NUMBER)
AS
    tempAmount NUMBER;

BEGIN
    SELECT Balance into tempAmount from Customers where CustomerID = customerID1;
    
    IF (tempAmount < transferAmount) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds for transfer.');
    END IF;
    
    UPDATE Customers SET Balance = Balance - transferAmount WHERE CustomerID = customerID1;
    
    UPDATE Customers SET Balance = Balance + transferAmount WHERE CustomerID = customerID2;
    
    COMMIT;
    
     DBMS_OUTPUT.PUT_LINE('Transfer successful.');
    
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
    
END;

-- Method Invocation
BEGIN
    SafeTransferFunds(1,2,1000);
END;

-- b)
CREATE OR REPLACE PROCEDURE UpdateSalary (employeeID IN Number, percentHike IN Number)
AS
    tempSalary Number;
    
BEGIN
    SELECT Salary INTO tempSalary FROM Employees WHERE EmployeeID = employeeID FOR UPDATE; 
    if(tempSalary IS NULL)THEN
        RAISE_APPLICATION_ERROR(-20001, 'No Employee present.');
    end if;
    
    UPDATE Employees SET Salary = Salary + (Salary * percentHike) WHERE EmployeeID = employeeID;
    DBMS_OUTPUT.PUT_LINE('Salary Update successful.');
    
    EXCEPTION 
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Salary update failed!' || SQLERRM);

END UpdateSalary;
/

-- Method Invocation
BEGIN
    UpdateSalary(1,0.12);
END;

-- c)
CREATE OR REPLACE PROCEDURE AddNewCustomer (
   p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE,
    p_Balance IN NUMBER
)
AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
    
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_CustomerID || ' already exists.');
    
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
        ROLLBACK;
END AddNewCustomer;
/

-- Method Invocation
BEGIN
    AddNewCustomer(4, 'Jane Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000);
END;
/

-- -------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 






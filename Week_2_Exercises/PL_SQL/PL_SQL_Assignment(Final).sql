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
-- Exercise 3
-- a) Write a stored procedure ProcessMonthlyInterest that calculates and updates the balance of all savings accounts by applying an interest rate of 1% to the current balance.
-- b) Write a stored procedure UpdateEmployeeBonus that updates the salary of employees in a given department by adding a bonus percentage passed as a parameter.
-- c) Write a stored procedure TransferFunds that transfers a specified amount from one account to another, checking that the source account has sufficient balance before making the transfer.

-- a)
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS

BEGIN        
        UPDATE Accounts SET Balance = Balance + (Balance * 0.01) WHERE AccountType = 'Savings';   
        DBMS_OUTPUT.PUT_LINE('Updated Balance!');
        COMMIT;
END;

BEGIN 
    ProcessMonthlyInterest;
END;

-- b)
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (department IN VARCHAR2, percentHike IN NUMBER)
AS

BEGIN        
        FOR employeeID IN (SELECT EmployeeID FROM Employees WHERE Department = department) LOOP
            UPDATE Employees SET Salary = Salary + (Salary * percentHike) WHERE EmployeeID = employeeID;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE('Updated Salary!');
        COMMIT;
END;

BEGIN 
    UpdateEmployeeBonus('IT',0.06);
END;

-- c)
-- Same as SafeTransferFunds()
-- Method Invocation
BEGIN
    SafeTransferFunds(1,2,1000);
END;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Exercise 4
-- a) Write a function CalculateAge that takes a customer's date of birth as input and returns their age in years.
-- b) Write a function CalculateMonthlyInstallment that takes the loan amount, interest rate, and loan duration in years as input and returns the monthly installment amount.
-- c) Write a function HasSufficientBalance that takes an account ID and an amount as input and returns a boolean indicating whether the account has at least the specified amount.


-- a)
CREATE OR REPLACE FUNCTION CalculateAge(DOB IN DATE)
RETURN NUMBER IS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, DOB) / 12);
END;


-- Method Invocation
DECLARE
    v_customer_age NUMBER;
BEGIN
    v_customer_age := CalculateAge(TO_DATE('1985-05-15', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('The age of the Customer: ' || v_customer_age);
END;

-- b)
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (loanAmount IN NUMBER,interestRate IN NUMBER, loanDuration IN NUMBER)
RETURN NUMBER IS
v_interest_amount NUMBER;
BEGIN
    v_interest_amount := TRUNC((loanAmount * interestRate * loanDuration)/100);
    RETURN v_interest_amount;
END;

-- Method Invocation
DECLARE
    v_monthly_installment NUMBER;
BEGIN
    v_monthly_installment := CalculateMonthlyInstallment (10000,7,2);
    DBMS_OUTPUT.PUT_LINE('The monthly installment of Customer: ' || v_monthly_installment);
END;

-- c)
CREATE OR REPLACE FUNCTION HasSufficientBalance (customerID IN NUMBER, amount IN NUMBER)
RETURN BOOLEAN IS
    v_customer_balance NUMBER;
BEGIN
    SELECT Balance 
    INTO v_customer_balance 
    FROM Customers 
    WHERE CustomerID = customerID;
    
    IF v_customer_balance < amount THEN
        RETURN FALSE;
    END IF;
    
    RETURN TRUE;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN TOO_MANY_ROWS THEN
        RETURN FALSE;
END;


-- Method Invocation
DECLARE 
    isSufficient BOOLEAN;
BEGIN
    isSufficient := HasSufficientBalance(2,1000);
    IF isSufficient THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient Balance.. Proceeding with Transaction..');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance.. Aborting Transaction..');
    END IF;
END;

-----------------------------------------------------------------------------------------------------------------------------------------------------------
-- Exercse 5
-- a) Write a trigger UpdateCustomerLastModified that updates the LastModified column of the Customers table to the current date whenever a customer's record is updated.
-- b) Write a trigger LogTransaction that inserts a record into an AuditLog table whenever a transaction is inserted into the Transactions table.
-- c) Write a trigger CheckTransactionRules that ensures withdrawals do not exceed the balance and deposits are positive before inserting a record into the Transactions table.

-- a)
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified 
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;

-- Trigger Invocation
BEGIN
        UPDATE Customers SET Balance = 10000 WHERE CustomerID = 1;
END;

-- b)
CREATE TABLE AuditLog(
    LogID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TransactionDate DATE,
    FromCustomerID NUMBER,
    ToCustomerID NUMBER,
    ModfiedDate DATE,
    TransferredAmount NUMBER
);

CREATE OR REPLACE TRIGGER LogTransaction  
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionDate, FromCustomerID, ToCustomerID, ModfiedDate, TransferredAmount)
    VALUES (:NEW.TransactionDate, :NEW.AccountID, NULL, SYSDATE, :NEW.Amount);
END;

-- Trigger Invocation
BEGIN
        INSERT INTO Transactions VALUES(3,1,SYSDATE,10000,'TRANSFER');
END;

-- c)
CREATE OR REPLACE TRIGGER CheckTransactionRules  
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'WITHDRAWAL' THEN
    
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance for withdrawal.');
        END IF;
    ELSIF :NEW.transactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit Amount must be positive!');
        END IF;
    End IF;
    
    EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
END;

-- Trigger Invocation
BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (5, 1, SYSDATE, 5000, 'WITHDRAWAL');
END;

-----------------------------------------------------------------------------------------------------------------------------------------------------------
-- Cursors
-- a) Write a PL/SQL block using an explicit cursor GenerateMonthlyStatements that retrieves all transactions for the current month and prints a statement for each customer.
-- b) Write a PL/SQL block using an explicit cursor ApplyAnnualFee that deducts an annual maintenance fee from the balance of all accounts.
-- c) Write a PL/SQL block using an explicit cursor UpdateLoanInterestRates that fetches all loans and updates their interest rates based on the new policy.

-- a)
DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_transaction_id Transactions.TransactionID%TYPE;
    v_account_id Transactions.AccountID%TYPE;
    v_transaction_date Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_transaction_type Transactions.TransactionType%TYPE;

    CURSOR c1 IS 
        SELECT CustomerID 
        FROM Customers;
        
    CURSOR c2(p_account_id IN NUMBER) IS 
        SELECT TransactionID, AccountID, TransactionDate, Amount, TransactionType
        FROM Transactions
        WHERE AccountID = p_account_id
        AND EXTRACT(MONTH FROM TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM TransactionDate) = EXTRACT(YEAR FROM SYSDATE);

BEGIN
    OPEN c1;
    LOOP
        FETCH c1 INTO v_customer_id;
        EXIT WHEN c1%NOTFOUND;
        OPEN c2(v_customer_id);
        LOOP
            FETCH c2 INTO v_transaction_id, v_account_id, v_transaction_date, v_amount, v_transaction_type;
            EXIT WHEN c2%NOTFOUND;
            
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id || 
                                 ' Transaction ID: ' || v_transaction_id || 
                                 ' Account ID: ' || v_account_id || 
                                 ' Date: ' || v_transaction_date || 
                                 ' Amount: ' || v_amount || 
                                 ' Type: ' || v_transaction_type);
        END LOOP;
        CLOSE c2;
    END LOOP;
    CLOSE c1;
END;


-- b)
DECLARE
    v_balance Customers.Balance%TYPE;
    v_annual_amount NUMBER := 200;

    CURSOR c1 IS SELECT Balance FROM Customers;
BEGIN

    OPEN c1;
    LOOP
        FETCH c1 INTO v_balance;
        UPDATE Customers SET Balance = Balance - v_annual_amount;
        EXIT WHEN c1%NOTFOUND;
    END LOOP;

END;

-- c)
DECLARE
    v_loan_interest Loans.InterestRate%TYPE;

    CURSOR c1 IS SELECT InterestRate FROM Loans;
BEGIN

    OPEN c1;
    LOOP
        FETCH c1 INTO v_loan_interest;
        UPDATE Loans SET InterestRate = InterestRate + 2;
        EXIT WHEN c1%NOTFOUND;
    END LOOP;

END;
---------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Exercise 7
-- a) Create a package CustomerManagement with procedures for adding a new customer, updating customer details, and a function to get customer balance.
-- b) Write a package EmployeeManagement with procedures to hire new employees, update employee details, and a function to calculate annual salary.
-- c) Create a package AccountOperations with procedures for opening a new account, closing an account, and a function to get the total balance of a customer across all accounts.


-- a)
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_balance IN Customers.Balance%TYPE
    );

    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_balance IN Customers.Balance%TYPE
    );

    FUNCTION GetCustomerBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_balance IN Customers.Balance%TYPE
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, Balance)
        VALUES (p_customer_id, p_name, p_balance);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Customer ID already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_balance IN Customers.Balance%TYPE
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            Balance = p_balance
        WHERE CustomerID = p_customer_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Customer not found.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;

        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Customer not found.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/


BEGIN
    CustomerManagement.AddCustomer(1, 'John Doe', 5000);

    CustomerManagement.UpdateCustomerDetails(1, 'John Doe', 6000);

    DECLARE
        v_balance NUMBER;
    BEGIN
        v_balance := CustomerManagement.GetCustomerBalance(1);
        DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || v_balance);
    END;
END;
/

-- b)
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    -- Procedure to hire a new employee
    PROCEDURE HireEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE,
        p_hire_date IN Employees.HireDate%TYPE
    );

    -- Procedure to update employee details
    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE
    );

    -- Function to calculate annual salary
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    -- Procedure to hire a new employee
    PROCEDURE HireEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE,
        p_hire_date IN Employees.HireDate%TYPE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, p_hire_date);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Employee ID already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END HireEmployee;

    -- Procedure to update employee details
    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_name,
            Position = p_position,
            Salary = p_salary,
            Department = p_department
        WHERE EmployeeID = p_employee_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Employee not found.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END UpdateEmployeeDetails;

    -- Function to calculate annual salary
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER IS
        v_salary NUMBER;
        v_annual_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;

        v_annual_salary := v_salary * 12;

        RETURN v_annual_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee not found.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

BEGIN
    -- Hire a new employee
    EmployeeManagement.HireEmployee(1, 'John Doe', 'Software Engineer', 5000, 'IT', SYSDATE);

    -- Update employee details
    EmployeeManagement.UpdateEmployeeDetails(1, 'John Doe', 'Senior Software Engineer', 6000, 'IT');

    -- Calculate annual salary
    DECLARE
        v_annual_salary NUMBER;
    BEGIN
        v_annual_salary := EmployeeManagement.CalculateAnnualSalary(1);
        DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || v_annual_salary);
    END;
END;
/


-- c)
CREATE OR REPLACE PACKAGE AccountOperations AS
    -- Procedure to open a new account
    PROCEDURE OpenAccount(
        p_account_id IN Accounts.AccountID%TYPE,
        p_customer_id IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance IN Accounts.Balance%TYPE
    );

    -- Procedure to close an account
    PROCEDURE CloseAccount(
        p_account_id IN Accounts.AccountID%TYPE
    );

    -- Function to get the total balance of a customer across all accounts
    FUNCTION GetTotalBalance(
        p_customer_id IN Accounts.CustomerID%TYPE
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    -- Procedure to open a new account
    PROCEDURE OpenAccount(
        p_account_id IN Accounts.AccountID%TYPE,
        p_customer_id IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance IN Accounts.Balance%TYPE
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Account ID already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END OpenAccount;

    -- Procedure to close an account
    PROCEDURE CloseAccount(
        p_account_id IN Accounts.AccountID%TYPE
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Account not found.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END CloseAccount;

    -- Function to get the total balance of a customer across all accounts
    FUNCTION GetTotalBalance(
        p_customer_id IN Accounts.CustomerID%TYPE
    ) RETURN NUMBER IS
        v_total_balance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        IF v_total_balance IS NULL THEN
            v_total_balance := 0;
        END IF;

        RETURN v_total_balance;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/

BEGIN
    -- Open a new account
    AccountOperations.OpenAccount(1001, 1, 'Savings', 1000);

    -- Close an account
    AccountOperations.CloseAccount(1001);

    -- Get total balance for a customer
    DECLARE
        v_total_balance NUMBER;
    BEGIN
        v_total_balance := AccountOperations.GetTotalBalance(1);
        DBMS_OUTPUT.PUT_LINE('Total Balance: ' || v_total_balance);
    END;
END;
/



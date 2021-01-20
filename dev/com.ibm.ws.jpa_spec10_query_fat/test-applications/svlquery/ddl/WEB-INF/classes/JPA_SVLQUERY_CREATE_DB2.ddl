CREATE TABLE ${schemaname}.OPENJPA_SEQUENCE_TABLE (ID SMALLINT NOT NULL, SEQUENCE_VALUE BIGINT, PRIMARY KEY (ID));

CREATE TABLE ${schemaname}.Jpa2Address (street VARCHAR(30) NOT NULL, city VARCHAR(254), state VARCHAR(254), plusFour VARCHAR(4), zip VARCHAR(5), PRIMARY KEY (street));
CREATE TABLE ${schemaname}.Jpa2Person (id INTEGER NOT NULL, age INTEGER, firstName VARCHAR(20), lastName VARCHAR(20), PRIMARY KEY (id));
CREATE TABLE ${schemaname}.JPAAddressBean (street VARCHAR(40) NOT NULL, city VARCHAR(30), state VARCHAR(20), zip VARCHAR(10), PRIMARY KEY (street));
CREATE TABLE ${schemaname}.JPACustomerPartTab (id INTEGER NOT NULL, name VARCHAR(40), rating INTEGER, PRIMARY KEY (id));
CREATE TABLE ${schemaname}.JPADeptBean (deptno INTEGER NOT NULL, budget REAL, name VARCHAR(40), charityAmount DOUBLE, charityName VARCHAR(40), MGR_EMPID integer, REPORTSTO_DEPTNO INTEGER, PRIMARY KEY (deptno));
CREATE TABLE ${schemaname}.JPAEmpBean (empid integer NOT NULL, bonus DOUBLE, execLevel CHAR(254), hireDate DATE, hireTime TIME, hireTimestamp TIMESTAMP, isManager SMALLINT, name VARCHAR(40), rating INTEGER, salary DOUBLE, DEPT_DEPTNO INTEGER, HOME_STREET VARCHAR(40), WORK_STREET VARCHAR(40), PRIMARY KEY (empid));
CREATE TABLE ${schemaname}.JPALineItemPartTab (lid INTEGER NOT NULL, cost DOUBLE, quantity INTEGER, ORDER_OID INTEGER, PRODUCT_PID INTEGER, PRIMARY KEY (lid));
CREATE TABLE ${schemaname}.JPAOrderPartTab (oid INTEGER NOT NULL, amount DOUBLE, delivered SMALLINT, CUSTOMER_ID INTEGER, PRIMARY KEY (oid));
CREATE TABLE ${schemaname}.JPAPartTab (partno INTEGER NOT NULL, name VARCHAR(40), PARTTYPE VARCHAR(31), assemblyCost DOUBLE, massIncrement DOUBLE, cost DOUBLE, mass DOUBLE, PRIMARY KEY (partno));
CREATE TABLE ${schemaname}.JPAProductPartTab (pid INTEGER NOT NULL, backorder INTEGER, description VARCHAR(40), inventory INTEGER, SUPPLIER_ID INTEGER, PRIMARY KEY (pid));
CREATE TABLE ${schemaname}.JPAProjectBean (projid integer NOT NULL, budget DOUBLE, cost DOUBLE, description VARCHAR(40), durationDays SMALLINT, name VARCHAR(40), personMonths SMALLINT, startTime BIGINT, DEPT_DEPTNO INTEGER, PRIMARY KEY (projid));
CREATE TABLE ${schemaname}.JPASupplierparttab (sid INTEGER NOT NULL, name VARCHAR(40), PRIMARY KEY (sid));
CREATE TABLE ${schemaname}.JPASupplierparttab_JPAPartTab (SUPPLIERS_SID INTEGER, SUPPLIES_PARTNO INTEGER);
CREATE TABLE ${schemaname}.JPATaskBean (taskid INTEGER NOT NULL, cost DOUBLE, description VARCHAR(40), name VARCHAR(40), PROJECT_PROJID integer, PRIMARY KEY (taskid));
CREATE TABLE ${schemaname}.JPATaskBean_JPAEmpBean (TASKS_TASKID INTEGER, EMPS_EMPID integer);
CREATE TABLE ${schemaname}.JPATypeTestBean (id INTEGER NOT NULL, ageofUniverse BIGINT, bigbytes BLOB, busPass BLOB, deficitUSA DOUBLE, i1 SMALLINT, i2 SMALLINT, i4 INTEGER, i8 BIGINT, iboolean SMALLINT, ibytes BLOB, ic CHAR(254), ichars VARCHAR(254), idouble DOUBLE, ifloat REAL, name VARCHAR(254), o1 SMALLINT, o2 SMALLINT, o4 INTEGER, o8 BIGINT, oboolean SMALLINT, obytes BLOB, oc CHAR(254), ochars VARCHAR(254), odouble DOUBLE, ofloat REAL, payScale VARCHAR(20), sdate DATE, status SMALLINT, stime TIME, stimestamp TIMESTAMP, ucalendar TIMESTAMP, udate TIMESTAMP, PRIMARY KEY (id));
CREATE TABLE ${schemaname}.JPAUsagePartTab (id INTEGER NOT NULL, quantity INTEGER, CHILD_PARTNO INTEGER, PARENT_PARTNO INTEGER, PRIMARY KEY (id));
CREATE TABLE ${schemaname}.JPAXYZ (id INTEGER NOT NULL, age INTEGER, firstName VARCHAR(20), lastName VARCHAR(20), PRIMARY KEY (id));
CREATE TABLE ${schemaname}.Person_residences (PERSON_ID INTEGER, street VARCHAR(30), city VARCHAR(254), state VARCHAR(254), plusFour VARCHAR(4), zip VARCHAR(5));

CREATE INDEX I_JPDPTBN_MGR ON ${schemaname}.JPADeptBean (MGR_EMPID);
CREATE INDEX I_JPDPTBN_REPORTSTO ON ${schemaname}.JPADeptBean (REPORTSTO_DEPTNO);
CREATE INDEX I_JPMPBEN_DEPT ON ${schemaname}.JPAEmpBean (DEPT_DEPTNO);
CREATE INDEX I_JPMPBEN_HOME ON ${schemaname}.JPAEmpBean (HOME_STREET);
CREATE INDEX I_JPMPBEN_WORK ON ${schemaname}.JPAEmpBean (WORK_STREET);
CREATE INDEX I_JPLNTTB_ORDER ON ${schemaname}.JPALineItemPartTab (ORDER_OID);
CREATE INDEX I_JPLNTTB_PRODUCT ON ${schemaname}.JPALineItemPartTab (PRODUCT_PID);
CREATE INDEX I_JPRDTTB_CUSTOMER ON ${schemaname}.JPAOrderPartTab (CUSTOMER_ID);
CREATE INDEX I_JPPRTTB_DTYPE ON ${schemaname}.JPAPartTab (PARTTYPE);
CREATE INDEX I_JPPRTTB_SUPPLIER ON ${schemaname}.JPAProductPartTab (SUPPLIER_ID);
CREATE INDEX I_JPPRTBN_DEPT ON ${schemaname}.JPAProjectBean (DEPT_DEPTNO);
CREATE INDEX I_JPSPTTB_ELEMENT ON ${schemaname}.JPASupplierparttab_JPAPartTab (SUPPLIES_PARTNO);
CREATE INDEX I_JPSPTTB_SUPPLIERS_SID ON ${schemaname}.JPASupplierparttab_JPAPartTab (SUPPLIERS_SID);
CREATE INDEX I_JPTSKBN_PROJECT ON ${schemaname}.JPATaskBean (PROJECT_PROJID);
CREATE INDEX I_JPTSPBN_ELEMENT ON ${schemaname}.JPATaskBean_JPAEmpBean (EMPS_EMPID);
CREATE INDEX I_JPTSPBN_TASKS_TASKID ON ${schemaname}.JPATaskBean_JPAEmpBean (TASKS_TASKID);
CREATE INDEX I_JPSGTTB_CHILD ON ${schemaname}.JPAUsagePartTab (CHILD_PARTNO);
CREATE INDEX I_JPSGTTB_PARENT ON ${schemaname}.JPAUsagePartTab (PARENT_PARTNO);
CREATE INDEX I_PRSNNCS_PERSON_ID ON ${schemaname}.Person_residences (PERSON_ID);
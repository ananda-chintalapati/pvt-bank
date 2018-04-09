CREATE DATABASE IF NOT EXISTS test_bank;

USE test_bank;

CREATE TABLE `release` (
	`RLS_ID` int(10) NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(50) NOT NULL,
	`RLS_DESC` VARCHAR(250),
	PRIMARY KEY (`RLS_ID`)
);

CREATE TABLE `test_suite` (
	`TEST_SUITE_ID` INT(10) NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(25) NOT NULL,
	`RELEASE_ID` INT(10) NOT NULL,
	PRIMARY KEY (`TEST_SUITE_ID`)
);

CREATE TABLE `test_case` (
 	`ID` int(10) NOT NULL AUTO_INCREMENT,
 	`TEST_SUITE_ID` INT(10) NOT NULL,
 	`DESCRIPTION` VARCHAR(250) NOT NULL,
 	`COMPONENT` VARCHAR(100) NOT NULL,
 	`STEPS` VARCHAR(1000) NOT NULL,
 	`EXECUTED_BY` VARCHAR(20) NOT NULL,
 	`EXECUTION_DT` TIMESTAMP,
 	`STATUS` VARCHAR(20),
 	`RESULT` VARCHAR(20),
 	PRIMARY KEY (`ID`)
);

INSERT INTO release (RLS_ID, NAME, RLS_DESC) VALUES(1, "BASE_RLS", "SAMPLE DESCRIPTION");
INSERT INTO release (RLS_ID, NAME, RLS_DESC) VALUES(2, "TEST_RLS", "TEST DESCRIPTION");

INSERT INTO test_suite (TEST_SUITE_ID, NAME, RELEASE_ID) VALUES(1, "MOBILE_TS", 1);
INSERT INTO test_suite (TEST_SUITE_ID, NAME, RELEASE_ID) VALUES(2, "TABLET_TS", 1);
INSERT INTO test_suite (TEST_SUITE_ID, NAME, RELEASE_ID) VALUES(3, "MOBILE_TS", 2);
INSERT INTO test_suite (TEST_SUITE_ID, NAME, RELEASE_ID) VALUES(4, "TABLET_TS", 2);

INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(1, 1, "TS_1", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(2, 1, "TS_2", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(3, 1, "TS_3", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "OPEN", "UNKNOWN");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(4, 1, "TS_4", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "OPEN", "UNKNOWN");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(5, 2, "TS_5", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "FAIL");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(6, 2, "TS_6", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "FAIL");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(7, 2, "TS_7", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(8, 2, "TS_8", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(9, 3, "TS_9", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(10, 3, "TS_10", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(11, 3, "TS_11", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "FAIL");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(12, 3, "TS_12", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "FAIL");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(13, 4, "TS_13", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "OPEN", "UNKNOWN");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(14, 4, "TS_14", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "OPEN", "UNKNOWN");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(15, 4, "TS_15", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "FAIL");
INSERT INTO test_case (ID, TEST_SUITE_ID, DESCRIPTION, COMPONENT, STEPS, EXECUTED_BY, EXECUTION_DT, STATUS, RESULT) VALUES(16, 4, "TS_16", "BILLING", "1. LOGIN 2. SELECT DEVICE 3. GOTO BILLING", "AB1234", "2015-11-01", "COMPLETE", "PASS");
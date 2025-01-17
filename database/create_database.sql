DROP DATABASE IF EXISTS transactions_db;
CREATE DATABASE IF NOT EXISTS transactions_db;

USE transactions_db;

CREATE TABLE `transactions` (
    `transaction_id`        INT NOT NULL AUTO_INCREMENT,
    `date`                  DATE NOT NULL,
    `time`                  TIME NOT NULL,
    `amount`                DECIMAL(8,2),
    `vendor`                VARCHAR(50),
    `description`           VARCHAR(50),

    PRIMARY KEY (`transaction_id`)
)
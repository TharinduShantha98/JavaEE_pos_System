DROP DATABASE IF EXISTS  JavaEEThogakade;
CREATE  DATABASE IF NOT EXISTS  JavaEEThogakade;
USE JavaEEThogakade;



DROP TABLE IF EXISTS  customer;
CREATE  TABLE IF NOT EXISTS customer(
  customerId VARCHAR (15),
  firstName VARCHAR (45),
  lastName VARCHAR (45),
  address TEXT,
  email VARCHAR (45),
  TelNo VARCHAR (15),
  CONSTRAINT PRIMARY KEY (customerId)
);


SHOW TABLES;
DESCRIBE customer;

DROP TABLE  IF EXISTS  `order`;
CREATE  TABLE IF NOT EXISTS  `order`(
    orderId VARCHAR (15),
    customerId VARCHAR (15),
    totalSale  DECIMAL (10,2),
    profit DECIMAL (10,2),
    dateAndTime VARCHAR (30),
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN  KEY (customerId) REFERENCES  customer(customerId) ON DELETE CASCADE  ON UPDATE CASCADE
);


SHOW TABLES;
DESC  `order`;










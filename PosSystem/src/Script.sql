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






DROP TABLE  IF EXISTS item;
CREATE TABLE IF NOT EXISTS item(
    itemCode VARCHAR(15),
    itemName VARCHAR(45),
    unitPrice DECIMAL (10,2),
    buyingPrice DECIMAL (10,2),
    packSise VARCHAR(15),
    itemQty DOUBLE,
    CONSTRAINT PRIMARY KEY (itemCode)
);


SHOW TABLES;


DROP TABLE IF EXISTS `order Detail`;
CREATE  TABLE  IF NOT EXISTS  `order Detail`(
    orderId VARCHAR (15),
    itemCode VARCHAR (15),
    saleQTY double,
    saleItemPrice  double,
    profit double,

    CONSTRAINT PRIMARY KEY (orderId, itemCode),
    CONSTRAINT FOREIGN KEY (orderId)  REFERENCES `order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES  item(itemCode) ON DELETE  CASCADE ON UPDATE CASCADE


);


SHOW tables;















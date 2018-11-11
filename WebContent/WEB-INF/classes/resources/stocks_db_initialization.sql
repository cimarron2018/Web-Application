/**
 * Create the stocks database that is used by the DatabaseStockService 
 * */

DROP TABLE IF EXISTS quotes CASCADE;
CREATE TABLE quotes(
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(4) NOT NULL,
   time DATETIME NOT NULL,
   price DECIMAL NOT NULL,
   PRIMARY KEY ( id )
);

INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 00:00:01','85.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-03 00:00:01','527.35');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2000-01-01 00:00:01','118.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 00:00:01','363.21');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-01 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-02 00:00:01','117.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-03 00:00:01','116.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-04 00:00:01','115.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-05 00:00:01','114.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-06 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-07 00:00:01','120.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-08 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-09 00:00:01','117.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-10 00:00:01','116.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-11 00:00:01','115.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-12 00:00:01','114.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-13 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-14 00:00:01','120.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-15 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-16 00:00:01','117.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-17 00:00:01','116.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-18 00:00:01','115.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-19 00:00:01','114.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-20 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-21 00:00:01','120.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-22 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-23 00:00:01','117.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-24 00:00:01','116.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-25 00:00:01','115.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-26 00:00:01','114.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-27 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-28 00:00:01','120.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-29 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-30 00:00:01','117.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-01-31 00:00:01','116.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-02-01 00:00:01','115.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-02-02 00:00:01','114.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-02-03 00:00:01','119.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AAPL','2018-02-04 00:00:01','120.27');
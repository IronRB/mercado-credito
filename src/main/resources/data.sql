INSERT INTO user(name,target,cant,amount_total) VALUES('Robert','NEW',1,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('Carlos','NEW',0,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('Camilo','NEW',6,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('Eliana','NEW',0,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('David','NEW',0,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('Sebastian','NEW',0,0);
INSERT INTO user(name,target,cant,amount_total) VALUES('Laura','NEW',0,0);

INSERT INTO loan(amount,term,user_id,balance,date) VALUES(1000,12,1,1000,'2021-08-05 02:18Z');

INSERT INTO target(AMOUNT_TOTAL_MAX,AMOUNT_TOTAL_MIN,CANT_MAX,CANT_MIN,MAX,RATE,target)
VALUES(99999,0,1,0,500000,0.15,'NEW');
INSERT INTO target(AMOUNT_TOTAL_MAX,AMOUNT_TOTAL_MIN,CANT_MAX,CANT_MIN,MAX,RATE,target)
VALUES(500000,100000,5,2,1000000,0.10,'FREQUENT');
INSERT INTO target(AMOUNT_TOTAL_MAX,AMOUNT_TOTAL_MIN,CANT_MAX,CANT_MIN,MAX,RATE,target)
VALUES(9999999,500001,99,6,500000,0.05,'PREMIUM');

--INSERT INTO payment(amount,date,debt,loan_id) VALUES(85.60637664794922,'2022-05-20T23:47:48-0500',743.18085,2);

COMMIT;
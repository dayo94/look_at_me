CREATE TABLE DAYO(
    NAME VARCHAR2(10) CONSTRAINT NA PRIMARY KEY,
    AGE NUMBER NOT NULL,
    GENDER VARCHAR2(3) NOT NULL
);



INSERT INTO DAYO
VALUES ('이다영',28,'여');

INSERT INTO DAYO
VALUES ('김해림',28,'여');

INSERT INTO DAYO
VALUES ('김토리',13,'남');

INSERT INTO DAYO
VALUES ('김모리',7,'남');

SELECT * FROM DAYO;

DESC DAYO;


ALTER TABLE DAYO
ADD BIRTH VARCHAR2(10);



UPDATE DAYO
    SET BIRTH = '94/05/19'
    WHERE NAME = '이다영';
    
    
UPDATE DAYO
    SET BIRTH = '94/02/12'
    WHERE NAME = '김해림';
    
    
--- ALTER TABLE tablename MODIFY 컬럼명 [데이터타입] 제약조건
--	컬럼의 정보를 변경하며 제약사항을 반영한다
--	NOT NULL, DEFAULT 제약사항을 부여할 때 사용한다

ALTER TABLE DAYO
MODIFY ( BIRTH DEFAULT '모름');

ALTER TABLE DAYO
MODIFY BIRTH DEFAULT NULL;




INSERT INTO DAYO (NAME,AGE,GENDER)
VALUES ('D',1,'12');



DELETE DAYO
WHERE NAME = 'D';

SELECT * FROM DAYO;

ALTER TABLE DAYO
ADD LOVE CHAR(1);

ALTER TABLE DAYO
DROP COLUMN LOVE;



ALTER TABLE DAYO
ADD LOVE CHAR(1) DEFAULT 'X';



UPDATE DAYO
    SET LOVE = 'O'
    WHERE NAME IN ('이다영', '김해림');

SELECT * FROM DAYO;

UPDATE DAYO
    SET AGE = 27, BIRTH = '95/02/12'
    WHERE NAME = '김해림';
    



SELECT * FROM patient; --환자
SELECT * FROM treat; --진료
SELECT * FROM doctor; --의사
SELECT * FROM department; --진료과
SELECT * FROM inpatient; --입원

-----------

--1. 2013년 1월 25일 내원한 환자중 다음의 정보조회
-- 진료과명, 진료의사명, 접수시간, 환자번호, 환자이름, 생년월일, 성별
--(진료과, 진료의, 접수시간으로 내림차순 정렬)

SELECT DE.DEP_NAME, D.DOC_NAME, T.TRT_RECEIPT, P.PAT_CODE, P.PAT_NAME,
    P.PAT_BIRTH, P.PAT_GENDER
FROM patient P, TREAT T, DOCTOR D, DEPARTMENT DE
WHERE P.PAT_CODE = T.PAT_CODE
AND T.DOC_CODE = D.DOC_CODE
AND D.DEP_CODE = DE.DEP_CODE
AND TRT_YEAR = 2013
AND TRT_DATE = 0125
ORDER BY DE.DEP_NAME, D.DOC_NAME DESC ,T.TRT_RECEIPT DESC ;


--진료과, 진료의, 접수시간으로 내림차순 정렬

--간담췌외과	한선생	1430	6	오창규	19730923	M
--간담췌외과	한선생	1030	39	고기리	20021104	M
--고관절외과	강선생	1250	38	도진	    20020523	F
--비과	        강선생	1330	33	권이나	19751107	F
--비과      	구선생	1630	43	허민지	19971115	F
--성형외과    	김선생	0900	3	박환자	19890330	F
--소아외과    	최선생	1000	15	한성	    20030817	F
--소아정형외과	한선생	1430	19	우별희	20010105	F
--소아정형외과	한선생	1100	17	배창환	20030407	M
--소아정형외과	한선생	0900	45	황보희라	19961012	F
--소화기내과	박선생	1010	30	권환자	20040526	M
--소화기내과	황선생	1030	39	고기리	20021104	M
--소화기내과	황선생	0900	3	박환자	19890330	F
--알레르기내과	강선생	1000	40	유희애	20070929	F
--이과	        박선생	0930	21	송주희	19910225	F
--족부외과    	조선생	1400	2	김환자	19710108	M
--족부외과    	조선생	1330	31	김환자	19810804	M
--혈액내과    	하선생	1600	36	허환자	20030514	F
--호흡기내과	김선생	1400	26	왕주희	20090404	F
--호흡기내과	이선생	1400	2	김환자	19710108	M





--2. 2013년 12월 25일 내원한 환자의 다음 정보조회
--내원일자, 환자번호, 환자이름, 생년월일, 성별
--*단 2014년 이후로 입원했던적이 있다면 입원일자, 입원시간도 출력
--(진료시간 기준 정렬)
-- OUTER JOIN (+)

SELECT * FROM patient; --환자
SELECT * FROM treat; --진료
SELECT * FROM doctor; --의사
SELECT * FROM department; --진료과
SELECT * FROM inpatient; --입원
---------------------------------------------------------
--내원일자, 환자번호, 환자이름, 생년월일, 성별
--*단 2014년 이후로 입원했던적이 있다면 입원일자, 입원시간도 출력
--(진료시간 기준 정렬)
SELECT T.TRT_DATE, P.PAT_CODE, P.PAT_NAME, P.PAT_BIRTH, P.PAT_GENDER, I.INP_DATE, I.INP_TIME 
FROM PATIENT P, TREAT T,
( SELECT * FROM INPATIENT
    WHERE inp_year >= 2014 
) I 
WHERE T.PAT_CODE = P.PAT_CODE
AND P.PAT_CODE = I.PAT_CODE(+)
AND T.TRT_YEAR = '2013'
AND T.TRT_DATE = '1225'
ORDER BY TRT_TIME;


--
--
--1225	34	송환자	19700713	F			
--1225	49	광성	20070627	M			
--1225	12	성오성	20050119	M	2014	0123	1040
--1225	22	김도연	19921125	F			
--1225	40	유희애	20070929	F			
--1225	16	김성민	20060903	M			
--1225	5	오대식	19801222	M			
--1225	1	오환자1	19831203	F			
--1225	25	차은희	19880320	F			
--1225	15	한성	20030817	F	2015	0516	1450
--1225	9	황지훈	19750603	M			
--1225	29	강대희	20020402	M			
--1225	48	곽휴	20000816	M	2014	1212	1450
--1225	46	정덕하	20020628	M			
--1225	9	황지훈	19750603	M			
--1225	21	송주희	19910225	F			
--1225	39	고기리	20021104	M	2015	0425	1350
--1225	33	권이나	19751107	F	2014	1002	1300
--1225	13	남궁오성	20041211	M			
--1225	32	박환자	19941117	M	2015	0303	1150
--1225	7	오환자3	19790102	M	2015	1026	1330
--1225	49	광성	20070627	M			
--1225	50	김애플	20001221	M	2015	0623	1050
--1225	44	송학	19831129	M	2014	0602	1340
--1225	29	강대희	20020402	M			
--1225	12	성오성	20050119	M	2014	0123	1040
--1225	42	지유	19881010	F			
--1225	3	박환자	19890330	F			


-- 3. 1번을 ANSI 코드로 작성하시오

SELECT DE.DEP_NAME, D.DOC_NAME, T.TRT_RECEIPT, P.PAT_CODE, P.PAT_NAME,
    P.PAT_BIRTH, P.PAT_GENDER
FROM patient P
INNER JOIN TREAT T
ON P.PAT_CODE = T.PAT_CODE
INNER JOIN DOCTOR D
ON T.DOC_CODE = D.DOC_CODE
INNER JOIN DEPARTMENT DE
ON D.DEP_CODE = DE.DEP_CODE
AND TRT_YEAR = 2013
AND TRT_DATE = 0125
ORDER BY DE.DEP_NAME, D.DOC_NAME DESC ,T.TRT_RECEIPT DESC ;




-- 4. 2번을 ANSI 코드로 작성하시오

SELECT T.TRT_DATE, P.PAT_CODE, P.PAT_NAME, P.PAT_BIRTH, P.PAT_GENDER, I.INP_DATE, I.INP_TIME 
FROM PATIENT P
INNER JOIN TREAT T
ON p.pat_code = t.pat_code
LEFT OUTER JOIN  
( SELECT * FROM INPATIENT
    WHERE inp_year >= 2014 
) I 
ON P.PAT_CODE = I.PAT_CODE
WHERE T.TRT_YEAR = '2013'
AND T.TRT_DATE = '1225'
ORDER BY TRT_TIME;

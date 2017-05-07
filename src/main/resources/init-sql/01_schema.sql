CREATE TABLE deptInfo (
  deptID            VARCHAR2(32),
  parentDeptID      VARCHAR2(32),
  deptName          VARCHAR2(128)
);

CREATE TABLE personInfo (
  personID          VARCHAR2(32),
  deptID            VARCHAR2(32),
  personName        VARCHAR2(64),
  personTel         VARCHAR2(128)
);

CREATE TABLE userInfo (
  userID            VARCHAR2(32),
  personID          VARCHAR2(32),
  deptID            VARCHAR2(32),
  userName          VARCHAR2(64),
  password          VARCHAR2(64)
);

CREATE TABLE canteenInfo (
  canteenID         VARCHAR2(32),
  personID          VARCHAR2(32),
  deptID            VARCHAR2(32),
  canteenDate       VARCHAR2(20),
  moneyNum          VARCHAR2(4)
);
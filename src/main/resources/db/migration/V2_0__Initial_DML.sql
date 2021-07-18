INSERT INTO employee(name, started, surname, identity) VALUES ('burak', TO_DATE('2017-11-24','yyyy-MM-dd'), 'koçak', 18589790120);
INSERT INTO annual_leave set identity=18589790120;
INSERT INTO employee(name, started, surname, identity) VALUES ('bora', TO_DATE('2012-05-14','yyyy-MM-dd'), 'demir', 98576495120);
INSERT INTO annual_leave set identity=98576495120;
INSERT INTO employee(name, started, surname, identity) VALUES ('gonca', TO_DATE('2008-08-05','yyyy-MM-dd'), 'çalışkan', 81367031268);
INSERT INTO annual_leave set identity=81367031268;
INSERT INTO employee(name, started, surname, identity) VALUES ('caner', TO_DATE('2021-04-05','yyyy-MM-dd'), 'örnek', 34195478149);
INSERT INTO annual_leave set identity=34195478149;

INSERT INTO approval_table(topic, dto_binder, status, note) VALUES ('ANNUAL_LEAVE', '{"identity": 18589790120, "fromDate": "' || TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd') || '", "toDate": "' || TO_DATE(DATEADD(day,10,CURRENT_TIMESTAMP),'yyyy-MM-dd') || '"}', 'CREATED', 'Yıllık izin talebi.');
INSERT INTO approval_table(topic, dto_binder, status, note) VALUES ('ANNUAL_LEAVE', '{"identity": 98576495120, "fromDate": "' || TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd') || '", "toDate": "' || TO_DATE(DATEADD(day,10,CURRENT_TIMESTAMP),'yyyy-MM-dd') || '"}', 'CREATED', 'Yıllık izin talebi.');
INSERT INTO approval_table(topic, dto_binder, status, note) VALUES ('ANNUAL_LEAVE', '{"identity": 81367031268, "fromDate": "' || TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd') || '", "toDate": "' || TO_DATE(DATEADD(day,10,CURRENT_TIMESTAMP),'yyyy-MM-dd') || '"}', 'CREATED', 'Yıllık izin talebi.');
INSERT INTO approval_table(topic, dto_binder, status, note) VALUES ('ANNUAL_LEAVE', '{"identity": 34195478149, "fromDate": "' || TO_DATE(CURRENT_TIMESTAMP,'yyyy-MM-dd') || '", "toDate": "' || TO_DATE(DATEADD(day,10,CURRENT_TIMESTAMP),'yyyy-MM-dd') || '"}', 'CREATED', 'Yıllık izin talebi.');
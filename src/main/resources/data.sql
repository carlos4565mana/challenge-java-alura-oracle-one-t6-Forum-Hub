INSERT INTO USERS( name,email, password) VALUES('Carlos', 'carlos@email.com', '123456');
INSERT INTO USERS( name,email, password) VALUES('Cesar', 'cesar@email.com', '123456');
INSERT INTO USERS( name,email, password) VALUES('Alana', 'alana@email.com', '123456');
INSERT INTO USERS( name,email, password) VALUES('Daiane', 'daiane@email.com', '123456');

INSERT INTO COURSE(category, name,status) VALUES('Programacao','React','true');
INSERT INTO COURSE(category, name,status ) VALUES('Linguas','Ingles','true');
INSERT INTO COURSE(category, name,status) VALUES('Banco de Dados','Postgres','true');
INSERT INTO COURSE(category, name,status) VALUES('Programacao','Java','true');


INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 1, 3);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 2, 2);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 3, 3);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 1, 1);
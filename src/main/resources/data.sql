INSERT INTO USERS( name,email, password,enabled,roles) VALUES('Carlos', 'carlos@email.com', '7c4a8d09ca3762af61e59520943dc26494f8941b',true,'admin user');
INSERT INTO USERS( name,email, password,enabled,roles) VALUES('Cesar', 'cesar@email.com', '7c4a8d09ca3762af61e59520943dc26494f8941b',true,'user');
INSERT INTO USERS( name,email, password,enabled,roles) VALUES('Alana', 'alana@email.com', '7c4a8d09ca3762af61e59520943dc26494f8941b',true,'user');
INSERT INTO USERS( name,email, password,enabled,roles) VALUES('Daiane', 'daiane@email.com', '7c4a8d09ca3762af61e59520943dc26494f8941b',true,'user');

INSERT INTO COURSE(category, name,enabled) VALUES('Programacao','React','true');
INSERT INTO COURSE(category, name,enabled ) VALUES('Linguas','Ingles','true');
INSERT INTO COURSE(category, name,enabled) VALUES('Banco de Dados','Postgres','true');
INSERT INTO COURSE(category, name,enabled) VALUES('Programacao','Java','true');


INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 1, 3);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 2, 2);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 3, 3);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', ' NOT_ANSWERED', 1, 1);

INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('voce da um control', '2019-05-05 20:00:00',2, 2);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('helo world', '2019-04-05 20:00:00',1, 2);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('voce da um control', '2019-06-05 20:00:00',1, 3);

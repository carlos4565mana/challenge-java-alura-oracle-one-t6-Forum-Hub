INSERT INTO USERS( name,email, password,role) VALUES('Carlos', 'carlos@email.com', '$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','ADMIN');
INSERT INTO USERS( name,email, password,role) VALUES('Cesar', 'cesar@email.com', '$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','USER');


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

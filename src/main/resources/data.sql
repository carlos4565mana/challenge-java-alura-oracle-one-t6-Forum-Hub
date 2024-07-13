/* Todas as senhas = 123 */

INSERT INTO USERS( name,email, password,role) VALUES('Carlos', 'carlos@gmail.com','$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','ADMIN');
INSERT INTO USERS( name,email, password,role) VALUES('Cesar', 'cesar@gmail.com', '$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','USER');
INSERT INTO USERS( name,email, password,role) VALUES('Ana', 'ana@gmail.com', '$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','USER');
INSERT INTO USERS( name,email, password,role) VALUES('Marta', 'marta@gmail.com', '$2a$10$.K8Ol9zpsQ5prA8HGFyQLO2EbbbjGTiGKvowsYzUvFvD.bdAAlVma','USER');


INSERT INTO COURSE(category, name,enabled) VALUES('Programacao','React','true');
INSERT INTO COURSE(category, name,enabled ) VALUES('Linguas','Ingles','true');
INSERT INTO COURSE(category, name,enabled) VALUES('Banco de Dados','Postgres','true');
INSERT INTO COURSE(category, name,enabled) VALUES('Programacao','Java','true');


INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'O que é useState?', '2019-05-05 18:00:00', 'ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Não sei o verbo to be', '2019-05-05 18:00:00', 'ANSWERED', 2, 2);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'O que é join table', '2019-05-05 18:00:00', 'ANSWERED', 3, 3);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto com vite', '2019-05-05 18:00:00', ' NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'O que uma stream em java', '2019-05-05 18:00:00', 'ANSWERED', 2, 4);
INSERT INTO TOPIC(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'O que uma List<> em java', '2019-05-05 18:00:00', 'NOT_ANSWERED', 1, 4);

INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('voce começa  treinar', '2019-05-05 20:00:00',2, 2);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('helo world', '2019-04-05 20:00:00',1, 2);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('combina duas tabelas através de alguma chave ou valor comum entre elas', '2019-06-05 20:00:00',1, 3);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('é um hook fundamental do React JS que permite a criação e manipulação de estado em componentes funcionais', '2019-05-05 20:00:00',4, 1);
INSERT INTO ANSWER(message, create_at, user_id, topic_id) VALUES('é uma sequência de elementos que suporta várias operações para processamento desses elementos', '2019-05-05 20:00:00',1, 5);


drop table email;
drop table usuario;
drop sequence seq_id_usuario;
drop sequence seq_id_email;

select * from usuario
select * from email

create sequence seq_id_usuario;
create sequence seq_id_email;

create table usuario (
	id int,
	usuario varchar(50),
	enderecoemail varchar(50),
	senha varchar(50),
	primary key(id)
);

create table email (
	id int,
	remetente varchar(50),
	destinatario varchar(50),
	corpo text,
	dataEmail varchar(10),
	hora varchar(8),
	id_usuario int,
	id_destinatario int,
	primary key (id),
	foreign key (id_usuario) references usuario (id),
	foreign key (id_destinatario) references usuario(id)
);

insert into usuario values (nextval('seq_id_usuario'), 'Santiago Cardoso', 'santiago@email.com', '123');
insert into usuario values (nextval('seq_id_usuario'), 'Pedro Souza', 'pedro@email.com', 'pedro');
insert into usuario values (nextval('seq_id_usuario'), 'Julia Vieira', 'julia@email.com', 'julia');
insert into usuario values (nextval('seq_id_usuario'), 'Caio Junior Silva', 'caio@email.com', 'caio');
insert into usuario values (nextval('seq_id_usuario'), 'Matheus Fernandes', 'matheus@email.com', 'matheus');
insert into usuario values (nextval('seq_id_usuario'), 'Gabriela de Almeida', 'gabi@email.com', 'gabi');
insert into usuario values (nextval('seq_id_usuario'), 'Junior Santos', 'juninho@email.com', 'juninho123');

insert into email values (nextval('seq_id_email'), 'santiago@email.com', 'pedro@email.com', 'Smi, fsq hme!', '30/06/2023', '21:55:23', 1, 2);
insert into email values (nextval('seq_id_email'), 'santiago@email.com', 'julia@email.com', 'Kje f uwtaf?', '30/06/2023', '14:23:44', 1, 3);
insert into email values (nextval('seq_id_email'), 'julia@email.com', 'santiago@email.com', 'Kgk, hus jog yos, lkf u wak nupk?', '30/06/2023', '03:19:03', 3, 1);
insert into email values (nextval('seq_id_email'), 'caio@email.com', 'juninho@email.com', 'Vp Qbupuov, chtvz zhpy hsnbt kph klzzlz', '30/06/2023', '23:59:23', 4, 3);
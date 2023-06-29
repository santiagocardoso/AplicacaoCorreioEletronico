drop table email
drop table usuario
drop sequence seq_id_usuario;
drop sequence seq_id_email;

select * from usuario
select * from usuario where id = 1
select * from email

create sequence seq_id_usuario;
create sequence seq_id_email;

create table usuario (
	id int,
	usuario varchar(50),
	enderecoemail varchar(50),
	senha varchar(50),
	primary key(id)
)

create table email (
	id int,
	remetente varchar(50),
	destinatario varchar(50),
	corpo text,
	dataEmail varchar(10),
	hora varchar(8),
	id_usuario integer,
	id_destinatario integer,
	primary key (id),
	foreign key (id_usuario) references usuario (id),
	foreign key (id_destinatario) references usuario(id)
)

select nextval('seq_id_usuario');
select nextval('seq_id_endereco');

insert into usuario values (nextval('seq_id_usuario'), 'Santiago', 'santiago@email.com', '123')
insert into usuario values (nextval('seq_id_usuario'), 'Pedro', 'pedro@email.com', '321')
insert into usuario values (nextval('seq_id_usuario'), 'Julia', 'julia@email.com', 'abacate')

insert into email values (nextval('seq_id_email'), 'Santiago', 'Pedro', 'Oie, bom dia!', '28/06/2023', '22:35:51', 1, 2)
insert into email values (nextval('seq_id_email'), 'Pedro', 'Santiago', 'Eae, tudo certo?', '10/07/2023', '14:12:23', 2, 1)
insert into email values (nextval('seq_id_email'), 'Julia', 'Santiago', 'Recebeu o email?', '14/02/2024', '03:23:45', 3, 1)
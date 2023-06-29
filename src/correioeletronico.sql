drop table email
drop table usuario

select * from usuario
select * from email

create table usuario (
	id integer,
	usuario varchar(50),
	enderecoemail varchar(50),
	senha varchar(50),
	primary key(id)
)

create table email (
	id int primary key,
	remetente varchar(50),
	destinatario varchar(50),
	corpo varchar(250),
	dataEmail varchar(10),
	hora varchar(8),
	id_usuario int,
	foreign key (id_usuario) references usuario (id)
)

create sequence seq_id_usuario;
create sequence seq_id_email;

select nextval('seq_id_pessoa');
select nextval('seq_id_endereco');

insert into usuario values (nextval('seq_id_usuario'), 'Santiago', 'santcar7@email.com', '123')
insert into usuario values (nextval('seq_id_usuario'), 'Pedro', 'pedro@email.com', '321')

insert into email values (nextval('seq_id_email'), 'Santiago', 'Pedro', 'Oie, bom dia!', '28/06/2023', '22:35:51', 2)
insert into email values (nextval('seq_id_email'), 'Pedro', 'Santiago', 'Eae, tudo certo?', '10/07/2023', '14:12:23', 1)
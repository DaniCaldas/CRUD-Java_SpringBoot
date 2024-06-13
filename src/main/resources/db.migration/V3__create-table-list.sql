create table list(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao text not null,
    status varchar(50),
    primary key(id)
);
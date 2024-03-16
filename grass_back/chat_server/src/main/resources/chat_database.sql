drop database db_chat;
create database db_chat;
use db_chat;
create table t_user(
    user_id bigint  primary key auto_increment,
    user_name varchar(30) not null
);
create table t_conversation(
    conversation_id bigint  primary key auto_increment,
    conversation_user_id bigint  not null ,
    conversation_create_time bigint not null ,
    conversation_modify_time bigint not null ,
    foreign key (conversation_user_id)
        references t_user(user_id)
                           on delete cascade,
    index(conversation_user_id) ,
    index(conversation_modify_time)

);
create table t_message(
    message_id bigint  primary key auto_increment,
    message_conversation_id bigint  not null ,
    message_create_time bigint not null ,
    message_sender_id bigint  not null ,
    message_receiver_id bigint not null ,
    message_context varchar(1024) not null ,
    message_read tinyint not null ,
    foreign key (message_conversation_id)
                      references t_conversation(conversation_id)
                      on delete cascade,
    index (message_conversation_id),
    index(message_create_time)
);
create table t_friend(
    friend_id bigint primary key auto_increment,
    friend_user_id bigint  not null ,
    friend_add_time bigint not null ,
    foreign key (friend_user_id)
                     references t_user(user_id)
                     on delete cascade,
        index  (friend_user_id)
);



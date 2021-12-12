CREATE DATABASE MEETTDB; -- db 생성

CREATE USER meett@localhost; -- 사용자 생성

GRANT all privileges on  *.* TO meett@localhost; -- 권한부여

ALTER USER 'meett'@'localhost'
identified WITH mysql_native_password BY 'meett12345';
flush privileges;

use meettdb;

select * from tbl_room;
select * from tbl_schedule;
select * from tbl_team;
select * from tbl_team_schedule;
select * from tbl_user;

drop table tbl_team_schedule;
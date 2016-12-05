drop procedure if exists proc_upgrade_2_0;
delimiter && 
create procedure proc_upgrade_2_0()
begin
	declare count int;
	
	select count(*) into count from information_schema.columns where table_name='v_user' and column_name='id';
	if(count > 0) then 
		alter table v_user change column id user_id varchar(100) not null;
	end if;
	
	select count(*) into count from information_schema.columns where table_name='v_user' and column_name='createtime';
	if(count > 0) then 
		alter table v_user change column createtime create_time timestamp not null;
	end if;
	
	select count(*) into count from information_schema.columns where table_name='v_user' and column_name='lastlogtime';
	if(count > 0) then 
		alter table v_user change column lastlogtime last_log_time timestamp not null;
	end if;
	
	select count(*) into count from information_schema.columns where table_name='v_user' and column_name='state';
	if(count > 0) then 
		alter table v_user change column state status int(10) not null;
	end if;
	
	select count(*) into count from information_schema.columns where table_name='v_student' and column_name='id';
	if(count > 0) then 
		alter table v_student change column id student_id varchar(100) not null;
	end if;
	
	select count(*) into count from information_schema.columns where table_name='v_event' and column_name='id';
	if(count > 0) then 
		alter table v_event change column id event_id varchar(100) not null;
	end if;
	
end &&
delimiter;
commit;

call proc_upgrade_2_0;
drop procedure if exists proc_upgrade_2_0;




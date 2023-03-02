INSERT INTO user_tb(email, password, p_info_id, created_at) values('ssar@nate.com', '1234',1, now());
INSERT INTO user_tb(email, password, c_info_id, created_at) values('init@nate.com', '1234',1, now());


INSERT INTO person_info_tb(name,phone,address,created_at) values('쌀', '01000000000','부산 부산진구 부전동 000-00', now());

INSERT INTO company_info_tb(logo, name, number, address, manager_name, manager_phone, created_at) values('/images/logo.png','이닛', '00000000000', '부산 부산진구 부전동 000-00', '이니티', '01000000000', now());

INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('성실한 개발자 구합니다.',1,0,'3000만원','계약직', '09:00:00','18:00:00','2023-02-16 00:00:00','좋은직장','위대한직장',now());
INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('멋진 개발자 구합니다.',1,0,'3000만원','계약직', '09:00:00','18:00:00','2023-03-15 00:00:00','좋은직장','위대한직장',now());
INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('열심히하는 개발자 구합니다.',1,0,'3000만원','계약직', '09:00:00','18:00:00','2023-03-07 00:00:00','좋은직장','위대한직장',now());
INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('백엔드 개발자 구합니다.',1,0,'3000만원','계약직', '09:00:00','18:00:00','2023-03-10 00:00:00','좋은직장','위대한직장',now());

INSERT INTO resume_tb(p_info_id,profile,title,portfolio,self_intro,created_at) values(1,'/images/profile1.jpg','성실한지원자입니다','https://naver.com','저는성실해요',now());

INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(1, 0, 0,'Java,Javascript,Html',now());
INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(0, 1, 0,'Java,Javascript,Sql',now());
INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(0, 2, 0,'Javascript,Sql,Html',now());
INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(0, 3, 0,'Java,Sql,Html',now());
INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(0, 4, 0,'Java,Sql,Android',now());
INSERT INTO skill_tb(p_info_id, post_id, resume_id, skills,created_at) values(0, 0, 1,'Java,Javascript,Android',now());

INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Java', 1, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Javascript', 1, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Sql', 1, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Javascript', 2, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Sql', 2, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Html', 2, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Java', 3, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Sql', 3, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Html', 3, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Java', 4, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Sql', 4, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Android', 4, 0);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Java', 0, 1);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Javascript', 0, 1);
INSERT INTO skill_filter_tb(skill, post_id, resume_id) values('Html', 0, 1);

INSERT INTO person_proposal_tb(p_info_id, post_id, resume_id, status, created_at) values(1, 1, 1, 0 ,now());


commit;
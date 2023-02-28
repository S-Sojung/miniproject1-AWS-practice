INSERT INTO user_tb(email, password, p_info_id, created_at) values('ssar@nate.com', '1234',1, now());
INSERT INTO user_tb(email, password, c_info_id, created_at) values('init@nate.com', '1234',1, now());


INSERT INTO person_info_tb(name,phone,address,created_at) values('쌀', '01000000000','부산 부산진구 부전동 000-00', now());

INSERT INTO company_info_tb(name, number, address, manager_name,manager_phone, created_at) values('이닛', '00000000000','부산 부산진구 부전동 000-00','이니티', '01000000000',now());

INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('성실한 개발자 구합니다.',1,0,'3000만원','계약직',' 09:00:00','18:00:00','2023-03-08 00:00:00','좋은직장','위대한직장',now());
INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('멋진 개발자 구합니다.',1,0,'3000만원','계약직',' 09:00:00','18:00:00','2023-03-08 00:00:00','좋은직장','위대한직장',now());
INSERT INTO job_post_tb(title,c_info_id,career,pay,condition,start_hour,end_hour,deadline,c_intro,job_intro,created_at) values('열심히하는 개발자 구합니다.',1,0,'3000만원','계약직',' 09:00:00','18:00:00','2023-03-08 00:00:00','좋은직장','위대한직장',now());

INSERT INTO resume_tb(p_info_id,profile,title,portfolio,self_intro,created_at) values(1,'/images/profile1.jpg','성실한지원자입니다','https://naver.com','저는성실해요',now());

INSERT INTO skill_tb(p_info_id,skills,created_at) values(1,'Java,Javascript,Html',now());
INSERT INTO skill_tb(post_id,skills,created_at) values(1,'java,javascript,c++',now());
INSERT INTO skill_tb(resume_id,skills,created_at) values(1,'java,javascript,c++',now());

commit;
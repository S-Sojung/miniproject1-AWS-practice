# 구인구직 프로젝트

## 기술스택
- JDK 11
- Springboot 2.7.8
- MyBatis
- 테스트 h2 디비
- 프로덕션(배포) MySQL 디비
- JSP
- Redis

## 모델링
### 1단계
- user 로그인 정보
    - id, username, password (DTO로 만들어서)
- person_info (개인 정보)
- company_info (기업 정보)
    - 둘의 상세 내용이 다르기 때문에 아예 구분(회원가입 부분에서..)
- 직무 테이블 (skill)
- job_post (구인 공고)
- resume (이력서)
### 2단계
- user_proposal 
- proposla_pass
- skill_filter
### 3단계
- person_scrap (개인 관심)
- company_scrap
- person_customer_service
- company_customer_service

## 기능정리
### 1단계 (완료)
- 개인 
    - 회원가입, 로그인, 개인정보 보기, 수정
    - 이력서 등록, 보기, 수정, 삭제
    - 구인 공고 메인, 디테일
- 기업  
    - 회원가입, 로그인, 개인정보 보기, 수정
    - 공고 등록, 보기, 수정, 삭제
### 2단계  (완료)
- 개인
    - 공고 마감일 보기 및 정렬, 추천 공고 띄우기
    - 이력서 지원하기 
- 기업
    - 공고 마감일 보기:삭제 권고
    - 추천 구직자 이력서 보기
    - 제출된 이력서 보기 , 합불합 표시 및 합격시 메시지 남기기
### 3단계  (완료)
- Redis 설정
- 로그인 회원가입시 Sha256 + salt 이용하기
- JUnit Test 
- 개인
    - 공고 스크랩 하기, 삭제
    - 이력서 공개 비공개 설정
- 기업
    - 구직자 이력서 스크랩 하기, 삭제
### 4단계
- 페이징
- 기업에 대한 평점
- 메인 구인 공고 필터링 
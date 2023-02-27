<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
 <div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/company/info" class="list-group-item" style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item hs_list_effect">공고 관리</a>
            <a href="/company/getResume" class="list-group-item">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item ">스크랩한 유저</a>
        </div>

        <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <h1 class="hs_line">등록한 공고</h1>
                <hr>
                <div class="jh_resume mt-5">
                    <button class="jh_resume_button mb-5 rounded bg-light" onclick="location.href=`/person/savePost`;"">➕ 새로운 공고
                        등록</button>
                    <div class="jh_resume_content mt-5 mb-3" style="display: flex; justify-content: space-between">
                        백엔드 개발자 구합니다?
                        <button type="button" class="btn btn-secondary">삭제</button>
                    </div>
                    <div class="jh_resume_content" style="display: flex; justify-content: space-between">
                        ?
                        <button type="button" class="btn btn-secondary">삭제</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="../layout/footer.jsp" %>
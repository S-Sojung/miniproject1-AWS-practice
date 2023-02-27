<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

<div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/person/info" class="list-group-item" style="width: 150px;">회원 정보</a>
            <a href="/person/resumes" class="list-group-item">이력서 관리</a>
            <a href="/person/scrap" class="list-group-item">스크랩</a>
            <a href="/person/history" class="list-group-item hs_list_effect">지원 이력</a>
        </div>

        <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <div class="d-flex justify-content-between">
                    <h1 class="hs_line">지원한 이력서</h1>
                </div>
                <hr>

                <div class="container mb-5 mt-5 w-100">
                    <table class="table table-hover">
                        <tr class=" table-dark">
                            <th class="col-4 px-2">&nbsp &nbsp 지원 공고</th>
                            <th class="col-3 text-center">회사명</th>
                            <th class="col-2 text-center">마감일자</th>
                            <th class="col-2 text-center">합격 여부</th>
                        </tr>
                        <tr>
                            <td> &nbsp &nbsp <a href="#">백엔드 개발자</a></td>
                            <td class="text-center">카카오</td>
                            <td class="text-center">2023.12.31</td>
                            <td class="text-center">합격</td>
                        </tr>
                        <tr>
                            <td> &nbsp &nbsp <a href="#">프론트 개발자</a></td>
                            <td class="text-center">겟인데어</td>
                            <td class="text-center">2023.12.31</td>
                            <td class="text-center">합격</td>
                        </tr>
                        <tr>
                            <td> &nbsp &nbsp <a href="#">풀스택 개발자 구합니다</a></td>
                            <td class="text-center">소정닷컴</td>
                            <td class="text-center">2023.12.31</td>
                            <td class="text-center">불합격</td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../layout/footer.jsp" %>
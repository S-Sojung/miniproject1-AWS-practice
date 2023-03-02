<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

<div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/company/info" class="list-group-item " style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item">공고 관리</a>
            <a href="/company/getResume" class="list-group-item hs_list_effect">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item ">스크랩한 유저</a>
        </div>

        <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <div class="d-flex justify-content-between">
                    <h1 class="hs_line">kAKAO에 도착한 이력서를 검토해보세요💌</h1>
                </div>
                <hr>

                <div class="container mb-5 mt-5 w-100">
                    <table class="table table-hover">
                        <tr class="table-dark">
                            <th class="col-3 px-2">&nbsp &nbsp 지원 공고</th>
                            <th class="col-2">이름</th>
                            <th class="col-5">이력서</th>
                            <th class="col-3">지원날짜</th>
                        </tr>
                        <c:forEach items="${companyProposalList}" var="proposal">
                        <tr>
                            <td> &nbsp &nbsp ${proposal.ptitle}</td>
                            <td>${proposal.name}</td>
                            <td><a href="/company/resumeDetail/${proposal.resumeId}" class="text-decoration-none">${proposal.rtitle}</a></td>
                            <td>${proposal.createdAt}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp" %>
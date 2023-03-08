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
                        <c:forEach items="${personProposalList}" var="proposal">
                            
                            <tr class="${proposal.status==1? 'table-info':(proposal.status==-1?'table-secondary':'')}">
                                <td> &nbsp &nbsp <a href="/person/detail/${proposal.postId}"  style="text-decoration: none;">${proposal.title}</a></td>
                                <td class="text-center">${proposal.name}</td>
                                <td class="text-center">${proposal.deadline}</td>
                                <td class="text-center">
                                <c:choose>
                                    <c:when test="${proposal.status==0}">
                                        대기중
                                    </c:when>
                                    <c:when test="${proposal.status==1}">
                                        <a class="link-primary" data-bs-toggle="modal" data-bs-target="#exampleModal-${proposal.id}">
                                        합격
                                        </a>
                                            <!-- Modal -->
                                        <div class="modal fade" id="exampleModal-${proposal.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">합격</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <c:forEach items="${proposalPassList}" var="propo">
                                                <c:if test="${propo.PProposalId==proposal.id}" >
                                                    ${propo.comment}
                                                </c:if>
                                                </c:forEach>
                                            </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        불합격
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>



    <%@ include file="../layout/footer.jsp" %>
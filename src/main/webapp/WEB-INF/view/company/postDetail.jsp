<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item hs_list_effect shadow">공고 관리</a>
                <a href="/company/getResume" class="list-group-item shadow">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item shadow">스크랩한 유저</a>
            </div>

            <div class="justify-content-center  mx-2 pb-4 w-100">
               
                    <div class="border border-tertiary p-5 rounded shadow">
                        <div class="d-flex my-4 justify-content-center">
                            <h1>${post.title}</h1>
                        </div>
                            <hr>
        
                        <div class="px-5">

                            <div class="pt-2 mt-3">
                                <div class="border border-tertiary pt-3 d-inline-flex me-3 mb-3 w-100">
                                    <table class="table table-borderless pt-5 ms-3">
                                        <tr class="pb-5">
                                            <th>지원 자격</th>
                                            <td>
                                            <c:choose>
                                               <c:when test="${post.career==0}">
                                                신입
                                               </c:when>
                                            
                                               <c:otherwise>
                                               ${post.career}년차
                                               </c:otherwise>
                                            </c:choose>
                                            </td>
                                            <th>연봉</th>
                                            <td>${post.pay}</td>
                                        </tr>
                                        <tr>
                                            <th>근무 조건</th>
                                            <td>${post.condition}</td>
                                            <th>근무 시간</th>
                                            <td class="d-flex">${post.startHour} ~ ${post.endHour}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="mt-4">
                                    <h5><b>마감 일자</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        ${post.deadline}
                                    </div>
                                </div>
                                <div class="mt-4">
                                    <h5><b>기업 소개</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        ${post.CIntro}
                                    </div>
                                </div>

                                <div class="mt-4">
                                    <h5><b>업무 소개</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        ${post.jobIntro}
                                    </div>
                                </div>

                                <div class="mt-4">
                                    <h5><b>기술/자격 조건</b></h5>
                                    <div class="jh_resume_skill">
                                        <div>
                                            <ul>
                                                <c:forEach items="${skills}" var="skill">
                                                    <li>${skill}</li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-4 mb-4">
                                    <h5><b>기업 정보</b></h5>
                                    <div class="border border-tertiary ps-5 pe-2 pt-3 d-inline-flex mb-3 w-100">
                                        <table class="table table-borderless pt-5 ms-5">
                                            <tr class="pb-5">
                                                <th>사원수</th>
                                                <td>${company.size}</td>
                                                <th>대표자</th>
                                                <td>${company.bossName}</td>
                                            </tr>
                                            <tr>
                                                <th>설립년도</th>
                                                <td>${company.cyear}</td>
                                                <th>전화번호</th>
                                                <td>${company.managerPhone}</td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td>${company.address}</td>
                                                <th>이메일</th>
                                                <td>${principal.email}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="text-center mt-5 mb-5">
                                    <button type="submit" class="btn btn-dark w-50" onclick="location.href='/company/updatePostForm/${post.id}';">수정 하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
               
            </div>
        </div>
        <script></script>
        <%@ include file="../layout/footer.jsp" %>
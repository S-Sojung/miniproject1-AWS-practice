<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container justify-content-center mt-4">
            <div class="d-flex justify-content-between w-100">
                <div class="border hs_content_box w-100 mx-2 mt-4 rounded">
                    <div class="px-5 pt-5 pb-2">
                        <h6>${company.name}</h6>
                    </div>
                    <div class="px-5">
                        <h3>${post.title}</h3>
                        <hr class="d-inline-flex w-100">

                        <div class="pt-2 mt-3">
                            <div class="border border-tertiary px-3 pt-3 d-inline-flex ms-2 me-3 mb-3 w-100">
                                <table class="table table-borderless pt-5 ms-5">
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
                                <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100" id="deadline">
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
                        </div>
                    </div>
                </div>

                <div>
                    <div class="card mt-4 p-4 ms-2" style="width:350px;">
                        <img src="${company.logo}" class="rounded mb-4 h-75 w-100">
                        <div class="d-flex justify-content-between m-1">
                            <h4><b>${post.title}</b></h4>
                        </div>
                        <div class="d-flex justify-content-between m-1 mb-3">
                            <div>
                                <h6>${company.name}</h6>
                                <div><b id="dDay"></b></div>
                                <div class="mt-1">
                                    <h6 style="color: gray;">${company.address}</h6>
                                </div>
                            </div>
                            <div class="mt-5">
                                <button type="button" class="btn btn-sm">
                                    <i class="fa-regular fa-thumbs-up fa-2xl"></i>
                                </button>
                            </div>
                        </div>

                        <!-- 지원하기 버튼 -->
                        <button type="button" class="btn btn-dark" style="height: 50px;" data-bs-toggle="modal"
                            data-bs-target="#myModal" id="myBtn">지원하기</button>
                        <!-- 지원하기 모달 -->
                        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title" id="exampleModalLabel">나의 이력서</h4>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/person/detail/{id}/resume">
                                            <c:forEach items="${resume}" var="res">
                                            <div class="border border-tertiary p-3 mb-1">
                                                <label for="html">${res.title}</label>
                                                <input type="radio" id="html" name="fav_language" value="HTML">
                                            </div>
                                            </c:forEach>
                                            
                                            <hr>
                                            <div class="d-flex justify-content-center">
                                                <button type="button" class="btn btn-secondary"
                                                    onclick="location.href='../saveResumeForm'">새로 작성</button>
                                                <button type="submit" class="btn btn-primary"
                                                    onclick="return confirmAndRedirect()">제출하기</button>
                                            </div>
                                    </div>
                                        </form>
                                </div>

                            </div>
            <c:forEach items="${resume}" var="res">
                    <div class="border border-tertiary p-3 mb-1">
                    <label for="html">${res.title}</label>
                    <input type="radio" id="html" name="fav_language" value="HTML">
                    </div>
            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                // <!-- modal창 띄우는 스크립트 -->
                const emailInputEl = document.querySelector("#exampleInputEmail1");
                const modelEl = document.querySelector("#myModal");

                modelEl.addEventListener("shown.bs.modal", function () {
                    emailInputEl.focus();
                })
                // <!-- modal창의 alert창에서 확인 누르면 이력서 제출되고 지원이력페이지로 이동 -->
                function confirmAndRedirect() {
                    if (confirm('이력서를 제출하시겠습니까?')) {
                        alert('성공적으로 지원되었습니다!')
                        window.location.href = '../history';
                        return true;
                    } else {
                        return false;
                    }
                }
                // 이력서 목록
                function goToDetailPage(id) {
                    $.ajax({
                        type: "GET",
                        url: "/person/resumeDetail/" + id,
                    }).done((res) => {
                        location.href = "/person/resumeDetail/" + id;
                    }).fail((err) => {
                    });

                }
            </script>

            <script>
                let today = new Date();
                let currDay = 24 * 60 * 60 * 1000;
                deadline = new Date($("#deadline").text());
                let dDay = Math.ceil((deadline-today)/currDay);
                if(dDay>0){
                    $("#dDay").text("D-"+dDay);
                }else if(dDay<0){
                    $("#dDay").text("마감되었습니다.");
                    $("#apply_button").remove();
                }else{

                    $("#dDay").text("D-Day");
                }
            </script>
            <%@ include file="../layout/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item shadow" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item hs_list_effect shadow">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item shadow">스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>

            <div class=" mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <div>
                        <h1>이력서 관리</h1>
                    </div>
                    <hr>

                    <div class="jh_resume mt-5">
                        <button class="jh_resume_button mb-5 rounded jm_card"
                            onclick="location.href=`/person/saveResumeForm`">➕ 새 이력서 등록</button>
                        <c:forEach items="${resumes}" var="resume">

                            <div id="resume_del" class="jh_resume_content mt-2 mb-2"
                                style="display: flex; justify-content: space-between">
                                <div class="d-flex align-items-center">
                                    <a href="/person/resumeDetail/${resume.id}"> ${resume.title} </a>
                                </div>
                                <div>
                                    <c:choose>
                                        <c:when test="${resume.publish == true}">
                                            <button type="button" class="btn btn-success">공개중</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-warning">비공개중</button>
                                        </c:otherwise>
                                    </c:choose>

                                    <button type="button" class="btn btn-dark"
                                        onclick="deleteResume(${resume.id})">삭제</button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <script>

            function deleteResume(id) {

                $.ajax({
                    type: "DELETE",
                    url: "/person/resumes/" + id,
                }).done((res) => {
                    $("#resume_del").remove();
                    alert("이력서 삭제에 성공하였습니다");
                    location.href = "/person/resumes";
                }).fail((err) => {
                    alert("이력서 삭제에 실패하였습니다");
                });
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>
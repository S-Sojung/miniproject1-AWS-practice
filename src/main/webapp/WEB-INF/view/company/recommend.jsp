<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div>
            <!--배경 이미지 넣을 시-->
        </div>
        <div class="container mt-5 w-75">
            <h4>등록한 공고와 매칭되는 구직자를 확인해보세요🌟</h4>
        </div>

        <div class="container mb-5 mt-3 w-75">
            <div class="accordion" id="accordionExample">

                <c:forEach items="${postInfoAndResumes}" var="postInfoAndResumes">

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading-${postInfoAndResumes.postId}">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse-${postInfoAndResumes.postId}" aria-expanded="true"
                                aria-controls="collapse-${postInfoAndResumes.postId}">

                                ${postInfoAndResumes.title}

                            </button>
                        </h2>
                        <div id="collapse-${postInfoAndResumes.postId}" class="accordion-collapse collapse"
                            aria-labelledby="heading-${postInfoAndResumes.postId}" data-bs-parent="#accordionExample">
                            <div class="accordion-body">

                                <div class="container mb-5 mt-3 w-100">
                                    <table class="table table-hover">
                                        <tr class=" table-dark">
                                            <th class="col-xs-2 px-5">이름</th>
                                            <th class="col-xs-5">이력서</th>
                                            <th class="col-xs-3">기술 스택</th>
                                            <th class="col-xs-1"></th>
                                        </tr>
                                        <c:forEach items="${postInfoAndResumes.resumes}" var="resume">
                                            <tr>
                                                <td class="px-5">${resume.name}</td>
                                                <td><a href="#" class="text-decoration-none">${resume.title}</a></td>
                                                <td>
                                                    <c:forEach items="${resume.skills}" var="skill">

                                                        <span class="badge text-bg-info">${skill}</span>

                                                    </c:forEach>
                                                </td>
                                                <td class="text-end px-5">
                                                    <i class="fa-regular fa-thumbs-up fa-1x"></i>
                                                </td>
                                            </tr>

                                        </c:forEach>


                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>




        <%@ include file="../layout/footer.jsp" %>
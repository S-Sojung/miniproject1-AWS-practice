<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>


        <div class="container mt-5 mb-3" style="width: 1300px;">
            <h4>등록한 공고와 매칭되는 구직자를 확인해보세요🌟</h4>
        </div>

        <div class="container mb-5 mt-3 " style="width: 1300px;">
            <div class="accordion" id="accordionExample">

                <c:forEach items="${postInfoAndResumes}" var="postInfoAndResumes">

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading-${postInfoAndResumes.postId}">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse-${postInfoAndResumes.postId}" aria-expanded="true"
                                aria-controls="collapse-${postInfoAndResumes.postId}" style="height: 80px;">

                                ${postInfoAndResumes.title}

                            </button>
                        </h2>

                        <div id="collapse-${postInfoAndResumes.postId}" class="accordion-collapse collapse"
                            aria-labelledby="heading-${postInfoAndResumes.postId}" data-bs-parent="#accordionExample">
                            <div class="accordion-body">

                                <div class="container mb-5 mt-3 w-100">

                                    <!--체크-->
                                    <table class="table table-hover">
                                        <tr class="table-dark">
                                            <th class="col-xs-2 px-5">이름</th>
                                            <th class="col-xs-5">이력서</th>
                                            <th class="col-xs-3">기술 스택</th>
                                            <th class="col-xs-1"></th>
                                        </tr>
                                        <c:forEach items="${postInfoAndResumes.resumes}" var="resume">

                                            <tr>
                                                <td class="px-5">${resume.name}</td>
                                                <td> <a href="/company/resumeDetail/${resume.id}"
                                                        style="text-decoration: none;">${resume.title}</a></td>
                                                <td>
                                                    <c:forEach items="${resume.skills}" var="skill">
                                                        <span class="badge text-bg-warning"
                                                            value="${skill})">${skill}</span>
                                                    </c:forEach>
                                                </td>
                                                <td class="text-end px-5">
                                                    <button type="button" class="btn btn-sm"
                                                        onclick="scrapOrCancle(${postInfoAndResumes.postId}, ${resume.id})">
                                                        <c:choose>
                                                            <c:when test="${resume.scrap == 0}">
                                                                <i class="fa-regular text-secondary fa-thumbs-up fa-2x"
                                                                    id="scrap-${postInfoAndResumes.postId}-${resume.id}"
                                                                    value="${resume.scrap}"></i>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <i class="fa-solid scrap_icon fa-thumbs-up fa-2x"
                                                                    id="scrap-${postInfoAndResumes.postId}-${resume.id}"
                                                                    value="${resume.scrap}"></i>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </button>
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



        <script>


            function scrapOrCancle(postId, resumeId) {
                //event.preventDefault();

                //console.log(postId);
                let scrapValue = $("#scrap-" + postId + "-" + resumeId).attr("value");
                console.log(scrapValue);
                let data = {
                    "resumeId": resumeId
                };

                if (scrapValue == 0) {
                    // insert
                    $.ajax({
                        type: "put",
                        url: "/company/scrap/" + resumeId,
                        data: JSON.stringify(data),
                        dateType: "JSON",
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8"
                        }
                    }).done((res) => {
                        $("#scrap-" + postId + "-" + resumeId).attr("value", 1);
                        $("#scrap-" + postId + "-" + resumeId).addClass("fa-solid scrap_icon");
                        $("#scrap-" + postId + "-" + resumeId).removeClass("fa-regular text-secondary");
                    }).fail((err) => {
                        alert(err.responseJSON.msg);
                    });
                } else {
                    $.ajax({
                        type: "delete",
                        url: "/company/scrap/" + resumeId

                    }).done((res) => {
                        $("#scrap-" + postId + "-" + resumeId).attr("value", 0);
                        $("#scrap-" + postId + "-" + resumeId).addClass("fa-regular text-secondary");
                        $("#scrap-" + postId + "-" + resumeId).removeClass("fa-solid scrap_icon");
                    }).fail((err) => {
                        alert(err.responseJSON.msg);
                    });
                }
            }


        </script>




        <%@ include file="../layout/footer.jsp" %>
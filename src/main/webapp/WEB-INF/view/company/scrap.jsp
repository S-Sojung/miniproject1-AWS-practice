<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item shadow">공고 관리</a>
                <a href="/company/getResume" class="list-group-item shadow">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item hs_list_effect shadow">스크랩한 유저</a>
            </div>

            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded shadow">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">스크랩한 지원자를 확인해보세요🎯</h1>
                    </div>
                    <hr>

                    <div class="container mb-5 mt-2 w-100">
                        <div class="d-flex justify-content-around w-100 align-center align-items-center bg-dark"
                            style="height: 40px;">
                            <div class="text-light"><b>이름</b></div>
                            <div class="text-light"><b>이력서</b></div>
                            <div class="text-light"><b>기술스택 </b></div>
                            <div></div>
                            <div></div>
                        </div>
                        </table>

                        <c:forEach items="${scrapList}" var="scrap">
                            <a href="/company/resumeDetail/${scrap.resumeId}" style="text-decoration: none;">
                                <div class="card rounded-0" id="card-${scrap.resumeId}">
                                   
                                        <div class="card-body d-flex justify-content-around align-center align-items-center
                                        w-100">
                                            <div>${scrap.name}</div>
                                            <div>${scrap.title}</div>
                                            <div>
                                                <c:forEach items="${scrap.skills}" var="skill">
                                                    <span class="badge text-bg-warning">${skill}</span>
                                                </c:forEach>
                                            </div>
                                            <div>
                                                <button type="button" class="btn btn-sm"
                                                    onclick="cancle(event, ${scrap.resumeId})">
                                                    <i class="fa-solid  scrap_icon fa-thumbs-up fa-2x"></i>
                                                </button>
                                            </div>
                                        </div>
                                    
                                </div>
                            </a>
                        </c:forEach>
                    </div>


                </div>
            </div>
        </div>


        <script>
            function cancle(event, resumeId) {
                event.preventDefault();
                $.ajax({
                    type: "delete",
                    url: "/company/scrap/" + resumeId
                }).done((res) => {

                    $("#card-" + resumeId).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>
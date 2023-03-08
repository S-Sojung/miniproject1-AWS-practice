<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item shadow">공고 관리</a>
                <a href="/company/getResume" class="list-group-item shadow">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item hs_list_effect shadow">스크랩한 유저</a>
            </div>

            <div class=" mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <div>
                        <h1>스크랩한 지원자를 확인해보세요🎯</h1>
                    </div>
                    <hr>

                    <div class="container mb-5 mt-5 w-100">
                        <div class="d-flex justify-content-between align-center align-items-center bg-dark px-5"
                            style="height: 40px;">
                            <div class="text-light" style="position: relative;left: 20px;"><b>이름</b></div>
                            <div class="text-light" style="position: relative;left: 30px;"><b>이력서</b></div>
                            <div class="text-light" style="position: relative;left: 130px;"><b>기술스택 </b></div>
                            <div style="width: 50px;"></div>
                            <div></div>
                        </div>
                        </table>

                        <c:forEach items="${scrapList}" var="scrap">
                            <a href="/company/resumeDetail/${scrap.resumeId}" style="text-decoration: none;">
                                <div class="card rounded-0" id="card-${scrap.resumeId}">

                                    <div class="d-flex justify-content-center px-5">
                                        <div class="card-body d-flex justify-content-between align-items-center"
                                            style="width:800px;">
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
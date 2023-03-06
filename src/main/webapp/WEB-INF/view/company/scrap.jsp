<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/company/info" class="list-group-item" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item">공고 관리</a>
                <a href="/company/getResume" class="list-group-item">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item hs_list_effect">스크랩한 유저</a>
            </div>

            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">스크랩한 지원자를 확인해보세요🎯</h1>
                    </div>
                    <hr>

                    <div class="container mb-5 mt-2 w-100">
                        <div class="d-flex justify-content-around w-100 align-center align-items-center bg-dark"
                            style="height: 40px;">
                            <div class="text-light text-bord">이름</div>
                            <div class="text-light">이력서</div>
                            <div class="text-light">기술스택 </div>
                            <div></div>
                            <div></div>
                        </div>
                        </table>

                        <c:forEach items="${scrapList}" var="scrap">
                            <div class="card rounded-0" id="card-${scrap.id}">
                                <div
                                    class="card-body d-flex justify-content-around w-100 align-center align-items-center">
                                    <div>${scrap.name}</div>
                                    <div>${scrap.title}</div>
                                    <div>
                                        <c:forEach items="${scrap.skills}" var="skill">
                                            <span class="badge text-bg-info">${skill}</span>
                                        </c:forEach>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-sm" onclick="cancle(${scrap.id})">
                                            <i class="fa-solid fa-thumbs-up fa-2x"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>
            </div>
        </div>


        <script>
            function cancle(id) {

                $.ajax({
                    type: "delete",
                    url: "/company/scrap/" + id
                }).done((res) => {

                    $("#card-" + id).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>
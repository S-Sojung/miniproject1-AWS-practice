<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item shadow">공고 관리</a>
                <a href="/company/getResume" class="list-group-item hs_list_effect shadow">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item shadow">스크랩한 유저</a>
            </div>

            <div class=" mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <h1>${resumeDetail.title}</h1>
                    <hr>
                    <div class="d-flex justify-content-center">
                        <div style="width: 85%;">
                            <div class="jh_resume_flexbox mt-3">
                                <img src="${resumeDetail.profile == null ? '/images/profile1.jpg' : resumeDetail.profile}"
                                    style="width: 188px; height: 226px;">
                                <table class="jh_resume_table">
                                    <tr>
                                        <td>이름</td>
                                        <td>${personDetail.name}</td>
                                    </tr>
                                    <tr>
                                        <td>생년월일</td>
                                        <td>${personDetail.birthday}</td>
                                    </tr>
                                    <tr>
                                        <td>연락처</td>
                                        <td>${personDetail.phone}</td>
                                    </tr>
                                    <tr>
                                        <td>주소</td>
                                        <td>${personDetail.address}</td>
                                    </tr>
                                    <tr>
                                        <td>이메일</td>
                                        <td>${personUser.email}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="mt-5">
                                <h4>포트폴리오 주소</h4>
                                <input type="url" class="form-control mt-2" id="floatingInput"
                                    placeholder="git 또는 blog 주소를 입력해주세요" style="display: block;"
                                    value="${resumeDetail.portfolio}" readOnly>
                            </div>
                            <div class="mt-5 jh_resume_skill">
                                <h4>기술스택</h4>
                                <div>
                                    <ul>
                                        <c:forEach items="${skillDetail}" var="skill">
                                            <li>${skill}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="mt-5">
                                <h4>자기소개</h4>
                                <textarea name="resume_content" class="w-100 opacity-50" rows="10" readOnly>${resumeDetail.selfIntro}
                               </textarea>
                            </div>
                            <div class="d-flex mt-4 justify-content-center">
                                <c:choose>
                                    <c:when test="${proposal!=null}">
                                        <table>
                                            <c:forEach items="${proposal}" var="pro">
                                                <tr>
                                                    <td><b>[ ${pro.title} ]</b> 에 지원한 이력서</td>
                                                    <c:choose>
                                                        <c:when test="${pro.status==0}">
                                                            <td>
                                                                <div class="px-2">
                                                                    <button type="button" class="btn btn-success"
                                                                        data-bs-toggle="modal"
                                                                        data-bs-target="#exampleModal">합격</button>
                                                                </div>
                                                                <div class="modal fade" id="exampleModal" tabindex="-1"
                                                                    aria-labelledby="exampleModalLabel"
                                                                    aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title"
                                                                                    id="exampleModalLabel">합격 메시지를 보내주세요
                                                                                </h5>
                                                                                <button type="button" class="btn-close"
                                                                                    data-bs-dismiss="modal"
                                                                                    aria-label="Close"></button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <form>
                                                                                    <div class="mb-3">
                                                                                        <label for="message-text"
                                                                                            class="col-form-label">Message:</label>
                                                                                        <textarea class="form-control "
                                                                                            id="message"></textarea>
                                                                                    </div>
                                                                                </form>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button"
                                                                                    class="btn btn-dark"
                                                                                    data-bs-dismiss="modal">취소하기</button>
                                                                                <button type="button"
                                                                                    class="btn init_color"
                                                                                    onclick="insertProposalPass(${pro.id})">메시지
                                                                                    보내기</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="px-2">
                                                                    <button type="button" class="btn btn-danger"
                                                                        onclick="updateById(${pro.id},this)">불합격</button>
                                                                </div>
                                                            </td>
                                                        </c:when>

                                                        <c:when test="${pro.status==1}">
                                                            <td style="color: rgb(1, 1, 167);"><b> &nbsp&nbsp&nbsp 합격 처리
                                                                </b>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="color: rgb(172, 1, 1);"><b> &nbsp&nbsp&nbsp 불합격
                                                                    처리</b>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="px-2">
                                            <button type="button" class="btn btn-light">제안하기</button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <script>
            function insertProposalPass(id) {
                let data = { "message": $("#message").val() };

                $.ajax({
                    type: "post",
                    url: "/company/proposalPass/" + id,
                    data: JSON.stringify(data),
                    headers: {
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    dataType: "json"
                })
                    .done(res => { //20X 일때
                        alert(res.msg);
                        updateById(id, "합격");
                        location.reload();
                    })
                    .fail(err => {
                        alert(err.responseJSON.msg);
                    });
            }

            function updateById(id, obj) {
                let status;
                if (obj != "합격") {
                    status = $(obj).text() == '합격' ? 1 : -1;
                } else {
                    status = 1;
                }
                let data = { "statusCode": status }
                console.log(data);
                $.ajax({
                    type: "put",
                    url: "/company/proposal/" + id,
                    data: JSON.stringify(data),
                    headers: {
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    dataType: "json"
                })
                    .done(res => { //20X 일때
                        alert(res.msg);
                        location.reload();
                    })
                    .fail(err => {
                        alert(err.responseJSON.msg);
                    });
            }
        </script>


        <%@ include file="../layout/footer.jsp" %>
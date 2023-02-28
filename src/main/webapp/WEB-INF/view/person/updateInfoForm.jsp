<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/person/info" class="list-group-item  hs_list_effect" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item ">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item ">스크랩</a>
                <a href="/person/history" class="list-group-item ">지원 이력</a>
            </div>



            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">개인회원 수정</h1>
                        <button type="button" class="btn btn-dark hs_update_button" onclick="personUpdate()">저장</button>
                    </div>
                    <hr />
                    <div>
                        <div class="container w-50 mt-5 text ">
                            <form>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이름</span>
                                    <input type="text" class="form-control" value="${person.name}" id="name">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                    <input type="tel" class="form-control" value="${person.phone}" id="phone">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이메일</span>
                                    <input type="email" class="form-control" value="${principal.email}" id="email"
                                        readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">주소</span>
                                    <input type="text" class="form-control" value="${person.address}" id="address">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-light justify-content-center hs_span_size">현재
                                        비밀번호</span>
                                    <input type="password" class="form-control" value="${principal.password}" readonly>
                                </div>
                                <%-- <span style="font-size:10px; color: grey;">비밀번호(영문자, 숫자, 특수문자 포함 8-20자 이내)</span>
                                    --%>
                                    <div class="input-group mb-3">
                                        <span
                                            class="input-group-text bg-light justify-content-center hs_span_size">비밀번호</span>
                                        <input type="password" class="form-control" id="changePassword">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text bg-light justify-content-center hs_span_size">비밀번호
                                            확인</span>
                                        <input type="password" class="form-control" id="checkChangePassword"
                                            onchange="checkingPassword()">
                                    </div>

                                    <div id="checkAlertBox"></div>

                                    <div class="my-5">
                                        <h4>기술스택</h4>
                                        <input type="hidden" value="${pSkills}" id="check">
                                        <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                            <c:forEach items="${skills}" var="skill">
                                                <input type="checkbox" name="skills" class="btn-check" id="${skill}"
                                                    value="${skill}" autocomplete="off">
                                                <label class="btn btn-outline-primary" for="${skill}">${skill}</label>
                                            </c:forEach>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            let checkSkill = $("#check").val().split(",");

            checkSkill.forEach(element => {
                $("input:checkbox[id='" + element + "']").prop("checked", true);
            });


            function checkingPassword() {
                let pw = $("#changePassword").val();
                let cpw = $("#checkChangePassword").val();

                if (pw == cpw) {
                    $("#checkAlertBox").empty();
                    let checkAlertBox = `<div class="alert alert-info">
                            <strong>Complete!</strong> 비밀번호 일치</div>`
                    $("#checkAlertBox").append(checkAlertBox);
                } else {
                    $("#checkAlertBox").empty();
                    let checkAlertBox = `<div class="alert alert-danger">
                     <strong>Check!</strong> 비밀번호 불일치 </div>`
                    $("#checkAlertBox").append(checkAlertBox);
                }
            }


            let skillList = [];

            function chooseSkiil() {
                skillList = [];

                $('input:checkbox[name=skills]').each(function () {
                    if ($(this).is(":checked") == true) {
                        skillList.push($(this).val());
                    }
                });
                if (skillList.length >= 6) {
                    alert("기술 스택이 5개를 초과할 수 없습니다!");
                    $('input:checkbox[name=skills]').prop("checked", false);
                    // $('input:checkbox[name=checkSkills]').val().remove();
                    return false;
                }

            }

            function personUpdate() {

                let check = chooseSkiil();
                if (check == false) {
                    return false;
                }

                let skills = skillList.join();

                let data = {
                    "name": $("#name").val(),
                    "phone": $("#phone").val(),
                    "email": $("#email").val(),
                    "address": $("#address").val(),
                    "password": $("#changePassword").val(),
                    "skills": skills
                };

                $.ajax({
                    type: "put",
                    data: JSON.stringify(data),
                    dataType: "json",
                    url: "/person/updateInfo",
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8"
                    }
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/person/info"
                }).fail((err) => {
                    console.log(err);
                    alert(err.responseJSON.msg);
                });
            }

        </script>
        <%@ include file="../layout/footer.jsp" %>
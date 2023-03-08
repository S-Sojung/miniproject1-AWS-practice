<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item  hs_list_effect shadow" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item shadow">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item shadow">스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>



            <div class=" mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <div class="d-flex justify-content-between">

                        <h1>개인회원 수정</h1>

                        <button type="button" class="btn btn-dark" style="width:80px; height: 40px;"
                            data-bs-toggle="modal" data-bs-target="#passwordCheck" data-bs-whatever="@mdo">저장</button>
                    </div>
                    <hr />
                    <div>
                        <div class="container w-50 mt-5 text ">
                            <form>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">이름</span>
                                    <input type="text" class="form-control" value="${person.name}" id="name">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">생년월일</span>
                                    <input type="date" class="form-control" id="birthday">
                                    <input type="hidden" class="form-control" value="${person.birthday}" id="pbirthday">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">연락처</span>
                                    <input type="tel" class="form-control" maxlength="11" value="${person.phone}"
                                        id="phone">
                                </div>

                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">주소</span>
                                    <input type="text" class="form-control" value="${person.address}" id="address">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">이메일</span>
                                    <input type="email" class="form-control" value="${principal.email}" id="email"
                                        readonly>
                                </div>

                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">비밀번호</span>
                                    <input type="password" class="form-control" id="changePassword">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text init_color justify-content-center hs_span_size hs_span">비밀번호
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

                                <div class="modal fade" id="passwordCheck" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 확인</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form>
                                                    <div class="mb-3">
                                                        <label for="recipient-Password"
                                                            class="col-form-label">Password</label>
                                                        <input type="password" class="form-control"
                                                            id="recipient-Password">
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-dark"
                                                    data-bs-dismiss="modal">닫기</button>
                                                <button type="button" class="btn init_color proposalBtn"
                                                    style="background-color: #a8e455;"
                                                    onclick="personUpdate()">저장</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $("#birthday").val($("#pbirthday").val().split(' ')[0]);
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
                        skillList.push($(this).val())
                    }
                });
                if (skillList.length > 6) {
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

                let password = $("#recipient-Password").val();

                // console.log(password);
                let skills = skillList.join();
                let birthday = $("#birthday").val() + " " + $("#pbirthday").val().split(' ')[1];
                let data = {
                    "name": $("#name").val(),
                    "birthday": birthday,
                    "phone": $("#phone").val(),
                    "email": $("#email").val(),
                    "address": $("#address").val(),
                    "password": $("#changePassword").val(),
                    "skills": skills,
                    "originPassword": password
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